package edu.tju.ina.seeworld.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.dao.resource.IAreaAndCountryDAO;
import edu.tju.ina.seeworld.dao.resource.ICategoryDAO;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;
import edu.tju.ina.seeworld.po.resource.Category;

public class ShowMovieUploadJspAction implements Action {
	/** ��Springע���CategoryDAO.java��Ķ��� */
	private ICategoryDAO categoryDao;

	private IAreaAndCountryDAO areaDao;

	public String execute() throws Exception {
		// ��ȡrequest����
		HttpServletRequest request = ServletActionContext.getRequest();

		// ��װ�������
		Category category = null;

		// ��װ�����������
		String categoryName = null;

		// ��װһ��������Ƶ��б�
		List<String> firstCategory = new ArrayList<String>();

		// ����ȡ��9��һ��������󣬲��ֱ�ÿ���������ƴ���firstCategory�б���
		for (int i = 77; i <= 82; i++) {
			// ȡ����i��CategoryVedio����
			category = categoryDao.findById(i);

			// ȡ����i��CategoryVedio��������
			categoryName = category.getName();

			// ����i��CategoryVedio�������ƴ���firstCategory�б���
			firstCategory.add(categoryName);
		}

		// ��firstCategory��װ��request��
		request.setAttribute("category", firstCategory);

		AreaAndCountry area = null;
		String areaName = null;
		List<String> areaList = new ArrayList<String>();
		for (int i = 1; i <= 4; i++) {
			area = areaDao.findById(i);
			areaName = area.getName();
			areaList.add(areaName);
		}
		request.setAttribute("area", areaList);

		List<Integer> year = new ArrayList<Integer>();
		for (Integer i = 1980; i <= 2010; i++) {
			year.add(i);
		}
		request.setAttribute("year", year);

		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public ICategoryDAO getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(ICategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	public IAreaAndCountryDAO getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(IAreaAndCountryDAO areaDao) {
		this.areaDao = areaDao;
	}

}
