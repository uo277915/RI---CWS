package uo.ri.cws.application.business.invoice.create.commands;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.business.vehicle.VehicleDto;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;

public class FindNotInvoicedWorkOrders implements Command<List<InvoicingWorkOrderDto>> {


	private String dni;
	private WorkOrderGateway wg = PersistenceFactory.forWorkOrder();
	private VehicleGateway vg = PersistenceFactory.forVehicle();
	private ClientGateway cg = PersistenceFactory.forClient();

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

		if (dni == null || dni.isBlank()) {
			throw new IllegalArgumentException("Dni value is not correct");
		}
		if (cg.findByDni(dni).isEmpty()) {
			throw new BusinessException("Client does not exist");
		}

		List<InvoicingWorkOrderDto> workOrders = new ArrayList<InvoicingWorkOrderDto>();

		String clientId = DtoAssembler.toClientDto(cg.findByDni(dni).get()).id;

		List<VehicleDto> vehicles = DtoAssembler.toVehicleList(vg.findByClientId(clientId));

		for (VehicleDto vehicleDto : vehicles) {
			workOrders.addAll(DtoAssembler.toInvoicingWorkOrderList(wg.findNotInvoicedForVehicle(vehicleDto.id)));

		}

		return workOrders;
	}

}
