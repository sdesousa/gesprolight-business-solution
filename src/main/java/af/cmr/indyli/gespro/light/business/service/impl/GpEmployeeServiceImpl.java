package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmployeeDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;

public class GpEmployeeServiceImpl implements IGpEmployeeService<GpEmployee>{

	private IGpEmployeeDAO<GpEmployee> empDAO = new GpEmployeeDAOImpl();
	
	public GpEmployee create(GpEmployee emp) throws GesproBusinessException{
		if (this.empDAO.ifEmpExistByFileNumberOrEmail(emp.getFileNumber(), emp.getEmail(), emp.getLogin())) {
			throw new GesproBusinessException(String.format("Un employee existe deja avec cet email[%s] ou ce login[%s] ou ce matricule[%s]",emp.getEmail(),emp.getLogin(),emp.getFileNumber()));
		}
		return this.empDAO.create(emp);
	}

	public void update(GpEmployee emp) throws GesproBusinessException{
		
	}

	public List<GpEmployee> findAll() {
		return this.empDAO.findAll();
	}

	public void deleteById(Integer empId) {
		this.empDAO.deleteById(empId);
	}

	public GpEmployee findById(Integer empId) {
		return this.empDAO.findById(empId);
	}

}
