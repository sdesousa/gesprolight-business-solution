package af.cmr.indyli.gespro.light.business.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpPhaseDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpPhaseDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.utils.GesProConstantes;

public class GpPhaseServiceImpl implements IGpPhaseService {

	private IGpPhaseDAO phaseDAO = new GpPhaseDAOImpl();
	private IGpProjectService projectService = new GpProjectServiceImpl();

	@Override
	public GpPhase create(GpPhase phase) throws GesproBusinessException {
		
		GpProject project = this.projectService.findById(phase.getGpProject().getId());
		phase.setGpProject(project);
		if (phase.getStartDate() != null && phase.getEndDate() != null) {

			LocalDate startDate = phase.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate endDate = phase.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Period p = Period.between(startDate, endDate);

			if (p.getMonths() > GesProConstantes.NB_PHASE_MONTH && phase.getAmount() < GesProConstantes.MIN_AMOUNT) {

				throw new GesproBusinessException(
						"Le montant de facturation d’une phase dont la durée dépasse 6 mois ne peut etre inférieure à 150.000 euros");
			}
		}

		if (phase.getStartDate() != null) {
			if (phase.getStartDate().before(phase.getGpProject().getStartDate())) {
				throw new GesproBusinessException("Une phase ne peut pas démarrer avant le projet qui lui est associé");
			}
		}

		return this.phaseDAO.create(phase);
	}

	@Override
	public void update(GpPhase phase) throws GesproBusinessException {
	}

	@Override
	public List<GpPhase> findAll() {
		return this.phaseDAO.findAll();
	}

	@Override
	public void deleteById(Integer phaseId) {
		this.phaseDAO.deleteById(phaseId);
	}

	@Override
	public GpPhase findById(Integer phaseId) {
		return this.phaseDAO.findById(phaseId);
	}

	@Override
	public List<GpPhase> findByProjectId(Integer projecId) {
		return this.phaseDAO.findByProjectId(projecId);
	}

}
