package com.wxcloud.dao;

import java.util.List;

import com.wxcloud.pojo.House;

public interface HouseDao {

	public House getHouseById(int id); // 查询-根据id

	public House getHouseNumber(int HouseNumber); // 查询-根据房号

	public House getHouseResidential(String Residential); // 查询-根据小区

	public List<House> findAllHouse(); // 得到全部信息

	public List<House> getHouse(int currentPaging, int pageSize); // 查找-按页

	public List<House> getHouseByResidential(String Residential,
			int currentPaging, int pageSize); // 查找指定小区-按页

	public int getHouseCount(); // 得到所有房间数

	public int getHouseByResidentialCount(String Residential); // 得到指定小区的用户数

	public int addHouse(House House); // 添加

	public int editHouse(House House); // 更新

	public int deleteHouse(int id); // 删除

	public void insertHouses(List<Object> ls);// 批量新增
}
