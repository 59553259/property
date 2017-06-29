package com.wxcloud.dao;

import java.util.List;

import com.wxcloud.pojo.Deal;

public interface DealDao {

	public Deal getDealById(int id); // 查询-根据id

	public Deal getDealByUser(String User); // 查询-根据业主

	public Deal getDealByState(String State); // 查询-根据状态

	public List<Deal> findAllDeal(); // 得到全部信息

	public List<Deal> getDeal(int currentPaging, int pageSize); // 查找-按页

	public List<Deal> getDealByUser(String User, int currentPaging, int pageSize); // 查找指定业主-按页

	public List<Deal> getDealByState(String State, int currentPaging,
			int pageSize); // 查找指定状态-按页

	public int getDealCount(); // 得到所有数目

	public int getDealByUserCount(String User); // 得到指定小区的用户数

	public int getDealByStateCount(String State); // 得到指定状态的用户数

	public int addDeal(Deal Deal); // 添加

	public int editDeal(Deal Deal); // 更新

	public int deleteDeal(int id); // 删除
}
