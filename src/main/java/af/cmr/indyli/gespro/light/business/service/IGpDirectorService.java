package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpDirectorService<DTO extends IEntity> {
	public DTO create(DTO emp) throws GesproBusinessException;

	public void update(DTO emp) throws GesproBusinessException;

	public List<DTO> findAll();

	public void deleteById(Integer empId) throws GesproBusinessException;

	public DTO findById(Integer empId);

}
