package com.wxcloud.dao;

import java.util.List;

import com.wxcloud.pojo.Cost;

public interface CostDao {

	public Cost getCostById(int id); // 查询-根据id

	public Cost getCostByUser(String User); // 查询-根据业主

	public Cost getCostByProject(String Project); // 查询-根据项目

	public Cost getCostByState(String State); // 查询-根据状态

	public List<Cost> findAllCost(); // 得到全部信息

	public List<Cost> getCost(int currentPaging, int pageSize); // 查找-按页

	public List<Cost> getCostByUser(String User, int currentPaging, int pageSize); // 查找指定业主-按页

	public List<Cost> getCostByProject(String Project, int currentPaging,
			int pageSize); // 查找指定项目-按页

	public List<Cost> getCostByState(String State, int currentPaging,
			int pageSize); // 查找指定状态-按页

	public int getCostCount(); // 得到所有数目

	public int getCostByUserCount(String User); // 得到指定小区的用户数

	public int getCostByProjectCount(String Project); // 得到指定项目的用户数

	public int getCostByStateCount(String State); // 得到指定状态的用户数

	public int addCost(Cost Cost); // 添加

	public int editCost(Cost Cost); // 更新

	public int deleteCost(int id); // 删除

}
