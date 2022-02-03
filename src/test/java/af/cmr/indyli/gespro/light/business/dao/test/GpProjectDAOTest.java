package af.cmr.indyli.gespro.light.business.dao.test;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public class GpProjectDAOTest {

	private IGpProjectManagerDAO empDAO = new GpProjectManagerDAOImpl();
	private IGpProjectDAO projectDAO = new GpProjectDAOImpl();
	private IGpOrganizationDAO organizationDAO = new GpOrganizationDAOImpl();

	private Integer pjIdForAllTest = null;
	private Integer pjIdCreateTest = null;

	private GpOrganization orgTest;
	private GpProjectManager pmTest;

	@Test
	public void testCreateProjectWithSuccess() {
		// Given
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -5);
		
		Date d = c.getTime();
		System.out.println(d + "   END");
		
		GpProject project = new GpProject();
		Assert.assertNull(project.getId());
		project.setProjectCode("Code-1");
		project.setName("Project-1");
		project.setDescription("First Project");
		project.setStartDate(new Date());
		project.setEndDate(d);
		project.setAmount(5623.66);
		project.setCreationDate(new Date());

		Assert.assertNotNull(this.pmTest.getId());
		Assert.assertNotNull(this.orgTest.getId());

		project.setGpOrganization(this.orgTest);
		project.setGpChefProjet(pmTest);

		project = this.projectDAO.create(project);
		Assert.assertNotNull(project.getId());

		this.pjIdCreateTest = project.getId();

	}

	@Test
	public void testFindAllProjectsWithSuccess() {
		// Given

		// When
		List<GpProject> projects = this.projectDAO.findAll();
		// Then
		Assert.assertTrue(projects.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer projectId = this.pjIdForAllTest;
		// When
		Assert.assertNotNull(projectId);
		GpProject project = this.projectDAO.findById(projectId);
		// Then
		Assert.assertNotNull(project);
	}

	@Test
	public void testUpdateProjectsWithSuccess() {
		// Given
		Integer projectId = this.pjIdForAllTest;
		Assert.assertNotNull(projectId);
		// When

		GpProject gpProject = this.projectDAO.findById(projectId);
		gpProject.setAmount(11);
		projectDAO.update(gpProject);
		Assert.assertTrue(gpProject.getAmount() == 11);
	}

	@Test
	public void testDeleteProjectWithSuccess() {
		// Given
		Integer projectId = this.pjIdForAllTest;
		Assert.assertNotNull(projectId);
		// When
		projectDAO.deleteById(projectId);
	}

	@Before
	public void prepareAllEntityBefore() {

		// Init GpProjetManager
		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1050");
		emp.setLastname("Segolene");
		emp.setFirstname("ROYAL");
		emp.setPhoneNumber("0256897856");
		emp.setPassword("mySecondPassword");
		emp.setEmail("segolene.royal@gouv.fr");
		emp.setLogin("sego.royal");
		emp = empDAO.create(emp);

		this.pmTest = new GpProjectManager();
		this.pmTest = emp;
		assertNotNull(this.pmTest.getId());

		// création organisation

		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization.setOrgCode("ALPHA");
		organization.setName("Big Org");
		organization.setAdrWeb("bigorg.com");
		organization.setContactEmail("big@org.com");
		organization.setContactName("CName");
		organization.setPhoneNumber(7895);
		organization = organizationDAO.create(organization);

		this.orgTest = new GpOrganization();
		this.orgTest = organization;
		Assert.assertNotNull(this.orgTest.getId());

		// création project
		GpProject project = new GpProject();
		Assert.assertNull(project.getId());
		project.setProjectCode("Code-1");
		project.setName("Project-1");
		project.setDescription("First Project");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(5623.66);
		project.setCreationDate(new Date());
		project.setGpOrganization(this.orgTest);
		project.setGpChefProjet(this.pmTest);

		project = this.projectDAO.create(project);
		Assert.assertNotNull(project.getId());

		this.pjIdForAllTest = project.getId();
	}

	@After
	public void deleteAllEntityAfter() {
		this.projectDAO.deleteById(this.pjIdForAllTest);
		if (!Objects.isNull(this.pjIdCreateTest)) {
			this.projectDAO.deleteById(pjIdCreateTest);
		}
		if (!Objects.isNull(this.pmTest)) {
			this.empDAO.deleteById(this.pmTest.getId());
		}
		if (!Objects.isNull(this.orgTest)) {
			this.organizationDAO.deleteById(this.orgTest.getId());
		}
	}

}
