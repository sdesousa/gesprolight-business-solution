package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public interface IGpPhaseDAO {
	public GpPhase create(GpPhase phase);

	public void update(GpPhase phase);

	public List<GpPhase> findAll();

	public void deleteById(Integer phaseId);

	public GpPhase findById(Integer phaseId);
	
	public GpPhase findByProjectId(Integer projectId);
}