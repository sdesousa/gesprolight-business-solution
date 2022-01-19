package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpAdmin;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpAdminService;
import af.cmr.indyli.gespro.light.business.service.impl.GpAdminServiceImpl;

public class GpAdminServiceTest {

	private IGpAdminService<GpAdmin> empService = new GpAdminServiceImpl();

	private Integer empIdForAllTest = null;
	private Integer createEmpId = null;

	@Test
	public void testCreateAdminWithSuccess() throws GesproBusinessException {
		// Given
		GpAdmin emp = new GpAdmin();
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
	public void testCreateAdminWithException() throws GesproBusinessException {
		// Given

		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpAdmin emp = new GpAdmin();
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
	public void testFindAllAdminWithSuccess() {
		// Given

		// When
		List<GpAdmin> emps = this.empService.findAll();
		// Then
		Assert.assertTrue(emps.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		GpAdmin emp = this.empService.findById(empId);
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
		GpAdmin emp = this.empService.findById(empId);

		// Then
		Assert.assertNull(emp);
	}

	@Test
	public void testUpdateAdmin() throws GesproBusinessException {
		// Given
		GpAdmin Admin = this.empService.findById(this.empIdForAllTest);
		Admin.setPhoneNumber("0001");
		// When
		this.empService.update(Admin);
		GpAdmin AdminUpdate = this.empService.findById(this.empIdForAllTest);
		// Then

		Assert.assertTrue(AdminUpdate.getPhoneNumber().equalsIgnoreCase("0001"));

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpAdmin emp = new GpAdmin();
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
