package af.cmr.indyli.gespro.light.business.service.impl;

import java.util.List;

import af.cmr.indyli.gespro.light.business.dao.IGpAddressDAO;
import af.cmr.indyli.gespro.light.business.dao.impl.GpAddressDAOImpl;
import af.cmr.indyli.gespro.light.business.entity.GpAddress;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpAddressService;

public class GpAddressServiceImpl implements IGpAddressService<GpAddress> {
	private IGpAddressDAO addrDAO = new GpAddressDAOImpl();

	@Override
	public GpAddress create(GpAddress addr) throws GesproBusinessException {
		GpAddress addrNew = addrDAO.create(addr);
		return addrNew;
	}

	@Override
	public void update(GpAddress addr) throws GesproBusinessException {
		addrDAO.update(addr);

	}

	@Override
	public List<GpAddress> findAll() {
		addrDAO.findAll();
		return addrDAO.findAll();
	}

	@Override
	public void deleteById(Integer addrId) throws GesproBusinessException {
		addrDAO.deleteById(addrId);

	}

	@Override
	public GpAddress findById(Integer addrId) {
		return addrDAO.findById(addrId);
	}

}
