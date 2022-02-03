package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;

public class GpOrganizationServiceImpl implements IGpOrganizationService {

	private IGpOrganizationDAO orgDAO = new GpOrganizationDAOImpl();

	public GpOrganization create(GpOrganization org) throws GesproBusinessException {
		GpOrganization existingOrg = this.findByName(org.getName());
		if (existingOrg != null) {
			throw new GesproBusinessException(String.format("le nome de Organization est deja utilise"));
		}
		return this.orgDAO.create(org);

	}

	public void update(GpOrganization org) throws GesproBusinessException {

		GpOrganization existOrg = this.orgDAO.findById(org.getId());
		if (existOrg.getId() == null) {
			throw new GesproBusinessException(
					String.format("le Organization que vous voulez mettre en jour n'existe pas "));
		}
		this.orgDAO.update(org);

	}

	public List<GpOrganization> findAll() {
		return this.orgDAO.findAll();
	}

	public void deleteById(Integer orgId) throws GesproBusinessException {
		GpOrganization existEmpl = this.orgDAO.findById(orgId);
		if (existEmpl == null) {
			throw new GesproBusinessException(String.format("le Organization que vous voulez supprimer n'existe pas "));
		}
		this.orgDAO.deleteById(orgId);
	}

	public GpOrganization findById(Integer orgId) {
		return this.orgDAO.findById(orgId);
	}

	@Override
	public GpOrganization findByName(String orgName) {
		GpOrganization existingOrg = this.orgDAO.findByName(orgName);
		return existingOrg;

	}

}
