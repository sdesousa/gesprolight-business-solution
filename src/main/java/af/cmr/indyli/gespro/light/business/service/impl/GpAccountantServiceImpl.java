package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;

import af.cmr.indyli.gespro.light.business.dao.IGpAccountantDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAccountantDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAccountant;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpAccountantService;

public class GpAccountantServiceImpl implements IGpAccountantService<GpAccountant> {

	private IGpAccountantDAO empDAO = new GpAccountantDAOImpl();

	public GpAccountant create(GpAccountant emp) throws GesproBusinessException {
		if (this.empDAO.ifEmpExistByFileNumberOrEmail(emp.getFileNumber(), emp.getEmail(), emp.getLogin())) {
			throw new GesproBusinessException(
					String.format("Un employee existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",
							emp.getEmail(), emp.getLogin(), emp.getFileNumber()));
		}
		if (!EmailValidator.getInstance().isValid(emp.getEmail())) {
			throw new GesproBusinessException(
					String.format("l'email[%s] n'est pas valide, veuillez entrer le bon email ", emp.getEmail()));
		}
		if (emp.getEmail() == "" || emp.getLogin() == "" || emp.getFirstname() == "" || emp.getFileNumber() == "") {
			throw new GesproBusinessException(
					String.format("l'email, le login, le nom, le matricule sont obligatoires "));
		}
		return this.empDAO.create(emp);
	}

	public void update(GpAccountant emp) throws GesproBusinessException {

		GpAccountant existEmpl = this.empDAO.findById(emp.getId());
		if (existEmpl.getId() == null) {
			throw new GesproBusinessException(
					String.format("le Accountant que vous voulez mettre en jour n'existe pas "));
		}
		this.empDAO.update(emp);

	}

	public List<GpAccountant> findAll() {
		return this.empDAO.findAll();
	}

	public void deleteById(Integer empId) throws GesproBusinessException {
		GpAccountant existEmpl = this.empDAO.findById(empId);
		if (existEmpl == null) {
			throw new GesproBusinessException(String.format("le Accountant que vous voulez supprimer n'existe pas "));
		}
		this.empDAO.deleteById(empId);
	}

	public GpAccountant findById(Integer empId) {
		return this.empDAO.findById(empId);
	}

}
