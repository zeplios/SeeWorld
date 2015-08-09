package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.tju.ina.seeworld.dao.resource.IMovieDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.Director;
import edu.tju.ina.seeworld.po.resource.Movie;

public class MultimediaTest {
	ApplicationContext ctx;
	IMovieDAO mDao;
	
	@Before
	public void setUp(){
		ctx = new ClassPathXmlApplicationContext("orm_seeworld.xml");
		mDao = (IMovieDAO) ctx.getBean("movieDao");
	}
	
	@Test
	public void printInfo() throws SeeWorldException{
		for(Movie m:mDao.findAll("",false)){
			System.out.println("电影名称："+m.getTitle());
			System.out.println("主演：");
			for(Actor a:m.getActors()){
				System.out.println("\t"+a.getName());
			}
			System.out.println("导演：");
			for(Director d:m.getDirectors()){
				System.out.println("\t"+d.getName());
			}		
		}
	}
}
