package uo.ri.cws.application.persistence.client.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.ClientRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class ClientGatewayImpl implements ClientGateway {


	@Override
	public void add(ClientRecord t) {

		PreparedStatement pst = null;

		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("CLIENT_ADD"));
			pst.setString(1, t.id);
			pst.setString(2, t.dni);
			pst.setString(3, t.email);
			pst.setString(4, t.name);
			pst.setString(5, t.phone);
			pst.setString(6, t.surname);
			pst.setLong(7, t.version);
			pst.setString(8, t.addressCity);
			pst.setString(9, t.addressStreet);
			pst.setString(10, t.addressZipcode);

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

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("CLIENT_REMOVE"));
			pst.setString(1, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(pst); // c
		}

	}

	@Override
	public void update(ClientRecord t) {

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("CLIENT_UPDATE"));
			pst.setString(1, t.email);
			pst.setString(2, t.name);
			pst.setString(3, t.surname);
			pst.setString(4, t.phone);
			pst.setLong(5, t.version);
			pst.setString(6, t.addressCity);
			pst.setString(7, t.addressStreet);
			pst.setString(8, t.addressZipcode);

			pst.setString(9, t.id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public Optional<ClientRecord> findById(String id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Optional<ClientRecord> mr;

		try {

			ps = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("CLIENT_FINDBYID"));
			ps.setString(1, id);
			rs = ps.executeQuery();

			mr = RecordAssembler.toClientRecord(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(rs, ps); // c
		}
		return mr;
	}

	@Override
	public List<ClientRecord> findAll() {

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("CLIENT_FINDALL"));

			rs = pst.executeQuery();

			return RecordAssembler.toClientRecordList(rs);


		} catch (

		SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public Optional<ClientRecord> findByDni(String dni) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Optional<ClientRecord> mr;

		try {

			ps = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("CLIENT_FINDBYDNI"));
			ps.setString(1, dni);
			rs = ps.executeQuery();

			mr = RecordAssembler.toClientRecord(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(rs, ps); // c
		}
		return mr;
	}

	@Override
	public List<ClientRecord> findRecommendedBy(String sponsorId) {

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("CLIENT_FINDRECOMMENDEDBY"));
			pst.setString(1, sponsorId);
			rs = pst.executeQuery();

			return RecordAssembler.toClientRecordList(rs);

		} catch (

		SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
