package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpAddressService<DTO extends IEntity> {
	public DTO create(DTO addr) throws GesproBusinessException;

	public void update(DTO addr) throws GesproBusinessException;

	public List<DTO> findAll();

	public void deleteById(Integer addrId) throws GesproBusinessException;

	public DTO findById(Integer addrId);

}
