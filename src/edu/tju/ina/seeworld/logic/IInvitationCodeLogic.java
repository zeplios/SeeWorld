package edu.tju.ina.seeworld.logic;

import java.sql.Timestamp;
import java.util.List;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.vo.InvitationCodeVO;

public interface IInvitationCodeLogic {

	/**
	 * 为指定用户生成一定数目的邀请码
	 * 
	 * @param User_vo
	 * @param num
	 *            生成邀请码的个数
	 */
	public List<InvitationCodeVO> generateInvitationCodeForUser(User user,
			int num) throws SeeWorldException;

	/**
	 * 查找某一用户的所有邀请码
	 * 
	 * @param User_vo
	 * @return
	 */
	public List<InvitationCodeVO> findInvitationCodeForUser(String userId)
			throws SeeWorldException;

	/**
	 * 按使用状态查找邀请码
	 * 
	 * @param used
	 * @return
	 */
	public List<InvitationCodeVO> findByUsed(Boolean used)
			throws SeeWorldException;

	/**
	 * 按过期时间查找邀请码
	 * 
	 * @param ts
	 * @return
	 */
	public List<InvitationCodeVO> findByExpiredTime(Timestamp ts)
			throws SeeWorldException;

	/**
	 * 在用户注册时，根据邀请码内容验证邀请码是否有效
	 * 
	 * @param ic
	 * @return
	 */
	public String isValid(String ic) throws SeeWorldException;

	public void useInvitationCode(String id) throws SeeWorldException;

	public void deleteInvitationCode(String id) throws SeeWorldException;
}
