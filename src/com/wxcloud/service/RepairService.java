package com.wxcloud.service;

import java.util.List;

import com.wxcloud.pojo.Repair;

public interface RepairService {

	public Repair getRepairById(int id); // 查询-根据id

	public int addRepair(Repair repair); // 添加

	public int editRepair(Repair repair); // 更改

	public int deleteRepair(int id); // 删除

	public Repair getRepairType(String RepairType); // 查询-根据类型

	public List<Repair> getRepair(int currentPaging, int pageSize); // 查找-按页

	public List<Repair> getRepairByType(String RepairType, int currentPaging,
			int pageSize); // 查找类型-按页

	public int getRepairTypeCount(String RepairType); // 得到指定类型的用户数

	public int getRepairCount(); // 得到所有记录数目

	public List<Repair> findAllRepair();

}
