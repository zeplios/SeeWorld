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
import edu.tju.ina.seeworld.dao.resource.IFormatDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.logic.UploadMovieLogic;
import edu.tju.ina.seeworld.logic.UploadTVPlayLogic;
import edu.tju.ina.seeworld.logic.UploadVideoLogic;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Format;
import edu.tju.ina.seeworld.po.user.User;

public class UploadTVPlayAction 
{
	private List<String> otherInfos;
	public void setOtherInfos(List<String> otherInfos) {
		this.otherInfos = otherInfos;
	}



	/** moviename */
	private String movieName;
	
	/** movie alias */
	private String movieAlias;
	
	private List<String> movieKeywords;
	
	public void setMovieKeywords(List<String> movieKeywords) {
		this.movieKeywords = movieKeywords;
	}



	public void setMovieAlias(String movieAlias) {
		this.movieAlias = movieAlias;
	}



	/** movie introduction*/
	private String movieIntroduction;
	
	/** movie director*/
	private List<String> directors;
	private List<String> directorsAlias;
	private List<String> directorsArea;


	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public void setDirectorsAlias(List<String> directorsAlias) {
		this.directorsAlias = directorsAlias;
	}

	public void setDirectorsArea(List<String> directorsArea) {
		this.directorsArea = directorsArea;
	}

	/** movie actors*/
	private List<String> actors;
	
	/**actors alias*/
	private List<String> actorsAlias;
	
	/** actor areaAndCountry*/
	private List<String> actorsArea;
	


	public void setActorsAlias(List<String> actorsAlias) {
		this.actorsAlias = actorsAlias;
	}

	public void setActorsArea(List<String> actorsArea) {
		this.actorsArea = actorsArea;
	}

	/** movie client path*/
	private String moviePath;
	
	/** movie style*/
	private List<String> categorys;
	
	/** movie area*/
	private String area;
	
	/** when was produced*/
	private String movieYear;
	
    private UploadTVPlayLogic uploadTVPlayLogic;
	


	public void setUploadTVPlayLogic(UploadTVPlayLogic uploadTVPlayLogic) {
		this.uploadTVPlayLogic = uploadTVPlayLogic;
	}



	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public void setMovieIntroduction(String introduction) {
		this.movieIntroduction = introduction;
	}


	public void setActors(List<String> actors) {
		this.actors = actors;
	}



	public void setMoviePath(String moviePath) {
		this.moviePath = moviePath;
	}





	public void setCategorys(List<String> categorys) {
		this.categorys = categorys;
	}



	public void setArea(String area) {
		this.area = area;
	}



	public void setMovieYear(String movieYear) {
		this.movieYear = movieYear;
	}








	public String execute() throws Exception 
	{    
		String userName="uranus";
		
		boolean isSuccess=uploadTVPlayLogic.saveTVPlay(movieKeywords,categorys, userName, movieName, movieIntroduction, directors,directorsAlias,directorsArea, actors,actorsAlias,actorsArea, area, movieYear, moviePath,movieAlias,otherInfos);
		if(isSuccess=true){
		return "movieUpload";
		}
		else
			return  "fail";
	}
}