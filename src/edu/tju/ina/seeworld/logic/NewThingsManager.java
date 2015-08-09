package edu.tju.ina.seeworld.logic;

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

public class NewThingsManager {
	private boolean enableForCollection;
	private boolean enableForComment;
	private boolean enableForView;
	private Integer intervalForCollection;
	private Integer intervalForComment;
	private Integer intervalForView;
	private Timer timer;
	private static Integer checkHour;
	public NewThingsManager(boolean enableForCollection,
			boolean enableForComment, boolean enableForView,
			Integer intervalForCollection, Integer intervalForComment,
			Integer intervalForView,Integer checkHour) {
		this.enableForCollection = enableForCollection;
		this.enableForComment = enableForComment;
		this.enableForView = enableForView;
		this.intervalForCollection = intervalForCollection;
		this.intervalForComment = intervalForComment;
		this.intervalForView = intervalForView;
		this.checkHour=checkHour;
		timer=new Timer(true);
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				checkTime();
			}
			
		}, 59*60*1000);
		System.out.println("新鲜事管理启动成功");
	}
	public static void checkTime(){
		Timestamp now = new Timestamp(System.currentTimeMillis());
		if(now.getHours()==checkHour){
			executeManage();
		}
	}
	public static void executeManage(){

	}
	public static Integer getCheckHour() {
		return checkHour;
	}
	public static void setCheckHour(Integer checkHour) {
		NewThingsManager.checkHour = checkHour;
	}
	public boolean isEnableForCollection() {
		return enableForCollection;
	}
	public void setEnableForCollection(boolean enableForCollection) {
		this.enableForCollection = enableForCollection;
	}
	public boolean isEnableForComment() {
		return enableForComment;
	}
	public void setEnableForComment(boolean enableForComment) {
		this.enableForComment = enableForComment;
	}
	public boolean isEnableForView() {
		return enableForView;
	}
	public void setEnableForView(boolean enableForView) {
		this.enableForView = enableForView;
	}
	public Integer getIntervalForCollection() {
		return intervalForCollection;
	}
	public void setIntervalForCollection(Integer intervalForCollection) {
		this.intervalForCollection = intervalForCollection;
	}
	public Integer getIntervalForComment() {
		return intervalForComment;
	}
	public void setIntervalForComment(Integer intervalForComment) {
		this.intervalForComment = intervalForComment;
	}
	public Integer getIntervalForView() {
		return intervalForView;
	}
	public void setIntervalForView(Integer intervalForView) {
		this.intervalForView = intervalForView;
	}
	
}
