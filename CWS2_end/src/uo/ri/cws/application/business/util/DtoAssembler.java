package uo.ri.cws.application.business.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherDto;
import uo.ri.cws.application.business.vehicle.VehicleDto;
import uo.ri.cws.application.business.workorder.WorkOrderDto;
import uo.ri.cws.application.persistence.client.ClientRecord;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;
import uo.ri.cws.application.persistence.mechanic.MechanicRecord;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanRecord;
import uo.ri.cws.application.persistence.vehicle.VehicleRecord;
import uo.ri.cws.application.persistence.voucher.VoucherRecord;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

public class DtoAssembler {

	public static Optional<MechanicDto> toDto(Optional<MechanicRecord> arg) {

		Optional<MechanicDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toMechanicDto(arg.get()));
		return result;
	}

	public static List<MechanicDto> toDtoList(List<MechanicRecord> arg) {

		List<MechanicDto> result = new ArrayList<MechanicDto>();
		for (MechanicRecord mr : arg)
			result.add(toMechanicDto(mr));
		return result;
	}

	public static MechanicRecord toRecord(MechanicDto arg) {

		MechanicRecord result = new MechanicRecord();
		result.id = arg.id;
		result.dni = arg.dni;
		result.name = arg.name;
		result.surname = arg.surname;
		return result;
	}

	private static MechanicDto toMechanicDto(MechanicRecord arg) {

		MechanicDto result = new MechanicDto();
		result.id = arg.id;
		result.name = arg.name;
		result.surname = arg.surname;
		result.dni = arg.dni;
		return result;
	}


	public static InvoiceDto toDto(InvoiceRecord arg) {

		InvoiceDto result = new InvoiceDto();
		result.id = arg.id;
		result.number = arg.number;
		/*
		 * result.status = InvoiceStatus.valueOf(arg.status);
		 */
		result.date = arg.date;
		result.total = arg.amount;
		result.vat = arg.vat;
		return result;
	}

	public static List<InvoicingWorkOrderDto> toInvoicingWorkOrderList(List<WorkOrderRecord> arg) {

		List<InvoicingWorkOrderDto> result = new ArrayList<InvoicingWorkOrderDto>();
		for (WorkOrderRecord record : arg)
			result.add(toDto(record));
		return result;
	}


	private static InvoicingWorkOrderDto toDto(WorkOrderRecord record) {

		InvoicingWorkOrderDto dto = new InvoicingWorkOrderDto();
		dto.id = record.id;
		dto.date = record.date.toLocalDate();
		dto.description = record.description;
		dto.status = record.status;
		dto.total = record.total;

		return dto;
	}

	public static Optional<ClientDto> toOptionalClientDto(Optional<ClientRecord> arg) {

		Optional<ClientDto> result = arg.isEmpty() ? Optional.ofNullable(null)
				: Optional.ofNullable(toClientDto(arg.get()));
		return result;
	}

	public static ClientDto toClientDto(ClientRecord arg) {

		ClientDto result = new ClientDto();

		result.id = arg.id;
		result.version = arg.version;

		result.dni = arg.dni;
		result.name = arg.name;
		result.surname = arg.surname;
		result.phone = arg.phone;
		result.email = arg.email;
		result.addressCity = arg.addressCity;
		result.addressStreet = arg.addressStreet;
		result.addressZipcode = arg.addressZipcode;

		return result;
	}

	public static List<VehicleDto> toVehicleList(List<VehicleRecord> arg) {

		List<VehicleDto> result = new ArrayList<VehicleDto>();
		for (VehicleRecord record : arg)
			result.add(toDto(record));
		return result;
	}

	private static VehicleDto toDto(VehicleRecord arg) {

		VehicleDto result = new VehicleDto();

		result.id = arg.id;
		result.version = arg.version;

		result.platenumber = arg.platenumber;
		result.client_id = arg.client_id;
		result.make = arg.make;
		result.model = arg.model;
		result.vehicletype_id = arg.vehicletype_id;

		return result;


	}

	public static ClientRecord toRecord(ClientDto arg) {

		ClientRecord result = new ClientRecord();

		result.id = arg.id;
		result.version = arg.version;

		result.dni = arg.dni;
		result.name = arg.name;
		result.surname = arg.surname;
		result.phone = arg.phone;
		result.email = arg.email;
		result.addressCity = arg.addressCity;
		result.addressStreet = arg.addressStreet;
		result.addressZipcode = arg.addressZipcode;

		return result;
	}

	public static List<ClientDto> toClientDtoList(List<ClientRecord> arg) {

		List<ClientDto> result = new ArrayList<ClientDto>();
		for (ClientRecord mr : arg)
			result.add(toClientDto(mr));
		return result;
	}

	public static VoucherRecord toRecord(VoucherDto vd) {

		VoucherRecord vr = new VoucherRecord();

		vr.id = vd.id;
		vr.version = vd.version;

		vr.clientId = vd.clientId;
		vr.accumulated = vd.accumulated;
		vr.description = vd.description;
		vr.available = vd.accumulated;
		vr.balance = vd.balance;
		vr.code = vd.code;

		return vr;
	}

	public static PaymentMeanRecord toPaymentMeanRecord(VoucherDto vd, String type) {

		PaymentMeanRecord vr = new PaymentMeanRecord();

		vr.id = vd.id;
		vr.version = vd.version;

		vr.accumulated = vd.accumulated;
		vr.client_id = vd.clientId;
		vr.dtype = type;

		return vr;
	}

	public static List<WorkOrderDto> toWorkOrderDtoList(List<WorkOrderRecord> arg) {

		List<WorkOrderDto> result = new ArrayList<WorkOrderDto>();
		for (WorkOrderRecord mr : arg)
			result.add(toWorkOrderDto(mr));
		return result;
	}

	public static WorkOrderDto toWorkOrderDto(WorkOrderRecord wr) {

		WorkOrderDto wd = new WorkOrderDto();

		wd.id = wr.id;
		wd.version = wr.version;

		wd.vehicleId = wr.vehicleId;
		wd.description = wr.description;
		wd.date = wr.date;
		wd.total = wr.total;
		wd.status = wr.status;
		wd.usedForVoucher = wr.usedForVoucher;

		wd.mechanicId = wr.mechanicId;
		wd.invoiceId = wr.invoiceId;

		return wd;
	}

	public static List<InvoiceDto> toInvoiceDtoList(List<InvoiceRecord> arg) {

		List<InvoiceDto> result = new ArrayList<InvoiceDto>();
		for (InvoiceRecord mr : arg)
			result.add(toInvoiceDto(mr));
		return result;
	}

	public static InvoiceDto toInvoiceDto(InvoiceRecord wr) {

		InvoiceDto id = new InvoiceDto();

		id.id = wr.id;
		id.version = wr.version;

		id.date = wr.date;
		id.total = wr.amount;
		id.status = wr.status;
		id.number = wr.number;
		id.vat = wr.vat;
		id.usedForVoucher = wr.usedforvoucher;

		return id;
	}

	public static WorkOrderRecord toRecord(WorkOrderDto wr) {

		WorkOrderRecord wd = new WorkOrderRecord();

		wd.id = wr.id;
		wd.version = wr.version;

		wd.vehicleId = wr.vehicleId;
		wd.description = wr.description;
		wd.date = wr.date;
		wd.total = wr.total;
		wd.status = wr.status;
		wd.usedForVoucher = wr.usedForVoucher;

		wd.mechanicId = wr.mechanicId;
		wd.invoiceId = wr.invoiceId;

		return wd;
	}
}
