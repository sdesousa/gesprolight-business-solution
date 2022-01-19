package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpOrganizationService<DTO extends IEntity> {
	public DTO create(DTO empOrg) throws GesproBusinessException;

	public void update(DTO empOrg) throws GesproBusinessException;

	public List<DTO> findAll();

	public void deleteById(Integer empOrgId) throws GesproBusinessException;

	public DTO findById(Integer empOrgId);

}
