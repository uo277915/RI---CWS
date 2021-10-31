package uo.ri.cws.application.persistence;

import uo.ri.cws.application.persistence.cash.CashGateway;
import uo.ri.cws.application.persistence.cash.impl.CashGatewayImpl;
import uo.ri.cws.application.persistence.charge.ChargeGateway;
import uo.ri.cws.application.persistence.charge.impl.ChargeGatewayImpl;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.impl.ClientGatewayImpl;
import uo.ri.cws.application.persistence.creditcard.CreditCardGateway;
import uo.ri.cws.application.persistence.creditcard.impl.CreditCardGatewayImpl;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.impl.InvoiceGatewayImpl;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.impl.MechanicGatewayImpl;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanGateway;
import uo.ri.cws.application.persistence.paymentmean.impl.PaymentMeanGatewayImpl;
import uo.ri.cws.application.persistence.recommendation.RecommendationGateway;
import uo.ri.cws.application.persistence.recommendation.impl.RecommendationGatewayImpl;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.imp.VehicleGatewayImpl;
import uo.ri.cws.application.persistence.voucher.VoucherGateway;
import uo.ri.cws.application.persistence.voucher.impl.VoucherGatewayImpl;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.impl.WorkOrderGatewayImpl;

public class PersistenceFactory {

	public static MechanicGateway forMechanic() {

		return new MechanicGatewayImpl();
	}

	public static WorkOrderGateway forWorkOrder() {

		return new WorkOrderGatewayImpl();
	}

	public static InvoiceGateway forInvoice() {

		return new InvoiceGatewayImpl();
	}

	public static ClientGateway forClient() {

		return new ClientGatewayImpl();
	}

	public static VehicleGateway forVehicle() {

		return new VehicleGatewayImpl();
	}

	public static RecommendationGateway forRecommendation() {

		return new RecommendationGatewayImpl();
	}

	public static PaymentMeanGateway forPaymentMean() {

		return new PaymentMeanGatewayImpl();
	}

	public static CreditCardGateway forCreditCard() {

		return new CreditCardGatewayImpl();
	}

	public static VoucherGateway forVoucher() {

		return new VoucherGatewayImpl();
	}

	public static CashGateway forCash() {

		return new CashGatewayImpl();
	}


	public static ChargeGateway forCharge() {

		return new ChargeGatewayImpl();
	}

	/*
	 * public static VehicleTypeGateway forVehicleType() {
	 * return new VehicleTypeGatewayImpl();
	 * }
	 * public static SubstitutionGateway forSubstitution () {
	 * return new SubstitutionGatewayImpl();
	 * }
	 * public static SparePartGateway forSparePart () {
	 * return new SparePartGatewayImpl();
	 * }
	 * public static InterventionGateway forIntervention() {
	 * return new InterventionGatewayImpl();
	 * }
	 */

}
