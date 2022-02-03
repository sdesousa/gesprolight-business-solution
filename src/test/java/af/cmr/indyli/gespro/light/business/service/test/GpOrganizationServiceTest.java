package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;

public class GpOrganizationServiceTest {

	private IGpOrganizationService orgService = new GpOrganizationServiceImpl();

	private Integer orgIdForAllTest = null;
	private Integer createOrgId = null;

	@Test
	public void testCreateOrganizationWithSuccess() throws GesproBusinessException {
		// Given
		GpOrganization organization = new GpOrganization();
		organization.setAdrWeb("website.com");
		organization.setContactEmail("contact@mail.com");
		organization.setContactName("CONTACT_NANE");
		organization.setName("ORG");
		organization.setOrgCode("OMC");
		organization.setPhoneNumber(1024);
		// when
		organization = orgService.create(organization);
		// Then
		Assert.assertNotNull(organization.getId());
	}

	@Test
	public void testCreateOrganizationWithException() throws GesproBusinessException {
		// Given

		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpOrganization org = new GpOrganization();
			Assert.assertNull(org.getId());
			org.setAdrWeb("orgainternational.com");
			org.setContactEmail("contact@orgainternational.com");
			org.setContactName("OI-contact");
			org.setName("OI-name");
			org.setOrgCode("OM001");
			org.setPhoneNumber(1233);
			org = orgService.create(org);
		});
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("le nome de Organization est deja utilise"));
	}

	@Test
	public void testFindAllOrganizationWithSuccess() {
		// Given

		// When
		List<GpOrganization> orgs = this.orgService.findAll();
		// Then
		Assert.assertTrue(orgs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer orgId = this.orgIdForAllTest;

		// When
		GpOrganization org = this.orgService.findById(orgId);
		// Then
		Assert.assertNotNull(org);
	}

	@Test
	public void testDelete() throws GesproBusinessException {
		// Given
		Integer orgId = this.orgIdForAllTest;
		this.orgIdForAllTest = 0;
		// When
		this.orgService.deleteById(orgId);
		GpOrganization org = this.orgService.findById(orgId);

		// Then
		Assert.assertNull(org);
	}

	@Test
	public void testUpdateOrganization() throws GesproBusinessException {
		// Given
		GpOrganization org = this.orgService.findById(this.orgIdForAllTest);
		int phone = 1000;
		org.setPhoneNumber(phone);
		// When
		this.orgService.update(org);
		GpOrganization orgUpdate = this.orgService.findById(this.orgIdForAllTest);
		// Then

		Assert.assertTrue(orgUpdate.getPhoneNumber() == phone);

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpOrganization org = new GpOrganization();
		Assert.assertNull(org.getId());
		org.setAdrWeb("orgainternational.com");
		org.setContactEmail("contact@orgainternational.com");
		org.setContactName("OI-contact");
		org.setName("OI-name");
		org.setOrgCode("OM001");
		org.setPhoneNumber(1233);
		org = orgService.create(org);
		orgIdForAllTest = org.getId();
	}

	@After
	public void deleteAllEntityAfter() throws GesproBusinessException {
		if (this.orgIdForAllTest != 0) {
			this.orgService.deleteById(this.orgIdForAllTest);
		}
		if (!Objects.isNull(this.createOrgId)) {
			this.orgService.deleteById(this.createOrgId);
		}
	}
}
