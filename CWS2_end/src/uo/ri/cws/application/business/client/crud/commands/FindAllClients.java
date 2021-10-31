package uo.ri.cws.application.business.client.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;

public class FindAllClients implements Command<List<ClientDto>> {

	private ClientGateway cg = PersistenceFactory.forClient();

	public FindAllClients() {

	}

	public List<ClientDto> execute() throws BusinessException {

		return DtoAssembler.toClientDtoList(cg.findAll());

	}
}
