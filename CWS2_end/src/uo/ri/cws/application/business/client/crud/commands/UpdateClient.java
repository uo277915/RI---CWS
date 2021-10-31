package uo.ri.cws.application.business.client.crud.commands;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;

public class UpdateClient implements Command<Void> {


	private ClientDto client;
	private ClientGateway cg = PersistenceFactory.forClient();

	public UpdateClient(ClientDto arg) {

		this.client = arg;
	}

	public Void execute() throws BusinessException {


		if (client == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (client.id == null || client.name == null || client.surname == null || client.dni == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (client.id.isBlank() || client.name.isBlank() || client.surname.isBlank() || client.dni.isBlank()) {
			throw new IllegalArgumentException("Tha Data cannot be blank!");
		}
		if (cg.findById(client.id).isEmpty()) {
			throw new BusinessException("There is no client with id = " + client.id + "!");
		}

		cg.update(DtoAssembler.toRecord(client));

		return null;
	}
}
