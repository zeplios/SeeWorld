package edu.tju.ina.seeworld.logic;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edu.tju.ina.seeworld.dao.resource.IMainpageDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Mainpage;

public class MainpageLogic {
	public IMainpageDAO mainpageDao;
	
	
	public List<Mainpage> findByIsScroll() throws SeeWorldException {
		// TODO Auto-generated method stub
		return mainpageDao.findByIsScroll();
	}

	public List<Mainpage> findByIsStatic() throws SeeWorldException {
		// TODO Auto-generated method stub
		return mainpageDao.findByIsStatic();
	}

	public List<Mainpage> findByIsScroll(int offset, int length)
			throws SeeWorldException {
		// TODO Auto-generated method stub
		return mainpageDao.findByIsScroll(offset, length);
	}

	public List<Mainpage> findByIsStatic(int offset, int length)
			throws SeeWorldException {
		// TODO Auto-generated method stub
		return mainpageDao.findByIsStatic(offset, length);
	}

	public IMainpageDAO getMainpageDao() {
		return mainpageDao;
	}

	public void setMainpageDao(IMainpageDAO mainpageDao) {
		this.mainpageDao = mainpageDao;
	}

	public Mainpage findById(Integer id) throws SeeWorldException {
		// TODO Auto-generated method stub
		return mainpageDao.findById(id);
	}

	public void update(Mainpage s) throws SeeWorldException {
		// TODO Auto-generated method stub
		mainpageDao.update(s);
	}

	public void save(Mainpage s) throws SeeWorldException {
		// TODO Auto-generated method stub
		Lock lock = new ReentrantLock();
		lock.lock();
		try{
			String nextId = mainpageDao.getNextAvaliableId("mainpage", 0);
			s.setId(Integer.parseInt(nextId));
			mainpageDao.save(s);
		}
		catch(Exception e){
			lock.unlock();
			System.out.println("ERROR:" + e.getMessage());
			return;
		}
		lock.unlock();
	}

	public void delete(Mainpage s) throws SeeWorldException {
		// TODO Auto-generated method stub
		mainpageDao.delete(s);
	}

}
