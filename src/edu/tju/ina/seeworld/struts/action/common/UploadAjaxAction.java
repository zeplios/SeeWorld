package edu.tju.ina.seeworld.struts.action.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.UploadUtils;

public class UploadAjaxAction extends BaseAction {
	private static final long serialVersionUID = 181221885134848594L;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
	private String tempPicPath;
	private SettingLogic settingLogic;

	public String uploadPhotoToTemp() {
		try {
			tempPicPath = settingLogic.getStringConfigValue(Constant.PHOTO_TEMP_PATH);
			ServletContext context = ServletActionContext.getServletContext();
			String path = context.getRealPath("/");	//返回一直到/SeeWorld/的绝对路径
			path = path.substring(0, path.indexOf(context.getContextPath().substring(1)) - 1);	//返回\SeeWorld\之前的部分
			String tempFileName = UploadUtils.uploadPhoto(upload, path + "/" + tempPicPath, uploadFileName);
			tempPicPath = "/" + tempPicPath + "/" + tempFileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String uploadPoster() {
		try {
			tempPicPath = settingLogic.getStringConfigValue(Constant.MULTIMEDIA_IMAGE_PATH);
			ServletContext context = ServletActionContext.getServletContext();
			String path = context.getRealPath("/");	//返回一直到/SeeWorld/的绝对路径
			path = path.substring(0, path.indexOf(context.getContextPath().substring(1)) - 1);	//返回\SeeWorld\之前的部分
			path += "/" + tempPicPath;
			String tempFileName = UploadUtils.uploadPhoto(upload, path, uploadFileName);
			tempPicPath = path + "/" + tempFileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getTempPicPath() {
		return tempPicPath;
	}

	public void setTempPicPath(String tempPicPath) {
		this.tempPicPath = tempPicPath;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
}
