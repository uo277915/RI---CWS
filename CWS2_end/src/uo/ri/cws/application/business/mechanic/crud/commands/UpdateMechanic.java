package uo.ri.cws.application.business.mechanic.crud.commands;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class UpdateMechanic implements Command<Void> {

	private MechanicDto mechanic;
	private MechanicGateway mg = PersistenceFactory.forMechanic();

	public UpdateMechanic(MechanicDto arg) {

		this.mechanic = arg;
	}

	public Void execute() throws BusinessException {


		if (mechanic == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (mechanic.id == null || mechanic.name == null || mechanic.surname == null || mechanic.dni == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (mechanic.id.isBlank() || mechanic.name.isBlank() || mechanic.surname.isBlank() || mechanic.dni.isBlank()) {
			throw new IllegalArgumentException("Tha Data cannot be blank!");
		}
		if (mg.findById(mechanic.id).isEmpty()) {
			throw new BusinessException("There is no mechanic with id = " + mechanic.id + "!");
		}

		mg.update(DtoAssembler.toRecord(mechanic));

		return null;
	}
}
