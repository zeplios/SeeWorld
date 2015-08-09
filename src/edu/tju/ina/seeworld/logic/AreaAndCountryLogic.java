package edu.tju.ina.seeworld.logic;

import java.io.Serializable;
import java.util.List;

import edu.tju.ina.seeworld.dao.resource.IAreaAndCountryDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.util.IDAssistant;

public class AreaAndCountryLogic {
	private IAreaAndCountryDAO areaAndCountryDao;
	private IDAssistant iDAssistant;

	public void setAreaAndCountryDao(IAreaAndCountryDAO areaAndCountryDao) {
		this.areaAndCountryDao = areaAndCountryDao;
	}
	
	public AreaAndCountry findById(Serializable id) throws SeeWorldException{
		return areaAndCountryDao.findById(id);
	}

	public void setiDAssistant(IDAssistant iDAssistant) {
		this.iDAssistant = iDAssistant;
	}

	public List<AreaAndCountry> getAllAreaAndCountryList()
			throws SeeWorldException {
		return areaAndCountryDao.findAll(" order by "+IAreaAndCountryDAO.NAME,true);
	}

	public List<AreaAndCountry> getAllAreaAndCountryListForCertainResource(
			String resourceName) throws SeeWorldException {
		String resourceId = iDAssistant.getResourceID(resourceName);
		Resource res = new Resource();
		res.setId(resourceId);
		res.setName(resourceName);
		return areaAndCountryDao.findCertainResourceAreaAndCountryList(res);
	}
	
	public List<AreaAndCountry> getAreaAndCountryByPage(int offset, int length) throws SeeWorldException{
		return areaAndCountryDao.findByPage(offset, length);
	}
	
	public void save(AreaAndCountry areaAndCountry) throws SeeWorldException {
		areaAndCountryDao.save(areaAndCountry);
	}

	public void delete(Integer id) throws SeeWorldException {
		areaAndCountryDao.delete(areaAndCountryDao.findById(id));
	}

	public void update(AreaAndCountry areaAndCountry) throws SeeWorldException {
		areaAndCountryDao.update(areaAndCountry);
	}
	public Integer getCount() throws SeeWorldException{
		return areaAndCountryDao.getCount(null);
	}
	
}
