package uo.ri.cws.application.business.invoice.create.commands;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.invoice.InvoiceDto.InvoiceStatus;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

public class CreateInvoice implements Command<InvoiceDto> {

	private Connection connection;
	private List<String> workOrderIds;

	private InvoiceGateway ig = PersistenceFactory.forInvoice();
	private WorkOrderGateway wg = PersistenceFactory.forWorkOrder();

	public CreateInvoice(List<String> workOrderIds) {

		this.workOrderIds = workOrderIds;
	}

	public InvoiceDto execute() throws BusinessException {

		InvoiceDto dto = new InvoiceDto();

		try {
			connection = Jdbc.getConnection();

			if (!checkWorkOrdersExist(workOrderIds))
				throw new BusinessException("Workorder does not exist");
			if (!checkWorkOrdersFinished(workOrderIds))
				throw new BusinessException("Workorder is not finished yet");

			long numberInvoice = generateInvoiceNumber();
			LocalDate dateInvoice = LocalDate.now();
			double amount = calculateTotalInvoice(workOrderIds); // vat not included
			double vat = vatPercentage(amount, dateInvoice);
			double total = amount * (1 + vat / 100); // vat included
			total = Round.twoCents(total);

			String idInvoice = createInvoice(numberInvoice, dateInvoice, vat, total);
			linkWorkordersToInvoice(idInvoice, workOrderIds);
			markWorkOrderAsInvoiced(workOrderIds);

			dto.id = idInvoice;
			dto.number = numberInvoice;
			dto.date = dateInvoice;
			dto.vat = vat;
			dto.total = total;
			dto.status = InvoiceStatus.NOT_YET_PAID;

			connection.commit();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(connection);
		}

		return dto;

	}

	/*
	 * checks whether every work order exist
	 */
	private boolean checkWorkOrdersExist(List<String> workOrderIDS) throws SQLException, BusinessException {

		for (String id : workOrderIDS) {

			Optional<WorkOrderRecord> mechanic = wg.findById(id);
			if (mechanic.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/*
	 * checks whether every work order id is FINISHED
	 */
	private boolean checkWorkOrdersFinished(List<String> workOrderIDS) throws SQLException, BusinessException {

		for (String workOrderID : workOrderIDS) {

			if (wg.findById(workOrderID).isEmpty()) {

				throw new BusinessException("WorkOrder does not exist!");
			}

			String status = wg.findById(workOrderID).get().status;

			if (!"FINISHED".equalsIgnoreCase(status)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Generates next invoice number (not to be confused with the inner id)
	 */
	private Long generateInvoiceNumber() throws SQLException {

		Optional<Long> last = ig.findLastInvoiceNumber();

		if (last.isEmpty()) {
			return 1L;
		} else {
			Long value = last.get();
			return value + 1;
		}
	}

	/*
	 * Compute total amount of the invoice (as the total of individual work orders'
	 * amount
	 */
	private double calculateTotalInvoice(List<String> workOrderIDS) throws BusinessException, SQLException {

		double totalInvoice = 0.0;
		for (String workOrderID : workOrderIDS) {
			totalInvoice += getWorkOrderTotal(workOrderID);
		}
		return totalInvoice;
	}

	/*
	 * checks whether every work order id is FINISHED
	 */
	private Double getWorkOrderTotal(String workOrderID) throws SQLException, BusinessException {

		Optional<WorkOrderRecord> workOrder = wg.findById(workOrderID);

		if (workOrder.isEmpty()) {
			throw new BusinessException("WorkOrder does not exist!");
		}

		Double money = wg.findById(workOrderID).get().total;
		return money;

	}

	/*
	 * returns vat percentage
	 */
	private double vatPercentage(double totalInvoice, LocalDate dateInvoice) {

		return LocalDate.parse("2012-07-01").isBefore(dateInvoice) ? 21.0 : 18.0;

	}

	/*
	 * Creates the invoice in the database; returns the id
	 */
	private String createInvoice(long numberInvoice, LocalDate dateInvoice, double vat, double total)
			throws SQLException {

		String idInvoice = UUID.randomUUID().toString();

		InvoiceRecord ir = new InvoiceRecord();

		ir.id = idInvoice;
		ir.number = numberInvoice;
		ir.date = dateInvoice;
		ir.vat = vat;
		ir.amount = total;
		ir.status = "NOT_YET_PAID";

		ig.add(ir);

		return idInvoice;
	}

	/*
	 * Set the invoice number field in work order table to the invoice number
	 * generated
	 */
	private void linkWorkordersToInvoice(String invoiceId, List<String> workOrderIDS) throws SQLException {

		for (String breakdownId : workOrderIDS) {
			wg.linkWorkorder(invoiceId, breakdownId);
		}

	}

	/*
	 * Sets status to INVOICED for every workorder
	 */
	private void markWorkOrderAsInvoiced(List<String> ids) throws SQLException {

		for (String id : ids) {
			wg.markAsInvoiced(id);
		}
	}

}
