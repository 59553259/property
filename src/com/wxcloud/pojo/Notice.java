package com.wxcloud.pojo;

import java.util.Date;

public class Notice {

	private int id;
	private String InfoTitle;
	private String InfoContent;
	private Date InfoTime;

	public void setInfoContent(String infoContent) {
		InfoContent = infoContent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfoTitle() {
		return InfoTitle;
	}

	public void setInfoTitle(String InfoTitle) {
		this.InfoTitle = InfoTitle;
	}

	public String getInfoContent() {
		return InfoContent;
	}

	public Date getInfoTime() {
		return InfoTime;
	}

	public void setInfoTime(Date InfoTime) {
		this.InfoTime = InfoTime;
	}

}
