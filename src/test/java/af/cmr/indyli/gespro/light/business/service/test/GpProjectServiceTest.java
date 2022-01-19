//package af.cmr.indyli.gespro.light.business.service.test;
//
//import java.util.List;
//import java.util.Objects;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import af.cmr.indyli.gespro.light.business.entity.GpProject;
//import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
//import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
//import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;
//
//public class GpProjectServiceTest {
//	
//private IGpProjectService projectService = new GpProjectServiceImpl();
//	
//
//	private Integer projectIdForAllTest = null;
//	private Integer createEmpId = null;
//
//	@Test
//	public void testCreateProjectWithSuccess() throws GesproBusinessException {
//		// Given
//		GpProject project = new GpProject();
//		Assert.assertNull(project.getId());
//		project.setFileNumber("18023");
//		project.setLastname("PELTIER");
//		project.setFirstname("Guillaume");
//		project.setPhoneNumber("0265863289");
//		project.setPassword("myPassword");
//		project.setEmail("guillaume.peltier@gouv.fr");
//		project.setLogin("guillaume.peltier");
//
//
//		// When
//		project = projectService.create(project);
//		//On le sauvegarde pour le supprimer apres
//		this.createEmpId = project.getId();
//
//		// Then
//		Assert.assertNotNull(project.getId());
//	}
//	
//	@Test
//	public void testCreateProjectWithException() throws GesproBusinessException {
//		// Given
//
//
//		// When
//		Exception exception = Assert.assertThrows(GesproBusinessException.class, () -> {
//			GpProject project = new GpProject();
//			Assert.assertNull(project.getId());
//			project.setFileNumber("2001");
//			project.setLastname("Laurent");
//			project.setFirstname("FABIUS");
//			project.setPhoneNumber("0125698745");
//			project.setPassword("myThirdPassword");
//			project.setEmail("laurent.fabius@gouv.fr");
//			project.setLogin("laurent.fabius");
//			projectService.create(project);
//	    });
//		String actualMessage = exception.getMessage();
//
//		// Then
//		Assert.assertTrue(actualMessage.contains("Un projectloyee existe deja avec cet email"));
//	}
//
//	@Test
//	public void testFindAllProjectWithSuccess() {
//		// Given
//
//		// When
//		List<GpProject> projects = this.projectService.findAll();
//		// Then
//		Assert.assertTrue(projects.size() > 0);
//	}
//
//	@Test
//	public void testFindByIdWithSuccess() {
//		// Given
//		Integer projectId = this.projectIdForAllTest;
//
//		// When
//		GpProject project = this.projectService.findById(projectId);
//		// Then
//		Assert.assertNotNull(project);
//	}
//
//	@Test
//	public void testDelete() {
//		// Given
//		Integer projectId = this.projectIdForAllTest;
//
//		// When
//		this.projectService.deleteById(projectId);
//		GpProject project = this.projectService.findById(projectId);
//
//		// Then
//		Assert.assertNull(project);
//	}
//
//	@Before
//	public void prepareAllEntityBefore() throws GesproBusinessException {
//		GpProject project = new GpProject();
//		Assert.assertNull(project.getId());
//		project.setFileNumber("2001");
//		project.setLastname("Laurent");
//		project.setFirstname("FABIUS");
//		project.setPhoneNumber("0125698745");
//		project.setPassword("myThirdPassword");
//		project.setEmail("laurent.fabius@gouv.fr");
//		project.setLogin("laurent.fabius");
//		project = projectService.create(project) ;
//		this.projectIdForAllTest = project.getId();
//	}
//
//	@After
//	public void deleteAllEntityAfter() {
//		this.projectService.deleteById(this.projectIdForAllTest);
//		if(!Objects.isNull(this.createEmpId)) {
//			this.projectService.deleteById(this.createEmpId);
//		}
//	}
//
//}
