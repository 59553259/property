package com.wxcloud.action;

import com.wxcloud.pojo.House;

public class HouseReportMgtAction extends ExcelAction {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getEntityCalss() {
		return House.class;
	}
}
