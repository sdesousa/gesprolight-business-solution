package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpPhaseService {

	public GpPhase create(GpPhase phase) throws GesproBusinessException;

	public void update(GpPhase phase) throws GesproBusinessException;

	public List<GpPhase> findAll();

	public void deleteById(Integer phaseId);

	public GpPhase findById(Integer phaseId);
	
	public GpPhase findByProjectId(Integer projectId);
}
