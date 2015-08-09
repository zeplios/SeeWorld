package edu.tju.ina.seeworld.logic;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.annotations.JSON;

import edu.tju.ina.seeworld.dao.common.IRbacDAO;
import edu.tju.ina.seeworld.dao.user.IAcademyDAO;
import edu.tju.ina.seeworld.dao.user.ISpecialtyDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.exception.SeeWorldRuntimeException;
import edu.tju.ina.seeworld.po.rbac.Role;
import edu.tju.ina.seeworld.po.user.Academy;
import edu.tju.ina.seeworld.po.user.Specialty;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.DateUtil;
import edu.tju.ina.seeworld.util.mail.MailSenderInfo;
import edu.tju.ina.seeworld.util.mail.SimpleMailSender;
import edu.tju.ina.seeworld.vo.UserVO;

public class UserLogic implements IUserLogic {
	private IAcademyDAO academyDao;
	private ISpecialtyDAO specialtyDao;
	private IRbacDAO<Role> roleDao;
	private IUserDAO userDao;

	private IInvitationCodeLogic invitationCodeLogic;
	private IPrivacySettingLogic privacySettingLogic;
	private SettingLogic settingLogic;

	private User user = new User();
	private List<User> list;

	public void setAcademyDao(IAcademyDAO academyDao) {
		this.academyDao = academyDao;
	}

	@JSON(serialize = false)
	public IAcademyDAO getAcademyDao() {
		return academyDao;
	}

	@JSON(serialize = false)
	public ISpecialtyDAO getSpecialtyDao() {
		return specialtyDao;
	}

	@JSON(serialize = false)
	public IRbacDAO<Role> getRoleDao() {
		return roleDao;
	}

	@JSON(serialize = false)
	public IUserDAO getUserDao() {
		return userDao;
	}

	public void setSpecialtyDao(ISpecialtyDAO specialtyDao) {
		this.specialtyDao = specialtyDao;
	}

