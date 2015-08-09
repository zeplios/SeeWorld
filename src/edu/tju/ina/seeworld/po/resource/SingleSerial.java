package edu.tju.ina.seeworld.po.resource;

import java.sql.Timestamp;

/**
 * Singleserial entity. 表示连续剧集中的一集
 * 
 * @author zhfch
 */


public class SingleSerial extends Multimedia implements java.io.Serializable {

	private static final long serialVersionUID = 6065328439349741112L;
	private Serial serial;
	private Short episode = 1;
	private String path;

	/** default constructor */
	public SingleSerial() {
	}

	/** minimal constructor */
	public SingleSerial(Serial serial, String sectionTitle, Timestamp addTime,
			Short episode) {
		this.serial = serial;
		this.episode = episode;
	}

	public Serial getSerial() {
		return this.serial;
	}

	public void setSerial(Serial serial) {
		this.serial = serial;
	}

	public Short getEpisode() {
		return this.episode;
	}

	public void setEpisode(Short episode) {
		this.episode = episode;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		String episode ="第" + getEpisode() + "集";
		return getSerial() + episode;
	}
	
}