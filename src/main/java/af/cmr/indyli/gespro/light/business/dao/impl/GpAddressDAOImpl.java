package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;

public class GpAddressDAOImpl implements IGpAddressDAO {
	private GpEntityManager entityManager = new GpEntityManager();
	private IGpOrganizationDAO organizationDAO = new GpOrganizationDAOImpl();
	private IGpEmployeeDAO<GpEmployee> empDAO = new GpEmployeeDAOImpl();

	public GpAddress create(GpAddress addr) {
		try {
			// On demarre une transaction
			this.entityManager.getDbConnect().setAutoCommit(false);
			// On commence par insérer dans la table mère avant d'inserer dans la table
			// fille
			String REQ_SQL = "INSERT INTO GP_ADDRESS ( STREET_NUMBER, STREET_LABEL, ZIP_CODE,COUNTRY, IS_MAIN, ORG_ID, EMP_ID) VALUES (?,?,?,?,?,?,?)";
			Object[] tabParam = { addr.getStreetNumber(), addr.getStreetLabel(), addr.getZipCode(), addr.getCountry(),
					addr.getIsMain(), addr.getGpOrganization().getId(), addr.getGpEmployee().getId() };
			this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);

			Integer addrId = entityManager.findIdByAnyColumn("GP_ADDRESS", "ZIP_CODE", addr.getZipCode(), "ADDRESS_ID");
			addr.setId(addrId);

			this.entityManager.getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addr;
	}

	public void update(GpAddress addr) {
		String REQ_SQL = "UPDATE  GP_ADDRESS SET STREET_NUMBER=?, STREET_LABEL = ?, ZIP_CODE = ? , COUNTRY = ? , IS_MAIN = ? , ORG_ID = ? , EMP_ID = ?   WHERE ADDRESS_ID = ?";
		Object[] tabParam = { addr.getStreetNumber(), addr.getStreetLabel(), addr.getZipCode(), addr.getCountry(),
				addr.getIsMain(), addr.getGpOrganization().getId(), addr.getGpEmployee().getId(), addr.getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	public List<GpAddress> findAll() {
		String REQ_SQL = "SELECT * FROM GP_ADDRESS";
		ResultSet resultat = this.entityManager.exec(REQ_SQL);
		List<GpAddress> addrList = new ArrayList<GpAddress>();
		if (resultat != null) {
			try {
				while (resultat.next()) {

					Integer addrId = resultat.getInt("ADDRESS_ID");
					Integer streetNumber = resultat.getInt("STREET_NUMBER");
					String streetLabel = resultat.getString("STREET_LABEL");
					Integer zipCode = resultat.getInt("ZIP_CODE");
					String country = resultat.getString("COUNTRY");
					Byte isMain = resultat.getByte("IS_MAIN");
					Integer orgId = resultat.getInt("ORG_ID");
					Integer empId = resultat.getInt("EMP_ID");
					
					GpAddress foundEmp = new GpAddress();
					foundEmp.setId(addrId);
					foundEmp.setStreetNumber(streetNumber);
					foundEmp.setStreetLabel(streetLabel);
					foundEmp.setZipCode(zipCode);
					foundEmp.setCountry(country);
					foundEmp.setIsMain(isMain);
					foundEmp.setGpOrganization(organizationDAO.findById(orgId));
					foundEmp.setGpEmployee(this.empDAO.findById(empId));
					addrList.add(foundEmp);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return addrList;
	}

	public GpAddress findById(Integer addrId) {
		String REQ_SQL = "SELECT * FROM GP_ADDRESS where ADDRESS_ID = ?";
		Object[] tabParam = { addrId };
		ResultSet resultat = entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		GpAddress foundAddress = null;
		if (resultat != null) {
			try {
				while (resultat.next()) {
					Integer id = resultat.getInt("ADDRESS_ID");
					Integer streetNumber = resultat.getInt("STREET_NUMBER");
					String streetLabel = resultat.getString("STREET_LABEL");
					Integer zipCode = resultat.getInt("ZIP_CODE");
					String country = resultat.getString("COUNTRY");
					Byte isMain = resultat.getByte("IS_MAIN");
					Integer idOrg = resultat.getInt("ORG_ID");
					Integer idEmp = resultat.getInt("EMP_ID");
					foundAddress = new GpAddress();
					foundAddress.setId(id);
					foundAddress.setStreetNumber(streetNumber);
					foundAddress.setStreetLabel(streetLabel);
					foundAddress.setZipCode(zipCode);
					foundAddress.setCountry(country);
					foundAddress.setIsMain(isMain);
					foundAddress.setGpOrganization(this.organizationDAO.findById(idOrg));
					foundAddress.setGpEmployee(this.empDAO.findById(idEmp));

				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return foundAddress;
	}

	public String getCurrentTableName() {
		return "GP_ADDRESS";
	}

	@Override
	public void deleteById(Integer addrId) {
		String REQ_SQL = "DELETE FROM GP_ADDRESS WHERE ADDRESS_ID = ?";
		Object[] tabParam = { addrId };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}
}
