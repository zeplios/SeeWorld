package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Mainpage;

public class MainpageDAO extends BaseHibernateDAO<Mainpage> implements IMainpageDAO{
	
	protected void initDao() {
		// TODO Auto-generated method stub
		super.init(Mainpage.class.getName());
	}

	public List<Mainpage> findByIsScroll() throws SeeWorldException {
		// TODO Auto-generated method stub
		return findByProperty(IS_SCROLL, true);
	}

	public List<Mainpage> findByIsStatic() throws SeeWorldException {
		// TODO Auto-generated method stub
		return findByProperty(IS_STATIC, true);
	}

	public List<Mainpage> findByIsScroll(int offset, int length) throws SeeWorldException {
		// TODO Auto-generated method stub
		String hql = "from Mainpage as model where model.isScroll = ?";
		return getListByPage(hql, offset, length, true);
	}

	public List<Mainpage> findByIsStatic(int offset, int length) throws SeeWorldException {
		// TODO Auto-generated method stub
		String hql = "from Mainpage as model where model.isStatic = ?";
		return getListByPage(hql, offset, length, true);
	}
}
