package uo.ri.cws.application.persistence.workorder.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

public class WorkOrderGatewayImpl implements WorkOrderGateway {

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

		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<WorkOrderRecord> wr;
		try {

			pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("WORKORDER_FINDBYID"));

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
	public List<WorkOrderRecord> findNotInvoicedForVehicle(String vehicleID) {

		PreparedStatement pst = null;
		ResultSet rs = null;

		List<WorkOrderRecord> workOrders = new ArrayList<WorkOrderRecord>();

		try {

			pst = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("WORKORDER_FINDNOTINVOICEDFORVEHICLE"));

			pst.setString(1, vehicleID);

			rs = pst.executeQuery();

			workOrders = RecordAssembler.toWorkOrderRecordList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return workOrders;
	}

	@Override
	public void linkWorkorder(String invoiceId, String workOrderId) {

		PreparedStatement pst = null;
		try {
			pst = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("WORKORDER_LINKWORKORDER"));

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
			pst = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("WORKORDER_MARKASINVOICED"));

			pst.setString(1, id);

			pst.executeUpdate();

		} catch (SQLException e) {

			throw new PersistenceException(e);
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public Optional<WorkOrderRecord> findByMechanicId(String id) {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<WorkOrderRecord> wr;
		try {

			pst = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("WORKORDER_FINDBYMECHANICID"));

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
	public List<WorkOrderRecord> findInvoicedForVehicle(String id) {

		PreparedStatement pst = null;
		ResultSet rs = null;

		List<WorkOrderRecord> workOrders = new ArrayList<WorkOrderRecord>();

		try {

			pst = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("WORKORDER_FINDINVOICEDFORVEHICLE"));

			pst.setString(1, id);

			rs = pst.executeQuery();

			workOrders = RecordAssembler.toWorkOrderRecordList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return workOrders;
	}

	@Override
	public Optional<WorkOrderRecord> findByInvoiceId(String id) {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<WorkOrderRecord> wr;
		try {

			pst = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("WORKORDER_FINDBYINVOICEID"));

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
	public void markAsUsed(WorkOrderRecord record) {

		PreparedStatement pst = null;
		try {
			pst = Jdbc.getCurrentConnection()
					.prepareStatement(Conf.getInstance().getProperty("WORKORDER_MARKASUSED"));

			pst.setString(1, record.id);

			pst.executeUpdate();

		} catch (SQLException e) {

			throw new PersistenceException(e);
		} finally {
			Jdbc.close(pst);
		}

	}

}
