package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpBillService {

	public GpBill create(GpBill bill) throws GesproBusinessException;

	public void update(GpBill bill) throws GesproBusinessException;

	public List<GpBill> findAll();

	public void deleteById(Integer billId);

	public GpBill findById(Integer billId);

}
