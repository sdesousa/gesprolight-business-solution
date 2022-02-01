package af.cmr.indyli.gespro.light.business.service;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;

public interface IGpProjectService {

	public GpProject create(GpProject project) throws GesproBusinessException;

	public void update(GpProject project) throws GesproBusinessException;

	public List<GpProject> findAll();

	public void deleteById(Integer projectId);

	public GpProject findById(Integer projectId);

	boolean ifProjectExistByCode(String code);
}