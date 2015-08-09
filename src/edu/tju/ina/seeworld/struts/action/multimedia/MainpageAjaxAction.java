package edu.tju.ina.seeworld.struts.action.multimedia;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.MainpageLogic;
import edu.tju.ina.seeworld.logic.MultimediaLogic;
import edu.tju.ina.seeworld.po.resource.Mainpage;
import edu.tju.ina.seeworld.po.resource.Multimedia;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;
import edu.tju.ina.seeworld.util.UploadUtils;
import edu.tju.ina.seeworld.util.VOPOTransformator;

public class MainpageAjaxAction extends AjaxAction{

	private static final long serialVersionUID = 2740764125801964014L;

	private MainpageLogic mainpageLogic;
	private VOPOTransformator vOPOTransformator;
	private MultimediaLogic multimediaLogic;
	
	private Integer targetId;
	private File image;
	private String desc;
	private int mainpageId;
	private int mainpageId2;	/*用于交换两个显示项位置*/
	private Boolean isScroll;
	
	/**
	 * 用于在首页获取列表
	 * @return
	 */
	public String findMainpageMultimedia(){
		resultList = new JSONArray();
		try {
			resultList.addAll(vOPOTransformator.transferMainpageToVO(mainpageLogic.findByIsScroll()));
			resultList.addAll(vOPOTransformator.transferMainpageToVO(mainpageLogic.findByIsStatic()));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 在后台视频列表页设置为首页显示项
	 * @return
	 */
	public String setMainpageMultimedia(){
		try {
			Multimedia m = multimediaLogic.findByID(targetId);
			Mainpage main = new Mainpage();
			main.setImage("");
			main.setBriefDescription("");
			main.setMultimedia(m);
			main.setIsScroll(isScroll);
			main.setIsStatic(!isScroll);
			main.setAddTime(new Timestamp(System.currentTimeMillis()));
			mainpageLogic.save(main);
		} catch (SeeWorldException e) {
			// TODO Handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 取消某资源的首页显示
	 * @return
	 */
	public String cancleMainpageMultimedia(){
		try {
			Mainpage s = mainpageLogic.findById(mainpageId);
			mainpageLogic.delete(s);
		} catch (SeeWorldException e) {
			// TODO Handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 交换两个显示项的顺序
	 * @return
	 */
	public String swapMainpageMultimedia(){
		try {
			Mainpage s1 = mainpageLogic.findById(mainpageId);
			Mainpage s2 = mainpageLogic.findById(mainpageId2);
			int tmpId = s1.getId();
			s1.setId(s2.getId());
			s2.setId(tmpId);
			mainpageLogic.update(s1);
			mainpageLogic.update(s2);
		} catch (SeeWorldException e) {
			// TODO Handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 用于修改首页管理中一个显示项的文字描述和图片
	 * @return
	 */
	public String modifyMainpageDetail(){
		try {
			Mainpage s = mainpageLogic.findById(mainpageId);
			if(s != null){
				if(image != null){
					ServletContext context = ServletActionContext.getServletContext();
					String path = context.getRealPath("/");	//返回一直到/SeeWorld/的绝对路径
					path = path.substring(0, path.indexOf(context.getContextPath().substring(1)) - 1);	//返回\SeeWorld\之前的部分
					path += "/posters";
					String newFilename = UploadUtils.uploadPosterInMainpage(image, path, "上传的首页图像");
					s.setImage(newFilename);
				}
				s.setBriefDescription(desc);
				mainpageLogic.update(s);
			}
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void setMainpageLogic(MainpageLogic mainpageLogic) {
		this.mainpageLogic = mainpageLogic;
	}

	public void setMultimediaLogic(MultimediaLogic multimediaLogic) {
		this.multimediaLogic = multimediaLogic;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setMainpageId(int mainpageId) {
		this.mainpageId = mainpageId;
	}

	public void setIsScroll(Boolean isScroll) {
		this.isScroll = isScroll;
	}

	public void setMainpageId2(int mainpageId2) {
		this.mainpageId2 = mainpageId2;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}
}
