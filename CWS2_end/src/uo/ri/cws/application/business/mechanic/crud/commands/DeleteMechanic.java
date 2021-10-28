package uo.ri.cws.application.business.mechanic.crud.commands;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class DeleteMechanic implements Command<Void> {

	private String idMechanic;
	private MechanicGateway mg = PersistenceFactory.forMechanic();

	public DeleteMechanic(String arg) {

		this.idMechanic = arg;
	}

	public Void execute() throws BusinessException {

		if (idMechanic == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (idMechanic.isBlank()) {
			throw new IllegalArgumentException("Tha Data cannot be blank!");
		}
		if (mg.findById(idMechanic).isEmpty()) {
			throw new BusinessException("There is no mechanic with id = " + idMechanic + "!");
		}
		if (hasWorkorders(idMechanic)) {
			throw new BusinessException("The mechanic with id = " + idMechanic + " has WorkOrders assigned!");
		}

		mg.remove(idMechanic);

		return null;
	}

	private boolean hasWorkorders(String id) {

		return !PersistenceFactory.forWorkOrder().findByMechanicId(id).isEmpty();
	}
}
