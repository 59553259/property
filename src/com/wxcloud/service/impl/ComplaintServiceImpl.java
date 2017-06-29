package com.wxcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxcloud.dao.ComplaintDao;
import com.wxcloud.pojo.Complaint;
import com.wxcloud.service.ComplaintService;

@Service(value = "ComplaintService")
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	private ComplaintDao ComplaintDao;

	@Override
	public Complaint getComplaintById(int id) {
		return ComplaintDao.getComplaintById(id);
	}

	@Override
	public Complaint getComplaintByUser(String User) {
		return ComplaintDao.getComplaintByUser(User);
	}

	@Override
	public Complaint getComplaintByState(String State) {
		return ComplaintDao.getComplaintByState(State);
	}

	@Override
	public List<Complaint> findAllComplaint() {
		return ComplaintDao.findAllComplaint();
	}

	@Override
	public List<Complaint> getComplaint(int currentPaging, int pageSize) {
		return ComplaintDao.getComplaint(currentPaging, pageSize);
	}

	@Override
	public List<Complaint> getComplaintByUser(String User, int currentPaging,
			int pageSize) {
		return ComplaintDao.getComplaintByUser(User, currentPaging, pageSize);
	}

	@Override
	public List<Complaint> getComplaintByState(String State, int currentPaging,
			int pageSize) {
		return ComplaintDao.getComplaintByState(State, currentPaging, pageSize);
	}

	@Override
	public int getComplaintCount() {
		return ComplaintDao.getComplaintCount();
	}

	@Override
	public int getComplaintByUserCount(String User) {
		return ComplaintDao.getComplaintByUserCount(User);
	}

	@Override
	public int getComplaintByStateCount(String State) {
		return ComplaintDao.getComplaintByStateCount(State);
	}

	@Override
	public int addComplaint(Complaint Complaint) {
		return ComplaintDao.addComplaint(Complaint);
	}

	@Override
	public int editComplaint(Complaint Complaint) {
		return ComplaintDao.editComplaint(Complaint);
	}

	@Override
	public int deleteComplaint(int id) {
		return ComplaintDao.deleteComplaint(id);
	}

}
