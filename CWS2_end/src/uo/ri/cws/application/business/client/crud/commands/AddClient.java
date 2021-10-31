package uo.ri.cws.application.business.client.crud.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.cash.CashGateway;
import uo.ri.cws.application.persistence.cash.CashRecord;
import uo.ri.cws.application.persistence.charge.ChargeGateway;
import uo.ri.cws.application.persistence.charge.ChargeRecord;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanGateway;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanRecord;
import uo.ri.cws.application.persistence.recommendation.RecommendationGateway;
import uo.ri.cws.application.persistence.recommendation.RecommendationRecord;

public class AddClient implements Command<ClientDto> {

	private ClientDto client;
	private String recommenderId;

	private ClientGateway cg = PersistenceFactory.forClient();
	private InvoiceGateway ig = PersistenceFactory.forInvoice();
	private PaymentMeanGateway pg = PersistenceFactory.forPaymentMean();
	private CashGateway pcg = PersistenceFactory.forCash();
	private RecommendationGateway rg = PersistenceFactory.forRecommendation();
	private ChargeGateway chg = PersistenceFactory.forCharge();

	public AddClient(ClientDto arg) {

	}

	public AddClient(ClientDto client, String recommenderId) {

		this.client = client;
		this.recommenderId = recommenderId;
	}

	public ClientDto execute() throws BusinessException {

		if (client == null || client.dni == null) {
			throw new IllegalArgumentException("Tha Data cannot be null!");
		}
		if (client.dni.isBlank()) {
			throw new IllegalArgumentException("Tha DNI cannot be empty!");
		}
		if (!cg.findById(client.id).isEmpty()) {
			throw new BusinessException("This ID is already in use!");
		}
		if (!cg.findByDni(client.dni).isEmpty()) {
			throw new BusinessException("This DNI is already in use!");
		}

		client.id = UUID.randomUUID().toString();
		cg.add(DtoAssembler.toRecord(client));

		String paymentId = UUID.randomUUID().toString();

		PaymentMeanRecord pmr = new PaymentMeanRecord();
		pmr.id = paymentId;
		pmr.client_id = client.id;
		pmr.dtype = "CASH";
		pmr.version = 1;
		pmr.accumulated = 0.0;

		pg.add(pmr);

		CashRecord cr = new CashRecord();
		cr.id = paymentId;

		pcg.add(cr);

		List<InvoiceRecord> paidInvoices = ig.findAllPaid();
		List<PaymentMeanRecord> paymentMeans = pg.findByClientId(recommenderId);
		List<ChargeRecord> charges = new ArrayList<ChargeRecord>();
		boolean isEligible = false;

		for (PaymentMeanRecord paymentMeanRecord : paymentMeans) {

			charges.addAll(chg.findByPaymentMean(paymentMeanRecord.id));
		}

		for (ChargeRecord charge : charges) {
			if (!isEligible)
				isEligible = paidInvoices.contains(ig.findById(charge.invoice_id).get());
		}


		if (cg.findById(recommenderId).isPresent()) {
			if (isEligible) {

				RecommendationRecord rr = new RecommendationRecord();

				rr.sponsor_id = recommenderId;
				rr.recommended_id = client.id;
				rr.usedForVoucher = false;

				rg.add(rr);

			}
		}
		return client;
	}
}
