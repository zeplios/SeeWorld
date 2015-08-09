package edu.tju.ina.seeworld.logic;

import java.util.HashSet;
import java.util.List;

import edu.tju.ina.seeworld.dao.resource.IDirectorDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.po.resource.Director;
import edu.tju.ina.seeworld.util.enums.AreaType;

public class DirectorLogic {
	private IDirectorDAO directorDao;

	public List<Director> findAll() throws SeeWorldException {
		return directorDao.findAll(" order by " + IDirectorDAO.NAME, true);
	}

	public Integer save(Director director) throws SeeWorldException {
		return (Integer)directorDao.save(director);
	}

	public void delete(Integer id) throws SeeWorldException {
		directorDao.delete(directorDao.findById(id));
	}

	public void update(Director director) throws SeeWorldException {
		directorDao.update(director);
	}

	public IDirectorDAO getDirectorDao() {
		return directorDao;
	}

	public void setDirectorDao(IDirectorDAO directorDao) {
		this.directorDao = directorDao;
	}

	public Director findById(Integer id) throws SeeWorldException {
		return directorDao.findById(id);
	}
	
	public List<Director> findByPage(int offset, int length) throws SeeWorldException{
		return directorDao.findByPage(offset, length);
	}
	
	public List<Director> findByName(String name) throws SeeWorldException {
		String hql = "from Director as d where d.name = ?";
		return directorDao.getListByPage(hql, 0, 10, name);
	}
	
	public List<Director> search(String name) throws SeeWorldException {
		String hql = "from Director as d where d.name like ?";
		name = name + '%';
		return directorDao.getListByPage(hql, 0, 10, name);
	}
	
	public Integer getCount() throws SeeWorldException{
		return directorDao.getCount(null);
	}
	
	/**
	 * 把Director链表转化为HashSet
	 * @param dl Director链表（每个元素项只有name属性，如果表示多个人，则name是一个以空格为分隔符的字符串）
	 * @return HashSet（每一项包含完整的Director属性）
	 * @author zhfch
	 * @throws SeeWorldException
	 */
	public HashSet<Director> directorNamesToHashSet(List<Director> dl) throws SeeWorldException{
		HashSet<Director> ds = new HashSet<Director>();
		if (dl != null){
			for (int i = 0 ; i < dl.size() ; i++){
				if (dl.get(i).getName() == null)
					continue;
				String [] names = dl.get(i).getName().split(" ");
				for (String name : names){
					List<Director> list = this.findByName(name);
					if (list.size() > 0)
						ds.add(list.get(0));
					else {
						Director d = new Director();
						d.setName(name);
						d.setAreaAndCountry(new AreaAndCountry(AreaType.UN.ordinal()));
						d.setId(this.save(d));
						ds.add(d);
					}
				}
			}
		}
		return ds;
	}
}
