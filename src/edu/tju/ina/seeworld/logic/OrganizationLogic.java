package edu.tju.ina.seeworld.logic;

import java.io.Serializable;
import java.util.List;

import edu.tju.ina.seeworld.dao.resource.IOrganizationDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.Organization;

public class OrganizationLogic {
	private IOrganizationDAO organizationDao;

	public void setOrganizationDao(IOrganizationDAO organizationDao) {
		this.organizationDao = organizationDao;
	}
	
	public Organization findById(Serializable id) throws SeeWorldException{
		return organizationDao.findById(id);
	}

	public List<Organization> getAllOrganizationList()
			throws SeeWorldException {
		return organizationDao.findAll(" order by "+IOrganizationDAO.NAME,true);
	}

	public Organization getOrganizationById(Integer id) throws SeeWorldException {
		return organizationDao.findById(id);
	}
	
	public void save(Organization organization) throws SeeWorldException {
		organizationDao.save(organization);
	}

	public void delete(Integer id) throws SeeWorldException {
		organizationDao.delete(organizationDao.findById(id));
	}

	public void update(Organization organization) throws SeeWorldException {
		organizationDao.update(organization);
	}
	
	public List<Organization> getOrganizationByPage(int offset, int length) throws SeeWorldException{
		return organizationDao.findByPage(offset, length);
	}
	
	public Integer getCount() throws SeeWorldException{
		return organizationDao.getCount(null);
	}
	
}
