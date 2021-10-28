package uo.ri.cws.application.business.mechanic.crud.commands;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class DeleteMechanic implements Command<Void>{

	private String idMechanic;
	private MechanicGateway mg = PersistenceFactory.forMechanic();

	public DeleteMechanic(String arg) {

		this.idMechanic = arg;
	}

	public Void execute() throws BusinessException {
		
		//IFS
		
		mg.remove(idMechanic);
		return null;
	}
}
