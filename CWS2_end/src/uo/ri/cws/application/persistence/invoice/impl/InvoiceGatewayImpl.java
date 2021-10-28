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

public class InvoiceGatewayImpl implements InvoiceGateway {

	private static final String SQL_LAST_INVOICE_NUMBER = "select max(number) from TInvoices";

	private static final String SQL_INSERT_INVOICE = "insert into TInvoices(id, number, date, vat, amount, status) "
			+ "	values(?, ?, ?, ?, ?, ?)";

	@Override
	public void add(InvoiceRecord t) {PreparedStatement pst = null;

	try {

		pst = Jdbc.getCurrentConnection().prepareStatement(SQL_INSERT_INVOICE);

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Long> findLastInvoiceNumber() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Long value = null;

		try {
			pst = Jdbc.getCurrentConnection().prepareStatement(SQL_LAST_INVOICE_NUMBER);
			
			rs = pst.executeQuery();
			
			value = rs.getLong(1);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return Optional.of(value);
	}
}
