package edu.tju.ina.seeworld.logic;

import java.util.List;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.vo.UserVO;

public interface IUserLogic {

	public User findById(String id) throws SeeWorldException;

	/**
	 * 用户注册相关逻辑，完成用户的添加
	 * 
	 * @param user
	 */
	public void userRegister(UserVO user) throws SeeWorldException;

	/**
	 * 用户信息更改时的相关逻辑操作
	 * 
	 * @param user
	 */
	public void updateInfo(UserVO user) throws SeeWorldException;

	public void update(User userVO) throws SeeWorldException;

	/**
	 * 删除用户信息
	 * 
	 * @param user
	 */
	public void delUser(String userID) throws SeeWorldException;

	/**
	 * 禁止某一用户发言
	 * 
	 * @param user
	 */
	public void banUser(String userID) throws SeeWorldException;

	public void allowUser(String userID) throws SeeWorldException;

	/**
	 * 检验用户名是否存在
	 * 
	 * @param user
	 */
	public Boolean checkUserName(String userName) throws SeeWorldException;

	/**
	 * 修改密码
	 * 
	 * @param user
	 *            用户 类，其中的password为新密码
	 * @param oldPwd
	 *            用户原密码
	 * @throws SeeWorldException
	 */
	public void modifyPassword(UserVO user, String oldPwd)
			throws SeeWorldException;

	/**
	 * 搜索用户AJAX
	 * 
	 * @param user
	 */
	public List<User> searchUser(UserVO user, int offset, int pagesize)
			throws SeeWorldException;

	/**
	 * 进入指定页
	 * 
	 * @param user
	 */
	public List<User> getUserListByPage(UserVO user, Integer offset,
			Integer pagesize) throws SeeWorldException;

	/**
	 * 找回密码
	 * 
	 * @param user
	 */
	public void findPassword(UserVO user) throws SeeWorldException;

	/**
	 * 获得活跃用户列表
	 * 
	 * @param user
	 */
	public List<User> getActiveUserList() throws SeeWorldException;

	public void modifyUserRole(UserVO user) throws SeeWorldException;

	public List<User> findAll() throws SeeWorldException;
	
	public List<User>findByUserName(String userName) throws SeeWorldException;

	public String getNextAvailableId() throws SeeWorldException;
}
