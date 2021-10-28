package uo.ri.cws.application.persistence.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.client.ClientRecord;
import uo.ri.cws.application.persistence.mechanic.MechanicRecord;
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


}
