package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAddressDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmployeeDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;

public class GpAddressDAOTest {
	private IGpAddressDAO addressDAO = new GpAddressDAOImpl();
	IGpEmployeeDAO<GpEmployee> empDAO = new GpEmployeeDAOImpl();
	IGpOrganizationDAO organizationDAO = new GpOrganizationDAOImpl();

	private Integer orgIdForAllTest = null;
	private Integer addrIdForAllTest = null;
	private Integer createAddrId = null;

	private Integer empIdForAllTest = null;

	@Test
	public void testFindAllAddressWithSuccess() {
		// Given

		// When
		List<GpAddress> addrList = this.addressDAO.findAll();
		// Then
		Assert.assertTrue(addrList.size() > 0);
	}

	@Test
	public void testFindAddressById() {
		// Given
		Integer id = this.addrIdForAllTest;
		// When
		GpAddress addr = this.addressDAO.findById(id);
		// Then
		Assert.assertNotNull(addr.getId());
	}

	@Test
	public void testUpdateAddress() {
		// Given
		GpAddress addr = this.addressDAO.findById(this.addrIdForAllTest);
		addr.setStreetNumber(111);
		// When
		this.addressDAO.update(addr);
		GpAddress addrUpdated = this.addressDAO.findById(addr.getId());
		// Then

		Assert.assertTrue(addrUpdated.getStreetNumber() == 111);
	}

	@Test
	public void testDelete() {
		// Given
		Integer id = addrIdForAllTest;

		// When
		this.addressDAO.deleteById(id);
		GpAddress addr = this.addressDAO.findById(id);

		// Then
		Assert.assertNull(addr);
	}

	@Before
	public void prepareAllEntityBefore() {

		GpOrganization org = new GpOrganization();
		// Assert.assertNull(org.getId());
		org.setAdrWeb("orgainternational.com");
		org.setContactEmail("contact@orgainternational.com");
		org.setContactName("OI-contact");
		org.setName("OI-name");
		org.setOrgCode("OM001");
		org.setPhoneNumber(1233);
		org = organizationDAO.create(org);
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
		emp = empDAO.create(emp);
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
		addr = addressDAO.create(addr);

		addrIdForAllTest = addr.getId();

	}

	@After
	public void deleteAllEntityAfter() {
		this.addressDAO.deleteById(this.addrIdForAllTest);
		this.empDAO.deleteById(empIdForAllTest);
		this.organizationDAO.findById(orgIdForAllTest);
		if (!Objects.isNull(this.createAddrId)) {
			this.addressDAO.deleteById(this.createAddrId);

		}
	}
}
