package af.cmr.indyli.gespro.light.business.dao.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpDeliverableDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpDeliverableDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public class GpDeliverableDAOTest {

	private IGpDeliverableDAO deliverableDAO = new GpDeliverableDAOImpl();
	private IGpProjectDAO projectDAO = new GpProjectDAOImpl();
	private IGpProjectManagerDAO empDAO = new GpProjectManagerDAOImpl();
	private IGpPhaseDAO phaseDAO = new GpPhaseDAOImpl();
	private IGpOrganizationDAO organizationDAO = new GpOrganizationDAOImpl();

	private Integer deliverableIdForAllTest = null;
	private Integer createdDelId = null;

	private GpPhase phaseTest;
	private GpProjectManager empTest;
	private GpOrganization orgTest;
	private GpProject pjTest;

	@Test
	public void testCreateDeliverableWithSuccess() {

		if (!Objects.isNull(this.deliverableIdForAllTest)) {
			this.deliverableDAO.deleteById(this.deliverableIdForAllTest);
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
		deliverable = deliverableDAO.create(deliverable);
		// Then
		Assert.assertNotNull(deliverable.getId());
		this.createdDelId = deliverable.getId();
	}

	@Test
	public void testFindAllDeliverablesWithSuccess() {
		// Given

		// When
		List<GpDeliverable> deliverables = this.deliverableDAO.findAll();
		// Then
		Assert.assertTrue(deliverables.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer deliverableId = this.deliverableIdForAllTest;

		// When
		GpDeliverable deliverable = this.deliverableDAO.findById(deliverableId);
		// Then
		Assert.assertNotNull(deliverable);
	}

	@Test
	public void testDeleteDeliverableWithSuccess() {
		// Given
		Integer delId = this.deliverableIdForAllTest;

		this.deliverableDAO.deleteById(delId);

		GpDeliverable deliverable = this.deliverableDAO.findById(delId);
		Assert.assertNull(deliverable);

	}

	@Before
	public void prepareAllEntityBefore() {

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
		emp = this.empDAO.create(emp);

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
		organization = this.organizationDAO.create(organization);

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
		project = this.projectDAO.create(project);

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
		phase = this.phaseDAO.create(phase);

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

		deliverable = this.deliverableDAO.create(deliverable);
		Assert.assertNotNull(deliverable.getId());

		this.deliverableIdForAllTest = deliverable.getId();
	}

	@After
	public void deleteAllEntityAfter() {

		this.deliverableDAO.deleteById(this.deliverableIdForAllTest);

		if (!Objects.isNull(this.createdDelId)) {
			this.deliverableDAO.deleteById(this.createdDelId);
		}

		if (!Objects.isNull(this.empTest)) {
			this.empDAO.deleteById(this.empTest.getId());
		}

		if (!Objects.isNull(this.orgTest)) {
			this.organizationDAO.deleteById(this.orgTest.getId());
		}

		if (!Objects.isNull(this.pjTest)) {
			this.projectDAO.deleteById(this.pjTest.getId());
		}

		if (!Objects.isNull(this.phaseTest.getId())) {
			this.phaseDAO.deleteById(this.phaseTest.getId());
		}

	}
}
