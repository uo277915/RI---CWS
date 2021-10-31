package uo.ri.cws.application.persistence.paymentmean.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanGateway;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;


public class PaymentMeanGatewayImpl implements PaymentMeanGateway {

	@Override
	public void add(PaymentMeanRecord t) {

		PreparedStatement pst = null;

		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("PAYMENTMEAN_ADD"));
			pst.setString(1, t.id);
			pst.setString(2, t.dtype);
			pst.setDouble(3, t.accumulated);
			pst.setLong(4, t.version);
			pst.setString(5, t.client_id);

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

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("PAYMENTMEAN_REMOVE"));
			pst.setString(1, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(pst); // c
		}

	}

	@Override
	public void update(PaymentMeanRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public Optional<PaymentMeanRecord> findById(String id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMeanRecord> findAll() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMeanRecord> findByClientId(String clientId) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PaymentMeanRecord> mr;

		try {

			ps = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("PAYMENTMEAN_FINDBYCLIENTID"));
			ps.setString(1, clientId);
			rs = ps.executeQuery();

			mr = RecordAssembler.toPaymentMeanRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(rs, ps); // c
		}
		return mr;
	}

}
