package test;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.tju.ina.seeworld.dao.common.IRbacDAO;
import edu.tju.ina.seeworld.dao.rbac.OperationDAO;
import edu.tju.ina.seeworld.dao.rbac.PermissionDAO;
import edu.tju.ina.seeworld.dao.rbac.ResourceDAO;
import edu.tju.ina.seeworld.dao.rbac.RoleDAO;
import edu.tju.ina.seeworld.dao.resource.IActorDAO;
import edu.tju.ina.seeworld.dao.resource.IAreaAndCountryDAO;
import edu.tju.ina.seeworld.dao.resource.ICategoryDAO;
import edu.tju.ina.seeworld.dao.resource.IDirectorDAO;
import edu.tju.ina.seeworld.dao.resource.ISerialDAO;
import edu.tju.ina.seeworld.dao.resource.ISingleSerialDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.po.rbac.Operation;
import edu.tju.ina.seeworld.po.rbac.Permission;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.rbac.Role;
import edu.tju.ina.seeworld.po.user.User;

public class ORMTestBase {
	ApplicationContext ctx;
	User user;
	Role role;
	Permission perm;
	Operation oper;
	Resource res;

	IDirectorDAO directorDao;
	IActorDAO actorDao;
	IAreaAndCountryDAO areaAndCountryDao;
	ICategoryDAO categoryDao;
	IUserDAO userDao;
	IRbacDAO<Role> roleDao;
	IRbacDAO<Permission> pDao;
	IRbacDAO<Operation> oDao;
	IRbacDAO<Resource> resDao;
	ISerialDAO serialDao;
	ISingleSerialDAO singleSerialDao;
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("orm_seeworld.xml");

		userDao = (IUserDAO) ctx.getBean("userDao");
		roleDao = (RoleDAO) ctx.getBean("roleDao");
		pDao = (PermissionDAO) ctx.getBean("permissionDao");
		oDao = (OperationDAO) ctx.getBean("operationDao");
		resDao = (ResourceDAO) ctx.getBean("resourceDao");
		singleSerialDao = (ISingleSerialDAO) ctx.getBean("singleSerialDao");
		serialDao = (ISerialDAO) ctx.getBean("serialDao");
		areaAndCountryDao = (IAreaAndCountryDAO) ctx
				.getBean("areaAndCountryDao");
		categoryDao=(ICategoryDAO)ctx.getBean("categoryDao");
		actorDao = (IActorDAO) ctx.getBean("actorDao");
		directorDao = (IDirectorDAO) ctx.getBean("directorDao");
	}
	
}
