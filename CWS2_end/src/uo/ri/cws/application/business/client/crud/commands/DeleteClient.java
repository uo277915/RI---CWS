package uo.ri.cws.application.business.client.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.cash.CashGateway;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.creditcard.CreditCardGateway;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanGateway;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanRecord;
import uo.ri.cws.application.persistence.recommendation.RecommendationGateway;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.voucher.VoucherGateway;

public class DeleteClient implements Command<Void> {

	private String idClient;
	private ClientGateway cg = PersistenceFactory.forClient();
	private VehicleGateway vg = PersistenceFactory.forVehicle();
	private RecommendationGateway rg = PersistenceFactory.forRecommendation();
	private PaymentMeanGateway pmg = PersistenceFactory.forPaymentMean();

	private CashGateway pcg = PersistenceFactory.forCash();
	private CreditCardGateway pccg = PersistenceFactory.forCreditCard();
	private VoucherGateway pvg = PersistenceFactory.forVoucher();

	public DeleteClient(String arg) {

		this.idClient = arg;
	}

	public Void execute() throws BusinessException {

		if (idClient == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (idClient.isBlank()) {
			throw new IllegalArgumentException("Tha Data cannot be blank!");
		}
		if (cg.findById(idClient).isEmpty()) {
			throw new BusinessException("There is no client with id = " + idClient + "!");
		}
		if (vg.findByClientId(idClient).size() > 0) {
			throw new BusinessException("You cannot delete a client with vehicles asigned!");
		}

		List<PaymentMeanRecord> paymentMeans = pmg.findByClientId(idClient);

		for (PaymentMeanRecord pmr : paymentMeans) {
			if (pmr.dtype.toUpperCase().equals("CASH")) {
				pcg.remove(pmr.id);
			} else if (pmr.dtype.toUpperCase().equals("CREDITCARD")) {

				pccg.remove(pmr.id);

			} else if (pmr.dtype.toUpperCase().equals("VOUCHER")) {

				pvg.remove(pmr.id);
			}

		}

		pmg.remove(idClient);

		rg.remove(idClient);

		cg.remove(idClient);

		return null;
	}
}
