package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class AddMechanic implements Command<MechanicDto> {

	private MechanicDto mechanic;
	private MechanicGateway mg = PersistenceFactory.forMechanic();

	public AddMechanic(MechanicDto arg) {
		this.mechanic = arg;

	}

	public MechanicDto execute() throws BusinessException {

		if (mechanic == null || mechanic.dni == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (mechanic.dni.isBlank()) {
			throw new IllegalArgumentException("Tha DNI cannot be empty!");
		}
		if (existMechanic(this.mechanic.dni)) {
			throw new BusinessException("This DNI is already in use!");
		}

		mechanic.id = UUID.randomUUID().toString();
		mg.add(DtoAssembler.toRecord(mechanic));

		return mechanic;
	}

	private boolean existMechanic(String dni) {
		if (mg.findByDni(dni).isEmpty())
			return false;
		else
			return true;
	}
}
