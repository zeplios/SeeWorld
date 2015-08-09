package test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import edu.tju.ina.seeworld.dao.user.IInvitationCodeDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.InvitationCode;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.InvitationCodeGenerator;

public class ORMTestInvitationCodeGen extends ORMTestBase {
	@Test
	public void genInvitationCode() {
		IInvitationCodeDAO icDao = (IInvitationCodeDAO) ctx
				.getBean("invitationCodeDao");
		try {
			List<User> userList = userDao.findAll("", false);
			for (User u : userList) {
				String id = InvitationCodeGenerator.generateString(6);
				long begin = Calendar.getInstance().getTimeInMillis();
				long end = begin + 7 * 24 * 60 * 60 * 1000;
				Timestamp expiredTime = new Timestamp(end);
				Timestamp addTime = new Timestamp(begin);
				InvitationCode invitationCode = new InvitationCode();
				invitationCode.setId(id);
				invitationCode.setExpiredTime(expiredTime);
				invitationCode.setAddTime(addTime);
				invitationCode.setUsed(false);
				invitationCode.setUser(u);
				icDao.save(invitationCode);
			}
		} catch (SeeWorldException e) {
			e.printStackTrace();
		}
	}
}
