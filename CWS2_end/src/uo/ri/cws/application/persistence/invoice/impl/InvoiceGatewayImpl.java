package uo.ri.cws.application.persistence.invoice.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class InvoiceGatewayImpl implements InvoiceGateway {

	@Override
	public void add(InvoiceRecord t) {

		PreparedStatement pst = null;

		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("INVOICE_ADD"));

			pst.setString(1, t.id);
			pst.setLong(2, t.number);
			pst.setDate(3, Date.valueOf(t.date));
			pst.setDouble(4, t.vat);
			pst.setDouble(5, t.amount);
			pst.setString(6, t.status);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(pst); // c
		}

	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(InvoiceRecord t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<InvoiceRecord> findById(String id) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvoiceRecord> findAll() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InvoiceRecord> findByNumber(Long number) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNextInvoiceNumber() {

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("INVOICE_GETNEXTINVOICENUMBER"));
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, next
			} else { // there is none yet
				return 1L;
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<InvoiceRecord> findAllPaid() {

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("INVOICE_FINDALLPAID"));

			rs = pst.executeQuery();

			return RecordAssembler.toInvoiceRecordList(rs);

		} catch (

		SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}
}
