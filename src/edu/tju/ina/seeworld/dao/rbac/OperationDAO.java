package edu.tju.ina.seeworld.dao.rbac;

import edu.tju.ina.seeworld.dao.common.RbacDAO;
import edu.tju.ina.seeworld.po.rbac.Operation;

/**
 * @see tju.ina.uranus.po.Operation
 * @author Uranus
 */

public class OperationDAO extends RbacDAO<Operation> implements IOperationDAO {

	@Override
	protected void initDao() {
		super.init(Operation.class.getName());
	}
	
}