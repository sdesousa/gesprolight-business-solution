package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;

public class GpOrganizationDAOTest {
	private IGpOrganizationDAO organizationDAO = new GpOrganizationDAOImpl();

	private Integer orgIdForAllTest = null;
	private Integer createOrgId = null;

	@Test
	public void testCreateBillWithSuccess() {

		// Given
		GpOrganization organization = new GpOrganization();
		organization.setAdrWeb("website.com");
		organization.setContactEmail("contact@mail.com");
		organization.setContactName("CONTACT_NANE");
		organization.setName("ORG");
		organization.setOrgCode("OMC");
		organization.setPhoneNumber(1024);
		// when
		organization = organizationDAO.create(organization);
		// Then
		Assert.assertNotNull(organization.getId());

	}

	@Test
	public void testFindAllAdminWithSuccess() {
		// Given

		// When
		List<GpOrganization> orgList = this.organizationDAO.findAll();
		// Then
		Assert.assertTrue(orgList.size() > 0);
	}

	@Test
	public void testFindById() {
		// Given
		Integer id = this.orgIdForAllTest;
		// When
		GpOrganization org = this.organizationDAO.findById(id);
		// Then
		Assert.assertNotNull(org.getId());
	}

	@Test
	public void testUpdateAddress() {
		// Given
		GpOrganization org = this.organizationDAO.findById(this.orgIdForAllTest);
		org.setPhoneNumber(100);
		// When
		this.organizationDAO.update(org);
		GpOrganization orgUpdated = this.organizationDAO.findById(org.getId());
		// Then

		Assert.assertTrue(orgUpdated.getPhoneNumber() == 100);
	}

	@Test
	public void testDeleteById() {
		// Given
		Integer id = this.orgIdForAllTest;
		// When
		this.organizationDAO.deleteById(id);
		GpOrganization org = this.organizationDAO.findById(id);
		// Then
		Assert.assertNull(org);
	}

	@Before
	public void prepareAllEntityBefore() {
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org.setAdrWeb("orgainternational.com");
		org.setContactEmail("contact@orgainternational.com");
		org.setContactName("OI-contact");
		org.setName("OI-name");
		org.setOrgCode("OM001");
		org.setPhoneNumber(1233);
		org = organizationDAO.create(org);
		orgIdForAllTest = org.getId();
	}

	@After
	public void deleteAllEntityAfter() {
		this.organizationDAO.deleteById(this.orgIdForAllTest);
		if (!Objects.isNull(this.createOrgId)) {
			this.organizationDAO.deleteById(this.createOrgId);
		}
	}
}
