package af.cmr.indyli.gespro.light.business.dao.test;

import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import af.cmr.indyli.gespro.light.business.dao.IGpAccountantDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAccountantDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;

public class GpAccountantDAOTest {

	private IGpAccountantDAO empAccDAO = new GpAccountantDAOImpl();
	private Integer empIdForAllTest = null;
	private Integer createEmprId = null;

	@Test
	public void testCreateComptableWithSuccess() {
		// Given
		GpAccountant emp = new GpAccountant();
		Assert.assertNull(emp.getId());
		emp.setFileNumber("2028");
		emp.setLastname("JOBAcc");
		emp.setFirstname("JoelAcc");
		emp.setPhoneNumber("2365987865");
		emp.setPassword("myAccPassword");
		emp.setEmail("jobAcc.joelAcc@gouv.fr");
		emp.setLogin("jobAcc.joelAcc");

		// When
		emp = empAccDAO.create(emp);

		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testFindAllComptableWithSuccess() {
		// Given

		// When
		List<GpAccountant> empsAcc = this.empAccDAO.findAll();
		// Then
		Assert.assertTrue(empsAcc.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() {
		// Given
		Integer empId = this.empIdForAllTest;

		// When
		GpAccountant emp = this.empAccDAO.findById(empId);
		// Then
		Assert.assertNotNull(emp.getId());
	}

	@Test
	public void testUpdateComptable() {
		// Given
		GpAccountant emp = this.empAccDAO.findById(this.empIdForAllTest);
		emp.setPhoneNumber("0001");
		// When
		this.empAccDAO.update(emp);
		GpAccountant empUpdate = this.empAccDAO.findById(this.empIdForAllTest);
		// Then

		Assert.assertTrue(empUpdate.getPhoneNumber().equalsIgnoreCase("0001"));
	}

	@Test
	public void testDeleteComptable() {
		// Given
		Integer empId = this.empIdForAllTest;
		// When
		this.empAccDAO.deleteById(empId);
		GpAccountant emp = this.empAccDAO.findById(empId);
		// Then
		Assert.assertNull(emp);
	}

	@Before
	public void prepareAllEntityBefore() {
		GpAccountant emp = new GpAccountant();

		emp.setFileNumber("2028");
		emp.setLastname("TCTAcc2");
		emp.setFirstname("CY2Acc2");
		emp.setPhoneNumber("22658632892");
		emp.setPassword("myPasswordAcc");
		emp.setEmail("tctsec2tech2.cy@gmail.com");
		emp.setLogin("tct2Acctech2.cy");
		emp = empAccDAO.create(emp);

		this.empIdForAllTest = emp.getId();
	}

	@After
	public void deleteAllEntityAfter() {
		this.empAccDAO.deleteById(empIdForAllTest);
		if (!Objects.isNull(this.createEmprId)) {
			this.empAccDAO.deleteById(this.createEmprId);

		}
	}
}
