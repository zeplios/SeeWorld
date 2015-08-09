package edu.tju.ina.seeworld.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.dao.resource.ICategoryDAO;
import edu.tju.ina.seeworld.po.resource.Category;

public class ShowVideoUploadJspAction implements Action 
{
	/** ��Springע���CategoryDAO.java��Ķ���*/
	private ICategoryDAO categoryDao;
	
	public String execute() throws Exception 
	{
		//��ȡrequest����
		HttpServletRequest request = ServletActionContext.getRequest();
	
		//��װһ���������
		Category category = null;
		
		//��װһ�������������
		String categoryName = null;
		
		//��װһ��������Ƶ��б�
		List<String> firstCategory = new ArrayList<String>();
			
		//����ȡ��9��һ��������󣬲��ֱ�ÿ���������ƴ���firstCategory�б���
		for(int i=21;i<=29;i++)
		{
			//ȡ����i��CategoryVedio����
			category = categoryDao.findById(i);
			
			//ȡ����i��CategoryVedio��������
			categoryName = category.getName();
			
			//����i��CategoryVedio�������ƴ���firstCategory�б���
			firstCategory.add(categoryName);			
		}
		
		//��firstCategory��װ��request��
		request.setAttribute("firstCategory", firstCategory);
	
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public ICategoryDAO getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(ICategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}
}
