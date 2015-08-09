package edu.tju.ina.seeworld.logic;

import java.util.HashSet;
import java.util.List;

import edu.tju.ina.seeworld.dao.resource.ILecturerDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Lecturer;
import edu.tju.ina.seeworld.po.resource.Organization;

public class LecturerLogic {
	private ILecturerDAO lecturerDao;

	public List<Lecturer> findAll() throws SeeWorldException {
		return lecturerDao.findAll(" order by " + ILecturerDAO.NAME, true);
	}

	public Integer save(Lecturer lecturer) throws SeeWorldException {
		return (Integer)lecturerDao.save(lecturer);
	}

	public void delete(Integer id) throws SeeWorldException {
		lecturerDao.delete(lecturerDao.findById(id));
	}

	public Lecturer findById(Integer id) throws SeeWorldException {
		return lecturerDao.findById(id);
	}
	
	public List<Lecturer> findByName(String name) throws SeeWorldException {
		String hql = "from Lecturer as l where l.name = ?";
		return lecturerDao.getListByPage(hql, 0, 10, name);
	}
	
	public void update(Lecturer lecturer) throws SeeWorldException {
		lecturerDao.update(lecturer);
	}

	public ILecturerDAO getLecturerDao() {
		return lecturerDao;
	}

	public void setLecturerDao(ILecturerDAO lecturerDao) {
		this.lecturerDao = lecturerDao;
	}

	public List<Lecturer> search(String name) throws SeeWorldException {
		String hql = "from Lecturer as l where l.name like ?";
		name = name + '%';
		return lecturerDao.getListByPage(hql, 0, 10, name);
	}
	
	public Integer getCount() throws SeeWorldException{
		return lecturerDao.getCount(null);
	}
	
	public List<Lecturer> getLecturerByPage(int offset, int length) throws SeeWorldException{
		return lecturerDao.findByPage(offset, length);
	}
	
	/**
	 * 把Lecturer链表转化为HashSet
	 * @param ll Lecturer链表（每个元素项只有name属性，如果表示多个人，则name是一个以空格为分隔符的字符串）
	 * @return HashSet（每一项包含完整的Lecturer属性）
	 * @author zhfch
	 * @throws SeeWorldException
	 */
	public HashSet<Lecturer> lecturerNamesToHashSet(List<Lecturer> ll) throws SeeWorldException{
		HashSet<Lecturer> ls = new HashSet<Lecturer>();
		if (ll != null){
			for (int i = 0 ; i < ll.size() ; i++){
				if (ll.get(i).getName() == null)
					continue;
				String [] names = ll.get(i).getName().split(" ");
				for (String name : names){
					List<Lecturer> list = this.findByName(name);
					if (list.size() > 0)
						ls.add(list.get(0));
					else {
						Lecturer l = new Lecturer();
						l.setName(name);
						l.setOrganization(new Organization(1));
						l.setId(this.save(l));
						ls.add(l);
					}
				}
			}
		}
		return ls;
	}
}
