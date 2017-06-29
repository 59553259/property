package com.wxcloud.service;

import java.util.List;

import com.wxcloud.pojo.Notice;

public interface NoticeService {

	public Notice getNoticeById(int id); // 查询-根据id

	public Notice getNoticeByInfoTitle(String InfoTitle); // 查询-根据标题

	public List<Notice> findAllNotice(); // 得到全部信息

	public List<Notice> getNotice(int currentPaging, int pageSize); // 查找-按页

	public List<Notice> getNoticeByInfoTitle(String InfoTitle,
			int currentPaging, int pageSize); // 查找指定标题-按页

	public int getNoticeCount(); // 得到所有数目

	public int getNoticeByInfoTitleCount(String InfoTitle); // 得到指定小区的用户数

	public int addNotice(Notice Notice); // 添加

	public int editNotice(Notice Notice); // 更新

	public int deleteNotice(int id); // 删除

}
