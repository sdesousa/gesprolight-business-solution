package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpAccountantService;
import af.cmr.indyli.gespro.light.business.service.impl.GpAccountantServiceImpl;

public class GpAccountantServiceTest {

	private IGpAccountantService<GpAccountant> empService = new GpAccountantServiceImpl();

	private Integer empIdForAllTest = null;
	private Integer createEmpId = null;

	@Test
	public void testCreateAccountantWithSuccess() throws GesproBusinessException {
		// Given
		GpAccountant emp = new GpAccountant();
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
	public void testCreateAccountantWithException() throws GesproBusinessException {
		// Given

		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpAccountant emp = new GpAccountant();
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
	public void testFindAllAccountantWithSuccess() {
		// Given

		// When
		List<GpAccountant> emps = this.empService.findAll();
		// Then
		Assert.assertTrue(emps.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		GpAccountant emp = this.empService.findById(empId);
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
		GpAccountant emp = this.empService.findById(empId);

		// Then
		Assert.assertNull(emp);
	}

	@Test
	public void testUpdateAccountant() throws GesproBusinessException {
		// Given
		GpAccountant Accountant = this.empService.findById(this.empIdForAllTest);
		Accountant.setPhoneNumber("0001");
		// When
		this.empService.update(Accountant);
		GpAccountant AccountantUpdate = this.empService.findById(this.empIdForAllTest);
		// Then

		Assert.assertTrue(AccountantUpdate.getPhoneNumber().equalsIgnoreCase("0001"));

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpAccountant emp = new GpAccountant();
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
