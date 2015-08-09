package edu.tju.ina.seeworld.struts.action.user;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IUserLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.StringUtils;
import edu.tju.ina.seeworld.util.UploadUtils;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.vo.EssentialUser;
import edu.tju.ina.seeworld.vo.UserVO;

public class UserAjaxAction extends AjaxAction implements ModelDriven<UserVO> {
	public static final Logger log = Logger.getLogger(UserAjaxAction.class);
	private static final long serialVersionUID = -6683687003779372897L;

	private UserVO model = new UserVO();
	private SettingLogic settingLogic;
	private IUserLogic userLogic;
	private VOPOTransformator vOPOTransformator;
	private String userId;
	private String oldPwd;

	private int x;
	private int y;
	private int w;
	private int h;

	//裁切前临时文件名称
	private String tempAvatar;
	//头像存放目录名称
	private String avatarPath;
	//最终用户头像完整路径
	private String photoPath;
	
	/**
	 * 保存裁切后的头像
	 * @return
	 */
	public String saveAvatar() {
		try {
			avatarPath = settingLogic.getStringConfigValue(Constant.AVATAR_PATH);
			ServletContext context = ServletActionContext.getServletContext();
			String path = context.getRealPath("/");	//返回一直到/SeeWorld/的绝对路径
			path = path.substring(0, path.indexOf(context.getContextPath().substring(1)) - 1);	//返回\SeeWorld\之前的部分
			String temp = path + File.separator + settingLogic.getStringConfigValue(Constant.PHOTO_TEMP_PATH) + tempAvatar;
			photoPath = UploadUtils.saveAvatar(temp, x, y, w, h,
					path + File.separator + avatarPath);
			EssentialUser user = getCurrentUser();
			User u = userLogic.findById(user.getId());
			String oldAvatarPath = u.getPhotoPath();
			u.setPhotoPath(photoPath);
			userLogic.update(u);
			if (oldAvatarPath.length() > 0) {
				oldAvatarPath = path + File.separator + oldAvatarPath;
			}
			File old = new File(oldAvatarPath);
			if (old.delete()) {
				log.info("Deleted old avatar " + oldAvatarPath + " of user "
						+ u);
			}
			File tempFile = new File(temp);
			if (tempFile.delete()) {
				if (log.isDebugEnabled()) {
					log.debug("deleted " + temp);
				}
			}
			model.setPhotoPath(photoPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public String findAllUser() throws SeeWorldException {
		List<User> users = userLogic.findAll();
		List<UserVO> list = vOPOTransformator
				.transferUserToUserVOForViewList(users);
		resultList = new JSONArray();
		resultList.addAll(list);
		return SUCCESS;
	}

	public String showActiveUserList() throws SeeWorldException {
		List<UserVO> list = vOPOTransformator
				.transferUserToUserVOForViewList(userLogic.getActiveUserList());
		resultList = new JSONArray();
		resultList.addAll(list.subList(0, 4));
		return SUCCESS;
	}

	//个人中心修改用户密码
	public String modifyPassword() {
		try {
			model.setId(getCurrentUser().getId());
			model.setPassword(StringUtils.encodeByMd5(model.getPassword()));
			oldPwd = StringUtils.encodeByMd5(oldPwd);
			userLogic.modifyPassword(model, oldPwd);
			model.setPassword(null);
		} catch (SeeWorldException e) {
			addFieldError("User_UserPasswordInvalid", e.getErrorMsg());
			if (log.isDebugEnabled()) {
				log.error(e.getMessage(), e);
			}
		}
		return SUCCESS;
	}

	//后台管理页删除用户
	public String deleteUser() {
		try {
			userLogic.delUser(userId);
		} catch (SeeWorldException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	//后台管理页禁用用户
	public String banUser() {
		try {
			userLogic.banUser(userId);
		} catch (SeeWorldException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	//后台管理页启用用户
	public String allowUser() {
		try {
			userLogic.allowUser(userId);
		} catch (SeeWorldException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String findCurrentUserInfo() throws SeeWorldException {
		model = new UserVO(userLogic.findById(getCurrentUser()
				.getId()));
		model.setPassword(null);
		return SUCCESS;
	}

	public String updateInfo() throws SeeWorldException {
		userLogic.updateInfo(model);
		return SUCCESS;
	}

	//注册页查找用户名是否已重复
	public String checkUserName() throws SeeWorldException {
		if (!userLogic.checkUserName(model.getUserName())) {
			addFieldError("User_UserNameAlreadyExisted", "用户名已存在！");
		}
		return SUCCESS;
	}
	
	public UserVO getModel() {
		return model;
	}

	@JSON(serialize = false)
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@JSON(serialize = false)
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@JSON(serialize = false)
	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	@JSON(serialize = false)
	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	@JSON(serialize = false)
	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	@JSON(serialize = false)
	public String getTempAvatar() {
		return tempAvatar;
	}

	public void setTempAvatar(String tempAvatar) {
		this.tempAvatar = tempAvatar;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public void setModel(UserVO model) {
		this.model = model;
	}

	public void setUserLogic(IUserLogic userLogic) {
		this.userLogic = userLogic;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
}