	public void setRoleDao(IRbacDAO<Role> roleDao) {
		this.roleDao = roleDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public void setInvitationCodeLogic(IInvitationCodeLogic invitationCodeLogic) {
		this.invitationCodeLogic = invitationCodeLogic;
	}

	public void banUser(String id) throws SeeWorldException {
		user = userDao.findById(id);
		user.setEnabled(false);
		userDao.update(user);
	}
	
	public void allowUser(String id) throws SeeWorldException {
		user = userDao.findById(id);
		user.setEnabled(true);
		userDao.update(user);
	}

	public void delUser(String id) throws SeeWorldException {
		//User user = new User();
		//user.setId(id);
		user = userDao.findById(id);
		userDao.delete(user);
	}

	public void update(User user) throws SeeWorldException {
		userDao.update(user);
	}

	public void updateInfo(UserVO userVO) throws SeeWorldException {
		Academy academy = academyDao.findById(Integer.valueOf(userVO
				.getAcademy_id()));
		Specialty specialty = specialtyDao.findById(Integer.valueOf(userVO
				.getSpecialty_id()));
		user = new User();
		user = userDao.findById(userVO.getId());
		user.setUsername(userVO.getUserName());
		user.setRealName(userVO.getRealName());
		user.setAcademy(academy);
		user.setSpecialty(specialty);
		user.setEmail(user.getEmail());
		user.setUid(user.getUid());
		userDao.update(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.tju.ina.seeworld.logic.IUserLogic#userRegister(edu.tju.ina.seeworld
	 * .vo.User_Vo) 注册用户： 存储用户，初始化权限，生成邀请码
	 */
	public void userRegister(UserVO userVO) throws SeeWorldException {
		user = new User();
		Academy academy = academyDao.findById(Integer.valueOf(userVO
				.getAcademy_id()));
		Specialty specialty = specialtyDao.findById(Integer.valueOf(userVO
				.getSpecialty_id()));
		Role role = roleDao.findByName("ROLE_COMMON");
		user.setUsername(userVO.getUserName());
		user.setPassword(edu.tju.ina.seeworld.util.StringUtils.encodeByMd5(userVO.getPassword()));
		user.setRealName(userVO.getRealName());
		user.setAcademy(academy);
		user.setSpecialty(specialty);
		user.setEmail(userVO.getEmail());
		user.setUid(userVO.getUid());
		user.setPhotoPath(Constant.DEFAULT_USER_PHOTO);
		user.setEnabled(true);
		user.setRole(role);
		Timestamp lastLoginTime = DateUtil.getCurrentTimestamp();
		user.setLastLoginTime(lastLoginTime);
		user.setLoginTimes(0);
		Lock lock = new ReentrantLock();
		lock.lock();
		try{
			user.setId(getNextAvailableId());
			userDao.save(user);
		}
		catch(Exception e){
			lock.unlock();
			System.out.println("ERROR:" + e.getMessage());
			return;
		}
		lock.unlock();
		privacySettingLogic.initUserSetting(user);
		if (Boolean.valueOf(settingLogic.getStringConfigValue(Constant.IS_INVITED_REQUIRED))) {
			invitationCodeLogic.generateInvitationCodeForUser(user,
					settingLogic.getIntConfigValue(Constant.INVITATIONCODE_PER_USER));
		}
	}

	public void setPrivacySettingLogic(IPrivacySettingLogic privacySettingLogic) {
		this.privacySettingLogic = privacySettingLogic;
	}

	public User getInstance() {
		return this.user;
	}

	public Boolean checkUserName(String userName) throws SeeWorldException {
		List<User> list = userDao.findByProperty(IUserDAO.USER_NAME, userName);
		if (list.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public void modifyPassword(UserVO userVO, String oldPwd)
			throws SeeWorldException {
		user = userDao.findById(userVO.getId());
		String oldPassword = user.getPassword();
		String password = userVO.getPassword();
		if (!oldPassword.equals(oldPwd)) {
			throw new SeeWorldException("用户原密码错误！");
		} else {
			if (!oldPassword.equals(password)) {
				user.setPassword(password);
				userDao.update(user);
			}
		}
	}

	public List<User> getUserListByPage(UserVO user, Integer offset,
			Integer pagesize) throws SeeWorldException {
		String realName = user.getRealName();
		Integer academy_id = user.getAcademy_id();
		Integer specialty_id = user.getSpecialty_id();
		String hql = "from User as model where ";
		if (realName != null) {
			if (!realName.equals("")) {
				try {
					byte[] tmpbyte = realName.getBytes("ISO-8859-1");
					realName = new String(tmpbyte);
				} catch (UnsupportedEncodingException e) {
					throw new SeeWorldException(e);
				}
				hql = hql + "model.realName='" + realName + "' and ";
			}
		}
		if (academy_id != null) {
			if (!academy_id.equals("0")) {
				hql = hql + "model.academy='" + academy_id + "' and ";
			}
		}
		if (specialty_id != null) {
			if (!specialty_id.equals("0")) {
				hql = hql + "model.specialty='" + specialty_id + "' and ";
			}
		}
		hql = hql.substring(0, hql.length() - 4);
		System.out.println(hql);
		list = userDao.getListByPage(hql, offset, pagesize);
		return list;
	}

	public List<User> searchUser(UserVO user, int offset, int pagesize)
			throws SeeWorldException {
		String realName = user.getRealName();
		Integer academy_id = user.getAcademy_id();
		Integer specialty_id = user.getSpecialty_id();
		String hql = "from User as model where ";
		if (StringUtils.isNotBlank(realName)) {
			/*
			 * try { byte[] tmpbyte = realName.getBytes("ISO-8859-1");
			 * realName=new String(tmpbyte); } catch
			 * (UnsupportedEncodingException e) { block e.printStackTrace(); }
			 */
			hql = hql + "model.realName=? and ";
		}
		if (academy_id != null) {
			if (!academy_id.equals("0")) {
				hql = hql + "model.academy=? and ";
			}
		}
		if (specialty_id != null) {
			if (!specialty_id.equals("0")) {
				hql = hql + "model.specialty=? and ";
			}
		}
		hql = hql.substring(0, hql.length() - 4);
		System.out.println(realName);
		System.out.println(hql);
		list = userDao.getListByPage(hql, offset, pagesize, realName,
				academy_id, specialty_id);
		return list;
	}

	public void findPassword(UserVO userVO) throws SeeWorldException {
		String userName = userVO.getUserName();
		List<User> list = userDao.findByProperty(IUserDAO.USER_NAME, userName);
		if (list.size() > 0) {
			User user = list.get(0);
			String email = user.getEmail();
			String password = user.getPassword();
			String realName = user.getRealName();
			String content = realName + "您好，您在本站的密码是:" + password;
			// TODO 改进此处代码 找回密码功能
			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost("smtp.163.com");
			mailInfo.setMailServerPort("25");
			mailInfo.setValidate(true);
			mailInfo.setUserName("seeworldinner@163.com");
			mailInfo.setPassword("123456");
			mailInfo.setFromAddress("seeworldinner@163.com");
			mailInfo.setToAddress(email);
			mailInfo.setSubject("找回密码");
			mailInfo.setContent(content);
			SimpleMailSender sms = new SimpleMailSender();
			if (!sms.sendHtmlMail(mailInfo)) {
				throw new SeeWorldRuntimeException("Find password error!");
			}
			;
		}
	}

	public List<User> getActiveUserList() throws SeeWorldException {
		String hql = "from User as model order by model.lastLoginTime,model.loginTimes desc";
		list = userDao.getListByPage(hql, 0, 
				settingLogic.getIntConfigValue(Constant.ACTIVE_USER_NUM));
		return list;
	}

	public void modifyUserRole(UserVO userVO) throws SeeWorldException {
		String id = userVO.getId();
		String roleId = userVO.getRoleId();
		User user = userDao.findById(id);
		Role role = roleDao.findById(roleId);
		user.setRole(role);
		userDao.update(user);
	}

	public User findById(String id) throws SeeWorldException {
		return userDao.findById(id);
	}
	
	public List<User>findByUserName(String userName) throws SeeWorldException{
		return userDao.findByUserName(userName);
	}
	
	public List<User> findAll() throws SeeWorldException{
		return userDao.findAll(" order by username asc", true);
	}

	public String getNextAvailableId() throws SeeWorldException {
		// TODO Auto-generated method stub
		return userDao.getNextAvailableId();
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
}
