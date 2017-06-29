package com.wxcloud.pojo;

import java.util.Date;

public class Express {
	private int id;
	private String CustomName;
	private String CustomPhone;
	private Date ArriveTime;
	private String State;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomName() {
		return CustomName;
	}

	public void setCustomName(String customName) {
		CustomName = customName;
	}

	public String getCustomPhone() {
		return CustomPhone;
	}

	public void setCustomPhone(String customPhone) {
		CustomPhone = customPhone;
	}

	public Date getArriveTime() {
		return ArriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		ArriveTime = arriveTime;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

}
