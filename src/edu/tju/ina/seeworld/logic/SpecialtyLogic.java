package edu.tju.ina.seeworld.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionContext;

import edu.tju.ina.seeworld.dao.user.IAcademyDAO;
import edu.tju.ina.seeworld.dao.user.ISpecialtyDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Academy;
import edu.tju.ina.seeworld.po.user.Specialty;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.SpecialtyVO;
import edu.tju.ina.seeworld.vo.UserVO;

public class SpecialtyLogic implements ISpecialtyLogic{
	
	private IAcademyDAO academyDao;
	private ISpecialtyDAO specialtyDao;
	private IUserDAO userDao;
	
	public void setAcademyDao(IAcademyDAO academyDao) {
		this.academyDao = academyDao;
	}
	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}
	public void setSpecialtyDao(ISpecialtyDAO specialtyDao) {
		this.specialtyDao = specialtyDao;
	}
	
	public JSONArray showSpecialtyList(Integer academy_id) throws SeeWorldException {
		Academy academy=academyDao.findById(academy_id);
		System.out.println(academy.getId());
		List<Specialty> list=specialtyDao.findByAcademy(academy);
		List<SpecialtyVO> list1=new ArrayList<SpecialtyVO>();
		JSONArray result=new JSONArray();
		
		for(int i=0;i<list.size();i++){
			Specialty specility=(Specialty) list.get(i);
			SpecialtyVO instance=new SpecialtyVO();
			instance.setName(specility.getName());
			instance.setId(specility.getId());
			list1.add(instance);
		}
		result.addAll(list1);
		return result;
	}
	
	public List<Specialty> showSpecialtyList() throws SeeWorldException {
		List<Specialty> list = new ArrayList<Specialty>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object obj = session.get(Constant.CURRENT_USER_MODEL_IN_SESSION);
		if(obj != null){
			String username = ((UserVO)obj).getUserName();
			User instance = userDao.findByUserName(username).get(0);
			Academy academy=instance.getAcademy();
			list = specialtyDao.findByAcademy(academy);
		}
		return list;
	}
}
