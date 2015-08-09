package edu.tju.ina.seeworld.util;

import java.util.List;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.log4j.Logger;

import edu.tju.ina.seeworld.dao.common.IRbacDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Operation;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.rbac.Role;

/**
 * 将resource等常量以map形式存储于内存中，方便系统中查找ID及对应NAME
 * @author Uranus
 */
public class IDAssistant {
	private Logger log = Logger.getLogger(IDAssistant.class.getName());
	private IRbacDAO<Resource> resourceDao;
	private IRbacDAO<Role> roleDao;
	private IRbacDAO<Operation> operationDao;

	private static DualHashBidiMap resourceMap = new DualHashBidiMap();
	private static DualHashBidiMap roleMap = new DualHashBidiMap();
	private static DualHashBidiMap operationMap = new DualHashBidiMap();
	
	private static List<Resource> resourceList;
	private static List<Role> roleList;
	private static List<Operation> operationList;

	public void init() throws SeeWorldException {
		resourceList = resourceDao.findAll("",false);
		roleList = roleDao.findAll("",false);
		operationList = operationDao.findAll("",false);
		long currentTime = System.currentTimeMillis();
		log.info("init the resourceMap at " + currentTime);
		for (Resource r : resourceList) {
			resourceMap.put(r.getId(), r.getName());
		}
		log.info("init the roleMap at " + currentTime);
		for (Role r : roleList) {
			roleMap.put(r.getId(), r.getName());
		}
		log.info("init the operationMap at " + currentTime);
		for (Operation o : operationList) {
			operationMap.put(o.getId(), o.getDesc());
		}
	}

	public void reInitResourceMap() throws SeeWorldException {
		resourceList = resourceDao.findAll("",false);
		long currentTime = System.currentTimeMillis();
		log.info("reinit the resourceMap at " + currentTime);
		resourceMap.clear();
		for (Resource r : resourceList) {
			resourceMap.put(r.getId(), r.getName());
		}
	}

	public void reInitRoleMap() throws SeeWorldException {
		roleList = roleDao.findAll("",false);
		long currentTime = System.currentTimeMillis();
		log.info("reinit the resourceMap at " + currentTime);
		roleMap.clear();
		for (Role r : roleList) {
			roleMap.put(r.getId(), r.getName());
		}
	}
	
	public void reInitOperationMap() throws SeeWorldException {
		operationList = operationDao.findAll("",false);
		long currentTime = System.currentTimeMillis();
		log.info("reinit the operationMap at " + currentTime);
		operationMap.clear();
		for (Operation o : operationList) {
			operationMap.put(o.getId(), o.getDesc());
		}
	}

	public void setResourceDao(IRbacDAO<Resource> resourceDao) {
		this.resourceDao = resourceDao;
	}

	public void setRoleDao(IRbacDAO<Role> roleDao) {
		this.roleDao = roleDao;
	}

	public void setOperationDao(IRbacDAO<Operation> operationDao) {
		this.operationDao = operationDao;
	}

	public String getResourceName(String id) {
		return (String) resourceMap.get(id);
	}

	public String getResourceID(String name) {
		return (String) resourceMap.getKey(name);
	}

	public String getRoleName(String id) {
		return (String) roleMap.get(id);
	}

	public String getRoleID(String name) {
		return (String) roleMap.getKey(name);
	}
	
	public String getOperationName(Integer id) {
		return (String) operationMap.get(id);
	}
	
	public String getOperationID(String name) {
		return (String) operationMap.getKey(name);
	}

	public static List<Resource> getResourceList() {
		return resourceList;
	}

	public static List<Role> getRoleList() {
		return roleList;
	}

	public static List<Operation> getOperationList() {
		return operationList;
	}
}
