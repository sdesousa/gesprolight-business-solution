package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpDirectorDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpDirectorDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDirector;

public class GpDirectorDAOTest {

	private IGpDirectorDAO empDirecDAO = new GpDirectorDAOImpl();
	private Integer empIdForAllTest = null;
	private Integer createEmprId = null;

	@Test
	public void testCreateDirecteurWithSuccess() {
		// Given
		GpDirector emp = new GpDirector();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2026");
		emp.setLastname("JOBDirec");
		emp.setFirstname("JoelDirec");
		emp.setPhoneNumber("2365987865");
		emp.setPassword("myDirecPassword");
		emp.setEmail("jobDirec.joelDirec@gouv.fr");
		emp.setLogin("jobDirec.joelDirec");

		// When
		emp = empDirecDAO.create(emp);

		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testFindAllDirecteurWithSuccess() {
		// Given

		// When
		List<GpDirector> empsDirec = this.empDirecDAO.findAll();
		// Then
		Assert.assertTrue(empsDirec.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		GpDirector emp = this.empDirecDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testUpdateDirecteur() {
		// Given
		GpDirector emp = this.empDirecDAO.findById(this.empIdForAllTest);
		emp.setPhoneNumber("0001");
		// When
		this.empDirecDAO.update(emp);
		GpDirector directeurUpdate = this.empDirecDAO.findById(this.empIdForAllTest);
		// Then

		Assert.assertTrue(directeurUpdate.getPhoneNumber().equalsIgnoreCase("0001"));
	}

	@Test
	public void testDeleteDirecteur() {
		// Given

		Integer empId = this.empIdForAllTest;

		// When
		this.empDirecDAO.deleteById(empId);
		GpDirector emp = this.empDirecDAO.findById(empId);
		// Then
		Assert.assertNull(emp);
	}

	@Before
	public void prepareAllEntityBefore() {
		GpDirector emp = new GpDirector();

		emp.setFileNumber("2027");
		emp.setLastname("TCTDirec2");
		emp.setFirstname("CY2Direc2");
		emp.setPhoneNumber("22658632892");
		emp.setPassword("myPasswordDirec");
		emp.setEmail("tctsec2tech2.cy@gmail.com");
		emp.setLogin("tct2Directech2.cy");
		emp = empDirecDAO.create(emp);
		this.empIdForAllTest = emp.getId();
	}

	@After
	public void deleteAllEntityAfter() {
		this.empDirecDAO.deleteById(empIdForAllTest);
		if (!Objects.isNull(this.createEmprId)) {
			this.empDirecDAO.deleteById(this.createEmprId);

		}
	}

}
