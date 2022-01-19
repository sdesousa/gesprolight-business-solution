package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpSecretaryDAO;
import af.cmr.indyli.gespro.light.business.entity.GpSecretary;

public class GpSecretaryDAOImpl extends GpAbstractEmployeeDAOImpl<GpSecretary> implements IGpSecretaryDAO {

	public GpSecretary create(GpSecretary emp) {
		try {
			// On demarre une transaction
			this.getEntityManager().getDbConnect().setAutoCommit(false);
			// On commence par insérer dans la table mère avant d'inserer dans la table
			// fille
			String REQ_SQL = "INSERT INTO GP_EMPLOYEE ( FILE_NUMBER,LASTNAME,FIRSTNAME,PHONE_NUMBER,PASSWORD,CREATION_DATE,EMAIL,LOGIN) VALUES (?,?,?,?,?,?,?,?)";
			Object[] tabParam = { emp.getFileNumber(), emp.getLastname(), emp.getFirstname(), emp.getPhoneNumber(),
					emp.getPassword(), new Date(), emp.getEmail(), emp.getLogin() };
			this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
			Integer empId = getEntityManager().findIdByAnyColumn("GP_EMPLOYEE", "EMAIL", emp.getEmail(), "EMP_ID");
			// On insere maintenant dans la table GP_SECRETARY
			String REQ_SQL_PM = "INSERT INTO GP_SECRETARY ( EMP_ID) VALUES (?)";
			Object[] tabParamPM = { empId };
			this.getEntityManager().updateAvecParamGenerique(REQ_SQL_PM, tabParamPM);
			emp.setId(empId);
			this.getEntityManager().getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

	public void update(GpSecretary emp) {
		String REQ_SQL = "UPDATE  GP_EMPLOYEE SET LASTNAME=? , FIRSTNAME=? , PHONE_NUMBER=? ,PASSWORD = ? ,EMAIL=? ,LOGIN=?     WHERE EMP_ID = ?";
		Object[] tabParam = { emp.getLastname(), emp.getFirstname(), emp.getPhoneNumber(), emp.getPassword(),
				emp.getEmail(), emp.getLogin(), emp.getId() };
		this.getEntityManager().updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	public List<GpSecretary> findAll() {
		String REQ_SQL = "SELECT * FROM GP_SECRETARY AS sect JOIN GP_EMPLOYEE AS emp WHERE sect.EMP_ID=emp.EMP_ID";
		ResultSet resultat = this.getEntityManager().exec(REQ_SQL);
		List<GpSecretary> empList = new ArrayList<GpSecretary>();
		if (resultat != null) {
			try {
				while (resultat.next()) {
					Integer empId = resultat.getInt("EMP_ID");
					String fileNumber = resultat.getString("FILE_NUMBER");
					String lastname = resultat.getString("LASTNAME");
					String firstname = resultat.getString("FIRSTNAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String password = resultat.getString("PASSWORD");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String email = resultat.getString("EMAIL");
					String login = resultat.getString("LOGIN");
					GpSecretary foundEmp = new GpSecretary();
					foundEmp.setId(empId);
					foundEmp.setFileNumber(fileNumber);
					foundEmp.setLastname(lastname);
					foundEmp.setFirstname(firstname);
					foundEmp.setCreationDate(creationDate);
					foundEmp.setPassword(password);
					foundEmp.setPhoneNumber(phoneNumber);
					foundEmp.setEmail(email);
					foundEmp.setLogin(login);
					empList.add(foundEmp);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}

	public GpSecretary findById(Integer empId) {
		String REQ_SQL = "SELECT * FROM GP_EMPLOYEE where EMP_ID = ?";
		Object[] tabParam = { empId };
		ResultSet resultat = this.getEntityManager().selectAvecParamGenerique(REQ_SQL, tabParam);
		GpSecretary foundEmp = null;
		if (resultat != null) {
			try {
				while (resultat.next()) {
					String fileNumber = resultat.getString("FILE_NUMBER");
					String lastname = resultat.getString("LASTNAME");
					String firstname = resultat.getString("FIRSTNAME");
					String phoneNumber = resultat.getString("PHONE_NUMBER");
					String password = resultat.getString("PASSWORD");
					Date creationDate = resultat.getDate("CREATION_DATE");
					String email = resultat.getString("EMAIL");
					String login = resultat.getString("LOGIN");
					foundEmp = new GpSecretary();
					foundEmp.setId(empId);
					foundEmp.setFileNumber(fileNumber);
					foundEmp.setLastname(lastname);
					foundEmp.setFirstname(firstname);
					foundEmp.setCreationDate(creationDate);
					foundEmp.setPassword(password);
					foundEmp.setPhoneNumber(phoneNumber);
					foundEmp.setEmail(email);
					foundEmp.setLogin(login);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return foundEmp;
	}

	@Override
	public String getCurrentTableName() {
		return "GP_SECRETARY";
	}
}
