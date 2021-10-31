package uo.ri.cws.application.persistence.creditcard.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.creditcard.CreditCardGateway;
import uo.ri.cws.application.persistence.creditcard.CreditCardRecord;
import uo.ri.cws.application.persistence.util.Conf;


public class CreditCardGatewayImpl implements CreditCardGateway {

	@Override
	public void add(CreditCardRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {

		PreparedStatement pst = null;

		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("CREDITCARD_REMOVE"));
			pst.setString(1, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(pst); // c
		}

	}

	@Override
	public void update(CreditCardRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public Optional<CreditCardRecord> findById(String id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CreditCardRecord> findAll() {

		// TODO Auto-generated method stub
		return null;
	}


}
