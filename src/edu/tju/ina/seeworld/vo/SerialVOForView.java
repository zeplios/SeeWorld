package edu.tju.ina.seeworld.vo;

import java.util.ArrayList;

import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.Director;
import edu.tju.ina.seeworld.po.resource.Serial;
import edu.tju.ina.seeworld.po.resource.SingleSerial;

public class SerialVOForView extends MultimediaVOForView {
	
	/**
	 * 分集列表
	 */
	ArrayList<SimpleMultimedia> singleSerials = new ArrayList<SimpleMultimedia>(0);
	private Short seasons;
	private Short season;

	private String areaAndCountry = "";
	private String actors = "";
	private String directors = "";

	public SerialVOForView(Serial s) {
		super(s);
		if (s != null) {
			for (SingleSerial ss : s.getSingleSerials()) {
				singleSerials.add(new SimpleMultimedia(ss.getId(), ss.getTitle(), ss.getResource().getId()));
			}
		}
		seasons = s.getSeasons();
		season = s.getSeason();
		this.areaAndCountry = s.getAreaAndCountry().getName();
		for (Actor a : s.getActors()) {
			this.actors += a.getName() + "|";
		}

		for (Director d : s.getDirectors()) {
			this.directors += d.getName() + "|";
		}
	}

	public ArrayList<SimpleMultimedia> getSingleSerials() {
		return singleSerials;
	}

	public Short getSeasons() {
		return seasons;
	}

	public Short getSeason() {
		return season;
	}

	public String getAreaAndCountry() {
		return areaAndCountry;
	}

	public String getActors() {
		return actors;
	}

	public String getDirectors() {
		return directors;
	}
}
