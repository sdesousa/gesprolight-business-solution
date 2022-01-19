package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpAddressService;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.light.business.service.impl.GpAddressServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpEmployeeServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;

public class GpAddressServiceTest {
	private IGpAddressService<GpAddress> addressService = new GpAddressServiceImpl();
	private IGpEmployeeService<GpEmployee> empService = new GpEmployeeServiceImpl();
	private IGpOrganizationService<GpOrganization> organizationService = new GpOrganizationServiceImpl();

	private Integer orgIdForAllTest = null;
	private Integer addrIdForAllTest = null;
	private Integer createAddrId = null;
	private Integer createOrgId = null;
	private Integer createEmpId = null;
	private Integer empIdForAllTest = null;

	@Test
	public void createAddressTes() throws GesproBusinessException {
		GpOrganization org = new GpOrganization();
		// Assert.assertNull(org.getId());
		org.setAdrWeb("orgnew.com");
		org.setContactEmail("contact@orgnew.com");
		org.setContactName("Orgnew-contact");
		org.setName("Orgnew-name");
		org.setOrgCode("OMN001");
		org.setPhoneNumber(1230);
		org = organizationService.create(org);

		GpEmployee emp = new GpEmployee();
		// Assert.assertNull(emp.getId());
		emp.setFileNumber("3001");
		emp.setLastname("Brice");
		emp.setFirstname("Joan");
		emp.setPhoneNumber("012569");
		emp.setPassword("orgNewPassword");
		emp.setEmail("Brice.Joan@gouv.fr");
		emp.setLogin("Brice.Joan");
		emp = empService.create(emp);

		GpAddress addr = new GpAddress();
		// Assert.assertNull(addr.getId());
		addr.setId(2050);
		addr.setStreetNumber(2050);
		addr.setStreetLabel("ROYAL");
		addr.setZipCode(44);
		addr.setCountry("France");
		byte isMain = 2;
		addr.setIsMain(isMain);

		addr.setGpOrganization(org);
		addr.setGpEmployee(emp);
		addr = addressService.create(addr);
		Assert.assertNotNull(addr.getId());

	}

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpAddress> addrList = this.addressService.findAll();
		// Then
		Assert.assertTrue(addrList.size() > 0);
	}

	@Test
	public void testFindAddressById() {
		// Given
		Integer id = this.addrIdForAllTest;
		// When
		GpAddress addr = this.addressService.findById(id);
		// Then
		Assert.assertNotNull(addr.getId());
	}

	@Test
	public void testUpdateAddress() throws GesproBusinessException {
		// Given
		GpAddress addr = this.addressService.findById(this.addrIdForAllTest);
		addr.setStreetNumber(111);
		// When
		this.addressService.update(addr);
		GpAddress addrUpdated = this.addressService.findById(addr.getId());
		// Then

		Assert.assertTrue(addrUpdated.getStreetNumber() == 111);
	}

	@Test
	public void testDelete() throws GesproBusinessException {
		// Given
		Integer id = addrIdForAllTest;

		// When
		this.addressService.deleteById(id);
		GpAddress addr = this.addressService.findById(id);

		// Then
		Assert.assertNull(addr);
	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {

		GpOrganization org = new GpOrganization();
		// Assert.assertNull(org.getId());
		org.setAdrWeb("orgainternational.com");
		org.setContactEmail("contact@orgainternational.com");
		org.setContactName("OI-contact");
		org.setName("OI-name");
		org.setOrgCode("OM001");
		org.setPhoneNumber(1233);
		org = organizationService.create(org);
		orgIdForAllTest = org.getId();

		GpEmployee emp = new GpEmployee();
		// Assert.assertNull(emp.getId());
		emp.setFileNumber("2001");
		emp.setLastname("Laurent");
		emp.setFirstname("FABIUS");
		emp.setPhoneNumber("0125698745");
		emp.setPassword("myThirdPassword");
		emp.setEmail("laurent.fabius@gouv.fr");
		emp.setLogin("laurent.fabius");
		emp = empService.create(emp);
		this.empIdForAllTest = emp.getId();

		GpAddress addr = new GpAddress();
		// Assert.assertNull(addr.getId());
		addr.setId(1050);
		addr.setStreetNumber(1050);
		addr.setStreetLabel("ROYAL");
		addr.setZipCode(44);
		addr.setCountry("ROYAL");
		byte isMain = 5;
		addr.setIsMain(isMain);

		addr.setGpOrganization(org);
		addr.setGpEmployee(emp);
		addr = addressService.create(addr);

		addrIdForAllTest = addr.getId();

	}

	@After
	public void deleteAllEntityAfter() throws GesproBusinessException {
		this.addressService.deleteById(this.addrIdForAllTest);
		this.empService.deleteById(empIdForAllTest);
		this.organizationService.deleteById(orgIdForAllTest);
		if (!Objects.isNull(this.addrIdForAllTest)) {
			this.addressService.deleteById(this.addrIdForAllTest);

		}
		if (!Objects.isNull(this.createAddrId)) {
			this.addressService.deleteById(this.createAddrId);

		}
		if (!Objects.isNull(this.createOrgId)) {
			this.organizationService.deleteById(this.createOrgId);

		}
		if (!Objects.isNull(this.createEmpId)) {
			this.empService.deleteById(this.createEmpId);

		}
	}
}
