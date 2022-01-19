package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpSecretaryDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpSecretaryDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpSecretary;

public class GpSecretaryDAOTest {

	private IGpSecretaryDAO empSecDAO = new GpSecretaryDAOImpl();
	private Integer empIdForAllTest = null;
	private Integer createEmprId = null;

	@Test
	public void testCreateSecretaryWithSuccess() {
		// Given
		GpSecretary emp = new GpSecretary();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2026");
		emp.setLastname("JOBSec");
		emp.setFirstname("JoelSec");
		emp.setPhoneNumber("2365987865");
		emp.setPassword("mySecPassword");
		emp.setEmail("jobsec.joelsec@gouv.fr");
		emp.setLogin("jobsec.joelsec");

		// When
		emp = empSecDAO.create(emp);

		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testFindAllSecretaryWithSuccess() {
		// Given

		// When
		List<GpSecretary> empsSec = this.empSecDAO.findAll();
		// Then
		Assert.assertTrue(empsSec.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		GpSecretary emp = this.empSecDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testDeleteSecretary() {
		// Given

		Integer empId = this.empIdForAllTest;
		// When
		this.empSecDAO.deleteById(empId);
		GpSecretary emp = this.empSecDAO.findById(empId);
		// Then
		Assert.assertNull(emp);
	}

	@Test
	public void testUpdateSecretary() {
		// Given
		GpSecretary secretary = this.empSecDAO.findById(this.empIdForAllTest);
		secretary.setPhoneNumber("0001");
		// When
		this.empSecDAO.update(secretary);
		GpSecretary secretaryUpdate = this.empSecDAO.findById(this.empIdForAllTest);
		// Then

		Assert.assertTrue(secretaryUpdate.getPhoneNumber().equalsIgnoreCase("0001"));
	}

	@Before
	public void prepareAllEntityBefore() {
		GpSecretary emp = new GpSecretary();

		emp.setFileNumber("2027");
		emp.setLastname("TCTsec2");
		emp.setFirstname("CY2sec2");
		emp.setPhoneNumber("22658632892");
		emp.setPassword("myPasswordsec");
		emp.setEmail("tctsec2tech2.cy@gmail.com");
		emp.setLogin("tct2sectech2.cy");
		emp = empSecDAO.create(emp);
		this.empIdForAllTest = emp.getId();

	}

	@After
	public void deleteAllEntityAfter() {
		this.empSecDAO.deleteById(empIdForAllTest);
		if (!Objects.isNull(this.createEmprId)) {
			this.empSecDAO.deleteById(this.createEmprId);

		}
	}
}
