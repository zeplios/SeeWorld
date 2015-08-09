package edu.tju.ina.seeworld.struts.action;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.dao.resource.IMovieDAO;
import edu.tju.ina.seeworld.po.resource.Movie;

public class ShowImageAction implements Action {

	private IMovieDAO movieDao;
	
	public String execute() throws Exception 
	{
		//��ȡrequest��session��session�б���ĵ�ǰ�û���ID
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse(); 
		
		String multimediaId = request.getParameter("multimediaId");
		Integer id = Integer.valueOf(multimediaId);
		
		Movie multimedia = movieDao.findById(id);		
		String ima = multimedia.getImage();
		
		response.setContentType("image/*");
		OutputStream outStream = response.getOutputStream(); 
		//outStream.write(ima); 
		outStream.close();
		outStream = null;
		
		// TODO Auto-generated method stub
		return null;
	}

	public IMovieDAO getMovieDao() {
		return movieDao;
	}
	public void setMovieDao(IMovieDAO movieDao) {
		this.movieDao = movieDao;
	}

	
}
