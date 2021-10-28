package uo.ri.cws.application.persistence.workorder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.RecordAssembler;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

public class WorkOrderGatewayImpl implements WorkOrderGateway {

	private static final String SQL_LINK_WORKORDER_TO_INVOICE = "update TWorkOrders set invoice_id = ? where id = ?";

	private static final String SQL_MARK_WORKORDER_AS_INVOICED = "update TWorkOrders set status = 'INVOICED' where id = ?";

	private static final String SQL_FIND_WORKORDERS = "select * from TWorkOrders where id = ?";

	private static String SQL = "select a.id, a.description, a.date, a.status, a.amount "
			+ "from TWorkOrders as a, TVehicles as v, TClients as c " + "where a.vehicle_id = v.id "
			+ "	and v.client_id = c.id " + "	and status <> 'INVOICED'" + "	and dni like ?";

	@Override
	public void add(WorkOrderRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {

		// TODO Auto-generated method stub

	}

	@Override
	public void update(WorkOrderRecord t) {

		// TODO Auto-generated method stub

	}

	@Override
	public Optional<WorkOrderRecord> findById(String id) {

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<WorkOrderRecord> wr;
		try {
			connection = Jdbc.getConnection();

			pst = connection.prepareStatement(SQL_FIND_WORKORDERS);

			pst.setString(1, id);

			rs = pst.executeQuery();

			wr = RecordAssembler.toWorkOrderRecord(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally

		{
			Jdbc.close(rs, pst);
		}

		return wr;
	}

	@Override
	public List<WorkOrderRecord> findAll() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderRecord> findNotInvoiced(String dni) {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		List<WorkOrderRecord> workOrders = new ArrayList<WorkOrderRecord>();

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setString(1, dni);

			rs = pst.executeQuery();

			workOrders = RecordAssembler.toWorkOrderRecordList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}

		return workOrders;
	}

	@Override
	public void linkWorkorder(String invoiceId, String workOrderId) {
		PreparedStatement pst = null;
		try {
			pst = Jdbc.getCurrentConnection().prepareStatement(SQL_LINK_WORKORDER_TO_INVOICE);

			pst.setString(1, invoiceId);
			pst.setString(2, workOrderId);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);

		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void markAsInvoiced(String id) {
		PreparedStatement pst = null;
		try {
			pst = Jdbc.getCurrentConnection().prepareStatement(SQL_MARK_WORKORDER_AS_INVOICED);

			pst.setString(1, id);

			pst.executeUpdate();

		} catch (SQLException e) {

			throw new PersistenceException(e);
		} finally {
			Jdbc.close(pst);
		}

	}

}
