package uo.ri.cws.application.persistence.vehicle.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.util.RecordAssembler;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.VehicleRecord;


public class VehicleGatewayImpl implements VehicleGateway {


	private static String VEHICLE_FINDBYCLIENTDNI = "select * "
			+ " from TVehicles "
			+ " where client_id = ? ";

	@Override
	public void add(VehicleRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {

		// TODO Auto-generated method stub

	}

	@Override
	public void update(VehicleRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public Optional<VehicleRecord> findById(String id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleRecord> findAll() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleRecord> findByClientId(String clientId) {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		List<VehicleRecord> vehicles = new ArrayList<VehicleRecord>();

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(VEHICLE_FINDBYCLIENTDNI);
			pst.setString(1, clientId);

			rs = pst.executeQuery();

			vehicles = RecordAssembler.toVehicleRecordList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return vehicles;
	}


}
