package af.cmr.indyli.gespro.light.business.service.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpProjectManagerService;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;

public class GpProjectManagerServiceTest {

	private IGpProjectManagerService<GpProjectManager> empPMService = new GpProjectManagerServiceImpl();

	private Integer empPMIdForAllTest = null;
	private Integer createEmpId = null;

	@Test
	public void testCreateProjectManagerWithSuccess() throws GesproBusinessException {
		// Given
		GpProjectManager empPM = new GpProjectManager();
		Assert.assertNull(empPM.getId());
		empPM.setFileNumber("18023");
		empPM.setLastname("PELTIER");
		empPM.setFirstname("Guillaume");
		empPM.setPhoneNumber("0265863289");
		empPM.setPassword("myPassword");
		empPM.setEmail("guillaume.peltier@gouv.fr");
		empPM.setLogin("guillaume.peltier");

		// When
		empPM = empPMService.create(empPM);
		// On le sauvegarde pour le supprimer apres
		this.createEmpId = empPM.getId();

		// Then
		Assert.assertNotNull(empPM.getId());
	}

	@Test
	public void testCreateProjectManagerWithException() throws GesproBusinessException {
		// Given

		// When
		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
			GpProjectManager empPM = new GpProjectManager();
			Assert.assertNull(empPM.getId());
			empPM.setFileNumber("2001");
			empPM.setLastname("Laurent");
			empPM.setFirstname("FABIUS");
			empPM.setPhoneNumber("0125698745");
			empPM.setPassword("myThirdPassword");
			empPM.setEmail("laurent.fabius@gouv.fr");
			empPM.setLogin("laurent.fabius");
			empPMService.create(empPM);
		});
		String actualMessage = exception.getMessage();

		// Then
		Assert.assertTrue(actualMessage.contains("Un projectManager existe deja avec cet email"));
	}

	@Test
	public void testFindAllProjectManagerWithSuccess() {
		// Given

		// When
		List<GpProjectManager> empPMs = this.empPMService.findAll();
		// Then
		Assert.assertTrue(empPMs.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empPMId = this.empPMIdForAllTest;

		// When
		GpProjectManager empPM = this.empPMService.findById(empPMId);
		// Then
		Assert.assertNotNull(empPM);
	}

	@Test
	public void testDelete() throws GesproBusinessException {
		// Given
		Integer empPMId = this.empPMIdForAllTest;
		this.empPMIdForAllTest = 0;
		// When
		this.empPMService.deleteById(empPMId);
		GpProjectManager empPM = this.empPMService.findById(empPMId);

		// Then
		Assert.assertNull(empPM);
	}

	@Test
	public void testUpdateProjectManager() throws GesproBusinessException {
		// Given
		GpProjectManager secretary = this.empPMService.findById(this.empPMIdForAllTest);
		secretary.setPhoneNumber("0001");
		// When
		this.empPMService.update(secretary);
		GpProjectManager secretaryUpdate = this.empPMService.findById(this.empPMIdForAllTest);
		// Then

		Assert.assertTrue(secretaryUpdate.getPhoneNumber().equalsIgnoreCase("0001"));

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {
		GpProjectManager empPM = new GpProjectManager();
		Assert.assertNull(empPM.getId());
		empPM.setFileNumber("2001");
		empPM.setLastname("Laurent");
		empPM.setFirstname("FABIUS");
		empPM.setPhoneNumber("0125698745");
		empPM.setPassword("myThirdPassword");
		empPM.setEmail("laurent.fabius@gouv.fr");
		empPM.setLogin("laurent.fabius");
		empPM = empPMService.create(empPM);
		this.empPMIdForAllTest = empPM.getId();
	}

	@After
	public void deleteAllEntityAfter() throws GesproBusinessException {
		if (this.empPMIdForAllTest != 0) {
			this.empPMService.deleteById(this.empPMIdForAllTest);
		}
		if (!Objects.isNull(this.createEmpId)) {
			this.empPMService.deleteById(this.createEmpId);
		}
	}
}
