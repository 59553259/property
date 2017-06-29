package com.wxcloud.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxcloud.pojo.Complaint;
import com.wxcloud.pojo.Repair;
import com.wxcloud.service.ComplaintService;
import com.wxcloud.service.RepairService;

public class ReportAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RepairService repairService;
	@Autowired
	private ComplaintService complaintService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getAllPower() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Repair> powers = repairService.findAllRepair();
		List repairType = new ArrayList();
		List repairTypeCount = new ArrayList();
		for (Repair power : powers) {
			repairType.add(power.getRepairType());
		}
		repairType = removeDuplicate(repairType);

		for (int i = 0; i < repairType.size(); i++) {
			repairTypeCount.add(repairService.getRepairTypeCount(repairType
					.get(i).toString()));
		}

		List<List> merge = new ArrayList();
		merge.add(repairType);
		merge.add(repairTypeCount);

		JSONArray jsonArray = JSONArray.fromObject(merge);
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getAllUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Complaint> powers = complaintService.findAllComplaint();
		List complaint = new ArrayList();
		List complaintCount = new ArrayList();
		for (Complaint power : powers) {
			complaint.add(power.getUser());
		}
		complaint = removeDuplicate(complaint);

		for (int i = 0; i < complaint.size(); i++) {
			complaintCount.add(complaintService
					.getComplaintByUserCount(complaint.get(i).toString()));
		}

		List<List> merge = new ArrayList();
		merge.add(complaint);
		merge.add(complaintCount);

		JSONArray jsonArray = JSONArray.fromObject(merge);
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static List removeDuplicate(List list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
				}
			}
		}
		return list;
	}
}
