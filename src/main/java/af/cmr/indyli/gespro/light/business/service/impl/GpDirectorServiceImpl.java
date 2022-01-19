package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpDirectorDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpDirectorDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpDirector;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpDirectorService;

public class GpDirectorServiceImpl implements IGpDirectorService<GpDirector> {

	private IGpDirectorDAO empDAO = new GpDirectorDAOImpl();

	public GpDirector create(GpDirector emp) throws GesproBusinessException {
		if (this.empDAO.ifEmpExistByFileNumberOrEmail(emp.getFileNumber(), emp.getEmail(), emp.getLogin())) {
			throw new GesproBusinessException(
					String.format("Un employee existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",
							emp.getEmail(), emp.getLogin(), emp.getFileNumber()));
		}
		return this.empDAO.create(emp);
	}

	public void update(GpDirector emp) throws GesproBusinessException {

		GpDirector existEmpl = this.empDAO.findById(emp.getId());
		if (existEmpl.getId() == null) {
			throw new GesproBusinessException(
					String.format("le Director que vous voulez mettre en jour n'existe pas "));
		}
		this.empDAO.update(emp);

	}

	public List<GpDirector> findAll() {
		return this.empDAO.findAll();
	}

	public void deleteById(Integer empId) throws GesproBusinessException {
		GpDirector existEmpl = this.empDAO.findById(empId);
		if (existEmpl == null) {
			throw new GesproBusinessException(String.format("le Director que vous voulez supprimer n'existe pas "));
		}
		this.empDAO.deleteById(empId);
	}

	public GpDirector findById(Integer empId) {
		return this.empDAO.findById(empId);
	}

}
