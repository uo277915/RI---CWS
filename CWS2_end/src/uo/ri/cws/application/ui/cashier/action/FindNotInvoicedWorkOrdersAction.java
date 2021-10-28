package uo.ri.cws.application.ui.cashier.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
import uo.ri.cws.application.ui.util.Printer;

public class FindNotInvoicedWorkOrdersAction implements Action {


	/**
	 * Process:
	 * - Ask customer dni
	 * - Display all uncharged workorder
	 * (status <> 'INVOICED'). For each workorder, display
	 * id, vehicle id, date, status, amount and description
	 */

	@Override
	public void execute() throws BusinessException {

		String dni = Console.readString("Client DNI ");

		Console.println("\nClient's not invoiced work orders\n");

		List<InvoicingWorkOrderDto> list = BusinessFactory.forInvoicingService()
				.findNotInvoicedWorkOrdersByClientDni(dni);

		Printer.printInvoicingWorkOrders(list);

	}

}
