package edu.tju.ina.seeworld.logic;

import java.util.Collection;
import java.util.List;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Multimedia;
import edu.tju.ina.seeworld.vo.SimpleMultimedia;

/**
 * 完成多媒体文件相关的操作
 * 
 * @author Uranus
 * 
 */
public interface IMultimediaLogic {
	/**
	 * 完成多媒体文件上传
	 * 
	 * @param m
	 */
	public void upload(Multimedia m) throws SeeWorldException;

	/**
	 * 删除多媒体文件
	 * 
	 * @param m
	 */
	public void delete(Multimedia m) throws SeeWorldException;

	/**
	 * 批量删除
	 * 
	 * @param ms
	 */
	public void deleteAll(Collection<? extends Multimedia> multimedias)
			throws SeeWorldException;

	/**
	 * 修改文件的信息
	 * 
	 * @param m
	 */
	public void update(Multimedia m) throws SeeWorldException;

	/**
	 * 列出所有多媒体文件
	 * 
	 * @return
	 */
	public List<? extends Multimedia> listAllMultimedia()
			throws SeeWorldException;
	
	public List<? extends Multimedia> findOrderedListByPropertyByPage(
			String propertyName, Object value, Object resourceId,boolean status,
			String orderColumn, int offset, int length)
			throws SeeWorldException;
	
	public Multimedia findByID(Integer id) throws SeeWorldException;
	
	public Multimedia findByIdAndResource(SimpleMultimedia multimedia) throws SeeWorldException;
}
