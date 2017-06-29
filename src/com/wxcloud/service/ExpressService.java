package com.wxcloud.service;

import java.util.List;

import com.wxcloud.pojo.Express;

public interface ExpressService {

	public Express getExpressById(int id); // 查询-根据id

	public Express getExpressByPhone(String Phone); // 查询-根据手机号

	public Express getExpressByState(String State); // 查询-根据状态

	public List<Express> findAllExpreee(); // 得到全部信息

	public List<Express> getExpress(int currentPaging, int pageSize); // 查找-按页

	public List<Express> getExpressByPhone(String Phone, int currentPaging,
			int pageSize); // 查找指定手机号-按页

	public List<Express> getExpressByState(String State, int currentPaging,
			int pageSize); // 查找指定状态-按页

	public int getExpressCount(); // 得到所有数目

	public int getExpressByPhoneCount(String Phone); // 得到指定手机的数目

	public int getExpressByStateCount(String State); // 得到指定状态的数目

	public int addExpress(Express express); // 添加

	public int editExpress(Express express); // 更新

	public int deleteExpress(int id); // 删除

}
