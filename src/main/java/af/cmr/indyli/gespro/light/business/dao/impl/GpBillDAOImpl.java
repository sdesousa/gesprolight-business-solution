package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpBillDAO;
import af.cmr.indyli.gespro.light.business.entity.GpBill;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;

public class GpBillDAOImpl implements IGpBillDAO {

	private GpEntityManager entityManager = new GpEntityManager();

	@Override
	public GpBill create(GpBill bill) {
		String REQ_SQL = "INSERT INTO GP_BILL (BILL_CODE, AMOUNT, BILL_STATUS, PHASE_ID) VALUES (?, ?, ?, ?)";
		Object[] tabParam = { bill.getBillCode(), bill.getAmount(), bill.getBillStatus(), bill.getGpPhase().getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
		Integer billId = entityManager.findIdByAnyColumn("GP_BILL", "BILL_CODE", bill.getBillCode(), "BILL_ID");
		bill.setId(billId);
		return bill;
	}

	@Override
	public void update(GpBill bill) {
		String REQ_SQL = "UPDATE GP_BILL SET BILL_CODE= ?,AMOUNT= ?,BILL_STATUS= ?,PHASE_ID= ? WHERE BILL_ID= ?";
		Object[] tabParam = { bill.getBillCode(), bill.getAmount(), bill.getBillStatus(), bill.getGpPhase().getId(),
				bill.getId() };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public List<GpBill> findAll() {
		String REQ_SQL = "SELECT * FROM GP_BILL";
		ResultSet resultat = this.entityManager.exec(REQ_SQL);
		List<GpBill> billList = new ArrayList<GpBill>();
		if (resultat != null) {
			try {
				while (resultat.next()) {
					GpBill foundBill = new GpBill();

					Integer id = resultat.getInt("BILL_ID");
					String billCode = resultat.getString("BILL_CODE");
					double amount = resultat.getDouble("AMOUNT");
					String status = resultat.getString("BILL_STATUS");
					Integer phaseId = resultat.getInt("PHASE_ID");

					GpPhaseDAOImpl dao = new GpPhaseDAOImpl();
					GpPhase phase = new GpPhase();
					phase = dao.findById(phaseId);

					foundBill.setId(id);
					foundBill.setAmount(amount);
					foundBill.setBillCode(billCode);
					foundBill.setBillStatus(status);
					foundBill.setGpPhase(phase);

					billList.add(foundBill);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return billList;
	}

	@Override
	public void deleteById(Integer billId) {
		String REQ_SQL = "DELETE FROM GP_BILL WHERE BILL_ID = ?";
		Object[] tabParam = { billId };
		this.entityManager.updateAvecParamGenerique(REQ_SQL, tabParam);
	}

	@Override
	public GpBill findById(Integer billId) {
		String REQ_SQL = "SELECT * FROM GP_BILL where BILL_ID = ?";
		Object[] tabParam = { billId };
		ResultSet resultat = this.entityManager.selectAvecParamGenerique(REQ_SQL, tabParam);
		GpBill foundBill = null;
		if (resultat != null) {
			try {
				while (resultat.next()) {

					String billCode = resultat.getString("BILL_CODE");
					double amount = resultat.getDouble("AMOUNT");
					String status = resultat.getString("BILL_STATUS");
					Integer phaseId = resultat.getInt("PHASE_ID");
					foundBill = new GpBill();
					GpPhaseDAOImpl dao = new GpPhaseDAOImpl();
					GpPhase phase = new GpPhase();
					phase = dao.findById(phaseId);

					foundBill.setId(billId);
					foundBill.setAmount(amount);
					foundBill.setBillCode(billCode);
					foundBill.setBillStatus(status);
					foundBill.setGpPhase(phase);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return foundBill;
	}
}
