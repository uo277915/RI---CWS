package uo.ri.cws.application.persistence.paymentmean;

import java.util.List;

import uo.ri.cws.application.persistence.Gateway;


public interface PaymentMeanGateway extends Gateway<PaymentMeanRecord> {


	public List<PaymentMeanRecord> findByClientId(String clientId);
}
