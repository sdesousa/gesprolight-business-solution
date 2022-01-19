package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;

public class GpPhaseDAOImpl implements IGpPhaseDAO {

	private GpEntityManager entityManager = new GpEntityManager();

	@Override
	public GpPhase create(GpPhase phase) {
		String REQ_SQL = "INSERT INTO GP_PHASE (PHASE_CODE, DESCRIPTION, START_DATE, END_DATE, AMOUNT, STATUS, IS_ENDED, CREATION_DATE, PROJECT_ID) VALUES (?,?,?,?,?,?,?,?,?)";
		Object[] tabParam = { phase.getPhaseCode(), phase.getDescription(), phase.getStartDate(), phase.getEndDate(),
				phase.getAmount(), phase.getStatus(), phase.getIsEnded(), new Date(), phase.getGpProject().getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		Integer phaseId = entityManager.findIdByAnyColumn("GP_PHASE", "PHASE_CODE", phase.getPhaseCode(), "PHASE_ID");
		phase.setId(phaseId);
		return phase;
	}

	@Override
	public void update(GpPhase phase) {
		String REQ_SQL = "UPDATE GP_PHASE SET  PHASE_CODE = ?, DESCRIPTION = ?, START_DATE = ?, END_DATE = ?, AMOUNT = ?, STATUS = ?, IS_ENDED = ?, UPDATE_DATE = ?, PROJECT_ID = ? "
				+ "WHERE PHASE_ID = ?";
		Object[] tabParam = { phase.getPhaseCode(), phase.getDescription(), phase.getStartDate(), phase.getEndDate(),
				phase.getAmount(), phase.getStatus(), phase.getIsEnded(), new Date(), phase.getGpProject().getId(),
				phase.getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpPhase> findAll() {
		String REQ_SQL = "SELECT * FROM GP_PHASE";
		ResultSet resultat = this.entityManager.exec(REQ_SQL);
		List<GpPhase> phaseList = new ArrayList<GpPhase>();
		if (resultat != null) {
			try {
				while (resultat.next()) {
					Integer phaseId = resultat.getInt("PHASE_ID");
					String phaseCode = resultat.getString("PHASE_CODE");
					String description = resultat.getString("DESCRIPTION");
					Date startDate = resultat.getDate("START_DATE");
					Date endDate = resultat.getDate("END_DATE");
					Double amount = resultat.getDouble("AMOUNT");
					Byte status = resultat.getByte("STATUS");
					Byte isEnded = resultat.getByte("IS_ENDED");
					Date creationDate = resultat.getDate("CREATION_DATE");
					Date updateDate = resultat.getDate("UPDATE_DATE");
					Integer projectId = resultat.getInt("PROJECT_ID");

					GpProject project = new GpProject();
					GpProjectDAOImpl projectDAOImpl = new GpProjectDAOImpl();
					project = projectDAOImpl.findById(projectId);

					GpPhase foundPhase = new GpPhase();
					foundPhase.setId(phaseId);
					foundPhase.setPhaseCode(phaseCode);
					foundPhase.setDescription(description);
					foundPhase.setStartDate(startDate);
					foundPhase.setEndDate(endDate);
					foundPhase.setAmount(amount);
					foundPhase.setStatus(status);
					foundPhase.setCreationDate(creationDate);
					foundPhase.setUpdateDate(updateDate);
					foundPhase.setIsEnded(isEnded);
					foundPhase.setGpProject(project);

					phaseList.add(foundPhase);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return phaseList;
	}

	@Override
	public void deleteById(Integer phaseId) {
		String REQ_SQL = "DELETE FROM GP_PHASE WHERE PHASE_ID = ?";
		Object[] tabParam = { phaseId };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public GpPhase findById(Integer phaseId) {
		String REQ_SQL = "SELECT * FROM GP_PHASE where PHASE_ID = ?";
		Object[] tabParam = { phaseId };
		ResultSet resultat = this.entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		GpPhase foundPhase = null;
		if (resultat != null) {
			try {
				while (resultat.next()) {
					String phaseCode = resultat.getString("PHASE_CODE");
					String description = resultat.getString("DESCRIPTION");
					Date startDate = resultat.getDate("START_DATE");
					Date endDate = resultat.getDate("END_DATE");
					Double amount = resultat.getDouble("AMOUNT");
					Byte status = resultat.getByte("STATUS");
					Byte isEnded = resultat.getByte("IS_ENDED");
					Date creationDate = resultat.getDate("CREATION_DATE");
					Date updateDate = resultat.getDate("UPDATE_DATE");
					Integer projectId = resultat.getInt("PROJECT_ID");

					GpProject project = new GpProject();
					GpProjectDAOImpl projectDAOImpl = new GpProjectDAOImpl();
					project = projectDAOImpl.findById(projectId);
					foundPhase = new GpPhase();
					foundPhase.setId(phaseId);
					foundPhase.setPhaseCode(phaseCode);
					foundPhase.setDescription(description);
					foundPhase.setStartDate(startDate);
					foundPhase.setEndDate(endDate);
					foundPhase.setAmount(amount);
					foundPhase.setStatus(status);
					foundPhase.setCreationDate(creationDate);
					foundPhase.setUpdateDate(updateDate);
					foundPhase.setIsEnded(isEnded);
					foundPhase.setGpProject(project);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return foundPhase;
	}
}
