package com.wxcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxcloud.dao.CostDao;
import com.wxcloud.pojo.Cost;
import com.wxcloud.service.CostService;

@Service(value = "costService")
public class CostServiceImpl implements CostService {

	@Autowired
	private CostDao costDao;

	@Override
	public Cost getCostById(int id) {
		return costDao.getCostById(id);
	}

	@Override
	public Cost getCostByUser(String User) {
		return costDao.getCostByUser(User);
	}

	@Override
	public Cost getCostByProject(String Project) {
		return costDao.getCostByProject(Project);
	}

	@Override
	public Cost getCostByState(String State) {
		return costDao.getCostByState(State);
	}

	@Override
	public List<Cost> getCost(int currentPaging, int pageSize) {
		return costDao.getCost(currentPaging, pageSize);
	}

	@Override
	public List<Cost> getCostByUser(String User, int currentPaging, int pageSize) {
		return costDao.getCostByUser(User, currentPaging, pageSize);
	}

	@Override
	public List<Cost> getCostByProject(String Project, int currentPaging,
			int pageSize) {
		return costDao.getCostByProject(Project, currentPaging, pageSize);
	}

	@Override
	public List<Cost> getCostByState(String State, int currentPaging,
			int pageSize) {
		return costDao.getCostByState(State, currentPaging, pageSize);
	}

	@Override
	public int getCostCount() {
		return costDao.getCostCount();
	}

	@Override
	public int getCostByUserCount(String User) {
		return costDao.getCostByUserCount(User);
	}

	@Override
	public int getCostByProjectCount(String Project) {
		return costDao.getCostByProjectCount(Project);
	}

	@Override
	public int getCostByStateCount(String State) {
		return costDao.getCostByStateCount(State);
	}

	@Override
	public int addCost(Cost Cost) {
		return costDao.addCost(Cost);
	}

	@Override
	public int editCost(Cost Cost) {
		return costDao.editCost(Cost);
	}

	@Override
	public int deleteCost(int id) {
		return costDao.deleteCost(id);
	}

	@Override
	public List<Cost> findAllCost() {
		return costDao.findAllCost();
	}

}
