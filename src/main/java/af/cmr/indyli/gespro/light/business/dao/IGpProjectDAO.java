package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.GpProject;

public interface IGpProjectDAO {
	public GpProject create(GpProject project);
	public void update(GpProject project);
	public List<GpProject> findAll();
	public void deleteById(Integer projectId);
	public GpProject findById(Integer projectId);
	boolean ifProjectByCode(String code);
}