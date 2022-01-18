package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpProject;

public class GpProjectDAOImpl implements IGpProjectDAO {

	private GpEntityManager entityManager = new GpEntityManager();

	@Override
	public GpProject create(GpProject project) {
		String REQ_SQL = "INSERT INTO GP_PROJECT ( PROJECT_CODE, NAME, DESCRIPTION, START_DATE, END_DATE, AMOUNT, CREATION_DATE, ORG_ID, EMP_ID) VALUES (?,?,?,?,?,?,?,?,?)";
		Object[] tabParam = { project.getProjectCode(), project.getName(), project.getDescription(),
				project.getStartDate(), project.getEndDate(), project.getAmount(), new Date(),
				project.getGpOrganization().getId(), project.getGpChefProjet().getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		Integer projectId = entityManager.findIdByAnyColumn("GP_PROJECT", "PROJECT_CODE", project.getProjectCode(),
				"PROJECT_ID");
		project.setId(projectId);
		return project;
	}

	@Override
	public void update(GpProject project) {
		String REQ_SQL = "UPDATE GP_PROJECT ,PROJECT_CODE=?,NAME=?,DESCRIPTION=?,START_DATE=?,END_DATE=?,AMOUNT=?,UPDATE_DATE=?,ORG_ID=?,EMP_ID=?  WHERE PROJECT_ID = ?";
		Object[] tabParam = { project.getProjectCode(), project.getName(), project.getDescription(),
				project.getStartDate(), project.getEndDate(), project.getAmount(), new Date(),
				project.getGpOrganization(), project.getGpChefProjet(), project.getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpProject> findAll() {
		String REQ_SQL = "SELECT * FROM GP_PROJECT";
		ResultSet resultat = this.entityManager.exec(REQ_SQL);
		List<GpProject> projectList = new ArrayList<GpProject>();
		if (resultat != null) {
			try {
				while (resultat.next()) {
					Integer projectId = resultat.getInt("PROJECT_ID");
					String projectCode = resultat.getString("PROJECT_CODE");
					String projectName = resultat.getString("NAME");
					String projectDescription = resultat.getString("DESCRIPTION");
					Date projectStartDate = resultat.getDate("START_DATE");
					Date projectEndDate = resultat.getDate("END_DATE");
					double amount = resultat.getDouble("AMOUNT");
					Date creationDate = resultat.getDate("CREATION_DATE");
					Date updateDate = resultat.getDate("CREATION_DATE");
					//Integer orgId = resultat.getInt("ORG_ID");
					Integer empId = resultat.getInt("EMP_ID");
					GpProject foundProject = new GpProject();

					foundProject.setId(projectId);
					foundProject.setProjectCode(projectCode);
					foundProject.setName(projectName);
					foundProject.setDescription(projectDescription);
					foundProject.setStartDate(projectStartDate);
					foundProject.setEndDate(projectEndDate);
					foundProject.setAmount(amount);
					foundProject.setCreationDate(creationDate);
					foundProject.setUpdateDate(updateDate);
					foundProject.setGpOrganization(new GpOrganization());

					GpProjectManagerDAOImpl projectManagerDAOImpl = new GpProjectManagerDAOImpl();
					foundProject.setGpChefProjet(projectManagerDAOImpl.findById(empId));

					projectList.add(foundProject);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return projectList;
	}

	@Override
	public void deleteById(Integer projectId) {
		String REQ_SQL = "DELETE FROM GP_PROJECT WHERE PROJECT_ID = ?";
		Object[] tabParam = { projectId };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public GpProject findById(Integer projectId) {
		String REQ_SQL = "SELECT * FROM GP_PROJECT where PROJECT_ID = ?";
		Object[] tabParam = { projectId };
		ResultSet resultat = this.entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		GpProject foundProject = null;
		if (resultat != null) {
			try {
				while (resultat.next()) {
					String projectCode = resultat.getString("PROJECT_CODE");
					String projectName = resultat.getString("NAME");
					String projectDescription = resultat.getString("DESCRIPTION");
					Date projectStartDate = resultat.getDate("START_DATE");
					Date projectEndDate = resultat.getDate("END_DATE");
					double amount = resultat.getDouble("AMOUNT");
					Date creationDate = resultat.getDate("CREATION_DATE");
					Date updateDate = resultat.getDate("CREATION_DATE");
					//Integer orgId = resultat.getInt("ORG_ID");
					Integer empId = resultat.getInt("EMP_ID");
					foundProject = new GpProject();
					foundProject.setId(projectId);
					foundProject.setProjectCode(projectCode);
					foundProject.setName(projectName);
					foundProject.setDescription(projectDescription);
					foundProject.setStartDate(projectStartDate);
					foundProject.setEndDate(projectEndDate);
					foundProject.setAmount(amount);
					foundProject.setCreationDate(creationDate);
					foundProject.setUpdateDate(updateDate);
					foundProject.setGpOrganization(new GpOrganization());

					GpProjectManagerDAOImpl projectManagerDAOImpl = new GpProjectManagerDAOImpl();
					foundProject.setGpChefProjet(projectManagerDAOImpl.findById(empId));
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return foundProject;
	}

}
