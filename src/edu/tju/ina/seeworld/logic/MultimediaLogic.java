package edu.tju.ina.seeworld.logic;

import java.util.Collection;
import java.util.List;

import edu.tju.ina.seeworld.dao.resource.IMultimediaDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Multimedia;
import edu.tju.ina.seeworld.vo.SimpleMultimedia;

public class MultimediaLogic implements IMultimediaLogic {
	private IMultimediaDAO multimediaDao;

	public void setMultimediaDao(IMultimediaDAO multimediaDao) {
		this.multimediaDao = multimediaDao;
	}

	public void delete(Multimedia m) throws SeeWorldException {
		multimediaDao.delete(m);
	}

	public void deleteAll(Collection<? extends Multimedia> multimedias) throws SeeWorldException {
		// TODO Auto-generated method stub
		multimediaDao.deleteAll(multimedias);
	}

	public List<? extends Multimedia> listAllMultimedia()
			throws SeeWorldException {
		return multimediaDao.findAll(" order by "+IMultimediaDAO.TITLE,true);
	}

	public void upload(Multimedia m) {
		// TODO Auto-generated method stub

	}
	
	public void update(Multimedia m) throws SeeWorldException{
		multimediaDao.update(m);
	}

	public List<? extends Multimedia> getByName(String name)
			throws SeeWorldException {
		return multimediaDao.findByName(name);
	}

	public Multimedia findByIdAndResource(SimpleMultimedia multimedia)
			throws SeeWorldException {
		List<Multimedia> list = multimediaDao.findByTwoProperty(
				IMultimediaDAO.ID, multimedia.getId(),
				IMultimediaDAO.RESOURCE + ".id", multimedia.getResourceId());
		return list.get(0);
	}

	public Multimedia findByID(Integer id) throws SeeWorldException {
		return multimediaDao.findById(id);
	}
	
	public List<? extends Multimedia> findOrderedListByPropertyByPage(
			String propertyName, Object value, Object resourceId,
			boolean status, String orderColumn, int offset, int length)
			throws SeeWorldException {
		return multimediaDao.findOrderedListByPropertyByPage(propertyName, value, resourceId, status, orderColumn, offset, length);
	}
}
