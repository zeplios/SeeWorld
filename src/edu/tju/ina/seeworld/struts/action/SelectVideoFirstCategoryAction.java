package edu.tju.ina.seeworld.struts.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.dao.resource.ICategoryDAO;
import edu.tju.ina.seeworld.po.resource.Category;

public class SelectVideoFirstCategoryAction implements Action 
{
	/** ��Springע���CategoryDAO.java��Ķ���*/
	private ICategoryDAO categoryDao;
	
	/**	��װCategory������Ƶ��б� */
	private List<String> firstCategory = new ArrayList<String>();
	
	public String execute() throws Exception 
	{		
		//��Category�����������δ���firstCategory�б���
		for(int i=21;i<=29;i++)
		{
			//ȡ����i��Category����
			Category category = categoryDao.findById(i);
			
			//ȡ����i��Category��������
			String categoryName = category.getName();
			
			//����i��Category�������ƴ���firstCategory�б���
			firstCategory.add(categoryName);
		}
		
		//��ȡrequest����
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//��ȡresponse����
		HttpServletResponse response = ServletActionContext.getResponse(); 
				
		//���û�ѡ���һ��������ƴ���firstCategoryName��			
		String firstCategoryName_ = request.getParameter("firstCategoryName");
		
		//��ʽת�� 
		//String firstCategoryName = URLDecoder.decode(firstCategoryName_, "UTF-8");
		String firstCategoryName = new String(firstCategoryName_.getBytes("iso-8859-1"),"UTF-8");
	
		//�����û�ѡ���һ������������video_�б���
		List<Category> video_ = (List<Category>)categoryDao.findByProperty("name",firstCategoryName);
		
		//�����û�ѡ���һ������������video��
		Category video = video_.get(0);
		
		//����������������SecondCategory�б���
		List<Category> secondCategory = (List<Category>)categoryDao.findByProperty("parent", video);
		
		//��Ŷ������������Ƶ�SecondCategoryName�б�
		List<String> secondCategoryName = new ArrayList();
		
		//���ν���������������ƴ���SecondCategoryName�б���
		for(int i=0;i<secondCategory.size();i++)
		{
			secondCategoryName.add(secondCategory.get(i).getName());
		}
	
		//����XML�ļ�
		response.setContentType("text/xml;charset=utf-8");
		 
		//��ȡout����
		PrintWriter out = response.getWriter();

		out.print("<categories>");
		
		for(int i=0;i<secondCategoryName.size();i++)
		{
			out.print("<category>" + secondCategoryName.get(i) + "</category>");
		}
		
		out.print("</categories>");
		
		// TODO Auto-generated method stub
		return null;
	}

	public ICategoryDAO getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(ICategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<String> getFirstCategory() {
		return firstCategory;
	}

	public void setFirstCategory(List<String> firstCategory) {
		this.firstCategory = firstCategory;
	}
	
	

}
