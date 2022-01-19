package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpEmpReaPhaseService {

	public GpEmpReaPhase create(GpEmpReaPhase empReaPhase) throws GesproBusinessException;

	public void update(GpEmpReaPhase empReaPhase) throws GesproBusinessException;

	public List<GpEmpReaPhase> findAll();

	public void deleteById(Integer empReaPhaseId);

	public GpEmpReaPhase findById(Integer empReaPhaseId);

}
