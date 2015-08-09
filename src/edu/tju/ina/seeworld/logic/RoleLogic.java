package edu.tju.ina.seeworld.logic;

import java.util.List;

import net.sf.json.JSONArray;
import edu.tju.ina.seeworld.dao.common.IRbacDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Role;
import edu.tju.ina.seeworld.vo.RoleVO;

public class RoleLogic implements IRoleLogic {
	private IRbacDAO<Role> roleDao;

	public void setRoleDao(IRbacDAO<Role> roleDao) {
		this.roleDao = roleDao;
	}

	public JSONArray showRoleList() throws SeeWorldException {
		JSONArray roleList = new JSONArray();
		List<Role> list = roleDao.findAll("",false);
		for (Role r : list) {
			RoleVO roleVo = new RoleVO();
			roleVo.setId(r.getId());
			roleVo.setName(r.getName());
			roleList.add(roleVo);
		}
		return roleList;
	}

}
