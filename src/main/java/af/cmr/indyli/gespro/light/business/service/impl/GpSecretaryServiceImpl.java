package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;

import af.cmr.indyli.gespro.light.business.dao.IGpSecretaryDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpSecretaryDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpSecretary;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpSecretaryService;

public class GpSecretaryServiceImpl implements IGpSecretaryService<GpSecretary> {

	private IGpSecretaryDAO empDAO = new GpSecretaryDAOImpl();

	public GpSecretary create(GpSecretary emp) throws GesproBusinessException {
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

	public void update(GpSecretary emp) throws GesproBusinessException {

		GpSecretary existEmpl = this.empDAO.findById(emp.getId());
		if (existEmpl.getId() == null) {
			throw new GesproBusinessException(
					String.format("le Secretary que vous voulez mettre en jour n'existe pas "));
		}
		this.empDAO.update(emp);

	}

	public List<GpSecretary> findAll() {
		return this.empDAO.findAll();
	}

	public void deleteById(Integer empId) throws GesproBusinessException {
		GpSecretary existEmpl = this.empDAO.findById(empId);
		if (existEmpl == null) {
			throw new GesproBusinessException(String.format("le Secretary que vous voulez supprimer n'existe pas "));
		}
		this.empDAO.deleteById(empId);
	}

	public GpSecretary findById(Integer empId) {
		return this.empDAO.findById(empId);
	}

}
