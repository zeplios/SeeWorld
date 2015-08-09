package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.tju.ina.seeworld.dao.common.IRbacDAO;
import edu.tju.ina.seeworld.dao.rbac.ResourceDAO;
import edu.tju.ina.seeworld.dao.resource.IAreaAndCountryDAO;
import edu.tju.ina.seeworld.dao.resource.IMultimediaDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;

public class ORMTestAreaAndCountry {
	ApplicationContext ctx;
	IMultimediaDAO multimediaDao;
	IAreaAndCountryDAO areaAndCountryDao;
	IRbacDAO<Resource> resourceDao;

	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("orm_seeworld.xml");
		multimediaDao = (IMultimediaDAO) ctx.getBean("multimediaDao");
		areaAndCountryDao = (IAreaAndCountryDAO) ctx
				.getBean("areaAndCountryDao");
		resourceDao = (ResourceDAO) ctx.getBean("resourceDao");
	}

	@Test
	public void getCertainTypeList() throws SeeWorldException {
		System.out.println("find Movie area country list:");
		Resource resource = resourceDao
				.findByName("MOVIE");
		List<AreaAndCountry> list = areaAndCountryDao
				.findCertainResourceAreaAndCountryList(resource);
		for (AreaAndCountry o : list) {
			System.out.println(o.getName());
		}
	}
}
