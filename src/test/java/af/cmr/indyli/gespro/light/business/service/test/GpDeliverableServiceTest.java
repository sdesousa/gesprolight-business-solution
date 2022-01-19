package af.cmr.indyli.gespro.light.business.service.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpDeliverableService;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpDeliverableServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

public class GpDeliverableServiceTest {

	private IGpDeliverableService deliverableService = new GpDeliverableServiceImpl();
	private IGpProjectService projectService = new GpProjectServiceImpl();
	private GpProjectManagerServiceImpl empService = new GpProjectManagerServiceImpl();
	private IGpPhaseService phaseService = new GpPhaseServiceImpl();
	private GpOrganizationServiceImpl organizationService = new GpOrganizationServiceImpl();

	private Integer deliverableIdForAllTest = null;
	private Integer createdDelId = null;

	private GpPhase phaseTest;
	private GpProjectManager empTest;
	private GpOrganization orgTest;
	private GpProject pjTest;

	@Test
	public void testCreateDeliverableWithSuccess() throws GesproBusinessException {

		if (!Objects.isNull(this.deliverableIdForAllTest)) {
			this.deliverableService.deleteById(this.deliverableIdForAllTest);
		}
		// Given
		GpDeliverable deliverable = new GpDeliverable();
		Assert.assertNull(deliverable.getId());
		deliverable.setDelCode("Deliverable-1");
		deliverable.setDescription("Deliverable Test Unit");
		deliverable.setLabel("Label Test");
		deliverable.setDelPath("Path Test Unit");
		deliverable.setCreationDate(new Date());
		deliverable.setGpPhase(this.phaseTest);
		// When
		deliverable = deliverableService.create(deliverable);
		// Then
		Assert.assertNotNull(deliverable.getId());
		this.createdDelId = deliverable.getId();
	}

	@Test
	public void testFindAllDeliverablesWithSuccess() {
		// Given

		// When
		List<GpDeliverable> deliverables = this.deliverableService.findAll();
		// Then
		Assert.assertTrue(deliverables.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer deliverableId = this.deliverableIdForAllTest;

		// When
		GpDeliverable deliverable = this.deliverableService.findById(deliverableId);
		// Then
		Assert.assertNotNull(deliverable);
	}

	@Test
	public void testUpdatePhaseWithSuccess() throws GesproBusinessException {
		// Given
		Integer delId = this.deliverableIdForAllTest;
		Assert.assertNotNull(delId);
		// When

		GpDeliverable gpDel = this.deliverableService.findById(delId);
		gpDel.setDescription("New Description");
		this.deliverableService.update(gpDel);
	}

	@Test
	public void testDeleteDeliverableWithSuccess() {
		// Given
		Integer delId = this.deliverableIdForAllTest;

		this.deliverableService.deleteById(delId);

		GpDeliverable deliverable = this.deliverableService.findById(delId);
		Assert.assertNull(deliverable);

	}

	@Before
	public void prepareAllEntityBefore() throws GesproBusinessException {

		// création employé
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

		// création deliverable
		GpDeliverable deliverable = new GpDeliverable();
		deliverable.setCreationDate(new Date());
		deliverable.setDelCode("ALPHA");
		deliverable.setDelPath("§PATH");
		deliverable.setDescription("Deliverable Test Unit");
		deliverable.setLabel("del alpha");
		deliverable.setGpPhase(phase);

		deliverable = this.deliverableService.create(deliverable);
		Assert.assertNotNull(deliverable.getId());

		this.deliverableIdForAllTest = deliverable.getId();
	}

	@After
	public void deleteAllEntityAfter() throws GesproBusinessException {

		this.deliverableService.deleteById(this.deliverableIdForAllTest);

		if (!Objects.isNull(this.createdDelId)) {
			this.deliverableService.deleteById(this.createdDelId);
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

		if (!Objects.isNull(this.phaseTest.getId())) {
			this.phaseService.deleteById(this.phaseTest.getId());
		}

	}
}
