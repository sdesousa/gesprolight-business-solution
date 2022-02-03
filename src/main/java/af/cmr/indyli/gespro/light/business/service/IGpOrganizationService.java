package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpOrganizationService {
	public GpOrganization create(GpOrganization org) throws GesproBusinessException;

	public void update(GpOrganization org) throws GesproBusinessException;

	public List<GpOrganization> findAll();

	public void deleteById(Integer orgId) throws GesproBusinessException;

	public GpOrganization findById(Integer orgId);

	public GpOrganization findByName(String orgName);

}
