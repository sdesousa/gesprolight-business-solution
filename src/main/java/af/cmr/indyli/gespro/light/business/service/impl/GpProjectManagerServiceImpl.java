package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpProjectManagerService;

public class GpProjectManagerServiceImpl implements IGpProjectManagerService<GpProjectManager> {

	private IGpProjectManagerDAO empPMDAO = new GpProjectManagerDAOImpl();

	public GpProjectManager create(GpProjectManager empPM) throws GesproBusinessException {
		if (this.empPMDAO.ifEmpExistByFileNumberOrEmail(empPM.getFileNumber(), empPM.getEmail(), empPM.getLogin())) {
			throw new GesproBusinessException(String.format(
					"Un projectManager existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",
					empPM.getEmail(), empPM.getLogin(), empPM.getFileNumber()));
		}
		if (!EmailValidator.getInstance().isValid(empPM.getEmail())) {
			throw new GesproBusinessException(
					String.format("l'email[%s] n'est pas valide, veuillez entrer le bon email ", empPM.getEmail()));
		}
		if (empPM.getEmail() == "" || empPM.getLogin() == "" || empPM.getFirstname() == ""
				|| empPM.getFileNumber() == "") {
			throw new GesproBusinessException(
					String.format("l'email, le login, le nom, le matricule sont obligatoires "));
		}
		return this.empPMDAO.create(empPM);
	}

	public void update(GpProjectManager empPM) throws GesproBusinessException {

		GpProjectManager existEmpl = this.empPMDAO.findById(empPM.getId());
		if (existEmpl.getId() == null) {
			throw new GesproBusinessException(
					String.format("le ProjectManager que vous voulez mettre en jour n'existe pas "));
		}
		this.empPMDAO.update(empPM);

	}

	public List<GpProjectManager> findAll() {
		return this.empPMDAO.findAll();
	}

	public void deleteById(Integer empPMId) throws GesproBusinessException {
		GpProjectManager existEmpl = this.empPMDAO.findById(empPMId);
		if (existEmpl == null) {
			throw new GesproBusinessException(
					String.format("le ProjectManager que vous voulez supprimer n'existe pas "));
		}
		this.empPMDAO.deleteById(empPMId);
	}

	public GpProjectManager findById(Integer empPMId) {
		return this.empPMDAO.findById(empPMId);
	}

}
