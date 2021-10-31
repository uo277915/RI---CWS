package uo.ri.cws.application.persistence.workorder;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface WorkOrderGateway extends Gateway<WorkOrderRecord> {

	List<WorkOrderRecord> findNotInvoicedForVehicle(String vehicleID);

	void linkWorkorder(String invoiceId, String workOrderId);

	void markAsInvoiced(String id);

	Optional<WorkOrderRecord> findByMechanicId(String id);

	List<WorkOrderRecord> findInvoicedForVehicle(String id);

	Optional<WorkOrderRecord> findByInvoiceId(String id);

	void markAsUsed(WorkOrderRecord record);

}
