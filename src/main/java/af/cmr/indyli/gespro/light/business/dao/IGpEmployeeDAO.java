package af.cmr.indyli.gespro.light.business.dao;

import java.util.List;

import af.cmr.indyli.gespro.light.business.entity.IEntity;

public interface IGpEmployeeDAO<Entity extends IEntity> {
	public Entity create(Entity emp);
	public void update(Entity emp);
	public List<Entity> findAll();
	public void deleteById(Integer empId); 
	public Entity findById(Integer empId);
	public boolean ifEmpExistByFileNumberOrEmail(String fileNumber,String email,String login);
}
