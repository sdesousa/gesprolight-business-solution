package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;

public class GpPhaseServiceImpl implements IGpPhaseService {

	private IGpPhaseDAO phaseDAO = new GpPhaseDAOImpl();

	@Override
	public GpPhase create(GpPhase phase) throws GesproBusinessException {
		return this.phaseDAO.create(phase);
	}

	@Override
	public void update(GpPhase phase) throws GesproBusinessException {
	}

	@Override
	public List<GpPhase> findAll() {
		return this.phaseDAO.findAll();
	}

	@Override
	public void deleteById(Integer phaseId) {
		this.phaseDAO.deleteById(phaseId);
	}

	@Override
	public GpPhase findById(Integer phaseId) {
		return this.phaseDAO.findById(phaseId);
	}

	@Override
	public GpPhase findByProjectId(Integer projecId) {
		return this.phaseDAO.findByProjectId(projecId);
	}

}
