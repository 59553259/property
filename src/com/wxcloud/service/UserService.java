package com.wxcloud.service;

import java.util.List;

import com.wxcloud.pojo.User;

public interface UserService {

	public User login(String name, String password);

	public int addUser(User user);

	public int editUser(User user);

	public int deleteUser(int id);

	public List<User> getUser(int currentPaging, int pageSize);

	public User getUserById(int id);

	public User getUserByUsername(String name);

	public List<User> getUserByPublicname(String name, int currentPaging,
			int pageSize);

	public int getUserCount();

	public int getUserByPublicnameCount(String name);

}