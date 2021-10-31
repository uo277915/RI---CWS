package uo.ri.cws.application.business.client.crud.commands;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.recommendation.RecommendationGateway;
import uo.ri.cws.application.persistence.recommendation.RecommendationRecord;

public class FindClientRecommendedBy implements Command<List<ClientDto>> {

	private String id;
	private ClientGateway cg = PersistenceFactory.forClient();
	private RecommendationGateway rg = PersistenceFactory.forRecommendation();

	public FindClientRecommendedBy(String id) {

		this.id = id;
	}

	public List<ClientDto> execute() throws BusinessException {

		if (id == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (id.isBlank()) {
			throw new IllegalArgumentException("Tha Data cannot be blank!");
		}

		List<ClientDto> clients = new ArrayList<ClientDto>();

		List<RecommendationRecord> recommendations = rg.findBySponsorId(id);

		for (RecommendationRecord r : recommendations) {
			clients.add(DtoAssembler.toClientDto(cg.findById(r.recommended_id).get()));
		}

		return clients;
	}
}
