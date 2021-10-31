package uo.ri.cws.application.persistence.charge;

import java.util.List;

import uo.ri.cws.application.persistence.Gateway;


public interface ChargeGateway extends Gateway<ChargeRecord> {

	List<ChargeRecord> findByPaymentMean(String id);

}
