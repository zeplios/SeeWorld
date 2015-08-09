package test;

import java.util.List;
import java.util.Set;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import edu.tju.ina.seeworld.dao.common.IRbacDAO;
import edu.tju.ina.seeworld.dao.rbac.OperationDAO;
import edu.tju.ina.seeworld.dao.rbac.PermissionDAO;
import edu.tju.ina.seeworld.dao.rbac.ResourceDAO;
import edu.tju.ina.seeworld.dao.rbac.RoleDAO;
import edu.tju.ina.seeworld.dao.resource.IActorDAO;
import edu.tju.ina.seeworld.dao.resource.IDirectorDAO;
import edu.tju.ina.seeworld.dao.resource.ISerialDAO;
import edu.tju.ina.seeworld.dao.resource.ISingleSerialDAO;
import edu.tju.ina.seeworld.dao.user.IFriendDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Operation;
import edu.tju.ina.seeworld.po.rbac.Permission;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.rbac.Role;
import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.Director;
import edu.tju.ina.seeworld.po.resource.Movie;
import edu.tju.ina.seeworld.po.user.Friend;
import edu.tju.ina.seeworld.po.user.User;

public class ORMTest extends AbstractTransactionalDataSourceSpringContextTests {

	User user;
	Role role;
	Permission perm;
	Operation oper;
	Resource res;

	IUserDAO userDao;
	IRbacDAO<Role> roleDao;
	IRbacDAO<Permission> pDao;
	IRbacDAO<Operation> oDao;
	IRbacDAO<Resource> resDao;
	ISerialDAO serialDao;
	ISingleSerialDAO singleSerialDao;

	protected String[] getConfigLocations() {
		return new String[] { "orm_seeworld.xml" };
	}

	protected void onSetUpInTransaction() throws Exception {
		userDao = (IUserDAO) this.applicationContext.getBean("userDao");
		roleDao = (RoleDAO) this.applicationContext.getBean("roleDao");
		pDao = (PermissionDAO) this.applicationContext
				.getBean("permissionDao");
		oDao = (OperationDAO) this.applicationContext.getBean("operationDao");
		resDao = (ResourceDAO) this.applicationContext.getBean("resourceDao");
		singleSerialDao = (ISingleSerialDAO) this.applicationContext
				.getBean("singleSerialDao");
		serialDao = (ISerialDAO) this.applicationContext.getBean("serialDao");
	}

	protected void onTearDownInTransaction() {
		userDao = null;
		roleDao = null;
		pDao = null;
		oDao = null;
		resDao = null;
		singleSerialDao = null;
		serialDao = null;
	}

	public void testActorDirectorFindAllAND() throws SeeWorldException {
		IActorDAO actorDao = (IActorDAO) this.applicationContext
				.getBean("actorDao");
		IDirectorDAO dDao = (IDirectorDAO) this.applicationContext
				.getBean("directorDao");
		long start = System.currentTimeMillis();
		System.out.println("###############全部演员信息：");
		for (Actor a : actorDao.findAll("", false)) {
			System.out.println("姓名：" + a.getName() + " " + a.getAlias());
			System.out.println("地区：" + a.getAreaAndCountry().getName());
			System.out.println("主演影片：");
			Set<Movie> mvs = a.getMovies();
			for (Movie m : mvs) {
				System.out.println("\t" + m.getTitle());
			}
		}
		System.out.println("###############全部导演信息：");
		for (Director d : dDao.findAll("", false)) {
			System.out.println("姓名：" + d.getName() + " " + d.getAlias());
			System.out.println("地区：" + d.getAreaAndCountry().getName());
			System.out.println("导演影片：");
			for (Movie m : d.getMovies()) {
				System.out.println("\t" + m.getTitle());
			}
		}
		System.out.println("time " + (System.currentTimeMillis() - start)
				/ 1000.00);

	}

	public void testSave() throws SeeWorldException {
		saveRole();
		saveRes();
		saveOper();
	}

	public void saveOper() throws SeeWorldException {
		oper = new Operation("MODIFY");
		oDao.save(oper);
		oper = new Operation("UPLOAD");
		oDao.save(oper);
		oper = new Operation("DELETE");
		oDao.save(oper);
		oper = new Operation("READ");
		oDao.save(oper);
	}

