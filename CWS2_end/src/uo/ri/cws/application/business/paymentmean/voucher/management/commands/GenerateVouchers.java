package uo.ri.cws.application.business.paymentmean.voucher.management.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicle.VehicleDto;
import uo.ri.cws.application.business.workorder.WorkOrderDto;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanGateway;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.voucher.VoucherGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;

public class GenerateVouchers implements Command<Integer> {

	private static final Double AMOUNT_VOUCHER20 = 20.;
	private static final String DESCRIPTION_VOUCHER20 = "By three workorders";

	VoucherGateway pvg = PersistenceFactory.forVoucher();
	PaymentMeanGateway pmg = PersistenceFactory.forPaymentMean();
	WorkOrderGateway wg = PersistenceFactory.forWorkOrder();
	ClientGateway cg = PersistenceFactory.forClient();
	VehicleGateway vg = PersistenceFactory.forVehicle();
	InvoiceGateway ig = PersistenceFactory.forInvoice();

	public GenerateVouchers() {

	}

	public Integer execute() throws BusinessException {

		Integer vouchersCreated = 0;

		List<ClientDto> clients = DtoAssembler.toClientDtoList(cg.findAll());

		for (ClientDto clientDto : clients) {
			List<VehicleDto> vehicles = DtoAssembler.toVehicleList(vg.findByClientId(clientDto.id));
			List<WorkOrderDto> workOrders = new ArrayList<WorkOrderDto>();
			List<InvoiceDto> invoices = DtoAssembler.toInvoiceDtoList(ig.findAllPaid());

			int paidWorkOrders = 0;

			for (VehicleDto vehicleDto : vehicles) {
				workOrders.addAll(DtoAssembler.toWorkOrderDtoList(wg.findInvoicedForVehicle(vehicleDto.id)));
			}

			for (InvoiceDto invoice : invoices) {
				for (WorkOrderDto workOrderDto : workOrders) {
					if (workOrderDto.invoiceId.equals(invoice.id)) {
						paidWorkOrders++;
					}
				}
			}
			for (int i = paidWorkOrders; i >= 3; i -= 3) {

				createVoucher(clientDto.id, workOrders);
				vouchersCreated++;
			}
		}

		return vouchersCreated;
	}

	private void createVoucher(String clientId, List<WorkOrderDto> workOrders) {

		VoucherDto vd = new VoucherDto();

		vd.clientId = clientId;
		vd.id = UUID.randomUUID().toString();
		vd.description = DESCRIPTION_VOUCHER20;
		vd.balance = AMOUNT_VOUCHER20;
		vd.accumulated = 0.;
		vd.code = UUID.randomUUID().toString();
		vd.version = 1L;

		pmg.add(DtoAssembler.toPaymentMeanRecord(vd, "VOUCHER"));
		pvg.add(DtoAssembler.toRecord(vd));

		for (int i = 3; i > 0; i--) {

			WorkOrderDto workOrder = workOrders.remove(workOrders.size() - 1);
			wg.markAsUsed(DtoAssembler.toRecord(workOrder));
		}
	}
}
