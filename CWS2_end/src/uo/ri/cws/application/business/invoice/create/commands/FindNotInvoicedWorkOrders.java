package uo.ri.cws.application.business.invoice.create.commands;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;

public class FindNotInvoicedWorkOrders implements Command<List<InvoicingWorkOrderDto>> {


	private String dni;
	private WorkOrderGateway wg = PersistenceFactory.forWorkOrder();

	/**
	 * Process:
	 * - Ask customer dni
	 * - Display all uncharged workorder
	 * (status <> 'INVOICED'). For each workorder, display
	 * id, vehicle id, date, status, amount and description
	 */


	public FindNotInvoicedWorkOrders(String dni) {

		this.dni = dni;
	}


	public List<InvoicingWorkOrderDto> execute() throws BusinessException {

		List<InvoicingWorkOrderDto> workOrders = new ArrayList<InvoicingWorkOrderDto>();

		workOrders = DtoAssembler.toInvoicingWorkOrderList(wg.findNotInvoiced(dni));

		return workOrders;
	}

}