	public void saveRes() throws SeeWorldException {
		res = new Resource("MOVIE");
		resDao.save(res);
		res = new Resource("VIDEO");
		resDao.save(res);
		res = new Resource("PLAY");
		resDao.save(res);
		res = new Resource("BOOK");
		resDao.save(res);
		res = new Resource("DWARE");
		resDao.save(res);
		res = new Resource("VWARE");
		resDao.save(res);
	}

	public void saveRole() throws SeeWorldException {
		role = new Role("EDITOR");
		roleDao.save(role);
		role = new Role("ASHARER");
		roleDao.save(role);
		role = new Role("VSHARER");
		roleDao.save(role);
		role = new Role("DSHARER");
		roleDao.save(role);
		role = new Role("USER");
		roleDao.save(role);
	}

	public void testPermissionDAO() throws SeeWorldException {
		perm = new Permission();
		perm.setName("ALL");
		pDao.save(perm);
		for (Resource r : resDao.findAll("", false)) {
			for (Operation o : oDao.findAll("", false)) {
				perm = new Permission();
				perm.setOperation(o);
				perm.setResource(r);
				perm.setName(o.getName() + "_" + r.getName());
				pDao.save(perm);
			}
		}
	}


	public void addUser() throws SeeWorldException {
		for (int i = 0; i < 10; i++) {
			user = new User("test" + i, "123", "test" + i, "test" + i
					+ "@126.com", i + "");
			user.setRole(roleDao.findByName("ROLE_COMMON"));
			System.out.println(userDao.save(user));
		}
	}

	public void delUser() throws SeeWorldException {
		List<User> users = userDao
				.findByRole(roleDao.findByName("ROLE_COMMON"));
		System.out.println(users.size());
		for (User s : users) {
			System.out.println("to delete " + s);
			userDao.delete(s);
		}

	}

	public void addFriend() throws SeeWorldException {
		List<User> users = userDao
				.findByRole(roleDao.findByName("ROLE_COMMON"));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < Math.round((Math.random() * 10)); j++) {
				User offer = users.get(i);
				User theOther = users.get((int) Math.round(Math.random() * 9));
				Friend f = null;
				try {
					f = new Friend(offer, theOther);
				} catch (RuntimeException e) {
					continue;
				}
				IFriendDAO fDao = (IFriendDAO) this.applicationContext
						.getBean("friendDao");
				fDao.save(f);
			}
		}
	}

	public void getFriend() throws SeeWorldException {
		user = userDao.findByUserName("test0").get(0);
		System.out.println("user test0's friends list:");
		for (User u : user.getAllFriends()) {
			System.out.println(u.getUsername());
		}
	}

	public void operfindAll() throws SeeWorldException {
		List<Operation> opers = oDao.findAll("", false);
		for (Operation o : opers) {
			System.out.println("Operation NAME:" + o.getName());
			System.out.println("Operation ID:" + o.getId());
		}
	}

	public void rolefindAll() throws SeeWorldException {
		List<Role> roles = roleDao.findAll("", false);
		for (Role r : roles) {
			System.out.println("ROLE NAME: " + r.getName());
			System.out.println("ROLE ID: " + r.getId());
			for (User u : r.getUsers()) {
				System.out.println("\tROLE USER:  " + u.getUsername());
			}
		}
	}

	public void resfindAll() throws SeeWorldException {
		List<Resource> ress = resDao.findAll("", false);
		for (Resource r : ress) {
			System.out.println("Resource NAME:" + r.getName());
			System.out.println("Resource ID:" + r.getId());
		}
	}

	public void findResNOpe() throws SeeWorldException {
		for (Permission p : pDao.findAll("", false)) {
			System.out.println("Permission name:" + p.getName());
			System.out.println("Permission resource:" + p.getResource());
			System.out.println("Permission Operation:" + p.getOperation());
		}
		for (Resource r : resDao.findAll("", false)) {
			System.out.println("Resource name:" + r);
			for (Permission p : r.getPermissions()) {
				System.out.println("\tResource Permission:" + p.getName());
			}
		}
		for (Operation o : oDao.findAll("", false)) {
			System.out.println("Operation name:" + o);
			for (Permission p : o.getPermissions()) {
				System.out.println("\tOperation Permission:" + p.getName());
			}
		}
	}

}
