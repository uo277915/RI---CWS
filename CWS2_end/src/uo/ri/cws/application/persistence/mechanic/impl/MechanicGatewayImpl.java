package uo.ri.cws.application.persistence.mechanic.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicRecord;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class MechanicGatewayImpl implements MechanicGateway {

	private static String TMechanicINSERT = "insert into TMechanics(id, dni, name, surname) values (?, ?, ?, ?)";
	private static String TMechanicDELETE= "delete from TMechanics where id = ?";
	private static String TMechanicFINDBYDNI = "select * from TMechanics where dni = ?";
	private static String TMechanicFINDBYID = "select * from TMechanics where id = ?";

	@Override
	public void add(MechanicRecord t) {

		PreparedStatement pst = null;

		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(TMechanicINSERT);
			pst.setString(1, t.id);
			pst.setString(2, t.dni);
			pst.setString(3, t.name);
			pst.setString(4, t.surname);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
			
		} finally {
			Jdbc.close(pst); // c
		}

	}

	@Override
	public void remove(String id) {

		PreparedStatement pst = null;

		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(TMechanicDELETE);
			pst.setString(1, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
			
		} finally {
			Jdbc.close(pst); // c
		}

	}

	@Override
	public void update(MechanicRecord t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<MechanicRecord> findById(String id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Optional<MechanicRecord> mr;

		try {

			ps = Jdbc.getCurrentConnection().prepareStatement(TMechanicFINDBYID);
			ps.setString(1, id);
			rs = ps.executeQuery();

			mr = RecordAssembler.toMechanicRecord(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
			
		} finally {
			Jdbc.close(rs, ps); // c
		}
		return mr;
	}

	@Override
	public List<MechanicRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<MechanicRecord> findByDni(String dni) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Optional<MechanicRecord> mr;

		try {

			ps = Jdbc.getCurrentConnection().prepareStatement(TMechanicFINDBYDNI);
			ps.setString(1, dni);
			rs = ps.executeQuery();

			mr = RecordAssembler.toMechanicRecord(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
			
		} finally {
			Jdbc.close(rs, ps); // c
		}
		return mr;
	}

}
