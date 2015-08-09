package edu.tju.ina.seeworld.struts.action.multimedia;

import java.sql.Timestamp;

import com.opensymphony.xwork2.ModelDriven;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.SingleSerialLogic;
import edu.tju.ina.seeworld.logic.UserLogic;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.SingleSerial;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;

public class SingleSerialAction extends BaseAction implements ModelDriven<SingleSerial> {

	private static final long serialVersionUID = -8996260101486710716L;
	private SingleSerialLogic singleSerialLogic;
	private UserLogic userLogic;
	
	private SingleSerial singleSerial = new SingleSerial();
	
	private int targetId;

	public String addAction() {
		if (singleSerial != null) {
			try {
				singleSerial.setAddTime(new Timestamp(System.currentTimeMillis()));

				singleSerial.setClickCount(0);
				singleSerial.setCollectedCount(0);
				singleSerial.setRecommendedCount(0);
				singleSerial.setStatus(true);
				singleSerial.setDeleted(false);
				
				singleSerial.setResource(new Resource("4"));
				User u = new User();
				u = userLogic.findByUserName(getCurrentUsername()).get(0);
				singleSerial.setUser(u);
				
				singleSerialLogic.save(singleSerial);
				//用于显示该剧集的单集列表
				targetId = singleSerial.getSerial().getId();
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return LIST;
		} else {
			return LOGIN;
		}
	}
	
	public String preUpdateAction() {
		try {
			singleSerial = singleSerialLogic.findByID(singleSerial.getId());
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UPDATE;
	}
	
	public String updateAction(){
		if (singleSerial != null) {
			try {
				SingleSerial old = singleSerialLogic.findByID(singleSerial.getId());
				old.setDeleted(false);
				old.setTitle(singleSerial.getTitle());
				old.setAlias(singleSerial.getAlias());
				old.setPath(singleSerial.getPath());
				old.setEpisode(singleSerial.getEpisode());
				singleSerialLogic.update(old);
				//用于显示该剧集的单集列表
				targetId = singleSerial.getSerial().getId();
			} catch (SeeWorldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return LIST;
		} else {
			return LOGIN;
		}
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setSingleSerialLogic(SingleSerialLogic singleSerialLogic) {
		this.singleSerialLogic = singleSerialLogic;
	}

	public void setUserLogic(UserLogic userLogic) {
		this.userLogic = userLogic;
	}

	public SingleSerial getModel() {
		// TODO Auto-generated method stub
		return singleSerial;
	}
}
