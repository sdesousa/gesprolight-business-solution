package af.cmr.indyli.gespro.light.business.service.test;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

public class GpPhaseServiceTest {

	private IGpPhaseService phaseService = new GpPhaseServiceImpl();
	private IGpProjectService projectService = new GpProjectServiceImpl();
	private GpProjectManagerServiceImpl empService = new GpProjectManagerServiceImpl();
	private GpOrganizationServiceImpl organizationService = new GpOrganizationServiceImpl();

	private GpProjectManager pmTest;
	private GpOrganization orgTest;
	private GpProject pjTest;
	private Integer phaseIdForAllTest;
	private Integer createPhaseId;

	@Test
	public void testCreatePhaseWithSuccess() throws GesproBusinessException {
		// Given
		GpPhase phase = new GpPhase();
		Assert.assertNull(phase.getId());

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -40);
		Date d = c.getTime();
		phase.setPhaseCode("ServiceTest");
		phase.setDescription("Deuxième phase du projet");
		phase.setStartDate(d);
		phase.setEndDate(new Date());
		phase.setAmount(78553.66);
		phase.setCreationDate(new Date());
		phase.setGpProject(pjTest);
		phase = this.phaseService.create(phase);

		this.createPhaseId = phase.getId();

		Assert.assertNotNull(phase.getId());
	}

	@Test
	public void testFindAllPhasesWithSuccess() {
		// Given

		// When
		List<GpPhase> phases = this.phaseService.findByProjectId(this.pjTest.getId());
		// Then
		Assert.assertTrue(phases.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer phaseId = this.phaseIdForAllTest;
		// When
		GpPhase phase = this.phaseService.findById(phaseId);
		// Then
		Assert.assertNotNull(phase);
	}

	@Test
	public void testUpdatePhaseWithSuccess() throws GesproBusinessException {
		// Given
		Integer phaseId = this.phaseIdForAllTest;
		Assert.assertNotNull(phaseId);
		// When

		GpPhase gpPhase = this.phaseService.findById(phaseId);
		gpPhase.setDescription("New Description");
		this.phaseService.update(gpPhase);
	}

	@Test
	public void testDeletePhaseWithSuccess() {
		Integer phaseId = this.phaseIdForAllTest;
		// When
		this.phaseService.deleteById(phaseId);

		// Then
		GpPhase phase = this.phaseService.findById(phaseId);
		Assert.assertNull(phase);
	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {

		// Init GpProjetManager
		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1050");
		emp.setLastname("Turbo");
		emp.setFirstname("Pascal");
		emp.setPhoneNumber("0256897856");
		emp.setPassword("thisismymdp");
		emp.setEmail("turbo.pascal@mail.fr");
		emp.setLogin("turbo.pascal");
		emp = empService.create(emp);

		this.pmTest = new GpProjectManager();
		this.pmTest = emp;
		assertNotNull(this.pmTest.getId());

		// création organisation

		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization.setOrgCode("100Trace");
		organization.setName("Sans Trace");
		organization.setAdrWeb("100trace.com");
		organization.setContactEmail("sanstrace@org.com");
		organization.setContactName("100-Trace");
		organization.setPhoneNumber(7895);
		organization = organizationService.create(organization);

		this.orgTest = new GpOrganization();
		this.orgTest = organization;
		Assert.assertNotNull(this.orgTest.getId());

		this.orgTest = new GpOrganization();
		this.orgTest = organization;
		Assert.assertNotNull(this.orgTest.getId());

		// création project
		GpProject project = new GpProject();
		Assert.assertNull(project.getId());
		project.setProjectCode("BETA");
		project.setName("SOLSTAR");
		project.setDescription("First Project Service Test");
		project.setStartDate(new Date());
		project.setEndDate(new Date());
		project.setAmount(5623.66);
		project.setCreationDate(new Date());
		project.setGpOrganization(this.orgTest);
		project.setGpChefProjet(this.pmTest);
		project = this.projectService.create(project);
		Assert.assertNotNull(project.getId());

		this.pjTest = new GpProject();
		this.pjTest = project;

		GpPhase phase = new GpPhase();
		Assert.assertNull(phase.getId());
		phase.setPhaseCode("1.1");
		phase.setDescription("Première phase du projet");
		phase.setStartDate(new Date());
		phase.setEndDate(new Date());
		phase.setAmount(7854.66);
		phase.setCreationDate(new Date());
		phase.setGpProject(pjTest);
		phase = this.phaseService.create(phase);
		Assert.assertNotNull(phase.getId());

		this.phaseIdForAllTest = phase.getId();
	}

	@After
	public void deleteAllEntityAfter() throws GesproBusinessException {

		this.phaseService.findById(this.phaseIdForAllTest);
		if (!Objects.isNull(this.createPhaseId)) {
			this.phaseService.deleteById(this.createPhaseId);
		}
		if (!Objects.isNull(this.pmTest.getId())) {
			this.empService.deleteById(this.pmTest.getId());
		}
		if (!Objects.isNull(this.orgTest)) {
			this.organizationService.deleteById(this.orgTest.getId());
		}
		if (!Objects.isNull(this.pmTest.getId())) {
			this.projectService.deleteById(this.pjTest.getId());
		}

	}

}
