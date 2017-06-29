package com.wxcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxcloud.dao.DealDao;
import com.wxcloud.pojo.Deal;
import com.wxcloud.service.DealService;

@Service(value = "dealService")
public class DealServiceImpl implements DealService {

	@Autowired
	private DealDao dealDao;

	@Override
	public Deal getDealById(int id) {
		return dealDao.getDealById(id);
	}

	@Override
	public Deal getDealByUser(String User) {
		return dealDao.getDealByUser(User);
	}

	@Override
	public Deal getDealByState(String State) {
		return dealDao.getDealByState(State);
	}

	@Override
	public List<Deal> findAllDeal() {
		return dealDao.findAllDeal();
	}

	@Override
	public List<Deal> getDeal(int currentPaging, int pageSize) {
		return dealDao.getDeal(currentPaging, pageSize);
	}

	@Override
	public List<Deal> getDealByUser(String User, int currentPaging, int pageSize) {
		return dealDao.getDealByUser(User, currentPaging, pageSize);
	}

	@Override
	public List<Deal> getDealByState(String State, int currentPaging,
			int pageSize) {
		return dealDao.getDealByState(State, currentPaging, pageSize);
	}

	@Override
	public int getDealCount() {
		return dealDao.getDealCount();
	}

	@Override
	public int getDealByUserCount(String User) {
		return dealDao.getDealByUserCount(User);
	}

	@Override
	public int getDealByStateCount(String State) {
		return dealDao.getDealByStateCount(State);
	}

	@Override
	public int addDeal(Deal Deal) {
		return dealDao.addDeal(Deal);
	}

	@Override
	public int editDeal(Deal Deal) {
		return dealDao.editDeal(Deal);
	}

	@Override
	public int deleteDeal(int id) {
		return dealDao.deleteDeal(id);
	}

}
