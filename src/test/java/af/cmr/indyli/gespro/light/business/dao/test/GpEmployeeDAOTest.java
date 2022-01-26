package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmployeeDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;

public class GpEmployeeDAOTest {

	private IGpEmployeeDAO<GpEmployee> empDAO = new GpEmployeeDAOImpl();
	private Integer empIdForAllTest = null;
	private Integer createEmpId = null;

	@Test
	public void testCreateEmployeeWithSuccess() {
		// Given
		GpEmployee emp = new GpEmployee();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("18023");
		emp.setLastname("PELTIER");
		emp.setFirstname("Guillaume");
		emp.setPhoneNumber("0265863289");
		emp.setPassword("myPassword");
		emp.setEmail("guillaume.peltier@gouv.fr");
		emp.setLogin("guillaume.peltier");

		// When
		emp = empDAO.create(emp);
		// On le sauvegarde pour le supprimer apres
		this.createEmpId = emp.getId();
		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testFindAllEmployeeWithSuccess() {
		// Given

		// When
		List<GpEmployee> emps = this.empDAO.findAll();
		// Then
		Assert.assertTrue(emps.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		GpEmployee emp = this.empDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp);
	}

	@Test
	public void testDelete() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		this.empDAO.deleteById(empId);
		GpEmployee emp = this.empDAO.findById(empId);

		// Then
		Assert.assertNull(emp);
	}

	@Test
	public void testUpdateEmployee() {
		// Given
		GpEmployee emp = this.empDAO.findById(this.empIdForAllTest);
		emp.setPhoneNumber("0001");
		// When
		this.empDAO.update(emp);
		GpEmployee empUpdate = this.empDAO.findById(this.empIdForAllTest);
		// Then

		Assert.assertTrue(empUpdate.getPhoneNumber().equalsIgnoreCase("0001"));
	}

	@Before
	public void prepareAllEntityBefore() {
		GpEmployee emp = new GpEmployee();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2001");
		emp.setLastname("Laurent");
		emp.setFirstname("FABIUS");
		emp.setPhoneNumber("0125698745");
		emp.setPassword("myThirdPassword");
		emp.setEmail("laurent.fabius@gouv.fr");
		emp.setLogin("laurent.fabius");
		emp = empDAO.create(emp);
		this.empIdForAllTest = emp.getId();
	}

	@After
	public void deleteAllEntityAfter() {
		this.empDAO.deleteById(this.empIdForAllTest);
		if (!Objects.isNull(this.createEmpId)) {
			this.empDAO.deleteById(this.createEmpId);
		}
	}
}
