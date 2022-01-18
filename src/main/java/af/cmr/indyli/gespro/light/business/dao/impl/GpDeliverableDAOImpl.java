package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpDeliverableDAO;
import af.cmr.indyli.gespro.light.business.entity.GpDeliverable;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public class GpDeliverableDAOImpl implements IGpDeliverableDAO {

	private GpEntityManager entityManager = new GpEntityManager();

	@Override
	public GpDeliverable create(GpDeliverable deliverable) {
		String REQ_SQL = "INSERT INTO GP_DELIVERABLE (DEL_CODE, LABEL, DESCRIPTION, DEL_PATH, CREATION_DATE, PHASE_ID) VALUES (?,?,?,?,?,?)";
		Object[] tabParam = { deliverable.getDelCode(), deliverable.getLabel(), deliverable.getDescription(),
				deliverable.getDelPath(), new Date(), deliverable.getGpPhase().getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		Integer deliverableId = entityManager.findIdByAnyColumn("GP_DELIVERABLE", "DEL_CODE", deliverable.getDelCode(),
				"DEL_ID");
		deliverable.setId(deliverableId);
		return deliverable;
	}

	@Override
	public void update(GpDeliverable deliverable) {
		String REQ_SQL = "UPDATE GP_DELIVERABLE SET DEL_CODE = ?,LABEL = ?, DESCRIPTION = ?, DEL_PATH = ?, PHASE_ID = ? WHERE DEL_ID = ?";
		Object[] tabParam = { deliverable.getDelCode(), deliverable.getLabel(), deliverable.getDescription(),
				deliverable.getDelPath(), deliverable.getGpPhase().getId(), deliverable.getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpDeliverable> findAll() {
		String REQ_SQL = "SELECT * FROM GP_DELIVERABLE";
		ResultSet resultat = this.entityManager.exec(REQ_SQL);
		List<GpDeliverable> deliverableList = new ArrayList<GpDeliverable>();
		if (resultat != null) {
			try {
				while (resultat.next()) {
					GpDeliverable foundDel = new GpDeliverable();

					Integer id = resultat.getInt("DEL_ID");
					String delCode = resultat.getString("DEL_CODE");
					String delPath = resultat.getString("DEL_PATH");
					String description = resultat.getString("DESCRIPTION");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String label = resultat.getString("LABEL");
					Integer phaseId = resultat.getInt("PHASE_ID");

					GpPhaseDAOImpl dao = new GpPhaseDAOImpl();
					GpPhase phase = new GpPhase();
					phase = dao.findById(phaseId);

					foundDel.setId(id);
					foundDel.setCreationDate(creationDate);
					foundDel.setDelCode(delCode);
					foundDel.setDelPath(delPath);
					foundDel.setDescription(description);
					foundDel.setLabel(label);
					foundDel.setGpPhase(phase);

					deliverableList.add(foundDel);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deliverableList;
	}

	@Override
	public void deleteById(Integer deliverableId) {
		String REQ_SQL = "DELETE FROM GP_DELIVERABLE WHERE DEL_ID = ?";
		Object[] tabParam = { deliverableId };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public GpDeliverable findById(Integer deliverableId) {
		String REQ_SQL = "SELECT * FROM GP_DELIVERABLE where DEL_ID = ?";
		Object[] tabParam = { deliverableId };
		ResultSet resultat = this.entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		GpDeliverable foundDel = null;
		if (resultat != null) {
			try {
				while (resultat.next()) {

					String delCode = resultat.getString("PROJECT_CODE");
					String delPath = resultat.getString("DEL_PATH");
					String description = resultat.getString("DESCRIPTION");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String label = resultat.getString("LABEL");
					Integer phaseId = resultat.getInt("PHASE_ID");

					GpPhaseDAOImpl dao = new GpPhaseDAOImpl();
					GpPhase phase = new GpPhase();
					phase = dao.findById(phaseId);
					foundDel  = new GpDeliverable();
					foundDel.setId(deliverableId);
					foundDel.setCreationDate(creationDate);
					foundDel.setDelCode(delCode);
					foundDel.setDelPath(delPath);
					foundDel.setDescription(description);
					foundDel.setLabel(label);
					foundDel.setGpPhase(phase);

				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return foundDel;
	}

}
