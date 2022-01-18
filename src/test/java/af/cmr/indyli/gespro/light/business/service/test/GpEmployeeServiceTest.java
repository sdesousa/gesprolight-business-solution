package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.impl.GpEmployeeServiceImpl;

public class GpEmployeeServiceTest {

	private IGpEmployeeService<GpEmployee> empService = new GpEmployeeServiceImpl();
	

	private Integer empIdForAllTest = null;
	private Integer createEmpId = null;

	@Test
	public void testCreateEmployeeWithSuccess() throws GesproBusinessException {
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
		emp = empService.create(emp);
		//On le sauvegarde pour le supprimer apres
		this.createEmpId = emp.getId();

		// Then
		Assert.assertNotNull(emp.getId());
	}
	
	@Test
	public void testCreateEmployeeWithException() throws GesproBusinessException {
		// Given


		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpEmployee emp = new GpEmployee();
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
	public void testFindAllEmployeeWithSuccess() {
		// Given

		// When
		List<GpEmployee> emps = this.empService.findAll();
		// Then
		Assert.assertTrue(emps.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		GpEmployee emp = this.empService.findById(empId);
		// Then
		Assert.assertNotNull(emp);
	}

	@Test
	public void testDelete() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		this.empService.deleteById(empId);
		GpEmployee emp = this.empService.findById(empId);

		// Then
		Assert.assertNull(emp);
	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpEmployee emp = new GpEmployee();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2001");
		emp.setLastname("Laurent");
		emp.setFirstname("FABIUS");
		emp.setPhoneNumber("0125698745");
		emp.setPassword("myThirdPassword");
		emp.setEmail("laurent.fabius@gouv.fr");
		emp.setLogin("laurent.fabius");
		emp = empService.create(emp) ;
		this.empIdForAllTest = emp.getId();
	}

	@After
	public void deleteAllEntityAfter() {
		this.empService.deleteById(this.empIdForAllTest);
		if(!Objects.isNull(this.createEmpId)) {
			this.empService.deleteById(this.createEmpId);
		}
	}
}
