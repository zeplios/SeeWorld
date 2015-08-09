package edu.tju.ina.seeworld.logic;

import java.util.List;

import edu.tju.ina.seeworld.dao.user.IAcademyDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Academy;

public class AcademyLogic implements IAcademyLogic {
	private IAcademyDAO academyDao;

	public IAcademyDAO getAcademyDao() {
		return academyDao;
	}

	public void setAcademyDao(IAcademyDAO academyDao) {
		this.academyDao = academyDao;
	}

	public void addAcademy(Academy academy) throws SeeWorldException {
		academyDao.save(academy);
	}

	public void deleteAcademy(Academy academy) throws SeeWorldException {
		academyDao.delete(academy);
	}

	public List<Academy> showAcademyList() throws SeeWorldException {
		List<Academy> list = academyDao.findAll(" order by "+IAcademyDAO.NAME,true);
		return list;

	}

	public void updateAcademy(Academy academy) {
		// TODO update academy
	}

}
