package edu.tju.ina.seeworld.vo;

import edu.tju.ina.seeworld.po.resource.Multimedia;

public class SimpleMultimedia {
	private Integer id;
	private String resourceId;
	private String title;
	private String alias;
	private String image;
	private SimpleUserVO user;

	public SimpleMultimedia(Multimedia multimedia) {
		if (multimedia != null) {
			id = multimedia.getId();
			resourceId = multimedia.getResource().getId();
			title = multimedia.toString();
			image = multimedia.getImage();
			alias = multimedia.getAlias();
			user = new SimpleUserVO(multimedia.getUser());
		}
	}
	
	public SimpleMultimedia(Integer id, String title, String resourceId) {
		this.id = id;
		this.resourceId = resourceId;
		this.title = title;
	}

	public SimpleMultimedia() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public SimpleUserVO getUser() {
		if (user == null)
			user = new SimpleUserVO();
		return user;
	}

	public void setUser(SimpleUserVO user) {
		this.user = user;
	}
	
}

