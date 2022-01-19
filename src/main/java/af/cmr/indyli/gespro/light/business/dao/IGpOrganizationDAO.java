package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpOrganization;

public interface IGpOrganizationDAO {
	public GpOrganization create(GpOrganization org);

	public void update(GpOrganization org);

	public List<GpOrganization> findAll();

	public void deleteById(Integer orgId);

	public GpOrganization findById(Integer orgId);

	public GpOrganization findByName(String orgName);

}
