package uo.ri.cws.application.persistence.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.charge.ChargeRecord;
import uo.ri.cws.application.persistence.client.ClientRecord;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;
import uo.ri.cws.application.persistence.mechanic.MechanicRecord;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanRecord;
import uo.ri.cws.application.persistence.recommendation.RecommendationRecord;
import uo.ri.cws.application.persistence.vehicle.VehicleRecord;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;


public class RecordAssembler {

	public static Optional<MechanicRecord> toMechanicRecord(ResultSet m) throws SQLException {

		if (m.next()) {
			return Optional.of(resultSetToMechanicRecord(m));
		} else
			return Optional.ofNullable(null);
	}


	public static List<MechanicRecord> toMechanicRecordList(ResultSet rs) throws SQLException {

		List<MechanicRecord> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToMechanicRecord(rs));
		}

		return res;
	}

	private static MechanicRecord resultSetToMechanicRecord(ResultSet rs) throws SQLException {

		MechanicRecord value = new MechanicRecord();
		value.id = rs.getString("id");

		value.dni = rs.getString("dni");
		value.name = rs.getString("name");
		value.surname = rs.getString("surname");
		return value;
	}

	public static Optional<WorkOrderRecord> toWorkOrderRecord(ResultSet rs) throws SQLException {

		WorkOrderRecord record = null;

		if (rs.next()) {
			record = resultSetToWorkOrderRecord(rs);
		}
		return Optional.ofNullable(record);

	}

	public static List<WorkOrderRecord> toWorkOrderRecordList(ResultSet rs) throws SQLException {

		List<WorkOrderRecord> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToWorkOrderRecord(rs));
		}

		return res;
	}


	private static WorkOrderRecord resultSetToWorkOrderRecord(ResultSet rs) throws SQLException {

		WorkOrderRecord record = new WorkOrderRecord();

		record.id = rs.getString("id");
		record.version = rs.getLong("version");

		record.vehicleId = rs.getString("vehicle_Id");
		record.description = rs.getString("description");
		record.date = rs.getTimestamp("date").toLocalDateTime();
		record.total = rs.getDouble("amount");
		record.status = rs.getString("status");
		record.mechanicId = rs.getString("mechanic_Id");
		record.invoiceId = rs.getString("invoice_Id");

		return record;
	}


	public static Optional<ClientRecord> toClientRecord(ResultSet rs) throws SQLException {

		ClientRecord record = null;

		if (rs.next()) {
			record = resultSetToClientRecord(rs);
		}
		return Optional.ofNullable(record);

	}


	private static ClientRecord resultSetToClientRecord(ResultSet rs) throws SQLException {


		ClientRecord record = new ClientRecord();

		record.id = rs.getString("id");
		record.version = rs.getLong("version");

		record.dni = rs.getString("dni");
		record.name = rs.getString("name");
		record.surname = rs.getString("surname");
		record.phone = rs.getString("phone");
		record.email = rs.getString("email");

		record.addressStreet = rs.getString("street");
		record.addressCity = rs.getString("city");
		record.addressZipcode = rs.getString("zipcode");

		return record;
	}


	public static List<VehicleRecord> toVehicleRecordList(ResultSet rs) throws SQLException {

		List<VehicleRecord> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToVehicleRecord(rs));
		}

		return res;
	}


	private static VehicleRecord resultSetToVehicleRecord(ResultSet rs) throws SQLException {

		VehicleRecord record = new VehicleRecord();

		record.id = rs.getString("id");
		record.version = rs.getLong("version");

		record.platenumber = rs.getString("platenumber");
		record.client_id = rs.getString("client_id");
		record.make = rs.getString("make");
		record.model = rs.getString("model");
		record.vehicletype_id = rs.getString("vehicletype_id");

		return record;
	}

	public static List<ClientRecord> toClientRecordList(ResultSet rs) throws SQLException {

		List<ClientRecord> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToClientRecord(rs));
		}

		return res;
	}


	public static Optional<RecommendationRecord> toRecommendationRecord(ResultSet rs) throws SQLException {

		RecommendationRecord record = null;

		if (rs.next()) {
			record = resultSetToRecommendationRecord(rs);
		}
		return Optional.ofNullable(record);
	}


	private static RecommendationRecord resultSetToRecommendationRecord(ResultSet rs) throws SQLException {

		RecommendationRecord rr = new RecommendationRecord();

		rr.id = rs.getString("id");
		rr.version = rs.getString("version");

		rr.usedForVoucher = rs.getBoolean("usedForVoucher");
		rr.sponsor_id = rs.getString("sponsor_id");
		rr.recommended_id = rs.getString("recommended_id");

		return rr;
	}


	public static List<RecommendationRecord> toRecommendationRecordList(ResultSet rs) throws SQLException {

		List<RecommendationRecord> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToRecommendationRecord(rs));
		}

		return res;
	}


	public static List<PaymentMeanRecord> toPaymentMeanRecordList(ResultSet rs) throws SQLException {

		List<PaymentMeanRecord> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToPaymentMeanRecord(rs));
		}

		return res;
	}


	private static PaymentMeanRecord resultSetToPaymentMeanRecord(ResultSet rs) throws SQLException {

		PaymentMeanRecord pmr = new PaymentMeanRecord();

		pmr.id = rs.getString("id");
		pmr.version = rs.getLong("version");

		pmr.client_id = rs.getString("client_id");
		pmr.dtype = rs.getString("dtype");
		pmr.accumulated = rs.getDouble("accumulated");

		return pmr;
	}


	public static List<InvoiceRecord> toInvoiceRecordList(ResultSet rs) throws SQLException {

		List<InvoiceRecord> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToInvoiceRecord(rs));
		}

		return res;
	}


	private static InvoiceRecord resultSetToInvoiceRecord(ResultSet rs) throws SQLException {

		InvoiceRecord ir = new InvoiceRecord();

		ir.id = rs.getString("id");
		ir.version = rs.getLong("version");

		ir.amount = rs.getDouble("amount");
		ir.date = rs.getDate("date").toLocalDate();
		ir.number = rs.getLong("number");
		ir.status = rs.getString("status");
		ir.usedforvoucher = rs.getBoolean("usedforvoucher");
		ir.vat = rs.getDouble("vat");

		return ir;
	}


	public static List<ChargeRecord> toChargeRecordList(ResultSet rs) throws SQLException {

		List<ChargeRecord> res = new ArrayList<>();
		while (rs.next()) {
			res.add(resultSetToChargeRecord(rs));
		}

		return res;
	}


	private static ChargeRecord resultSetToChargeRecord(ResultSet rs) throws SQLException {

		ChargeRecord chr = new ChargeRecord();

		chr.id = rs.getString("id");
		chr.version = rs.getLong("version");

		chr.amount = rs.getDouble("amount");
		chr.invoice_id = rs.getString("invoice_id");
		chr.paymentMean_id = rs.getString("paymentMean_id");

		return chr;
	}


}
