package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpTechnicianDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpTechnicianDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpTechnician;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpTechnicianService;

public class GpTechnicianServiceImpl implements IGpTechnicianService<GpTechnician> {

	private IGpTechnicianDAO empDAO = new GpTechnicianDAOImpl();

	public GpTechnician create(GpTechnician emp) throws GesproBusinessException {
		if (this.empDAO.ifEmpExistByFileNumberOrEmail(emp.getFileNumber(), emp.getEmail(), emp.getLogin())) {
			throw new GesproBusinessException(
					String.format("Un employee existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",
							emp.getEmail(), emp.getLogin(), emp.getFileNumber()));
		}
		return this.empDAO.create(emp);
	}

	public void update(GpTechnician emp) throws GesproBusinessException {

		GpTechnician existEmpl = this.empDAO.findById(emp.getId());
		if (existEmpl.getId() == null) {
			throw new GesproBusinessException(
					String.format("le Technician que vous voulez mettre en jour n'existe pas "));
		}
		this.empDAO.update(emp);

	}

	public List<GpTechnician> findAll() {
		return this.empDAO.findAll();
	}

	public void deleteById(Integer empId) throws GesproBusinessException {
		GpTechnician existEmpl = this.empDAO.findById(empId);
		if (existEmpl == null) {
			throw new GesproBusinessException(String.format("le Technician que vous voulez supprimer n'existe pas "));
		}
		this.empDAO.deleteById(empId);
	}

	public GpTechnician findById(Integer empId) {
		return this.empDAO.findById(empId);
	}

}
