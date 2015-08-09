package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.tju.ina.seeworld.dao.resource.IMovieDAO;
import edu.tju.ina.seeworld.dao.resource.IMultimediaDAO;
import edu.tju.ina.seeworld.dao.resource.IVideoDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Multimedia;

public class ORMTestMultimedia {
	ApplicationContext ctx;
	IMultimediaDAO multimediaDao;
	IMovieDAO movieDao;
	IVideoDAO videoDao;

	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("orm_seeworld.xml");
		multimediaDao = (IMultimediaDAO) ctx.getBean("multimediaDao");
		movieDao = (IMovieDAO) ctx.getBean("movieDao");
		videoDao = (IVideoDAO) ctx.getBean("videoDao");
	}

	public void findAllYear() throws Exception {
		List<Multimedia> list = multimediaDao
				.findByQuery("select m.publishedYear from Multimedia m group by m.publishedYear");
		for (Object o : list.toArray()) {
			System.out.println(o);
		}
	}

	@Test
	public void getCount() throws SeeWorldException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(IMultimediaDAO.AREA + ".id", 1);
		System.out.println(movieDao.getCount(param));
	}
}
