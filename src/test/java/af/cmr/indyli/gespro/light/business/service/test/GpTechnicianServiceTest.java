package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpTechnician;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpTechnicianService;
import af.cmr.indyli.gespro.light.business.service.impl.GpTechnicianServiceImpl;

public class GpTechnicianServiceTest {

	private IGpTechnicianService<GpTechnician> empService = new GpTechnicianServiceImpl();

	private Integer empIdForAllTest = null;
	private Integer createEmpId = null;

	@Test
	public void testCreateTechnicianWithSuccess() throws GesproBusinessException {
		// Given
		GpTechnician emp = new GpTechnician();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("18023");
		emp.setLastname("PELTIER");
		emp.setFirstname("Guillaume");
		emp.setPhoneNumber("0265863289");
		emp.setPassword("myPassword");
		emp.setEmail("guillaume.peltier@gouv.fr");
		emp.setLogin("guillaume.peltier");

		// When
		emp = empService.create(emp);
		// On le sauvegarde pour le supprimer apres
		this.createEmpId = emp.getId();

		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testCreateTechnicianWithException() throws GesproBusinessException {
		// Given

		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpTechnician emp = new GpTechnician();
			Assert.assertNull(emp.getId());
			emp.setFileNumber("2001");
			emp.setLastname("Laurent");
			emp.setFirstname("FABIUS");
			emp.setPhoneNumber("0125698745");
			emp.setPassword("myThirdPassword");
			emp.setEmail("laurent.fabius@gouv.fr");
			emp.setLogin("laurent.fabius");
			empService.create(emp);
		});
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("Un employee existe deja avec cet email"));
	}

	@Test
	public void testFindAllTechnicianWithSuccess() {
		// Given

		// When
		List<GpTechnician> emps = this.empService.findAll();
		// Then
		Assert.assertTrue(emps.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		GpTechnician emp = this.empService.findById(empId);
		// Then
		Assert.assertNotNull(emp);
	}

	@Test
	public void testDelete() throws GesproBusinessException {
		// Given
		Integer empId = this.empIdForAllTest;
		this.empIdForAllTest = 0;
		// When
		this.empService.deleteById(empId);
		GpTechnician emp = this.empService.findById(empId);

		// Then
		Assert.assertNull(emp);
	}

	@Test
	public void testUpdateTechnician() throws GesproBusinessException {
		// Given
		GpTechnician Technician = this.empService.findById(this.empIdForAllTest);
		Technician.setPhoneNumber("0001");
		// When
		this.empService.update(Technician);
		GpTechnician TechnicianUpdate = this.empService.findById(this.empIdForAllTest);
		// Then

		Assert.assertTrue(TechnicianUpdate.getPhoneNumber().equalsIgnoreCase("0001"));

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpTechnician emp = new GpTechnician();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2001");
		emp.setLastname("Laurent");
		emp.setFirstname("FABIUS");
		emp.setPhoneNumber("0125698745");
		emp.setPassword("myThirdPassword");
		emp.setEmail("laurent.fabius@gouv.fr");
		emp.setLogin("laurent.fabius");
		emp = empService.create(emp);
		this.empIdForAllTest = emp.getId();
	}

	@After
	public void deleteAllEntityAfter() throws GesproBusinessException {
		if (this.empIdForAllTest != 0) {
			this.empService.deleteById(this.empIdForAllTest);
		}
		if (!Objects.isNull(this.createEmpId)) {
			this.empService.deleteById(this.createEmpId);
		}
	}
}
