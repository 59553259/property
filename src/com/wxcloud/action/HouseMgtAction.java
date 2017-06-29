package com.wxcloud.action;

import java.util.ArrayList;
import java.util.List;

public class HouseMgtAction extends ExcelTempletAction {

	@Override
	public List<List<String>> getExcelTilte() {
		List<List<String>> lss = new ArrayList<List<String>>();
		List<String> ls1 = new ArrayList<String>();
		ls1.add("HouseNumber");
		ls1.add("Residential");
		ls1.add("Building");
		ls1.add("Unit");
		ls1.add("Park");
		ls1.add("Location");
		ls1.add("Facilities");
		ls1.add("HouseType");
		lss.add(ls1);
		List<String> ls2 = new ArrayList<String>();
		ls2.add("房号");
		ls2.add("小区");
		ls2.add("楼盘");
		ls2.add("住户单元");
		ls2.add("停车场");
		ls2.add("车位的位置");
		ls2.add("小区设施分布");
		ls2.add("户型");
		lss.add(ls2);
		return lss;
	}

	@Override
	public String getExcelName() {
		return "房产信息Excel模板";
	}

}
