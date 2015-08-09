package edu.tju.ina.seeworld.logic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import edu.tju.ina.seeworld.dao.user.IInvitationCodeDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.InvitationCode;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.InvitationCodeGenerator;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.vo.InvitationCodeVO;

public class InvitationCodeLogic implements IInvitationCodeLogic {

	private IInvitationCodeDAO invitationCodeDao;
	private VOPOTransformator vOPOTransformator;
	/**
	 * 邀请码长度
	 */
	public static final int IC_LENGTH = 6;

	public void setInvitationCodeDao(IInvitationCodeDAO invitationCodeDao) {
		this.invitationCodeDao = invitationCodeDao;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public List<InvitationCodeVO> findByExpiredTime(Timestamp ts)
			throws SeeWorldException {
		return vOPOTransformator
				.transferInvitationCodeToVOList(invitationCodeDao
						.findByExpiredTime(ts));
	}

	public List<InvitationCodeVO> findByUsed(Boolean used)
			throws SeeWorldException {
		return vOPOTransformator
				.transferInvitationCodeToVOList(invitationCodeDao
						.findByUsed(used));
	}

	public List<InvitationCodeVO> findInvitationCodeForUser(String userId)
			throws SeeWorldException {
		User user = new User();
		user.setId(userId);
		List<InvitationCode> allInvitationCode = invitationCodeDao
				.findByUser(user);
		return vOPOTransformator
				.transferInvitationCodeToVOList(allInvitationCode);
	}

	public List<InvitationCodeVO> generateInvitationCodeForUser(User user,
			int num) throws SeeWorldException {
		List<InvitationCode> resList = new ArrayList<InvitationCode>();
		for (int i = 0; i < num; i++) {
			String id = InvitationCodeGenerator.generateString(IC_LENGTH);
			long begin = Calendar.getInstance().getTimeInMillis();
			long end = begin + 7 * 24 * 60 * 60 * 1000;
			Timestamp expiredTime = new Timestamp(end);
			Timestamp addTime = new Timestamp(begin);
			InvitationCode invitationCode = new InvitationCode();
			invitationCode.setId(id);
			invitationCode.setExpiredTime(expiredTime);
			invitationCode.setAddTime(addTime);
			invitationCode.setUsed(false);
			invitationCode.setUser(user);
			invitationCodeDao.save(invitationCode);
			resList.add(invitationCode);
		}
		return vOPOTransformator.transferInvitationCodeToVOList(resList);
	}

	public String isValid(String ic) throws SeeWorldException {
		InvitationCode instance;
		try {
			instance = invitationCodeDao.findById(ic);
			if (instance != null&&StringUtils.isNotBlank(instance.getId())) {
				if (!instance.isUsed()) {
					if (!instance.isExpired()) {
						instance.setUsed(true);
						return Constant.INVITATIONCODE_VALID;// 可以使用
					} else {
						return Constant.INVITATIONCODE_EXPIRED;// 过期
					}
				} else {
					return Constant.INVITATIONCODE_USERD;// 已经被用过
				}
			} else {
				return Constant.INVITATIONCODE_NONENTITY;// 不存在次邀请码
			}
		} catch (SeeWorldException e) {
			if (e.getCause() instanceof org.hibernate.ObjectNotFoundException) {
				return Constant.INVITATIONCODE_NONENTITY;
			}else{
				throw e;
			}
		}
	}

	public void useInvitationCode(String id) throws SeeWorldException{
		InvitationCode ic = invitationCodeDao.findById(id);
		ic.setUsed(true);
		invitationCodeDao.update(ic);
	}
	
	public void deleteInvitationCode(String id) throws SeeWorldException {
		InvitationCode ic = new InvitationCode();
		ic.setId(id);
		invitationCodeDao.delete(ic);
	}
}
