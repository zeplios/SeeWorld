package test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.tju.ina.seeworld.exception.SeeWorldRuntimeException;
import edu.tju.ina.seeworld.util.mail.MailSenderInfo;
import edu.tju.ina.seeworld.util.mail.SimpleMailSender;

public class EmailTest {

	@Test
	public void test() {
		//String userName = "test0";
		String email = "zhfch28@163.com";
		String password = "111111";
		String realName = "test0";
		String content = realName + "您好，您在本站的密码是:" + password;
		// TODO 改进此处代码 找回密码功能
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("tjuseeworld@163.com");
		mailInfo.setPassword("123456");
		mailInfo.setFromAddress("tjuseeworld@163.com");
		mailInfo.setToAddress(email);
		mailInfo.setSubject("找回密码");
		mailInfo.setContent(content);
		SimpleMailSender sms = new SimpleMailSender();
		if (!sms.sendHtmlMail(mailInfo)) {
			throw new SeeWorldRuntimeException("Find password error!");
		}
		//fail("Not yet implemented");
	}

}
