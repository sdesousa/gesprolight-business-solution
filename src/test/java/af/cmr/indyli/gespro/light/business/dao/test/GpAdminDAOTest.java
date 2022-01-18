package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpAdminDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAdminDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAdmin;

public class GpAdminDAOTest {

	private IGpAdminDAO empAdmnDAO = new GpAdminDAOImpl();
	private Integer empIdForAllTest = null;
	private Integer createEmprId = null;

	@Test
	public void testCreateAdminWithSuccess() {
		// Given
		GpAdmin emp = new GpAdmin();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2029");
		emp.setLastname("JOBAdmn");
		emp.setFirstname("JoelAdmn");
		emp.setPhoneNumber("2365987865");
		emp.setPassword("myAdmnPassword");
		emp.setEmail("jobAdmn.joelAdmn@gouv.fr");
		emp.setLogin("jobAdmn.joelAdmn");

		// When
		emp = empAdmnDAO.create(emp);

		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testFindAllAdminWithSuccess() {
		// Given

		// When
		List<GpAdmin> empsAdmn = this.empAdmnDAO.findAll();
		// Then
		Assert.assertTrue(empsAdmn.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		GpAdmin emp = this.empAdmnDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testUpdateAdmin() {
		// Given
		GpAdmin emp = this.empAdmnDAO.findById(this.empIdForAllTest);
		emp.setPhoneNumber("0001");
		// When
		this.empAdmnDAO.update(emp);
		GpAdmin empUpdate = this.empAdmnDAO.findById(this.empIdForAllTest);
		// Then

		Assert.assertTrue(empUpdate.getPhoneNumber().equalsIgnoreCase("0001"));
	}

	@Test
	public void testDeleteAdmin() {
		// Given

		Integer empId = this.empIdForAllTest;

		// When
		this.empAdmnDAO.deleteById(empId);
		GpAdmin emp = this.empAdmnDAO.findById(empId);
		// Then
		Assert.assertNull(emp);
	}

	@Before
	public void prepareAllEntityBefore() {
		GpAdmin emp = new GpAdmin();

		emp.setFileNumber("2028");
		emp.setLastname("TCTAdmn2");
		emp.setFirstname("CY2Admn2");
		emp.setPhoneNumber("22658632892");
		emp.setPassword("myPasswordAdmn");
		emp.setEmail("tctsec2tech2.cy@gmail.com");
		emp.setLogin("tct2Admntech2.cy");
		emp = empAdmnDAO.create(emp);
		this.empIdForAllTest = emp.getId();
	}

	@After
	public void deleteAllEntityAfter() {
		this.empAdmnDAO.deleteById(empIdForAllTest);
		if (!Objects.isNull(this.createEmprId)) {
			this.empAdmnDAO.deleteById(this.createEmprId);

		}
	}
}
