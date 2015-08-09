package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Mainpage;

public interface IMainpageDAO extends IBaseHibernateDAO<Mainpage>{
	public static String IS_SCROLL = "isScroll";
	public static String IS_STATIC = "isStatic";
	
	public List<Mainpage> findByIsScroll() throws SeeWorldException;
	public List<Mainpage> findByIsStatic() throws SeeWorldException;
	public List<Mainpage> findByIsScroll(int offset, int length) throws SeeWorldException;
	public List<Mainpage> findByIsStatic(int offset, int length) throws SeeWorldException;
}
