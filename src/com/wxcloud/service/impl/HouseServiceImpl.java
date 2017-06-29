package com.wxcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxcloud.dao.HouseDao;
import com.wxcloud.pojo.House;
import com.wxcloud.service.HouseService;

@Service(value = "houseService")
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseDao houseDao;

	@Override
	public House getHouseById(int id) {
		return houseDao.getHouseById(id);
	}

	@Override
	public House getHouseNumber(int HouseNumber) {
		return houseDao.getHouseNumber(HouseNumber);
	}

	@Override
	public House getHouseResidential(String Residential) {
		return houseDao.getHouseResidential(Residential);
	}

	@Override
	public List<House> getHouse(int currentPaging, int pageSize) {
		List<House> list = houseDao.getHouse(currentPaging, pageSize);
		return list;
	}

	@Override
	public List<House> getHouseByResidential(String Residential,
			int currentPaging, int pageSize) {
		List<House> list = houseDao.getHouseByResidential(Residential,
				currentPaging, pageSize);
		return list;
	}

	@Override
	public int getHouseCount() {
		return houseDao.getHouseCount();
	}

	@Override
	public int getHouseByResidentialCount(String Residential) {
		return houseDao.getHouseByResidentialCount(Residential);
	}

	@Override
	public int addHouse(House House) {
		return houseDao.addHouse(House);
	}

	@Override
	public int editHouse(House House) {
		return houseDao.editHouse(House);
	}

	@Override
	public int deleteHouse(int id) {
		return houseDao.deleteHouse(id);
	}

	@Override
	public void addHouses(List<Object> ls) {
		houseDao.insertHouses(ls);

	}

}
