package uo.ri.cws.application.persistence.workorder;

import java.time.LocalDateTime;

public class WorkOrderRecord {

	public String id;
	public long version;

	public String vehicleId;
	public String description;
	public LocalDateTime date;
	public double total;
	public String status;
	public boolean usedForVoucher;

	// might be null
	public String mechanicId;
	public String invoiceId;

}
