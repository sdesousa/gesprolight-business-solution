package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmpReaPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmpReaPhaseDAOImp;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmpReaPhaseService;

public class GpEmpReaPhaseServiceImpl implements IGpEmpReaPhaseService {

	private IGpEmpReaPhaseDAO empReaPhaseDAO = new GpEmpReaPhaseDAOImp();

	@Override
	public GpEmpReaPhase create(GpEmpReaPhase empReaPhase) throws GesproBusinessException {
		return this.empReaPhaseDAO.create(empReaPhase);
	}

	@Override
	public void update(GpEmpReaPhase empReaPhase) throws GesproBusinessException {

	}

	@Override
	public List<GpEmpReaPhase> findAll() {
		return this.empReaPhaseDAO.findAll();
	}

	@Override
	public void deleteById(Integer empReaPhaseId) {
		this.empReaPhaseDAO.deleteById(empReaPhaseId);
	}

	@Override
	public GpEmpReaPhase findById(Integer empReaPhaseId) {
		return this.empReaPhaseDAO.findById(empReaPhaseId);
	}

}
