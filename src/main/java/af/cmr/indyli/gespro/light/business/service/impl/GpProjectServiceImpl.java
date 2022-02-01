package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpProjectDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpProjectDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;

public class GpProjectServiceImpl implements IGpProjectService {

	private IGpProjectDAO projectDAO = new GpProjectDAOImpl();

	@Override
	public GpProject create(GpProject project) throws GesproBusinessException {

		if (project.getStartDate() == null) {
			throw new GesproBusinessException("La date de démarrage est requise");
		}

		if (project.getEndDate() != null) {
			if (project.getEndDate().before(project.getStartDate())) {
				throw new GesproBusinessException(String
						.format("La date de fin doit etre postérieur la date de début (%s)", project.getStartDate()));
			}
		}

		if (project.getGpOrganization() == null) {
			throw new GesproBusinessException("On ne peut créer un projet sans fournir l'organisme associé");
		}

		return this.projectDAO.create(project);
	}

	@Override
	public void update(GpProject project) throws GesproBusinessException {

	}

	@Override
	public List<GpProject> findAll() {
		return this.projectDAO.findAll();
	}

	@Override
	public void deleteById(Integer projectId) {
		this.projectDAO.deleteById(projectId);
	}

	@Override
	public GpProject findById(Integer projectId) {
		return this.projectDAO.findById(projectId);
	}

}
