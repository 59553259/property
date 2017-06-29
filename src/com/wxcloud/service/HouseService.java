package com.wxcloud.service;

import java.util.List;

import com.wxcloud.pojo.House;

public interface HouseService {

	public House getHouseById(int id); // 查询-根据id

	public House getHouseNumber(int HouseNumber); // 查询-根据房间号

	public House getHouseResidential(String Residential); // 查询-根据小区

	public List<House> getHouse(int currentPaging, int pageSize); // 查找-按页

	public List<House> getHouseByResidential(String Residential,
			int currentPaging, int pageSize); // 查找指定小区-按页

	public int getHouseCount(); // 得到所有房间数

	public int getHouseByResidentialCount(String Residential); // 得到指定小区的用户数

	public int addHouse(House House); // 添加

	public int editHouse(House House); // 更新

	public int deleteHouse(int id); // 删除

	public void addHouses(List<Object> ls); // 批量保持
}
