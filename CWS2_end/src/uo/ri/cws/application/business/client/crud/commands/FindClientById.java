package uo.ri.cws.application.business.client.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;

public class FindClientById implements Command<Optional<ClientDto>> {

	private String id;
	private ClientGateway cg = PersistenceFactory.forClient();

	public FindClientById(String id) {

		this.id = id;
	}

	public Optional<ClientDto> execute() throws BusinessException {

		if (id == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (id.isBlank()) {
			throw new IllegalArgumentException("Tha Data cannot be blank!");
		}

		return DtoAssembler.toOptionalClientDto(cg.findById(id));
	}
}
