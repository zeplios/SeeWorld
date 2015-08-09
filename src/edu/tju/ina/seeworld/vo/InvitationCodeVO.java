package edu.tju.ina.seeworld.vo;

import java.sql.Timestamp;

import edu.tju.ina.seeworld.po.user.InvitationCode;
import edu.tju.ina.seeworld.util.DateUtil;

public class InvitationCodeVO {
	private String id;
	private String userId;
	/**
	 * 该邀请码状态，是否过期，是否可用
	 */
	private String status;

	public static final String VALID_IC = "可使用，过期日期：";
	public static final String INVALID_IC_USED = "已使用";
	public static final String INVALID_IC_EXPIRED = "过期";
	
	public InvitationCodeVO() {

	}

	public InvitationCodeVO(InvitationCode ic) {
		this.id = ic.getId();
		this.userId = ic.getUser().getId();
		if (ic.isUsed()) {
			status = INVALID_IC_USED;
		} else if (ic.getExpiredTime().compareTo(
				new Timestamp(System.currentTimeMillis())) < 0) {
			status = INVALID_IC_EXPIRED;
		} else {
			status = VALID_IC
					+ DateUtil.getFormattedDateString(ic.getExpiredTime());
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
