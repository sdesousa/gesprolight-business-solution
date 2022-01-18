package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmpReaPhaseDAO;
import af.cmr.indyli.gespro.light.business.entity.GpEmpReaPhase;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;

public class GpEmpReaPhaseDAOImp implements IGpEmpReaPhaseDAO {

	private GpEntityManager entityManager = new GpEntityManager();

	@Override
	public GpEmpReaPhase create(GpEmpReaPhase empReaPhase) {
		String REQ_SQL = "INSERT INTO  GP_EMP_REA_PHASE (CREATION_DATE, PHASE_ID, EMP_ID ) VALUES (?, ?, ? )";
		Object[] tabParam = { new Date(), empReaPhase.getGpPhase().getId(), empReaPhase.getGpEmployee().getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		Integer empReaPhaseId = entityManager.findIdByAnyColumn("GP_EMP_REA_PHASE", "CREATION_DATE",
				empReaPhase.getCreationDate(), "ASSO_REA_ID");
		empReaPhase.setId(empReaPhaseId);
		return empReaPhase;
	}

	@Override
	public void update(GpEmpReaPhase empReaPhase) {
		String REQ_SQL = "UPDATE GP_EMP_REA_PHASE SET PHASE_ID = ?, EMP_ID = ?  WHERE ASSO_REA_ID = ?";
		Object[] tabParam = { empReaPhase.getGpPhase().getId(), empReaPhase.getGpEmployee().getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpEmpReaPhase> findAll() {
		String REQ_SQL = "SELECT * FROM GP_EMP_REA_PHASE";
		ResultSet resultat = this.entityManager.exec(REQ_SQL);
		List<GpEmpReaPhase> empReaPhaseList = new ArrayList<GpEmpReaPhase>();
		if (resultat != null) {
			try {
				while (resultat.next()) {
					GpEmpReaPhase foundEmpReaPhase = new GpEmpReaPhase();

					Date creationDate = resultat.getDate("CREATION_DATE");

					Integer id = resultat.getInt("ASSO_REA_ID");
					Integer phaseId = resultat.getInt("PHASE_ID");
					Integer empId = resultat.getInt("EMP_ID");

					GpPhaseDAOImpl dao = new GpPhaseDAOImpl();
					GpPhase phase = new GpPhase();
					phase = dao.findById(phaseId);

					GpProjectManagerDAOImpl dao1 = new GpProjectManagerDAOImpl();
					GpEmployee employee = new GpProjectManager();
					employee = dao1.findById(empId);

					foundEmpReaPhase.setId(id);
					foundEmpReaPhase.setGpPhase(phase);
					foundEmpReaPhase.setGpEmployee(employee);
					foundEmpReaPhase.setCreationDate(creationDate);

					empReaPhaseList.add(foundEmpReaPhase);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empReaPhaseList;
	}

	@Override
	public void deleteById(Integer empReaPhaseId) {
		String REQ_SQL = "DELETE FROM GP_EMP_REA_PHASE WHERE ASSO_REA_ID = ?";
		Object[] tabParam = { empReaPhaseId };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public GpEmpReaPhase findById(Integer empReaPhaseId) {
		String REQ_SQL = "SELECT * FROM GP_EMP_REA_PHASE WHERE ASSO_REA_ID = ?";
		Object[] tabParam = { empReaPhaseId };
		ResultSet resultat = this.entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		GpEmpReaPhase foundEmpReaPhase = null;
		if (resultat != null) {
			try {
				while (resultat.next()) {

					Date creationDate = resultat.getDate("CREATION_DATE");

					Integer id = resultat.getInt("ASSO_REA_ID");
					Integer phaseId = resultat.getInt("PHASE_ID");
					Integer empId = resultat.getInt("EMP_ID");

					GpPhaseDAOImpl dao = new GpPhaseDAOImpl();
					GpPhase phase = new GpPhase();
					phase = dao.findById(phaseId);

					GpProjectManagerDAOImpl dao1 = new GpProjectManagerDAOImpl();
					GpEmployee employee = new GpProjectManager();
					employee = dao1.findById(empId);
					foundEmpReaPhase = new GpEmpReaPhase();
					foundEmpReaPhase.setId(id);
					foundEmpReaPhase.setGpPhase(phase);
					foundEmpReaPhase.setGpEmployee(employee);
					foundEmpReaPhase.setCreationDate(creationDate);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return foundEmpReaPhase;
	}

}
