package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpProjectManagerService<DTO extends IEntity> {
	public DTO create(DTO empPM) throws GesproBusinessException;

	public void update(DTO empPM) throws GesproBusinessException;

	public List<DTO> findAll();

	public void deleteById(Integer empPMId) throws GesproBusinessException;

	public DTO findById(Integer empPMId);

}
