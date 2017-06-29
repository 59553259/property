package com.wxcloud.pojo;

import java.util.Date;

public class User {

	public static final int USER_STATE_DISABLE = 0; // 禁用
	public static final int USER_STATE_ENABLE = 1; // 可用

	private int id;
	private String User;
	private String Name;
	private String Password;
	private String Sex;
	private String IdCard;
	private String Email;
	private String Phone;
	private String Pald;
	private String Hold;
	private int state;
	private Date regdate;

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getIdCard() {
		return IdCard;
	}

	public void setIdCard(String idCard) {
		IdCard = idCard;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getPald() {
		return Pald;
	}

	public void setPald(String pald) {
		Pald = pald;
	}

	public String getHold() {
		return Hold;
	}

	public void setHold(String hold) {
		Hold = hold;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}