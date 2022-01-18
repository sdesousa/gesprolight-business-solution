package af.cmr.indyli.gespro.light.business.dao.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpBillDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpBillDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpOrganizationDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public class GpBillDAOTest {

	private IGpBillDAO billDAO = new GpBillDAOImpl();
	private IGpProjectDAO projectDAO = new GpProjectDAOImpl();
	private IGpProjectManagerDAO empDAO = new GpProjectManagerDAOImpl();
	private IGpPhaseDAO phaseDAO = new GpPhaseDAOImpl();
	private IGpOrganizationDAO organizationDAO = new GpOrganizationDAOImpl();

	private Integer billIdForAllTest = null;
	private Integer createdBillId = null;

	private GpPhase phaseTest;
	private GpProjectManager empTest;
	private GpOrganization orgTest;
	private GpProject pjTest;

	@Test
	public void testCreateBillWithSuccess() {

		if (!Objects.isNull(this.billIdForAllTest)) {
			this.billDAO.deleteById(this.billIdForAllTest);
		}

		// Given
		GpBill bill = new GpBill();
		Assert.assertNull(bill.getId());

		bill.setBillCode("Bill-1");
		bill.setAmount(623.66);
		bill.setBillStatus("PAID");
		bill.setGpPhase(this.phaseTest);
		// When
		bill = billDAO.create(bill);
		// Then
		this.createdBillId = bill.getId();
		Assert.assertNotNull(bill.getId());
	}

	@Test
	public void testFindAllBillsWithSuccess() {
		// Given
		// When
		List<GpBill> bills = this.billDAO.findAll();
		// Then
		Assert.assertTrue(bills.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer billId = this.billIdForAllTest;
		// When
		GpBill bill = this.billDAO.findById(billId);
		// Then
		Assert.assertNotNull(bill.getId());
	}

	@Test
	public void testDeleteBillWithSuccess() {
		Integer billId = this.billIdForAllTest;
		// When
		this.billDAO.deleteById(billId);
		// Then
		GpPhase bill = this.phaseDAO.findById(billId);
		Assert.assertNull(bill);
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

		// création gp_bill
		GpBill bill = new GpBill();
		bill.setBillCode("CA 526");
		bill.setBillStatus("PAID");
		bill.setAmount(8695.32);
		bill.setGpPhase(this.phaseTest);

		bill = this.billDAO.create(bill);
		Assert.assertNotNull(bill.getId());

		this.billIdForAllTest = bill.getId();
	}

	@After
	public void deleteAllEntityAfter() {

		this.billDAO.deleteById(this.billIdForAllTest);

		if (!Objects.isNull(this.createdBillId)) {
			this.billDAO.deleteById(this.createdBillId);
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
