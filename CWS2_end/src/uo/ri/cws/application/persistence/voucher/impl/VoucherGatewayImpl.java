package uo.ri.cws.application.persistence.voucher.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.voucher.VoucherGateway;
import uo.ri.cws.application.persistence.voucher.VoucherRecord;


public class VoucherGatewayImpl implements VoucherGateway {

	@Override
	public void add(VoucherRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {

		PreparedStatement pst = null;

		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("VOUCHER_REMOVE"));
			pst.setString(1, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(pst); // c
		}

	}

	@Override
	public void update(VoucherRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public Optional<VoucherRecord> findById(String id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoucherRecord> findAll() {

		// TODO Auto-generated method stub
		return null;
	}

}
