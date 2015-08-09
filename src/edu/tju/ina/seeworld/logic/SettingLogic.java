package edu.tju.ina.seeworld.logic;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IRbacDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Setting;

public class SettingLogic {
	private IRbacDAO<Setting> settingDao;

	public List<Setting> findAll() throws SeeWorldException {
		return settingDao.findAll(" order by " + IRbacDAO.NAME, true);
	}

	public void save(Setting setting) throws SeeWorldException {
		settingDao.save(setting);
	}

	public void delete(Setting setting) throws SeeWorldException {
		settingDao.delete(setting);
	}

	public void update(Setting setting) throws SeeWorldException {
		settingDao.update(setting);
	}

	public IRbacDAO<Setting> getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(IRbacDAO<Setting> settingDao) {
		this.settingDao = settingDao;
	}

	public Integer getIntConfigValue(String name) throws SeeWorldException {
		return Integer.parseInt(settingDao.findByName(name).getValue());
	}
	
	public String getStringConfigValue(String name) throws SeeWorldException {
		return settingDao.findByName(name).getValue();
	}
	
	public void setConfigValue(String name, Object value) throws SeeWorldException {
		Setting s = settingDao.findByName(name);
		if(s == null)
			return;
		s.setValue(value.toString());
		settingDao.update(s);
	}
}
