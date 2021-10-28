package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindMechanicByDni implements Command<Optional<MechanicDto>> {

	private String dni;
	private MechanicGateway mg = PersistenceFactory.forMechanic();

	public FindMechanicByDni(String dni) {

		this.dni = dni;
	}

	public Optional<MechanicDto> execute() throws BusinessException {

		if (dni == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (dni.isBlank()) {
			throw new IllegalArgumentException("Tha Data cannot be blank!");
		}

		return DtoAssembler.toDto(mg.findByDni(dni));
	}
}
