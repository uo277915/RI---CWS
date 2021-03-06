package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindMechanicById implements Command<Optional<MechanicDto>> {

	private String id;
	private MechanicGateway mg = PersistenceFactory.forMechanic();

	public FindMechanicById(String id) {

		this.id = id;
	}

	public Optional<MechanicDto> execute() throws BusinessException {

		if (id == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (id.isBlank()) {
			throw new IllegalArgumentException("Tha Data cannot be blank!");
		}

		return DtoAssembler.toDto(mg.findById(id));
	}
}
