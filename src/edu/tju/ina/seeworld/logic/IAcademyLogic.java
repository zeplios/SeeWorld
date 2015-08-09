package edu.tju.ina.seeworld.logic;

import java.util.List;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Academy;



public interface IAcademyLogic {
	/**
	 * 获得学院列表
	 * 
	 * @param 
	 */
	public List<Academy> showAcademyList() throws SeeWorldException;
	
	/**
	 * 添加学院
	 * 
	 * @param 
	 */
	public void addAcademy(Academy academy) throws SeeWorldException;
	/**
	 * 删除学院
	 * 
	 * @param 
	 */
	public void deleteAcademy(Academy academy) throws SeeWorldException;
	/**
	 * 更新学院
	 * 
	 * @param 
	 */
	public void updateAcademy(Academy academy) throws SeeWorldException;
}
