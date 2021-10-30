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

		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {

		// TODO Auto-generated method stub

	}

	@Override
	public void update(ClientRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public Optional<ClientRecord> findById(String id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientRecord> findAll() {

		// TODO Auto-generated method stub
		return null;
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

}
