package uo.ri.cws.application.persistence.workorder;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface WorkOrderGateway extends Gateway<WorkOrderRecord> {

	List<WorkOrderRecord> findNotInvoiced(String dni);

	void linkWorkorder(String invoiceId, String workOrderId);

	void markAsInvoiced(String id);

	Optional<WorkOrderRecord> findByMechanicId(String id);
}
