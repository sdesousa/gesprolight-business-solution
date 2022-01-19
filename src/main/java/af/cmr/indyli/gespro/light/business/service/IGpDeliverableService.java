package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpDeliverableService {
	

	public GpDeliverable create(GpDeliverable del) throws GesproBusinessException;
	
	public void update(GpDeliverable del) throws GesproBusinessException;

	public List<GpDeliverable> findAll();

	public void deleteById(Integer delId);

	public GpDeliverable findById(Integer delId);

}
