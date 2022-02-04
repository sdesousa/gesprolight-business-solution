package af.cmr.indyli.gespro.light.business.dao.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.IEntity;

public abstract class GpAbstractEmployeeDAOImpl<Entity extends IEntity> implements IGpEmployeeDAO<Entity>{

	private GpEntityManager entityManager = new GpEntityManager();
	@Override
	public Entity create(Entity emp) { 
		return null;
	}

	@Override
	public void update(Entity emp) {
		
	}

	@Override
	public List<Entity> findAll() {
		return null;
	}

	@Override
	public void deleteById(Integer empId) {
		String REQ_SQL = "DELETE FROM "+this.getCurrentTableName()+" WHERE EMP_ID = ?";
    	Object[] tabParam = {empId};
    	this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
    	//On supprime ensuite dans la table mere
    	if (!this.getCurrentTableName().equals(GpEmployee.GP_EMPLOYEE_TABLE_NAME)) {
    		String REQ_SQL_EMP = "DELETE FROM "+ GpEmployee.GP_EMPLOYEE_TABLE_NAME +" WHERE EMP_ID = ?";
        	this.getEntityManager().updateAvecParamGenerique(REQ_SQL_EMP, tabParam);
    	}
    	
	}

	@Override
	public Entity findById(Integer empId) {
		return null;
	}

	@Override
	public boolean ifEmpExistByFileNumberOrEmail(String fileNumber, String email,String login) {
		Integer empIdForEmail = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "EMAIL", email, "EMP_ID");
		Integer empIdForFileNumber = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "FILE_NUMBER", fileNumber, "EMP_ID");
		Integer empIdForLogin = this.entityManager.findIdByAnyColumn("GP_EMPLOYEE", "LOGIN", login, "EMP_ID");
		return empIdForEmail != null || empIdForFileNumber != null || empIdForLogin != null;
	}

	public GpEntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(GpEntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public abstract String getCurrentTableName();

}
