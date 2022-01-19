package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpDeliverableDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpDeliverableDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpDeliverableService;

public class GpDeliverableServiceImpl implements IGpDeliverableService {

	private IGpDeliverableDAO delDAO = new GpDeliverableDAOImpl();

	@Override
	public GpDeliverable create(GpDeliverable del) throws GesproBusinessException {
		return this.delDAO.create(del);
	}

	@Override
	public void update(GpDeliverable del) throws GesproBusinessException {

	}

	@Override
	public List<GpDeliverable> findAll() {
		return this.delDAO.findAll();
	}

	@Override
	public void deleteById(Integer delId) {
		this.delDAO.deleteById(delId);
	}

	@Override
	public GpDeliverable findById(Integer delId) {
		return this.delDAO.findById(delId);
	}

}
