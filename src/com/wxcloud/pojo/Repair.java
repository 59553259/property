package com.wxcloud.pojo;

public class Repair {

	private int id;
	private String RepairTime;
	private String RepairReason;
	private String RepairCondition;
	private String RepairPerson;
	private String RepairType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRepairReason() {
		return RepairReason;
	}

	public void setRepairReason(String RepairReason) {
		this.RepairReason = RepairReason;
	}

	public String getRepairCondition() {
		return RepairCondition;
	}

	public void setRepairCondition(String RepairCondition) {
		this.RepairCondition = RepairCondition;
	}

	public String getRepairTime() {
		return RepairTime;
	}

	public void setRepairTime(String RepairTime) {
		this.RepairTime = RepairTime;
	}

	public String getRepairPerson() {
		return RepairPerson;
	}

	public void setRepairPerson(String RepairPerson) {
		this.RepairPerson = RepairPerson;
	}

	public String getRepairType() {
		return RepairType;
	}

	public void setRepairType(String RepairType) {
		this.RepairType = RepairType;
	}

}