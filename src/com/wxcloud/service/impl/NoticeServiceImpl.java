package com.wxcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxcloud.dao.NoticeDao;
import com.wxcloud.pojo.Notice;
import com.wxcloud.service.NoticeService;

@Service(value = "noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;

	@Override
	public Notice getNoticeById(int id) {
		return noticeDao.getNoticeById(id);
	}

	@Override
	public Notice getNoticeByInfoTitle(String InfoTitle) {
		return noticeDao.getNoticeByInfoTitle(InfoTitle);
	}

	@Override
	public List<Notice> findAllNotice() {
		return noticeDao.findAllNotice();
	}

	@Override
	public List<Notice> getNotice(int currentPaging, int pageSize) {
		return noticeDao.getNotice(currentPaging, pageSize);
	}

	@Override
	public List<Notice> getNoticeByInfoTitle(String InfoTitle,
			int currentPaging, int pageSize) {
		return noticeDao.getNoticeByInfoTitle(InfoTitle, currentPaging,
				pageSize);
	}

	@Override
	public int getNoticeCount() {
		return noticeDao.getNoticeCount();
	}

	@Override
	public int getNoticeByInfoTitleCount(String InfoTitle) {
		return noticeDao.getNoticeByInfoTitleCount(InfoTitle);
	}

	@Override
	public int addNotice(Notice Notice) {
		return noticeDao.addNotice(Notice);
	}

	@Override
	public int editNotice(Notice Notice) {
		return noticeDao.editNotice(Notice);
	}

	@Override
	public int deleteNotice(int id) {
		return noticeDao.deleteNotice(id);
	}

}
