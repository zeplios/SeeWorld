package edu.tju.ina.seeworld.struts.action.common;

import edu.tju.ina.seeworld.exception.SeeWorldException;

/**
 * 规范后台资源管理action中的基本方法
 * 例如academy,category等
 * @author Uranus
 * 
 */
public interface ActionBasicOperation {
	
	public String listAction() throws SeeWorldException;

	public String saveAction() throws SeeWorldException;

	public String deleteAction() throws SeeWorldException;

	public String updateAction() throws SeeWorldException;
	
}
