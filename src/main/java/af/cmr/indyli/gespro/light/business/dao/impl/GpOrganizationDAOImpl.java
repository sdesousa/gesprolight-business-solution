package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpOrganizationDAO;
import af.cmr.indyli.gespro.light.business.entity.GpOrganization;

public class GpOrganizationDAOImpl implements IGpOrganizationDAO {
	private GpEntityManager entityManager = new GpEntityManager();

	public GpOrganization create(GpOrganization org) {
		try {
			// On demarre une transaction
			this.entityManager.getDbConnect().setAutoCommit(false);
			// On commence par insérer dans la table mère avant d'inserer dans la table
			// fille
			String REQ_SQL = "INSERT INTO GP_ORGANIZATION ( ORG_CODE, NAME, PHONE_NUMBER,CONTACT_NAME, CONTACT_EMAIL, ADR_WEB) VALUES (?,?,?,?,?,?)";
			Object[] tabParam = { org.getOrgCode(), org.getName(), org.getPhoneNumber(), org.getContactName(),
					org.getContactEmail(), org.getAdrWeb() };
			this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);

			Integer orgId = entityManager.findIdByAnyColumn("GP_Organization", "ORG_CODE", org.getOrgCode(), "ORG_ID");
			org.setId(orgId);
			this.entityManager.getDbConnect().setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return org;
	}

	public void update(GpOrganization org) {
		String REQ_SQL = "UPDATE  GP_ORGANIZATION SET ORG_CODE=?, NAME=?, PHONE_NUMBER=?,CONTACT_NAME=?, CONTACT_EMAIL=?, ADR_WEB=?  WHERE ORG_ID=?";
		Object[] tabParam = { org.getOrgCode(), org.getName(), org.getPhoneNumber(), org.getContactName(),
				org.getContactEmail(), org.getAdrWeb(), org.getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	public List<GpOrganization> findAll() {
		String REQ_SQL = "SELECT * FROM GP_ORGANIZATION";
		ResultSet resultat = this.entityManager.exec(REQ_SQL);
		List<GpOrganization> orgList = new ArrayList<GpOrganization>();
		if (resultat != null) {
			try {
				while (resultat.next()) {

					Integer orgId = resultat.getInt("ORG_ID");
					String orgCode = resultat.getString("ORG_CODE");
					String name = resultat.getString("NAME");
					Integer phone = resultat.getInt("PHONE_NUMBER");
					String contactName = resultat.getString("CONTACT_NAME");
					String contactEmail = resultat.getString("CONTACT_EMAIL");
					String addrWeb = resultat.getString("ADR_WEB");

					GpOrganization org = new GpOrganization();
					org.setId(orgId);
					org.setOrgCode(orgCode);
					org.setName(name);
					org.setPhoneNumber(phone);
					org.setContactName(contactName);
					org.setContactEmail(contactEmail);
					org.setAdrWeb(addrWeb);
					orgList.add(org);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orgList;
	}

	public GpOrganization findById(Integer orgId) {
		String REQ_SQL = "SELECT * FROM GP_ORGANIZATION WHERE ORG_ID = ?";
		Object[] tabParam = { orgId };
		ResultSet resultat = this.entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		GpOrganization org = null;
		if (resultat != null) {
			try {
				while (resultat.next()) {
					String orgCode = resultat.getString("ORG_CODE");
					String name = resultat.getString("NAME");
					Integer phone = resultat.getInt("PHONE_NUMBER");
					String contactName = resultat.getString("CONTACT_NAME");
					String contactEmail = resultat.getString("CONTACT_EMAIL");
					String addrWeb = resultat.getString("ADR_WEB");
					org = new GpOrganization();
					org.setId(orgId);
					org.setOrgCode(orgCode);
					org.setName(name);
					org.setPhoneNumber(phone);
					org.setContactName(contactName);
					org.setContactEmail(contactEmail);
					org.setAdrWeb(addrWeb);

				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return org;
	}

	@Override
	public void deleteById(Integer orgId) {
		String REQ_SQL = "DELETE FROM GP_ORGANIZATION WHERE ORG_ID = ?";
		Object[] tabParam = { orgId };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	public String getCurrentTableName() {
		return "GP_ORGANIZATION";
	}
}
