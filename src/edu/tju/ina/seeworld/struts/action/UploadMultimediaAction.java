package edu.tju.ina.seeworld.struts.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import test.ContinueFTP;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.dao.resource.ICategoryDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.logic.UploadMovieLogic;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.user.User;

public class UploadMultimediaAction 
{
	/** moviename */
	private String name;
	
	/** movie introduction*/
	private String introduction;
	
	/** movie director*/
	private String director;
	
	/** movie actors*/
	private List<String> actors;
	
	/** movie client path*/
	private String path;
	
	/** movie style*/
	private String category;
	
	/** movie area*/
	private String area;
	
	/** when was produced*/
	private String year;
	
    private UploadMovieLogic uploadMovieLogic;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setUploadMovieLogic(UploadMovieLogic uploadMovieLogic) {
		this.uploadMovieLogic = uploadMovieLogic;
	}

	public String movieUpload() throws Exception 
	{
		
		
		String userId="402881ac277afac101277afac7a50001";
		
		uploadMovieLogic.saveMovie(category, userId, name, introduction, director, actors, area, year, path);
		
		return "movieupload";
	}

	 public String[] getPath(String path){
		 return path.split("&&");
	 }
	
	
}
