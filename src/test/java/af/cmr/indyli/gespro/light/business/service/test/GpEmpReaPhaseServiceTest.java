package af.cmr.indyli.gespro.light.business.service.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpEmpReaPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

public class GpEmpReaPhaseServiceTest {

	private GpEmpReaPhaseServiceImpl empReaPhaseService = new GpEmpReaPhaseServiceImpl();
	private IGpProjectService projectService = new GpProjectServiceImpl();
	private GpProjectManagerServiceImpl empService = new GpProjectManagerServiceImpl();
	private IGpPhaseService phaseService = new GpPhaseServiceImpl();
	private GpOrganizationServiceImpl organizationService = new GpOrganizationServiceImpl();

	private Integer emReaPhaseIdForAllTest = null;
	private Integer emReaPhaseCreateIdTest = null;

	private GpPhase phaseTest;
	private GpProjectManager empTest;
	private GpOrganization orgTest;
	private GpProject pjTest;

	@Test
	public void testCreateEmpReaPhaseWithSuccess() throws GesproBusinessException {

		GpEmpReaPhase gpEmpReaPhase = new GpEmpReaPhase();
		gpEmpReaPhase.setCreationDate(new Date());
		gpEmpReaPhase.setGpPhase(this.phaseTest);
		gpEmpReaPhase.setGpEmployee(empTest);

		gpEmpReaPhase = this.empReaPhaseService.create(gpEmpReaPhase);
		Assert.assertNotNull(gpEmpReaPhase);

		this.emReaPhaseCreateIdTest = gpEmpReaPhase.getId();
	}

	@Test
	public void testFindAllEmpReaPhaseWithSuccess() {
		// When
		List<GpEmpReaPhase> empReaPhases = this.empReaPhaseService.findAll();
		// Then
		Assert.assertTrue(empReaPhases.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empReaPhaseId = this.emReaPhaseIdForAllTest;
		// When
		Assert.assertNotNull(empReaPhaseId);
		GpEmpReaPhase empReaPhase = this.empReaPhaseService.findById(empReaPhaseId);
		// Then
		Assert.assertNotNull(empReaPhase.getId());
	}

	@Test
	public void testDeleteEmpReaPhaseWithSuccess() throws GesproBusinessException {

		Integer eRphaseId = this.emReaPhaseIdForAllTest;

		// When
		this.empReaPhaseService.deleteById(eRphaseId);

		// Then
		GpPhase eRphase = this.phaseService.findById(eRphaseId);
		Assert.assertNull(eRphase);
	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {

		GpProjectManager emp = new GpProjectManager();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("1050");
		emp.setLastname("Segolene");
		emp.setFirstname("ROYAL");
		emp.setPhoneNumber("0256897856");
		emp.setPassword("mySecondPassword");
		emp.setEmail("segolene.royal@gouv.fr");
		emp.setLogin("segos.royal");
		emp = this.empService.create(emp);

		this.empTest = new GpProjectManager();
		this.empTest = emp;
		assertNotNull(this.empTest.getId());

		// création organisation

		GpOrganization organization = new GpOrganization();
		Assert.assertNull(organization.getId());
		organization.setOrgCode("ALPHA");
		organization.setName("Big Org");
		organization.setAdrWeb("bigorg.com");
		organization.setContactEmail("big@org.com");
		organization.setContactName("CName");
		organization.setPhoneNumber(7895);
		organization = this.organizationService.create(organization);

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
		project.setGpChefProjet(this.empTest);
		project = this.projectService.create(project);

		this.pjTest = new GpProject();
		this.pjTest = project;
		Assert.assertNotNull(project.getId());

		// création phase
		GpPhase phase = new GpPhase();
		Assert.assertNull(phase.getId());
		phase.setPhaseCode("Phase-1");
		phase.setDescription("Première phase du projet");
		phase.setStartDate(new Date());
		phase.setEndDate(new Date());
		phase.setAmount(5623.66);
		phase.setCreationDate(new Date());
		phase.setGpProject(pjTest);
		phase = this.phaseService.create(phase);

		this.phaseTest = new GpPhase();
		this.phaseTest = phase;
		Assert.assertNotNull(phase.getId());

		// création emp_rea_phase
		GpEmpReaPhase empReaPhase = new GpEmpReaPhase();
		empReaPhase.setCreationDate(new Date());
		empReaPhase.setGpEmployee(this.empTest);
		empReaPhase.setGpPhase(this.phaseTest);
		empReaPhase = empReaPhaseService.create(empReaPhase);

		Assert.assertNotNull(empReaPhase.getId());

		this.emReaPhaseIdForAllTest = empReaPhase.getId();
	}

	@After
	public void deleteAllEntityAfter() throws GesproBusinessException {
		this.empReaPhaseService.deleteById(this.emReaPhaseIdForAllTest);

		if (!Objects.isNull(this.emReaPhaseCreateIdTest)) {
			this.empReaPhaseService.deleteById(emReaPhaseCreateIdTest);
		}

		if (!Objects.isNull(this.empTest)) {
			this.empService.deleteById(this.empTest.getId());
		}

		if (!Objects.isNull(this.orgTest)) {
			this.organizationService.deleteById(this.orgTest.getId());
		}

		if (!Objects.isNull(this.pjTest)) {
			this.projectService.deleteById(this.pjTest.getId());
		}
	}

}
