package uo.ri.cws.application.business.paymentmean.voucher.management;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherDto;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherService;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherSummaryDto;


public class VoucherServiceImpl implements VoucherService {

	@Override
	public int generateVouchers() throws BusinessException {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<VoucherDto> findVouchersById(String id) throws BusinessException {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoucherDto> findVouchersByClientId(String id) throws BusinessException {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoucherSummaryDto> getVoucherSummary() throws BusinessException {

		// TODO Auto-generated method stub
		return null;
	}

}
