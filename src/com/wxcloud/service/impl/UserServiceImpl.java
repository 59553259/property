package com.wxcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxcloud.dao.UserDao;
import com.wxcloud.pojo.User;
import com.wxcloud.service.UserService;
import com.wxcloud.util.Util;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User login(String User, String password) {
		User user = userDao.getUserByName(User);
		if (user != null) {
			String pa = user.getPassword();
			if (password.equals(pa)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public int editUser(User user) {
		return userDao.editUser(user);
	}

	@Override
	public int deleteUser(int id) {
		return userDao.deleteUser(id);
	}

	@Override
	public List<User> getUser(int currentPaging, int pageSize) {
		List<User> list = userDao.getUser(currentPaging, pageSize);
		return list;
	}

	@Override
	public int getUserCount() {
		return userDao.getUserCount();
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public User getUserByUsername(String name) {
		return userDao.getUserByName(name);
	}

	@Override
	public List<User> getUserByPublicname(String name, int currentPaging,
			int pageSize) {
		List<User> list = userDao.getUserByPublicname(name, currentPaging,
				pageSize);
		return list;
	}

	@Override
	public int getUserByPublicnameCount(String name) {
		return userDao.getUserByPublicnameCount(name);
	}

}