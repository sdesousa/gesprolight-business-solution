package af.cmr.indyli.gespro.light.business.dao.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public class GpPhaseDAOTest {

	private IGpPhaseDAO phaseDAO = new GpPhaseDAOImpl();
	private IGpProjectDAO projectDAO = new GpProjectDAOImpl();
	private IGpProjectManagerDAO empDAO = new GpProjectManagerDAOImpl();
	private IGpOrganizationDAO organizationDAO = new GpOrganizationDAOImpl();

	private GpProjectManager pmTest;
	private GpOrganization orgTest;
	private GpProject pjTest;
	private Integer phaseIdForAllTest;
	private Integer createPhaseId;

	@Test
	public void testCreatePhaseWithSuccess() {
		// Given
		GpPhase phase = new GpPhase();
		Assert.assertNull(phase.getId());

		phase.setPhaseCode("Phase-2");
		phase.setDescription("Deuxième phase du projet");
		phase.setStartDate(new Date());
		phase.setEndDate(new Date());
		phase.setAmount(5653.66);
		phase.setCreationDate(new Date());
		phase.setGpProject(pjTest);
		phase = this.phaseDAO.create(phase);

		this.createPhaseId = phase.getId();

		Assert.assertNotNull(phase.getId());
	}

	@Test
	public void testFindAllPhasesWithSuccess() {
		// Given

		// When
		List<GpPhase> phases = this.phaseDAO.findAll();
		// Then
		Assert.assertTrue(phases.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer phaseId = this.phaseIdForAllTest;
		// When
		GpPhase phase = this.phaseDAO.findById(phaseId);
		// Then
		Assert.assertNotNull(phase);
	}

	@Test
	public void testDeletePhaseWithSuccess() {
		Integer phaseId = this.phaseIdForAllTest;

		// When
		this.empDAO.deleteById(phaseId);

		// Then
		GpPhase phase = this.phaseDAO.findById(phaseId);
		Assert.assertNull(phase);

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

		this.pjTest = new GpProject();
		this.pjTest = project;

		GpPhase phase = new GpPhase();
		Assert.assertNull(phase.getId());
		phase.setPhaseCode("Phase-1");
		phase.setDescription("Première phase du projet");
		phase.setStartDate(new Date());
		phase.setEndDate(new Date());
		phase.setAmount(5623.66);
		phase.setCreationDate(new Date());
		phase.setGpProject(pjTest);
		phase = this.phaseDAO.create(phase);
		Assert.assertNotNull(phase.getId());

		this.phaseIdForAllTest = phase.getId();
	}

	@After
	public void deleteAllEntityAfter() {

		this.phaseDAO.findById(this.phaseIdForAllTest);
		if (!Objects.isNull(this.createPhaseId)) {
			this.phaseDAO.deleteById(this.createPhaseId);
		}
		if (!Objects.isNull(this.pmTest.getId())) {
			this.empDAO.deleteById(this.pmTest.getId());
		}
		if (!Objects.isNull(this.orgTest)) {
			this.organizationDAO.deleteById(this.orgTest.getId());
		}
		if (!Objects.isNull(this.pmTest.getId())) {
			this.projectDAO.deleteById(this.pjTest.getId());
		}

	}

}
