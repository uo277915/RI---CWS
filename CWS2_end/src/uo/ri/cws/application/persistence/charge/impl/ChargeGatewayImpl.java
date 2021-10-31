package uo.ri.cws.application.persistence.charge.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.charge.ChargeGateway;
import uo.ri.cws.application.persistence.charge.ChargeRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;


public class ChargeGatewayImpl implements ChargeGateway {

	@Override
	public void add(ChargeRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {

		// TODO Auto-generated method stub

	}

	@Override
	public void update(ChargeRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public Optional<ChargeRecord> findById(String id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChargeRecord> findAll() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChargeRecord> findByPaymentMean(String id) {

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("CHARGE_FINDBYPAYMENTMEAN"));
			pst.setString(1, id);

			rs = pst.executeQuery();

			return RecordAssembler.toChargeRecordList(rs);

		} catch (

		SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
