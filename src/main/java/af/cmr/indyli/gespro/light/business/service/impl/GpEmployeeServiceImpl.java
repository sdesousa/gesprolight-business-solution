package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;

import af.cmr.indyli.gespro.light.business.dao.IGpEmployeeDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpEmployeeDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;

public class GpEmployeeServiceImpl implements IGpEmployeeService<GpEmployee> {

	private IGpEmployeeDAO<GpEmployee> empDAO = new GpEmployeeDAOImpl();

	public GpEmployee create(GpEmployee emp) throws GesproBusinessException {
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

	public void update(GpEmployee emp) throws GesproBusinessException {
		GpEmployee existEmpl = this.empDAO.findById(emp.getId());
		if (existEmpl.getId() == null) {
			throw new GesproBusinessException(String.format("l'Employee que vous voulez mettre en jour n'existe pas "));
		}
		this.empDAO.update(emp);
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
