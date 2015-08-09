package edu.tju.ina.seeworld.logic;

import net.sf.json.JSONArray;
import edu.tju.ina.seeworld.exception.SeeWorldException;

public interface IRoleLogic {
	public JSONArray showRoleList() throws SeeWorldException;
}
