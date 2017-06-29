package com.wxcloud.dao;

import java.util.List;

import com.wxcloud.pojo.Complaint;

public interface ComplaintDao {

	public Complaint getComplaintById(int id); // 查询-根据id

	public Complaint getComplaintByUser(String User); // 查询-根据业主

	public Complaint getComplaintByState(String State); // 查询-根据状态

	public List<Complaint> findAllComplaint(); // 得到全部信息

	public List<Complaint> getComplaint(int currentPaging, int pageSize); // 查找-按页

	public List<Complaint> getComplaintByUser(String User, int currentPaging,
			int pageSize); // 查找指定业主-按页

	public List<Complaint> getComplaintByState(String State, int currentPaging,
			int pageSize); // 查找指定状态-按页

	public int getComplaintCount(); // 得到所有数目

	public int getComplaintByUserCount(String User); // 得到指定小区的用户数

	public int getComplaintByStateCount(String State); // 得到指定状态的用户数

	public int addComplaint(Complaint Complaint); // 添加

	public int editComplaint(Complaint Complaint); // 更新

	public int deleteComplaint(int id); // 删除
}
