package edu.tju.ina.seeworld.logic;

import java.util.HashSet;
import java.util.List;

import edu.tju.ina.seeworld.dao.resource.IActorDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.util.enums.AreaType;

public class ActorLogic {
	private IActorDAO actorDao;
	public List<Actor> findAll() throws SeeWorldException{
		return actorDao.findAll(" order by " + IActorDAO.NAME, true);
	}
	public Integer save(Actor actor) throws SeeWorldException {
		return (Integer)actorDao.save(actor);
	}

	public void delete(Integer id) throws SeeWorldException {
		actorDao.delete(actorDao.findById(id));
	}

	public void update(Actor actor) throws SeeWorldException {
		actorDao.update(actor);
	}
	
	public void setActorDao(IActorDAO actorDao) {
		this.actorDao = actorDao;
	}
	
	public Actor findById(Integer id) throws SeeWorldException {
		return actorDao.findById(id);
	}
	
	public List<Actor> findByPage(int offset, int length) throws SeeWorldException{
		return actorDao.findByPage(offset, length);
	}
	
	public List<Actor> findByName(String name) throws SeeWorldException {
		String hql = "from Actor as a where a.name = ?";
		return actorDao.getListByPage(hql, 0, 10, name);
	}
	
	public List<Actor> search(String name) throws SeeWorldException {
		String hql = "from Actor as a where a.name like ?";
		name = name + '%';
		return actorDao.getListByPage(hql, 0, 10, name);
	}
	
	public Integer getCount() throws SeeWorldException{
		return actorDao.getCount(null);
	}
	
	/**
	 * 把ActorList链表转化为HashSet
	 * @param al Actor链表（每个元素项只有name属性，如果表示多个人，name是一个以空格为分隔符的字符串）
	 * @return HashSet（每一项包含完整的Director属性）
	 * @author zhfch
	 * @throws SeeWorldException
	 */
	public HashSet<Actor> actorNamesToHashSet(List<Actor> al) throws SeeWorldException{
		HashSet<Actor> as = new HashSet<Actor>();
		if (al != null){
			for(int i = 0 ; i < al.size() ; i++){
				if (al.get(i).getName() == null)
					continue;
				String [] names = al.get(i).getName().split(" ");
				for (String name : names){
					List<Actor> list = this.findByName(name);
					if (list.size() > 0)
						as.add(list.get(0));
					else {
						Actor a = new Actor();
						a.setName(name);
						a.setAreaAndCountry(new AreaAndCountry(AreaType.UN.ordinal()));
						a.setId(this.save(a));
						as.add(a);
					}
				}
				
			}
		}
		return as;
	}
}
