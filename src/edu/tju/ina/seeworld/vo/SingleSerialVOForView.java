package edu.tju.ina.seeworld.vo;

import edu.tju.ina.seeworld.po.resource.SingleSerial;

public class SingleSerialVOForView extends MultimediaVOForView {

	private SerialVOForView serial;
	private Integer commonId;
	private Short episode;
	private String path = "";

	public SingleSerialVOForView(SingleSerial singleSerial) {
		super(singleSerial);
		if (singleSerial != null) {
			this.serial = new SerialVOForView(singleSerial.getSerial());
			this.commonId = singleSerial.getSerial().getId();
			this.episode = singleSerial.getEpisode();
			this.path = singleSerial.getPath();
		}
	}

	public Integer getCommonId() {
		return commonId;
	}

	public Short getEpisode() {
		return episode;
	}

	public String getPath() {
		return path;
	}

	public SerialVOForView getSerial() {
		return serial;
	}
}
