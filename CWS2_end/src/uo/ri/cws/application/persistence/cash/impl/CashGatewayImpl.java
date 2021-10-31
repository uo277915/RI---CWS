package uo.ri.cws.application.persistence.cash.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.cash.CashGateway;
import uo.ri.cws.application.persistence.cash.CashRecord;
import uo.ri.cws.application.persistence.util.Conf;


public class CashGatewayImpl implements CashGateway {

	@Override
	public void add(CashRecord t) {

		PreparedStatement pst = null;

		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("CASH_ADD"));
			pst.setString(1, t.id);

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

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("CASH_REMOVE"));
			pst.setString(1, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(pst); // c
		}
	}

	@Override
	public void update(CashRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public Optional<CashRecord> findById(String id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CashRecord> findAll() {

		// TODO Auto-generated method stub
		return null;
	}

}
