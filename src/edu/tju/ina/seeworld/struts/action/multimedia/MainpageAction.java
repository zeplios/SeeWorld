package edu.tju.ina.seeworld.struts.action.multimedia;

import java.util.ArrayList;
import java.util.List;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.MainpageLogic;
import edu.tju.ina.seeworld.struts.action.common.BaseAction;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.vo.MainpageVO;

public class MainpageAction extends BaseAction{

	private static final long serialVersionUID = -5485458941624819135L;
	
	private MainpageLogic mainpageLogic;
	private VOPOTransformator vOPOTransformator;
	
	private List<MainpageVO> mainpageList;
	
	public String listMpScrollAction(){
		try {
			mainpageList = vOPOTransformator.transferMainpageToVO(mainpageLogic.findByIsScroll());
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(mainpageList == null)
			mainpageList = new ArrayList<MainpageVO>();
		return LIST;
	}
	public String listMpStaticAction(){
		try {
			mainpageList = vOPOTransformator.transferMainpageToVO(mainpageLogic.findByIsStatic());
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(mainpageList == null)
			mainpageList = new ArrayList<MainpageVO>();
		return LIST;
	}

	public void setMainpageLogic(MainpageLogic mainpageLogic) {
		this.mainpageLogic = mainpageLogic;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}
	public List<MainpageVO> getMainpageList() {
		return mainpageList;
	}
}
