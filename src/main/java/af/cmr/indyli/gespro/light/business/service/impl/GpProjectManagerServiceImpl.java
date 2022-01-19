 package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectManagerDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectManagerDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpProjectManagerService;

public class GpProjectManagerServiceImpl implements IGpProjectManagerService<GpProjectManager> {

	private IGpProjectManagerDAO empPMDAO = new GpProjectManagerDAOImpl();

	public GpProjectManager create(GpProjectManager empPM) throws GesproBusinessException {
		if (this.empPMDAO.ifEmpExistByFileNumberOrEmail(empPM.getFileNumber(), empPM.getEmail(), empPM.getLogin())) {
			throw new GesproBusinessException(
					String.format("Un projectManager existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",
							empPM.getEmail(), empPM.getLogin(), empPM.getFileNumber()));
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
			throw new GesproBusinessException(String.format("le ProjectManager que vous voulez supprimer n'existe pas "));
		}
		this.empPMDAO.deleteById(empPMId);
	}

	public GpProjectManager findById(Integer empPMId) {
		return this.empPMDAO.findById(empPMId);
	}

}
