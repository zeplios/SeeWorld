package edu.tju.ina.seeworld.logic;

import java.util.List;

import net.sf.json.JSONArray;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Specialty;

public interface ISpecialtyLogic {
	/**
	 * 查找某一学院的所有专业
	 * 
	 * @param 
	 * @return
	 */
	public JSONArray showSpecialtyList(Integer academy_id) throws SeeWorldException;
	/**
	 * 查找所有专业
	 * 
	 * @param 
	 * @return
	 */
	public List<Specialty> showSpecialtyList() throws SeeWorldException;
}
