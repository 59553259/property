package com.wxcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxcloud.dao.ExpressDao;
import com.wxcloud.pojo.Express;
import com.wxcloud.service.ExpressService;

@Service(value = "expressService")
public class ExpressServiceImpl implements ExpressService {

	@Autowired
	private ExpressDao expressDao;

	@Override
	public Express getExpressById(int id) {
		return expressDao.getExpressById(id);
	}

	@Override
	public Express getExpressByPhone(String Phone) {
		return expressDao.getExpressByPhone(Phone);
	}

	@Override
	public Express getExpressByState(String State) {
		return expressDao.getExpressByState(State);
	}

	@Override
	public List<Express> findAllExpreee() {
		return expressDao.findAllExpreee();
	}

	@Override
	public List<Express> getExpress(int currentPaging, int pageSize) {
		return expressDao.getExpress(currentPaging, pageSize);
	}

	@Override
	public List<Express> getExpressByPhone(String Phone, int currentPaging,
			int pageSize) {
		return expressDao.getExpressByPhone(Phone, currentPaging, pageSize);
	}

	@Override
	public List<Express> getExpressByState(String State, int currentPaging,
			int pageSize) {
		return expressDao.getExpressByState(State, currentPaging, pageSize);
	}

	@Override
	public int getExpressCount() {
		return expressDao.getExpressCount();
	}

	@Override
	public int getExpressByPhoneCount(String Phone) {
		return expressDao.getExpressByPhoneCount(Phone);
	}

	@Override
	public int getExpressByStateCount(String State) {
		return expressDao.getExpressByStateCount(State);
	}

	@Override
	public int addExpress(Express express) {
		return expressDao.addExpress(express);
	}

	@Override
	public int editExpress(Express express) {
		return expressDao.editExpress(express);
	}

	@Override
	public int deleteExpress(int id) {
		return expressDao.deleteExpress(id);
	}

}
