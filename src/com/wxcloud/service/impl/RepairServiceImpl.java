package com.wxcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxcloud.dao.RepairDao;
import com.wxcloud.pojo.Repair;
import com.wxcloud.service.RepairService;

@Service(value = "repairService")
public class RepairServiceImpl implements RepairService {

	@Autowired
	private RepairDao repairDao;

	@Override
	public Repair getRepairById(int id) {
		return repairDao.getRepairById(id);
	}

	@Override
	public int addRepair(Repair repair) {
		return repairDao.addRepair(repair);

	}

	@Override
	public int editRepair(Repair repair) {
		return repairDao.editRepair(repair);
	}

	@Override
	public int deleteRepair(int id) {
		return repairDao.deleteRepair(id);
	}

	@Override
	public Repair getRepairType(String RepairType) {
		return repairDao.getRepairType(RepairType);
	}

	@Override
	public List<Repair> getRepair(int currentPaging, int pageSize) {
		return repairDao.getRepair(currentPaging, pageSize);
	}

	@Override
	public List<Repair> getRepairByType(String RepairType, int currentPaging,
			int pageSize) {
		return repairDao.getRepairByType(RepairType, currentPaging, pageSize);
	}

	@Override
	public int getRepairTypeCount(String RepairType) {
		return repairDao.getRepairTypeCount(RepairType);
	}

	@Override
	public int getRepairCount() {
		return repairDao.getRepairCount();
	}

	@Override
	public List<Repair> findAllRepair() {
		return repairDao.findAllRepair();
	}

}
