package com.wxcloud.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wxcloud.pojo.Repair;
import com.wxcloud.service.RepairService;
import com.wxcloud.util.Util;

@SuppressWarnings("unused")
public class RepairAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	String msg;

	@Autowired
	private RepairService repairService;
	private int id;
	private String RepairTime;
	private String RepairReason;
	private String RepairCondition;
	private String RepairPerson;
	private String RepairType;

	private List<Repair> lst;
	private int currentPage = 1;
	private int totalPage;
	private int totalSize;
	private int pageSize = Util.PAGE_SIZE;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRepairReason() {
		return RepairReason;
	}

	public void setRepairReason(String RepairReason) {
		this.RepairReason = RepairReason;
	}

	public String getRepairCondition() {
		return RepairCondition;
	}

	public void setRepairCondition(String RepairCondition) {
		this.RepairCondition = RepairCondition;
	}

	public String getRepairTime() {
		return RepairTime;
	}

	public void setRepairTime(String RepairTime) {
		this.RepairTime = RepairTime;
	}

	public String getRepairPerson() {
		return RepairPerson;
	}

	public void setRepairPerson(String RepairPerson) {
		this.RepairPerson = RepairPerson;
	}

	public String getRepairType() {
		return RepairType;
	}

	public void setRepairType(String RepairType) {
		this.RepairType = RepairType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Repair> getLst() {
		return lst;
	}

	public void setLst(List<Repair> lst) {
		this.lst = lst;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String RepairQuery() { // 查询
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		String searchType = request.getParameter("searchType");
		String key = request.getParameter("searchname");
		int count = 0;
		pageSize = Util.PAGE_SIZE;
		if ((searchType == null) || (searchType.equals(""))) {
			lst = repairService.getRepair(currentPage, count);
			count = repairService.getRepairCount();
		} else if (searchType.equals("query_RepairType")) { // 根据类型查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = repairService.getRepairByType(key, currentPage, count);
			count = repairService.getRepairTypeCount(key);
		}
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = (count / pageSize) + 1;
		}
		return Util.RESULT_SUCCESS;
	}

	public String RepairAdd() { // 添加
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_ADDREPAIR); // 设置操作类型
		id = 0;
		if ((RepairReason == null) || (RepairReason.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的维修理由!");
			return Util.RESULT_FAILED;
		}
		if ((RepairCondition == null) || (RepairCondition.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的状态!");
			return Util.RESULT_FAILED;
		}
		if ((RepairPerson == null) || (RepairPerson.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的维修人员!");
			return Util.RESULT_FAILED;
		}
		if ((RepairType == null) || (RepairType.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的维修类型!");
			return Util.RESULT_FAILED;
		}
		if (RepairTime == null) {
			addFieldError(Util.MSG, "请输入合法的维修时间!");
			return Util.RESULT_FAILED;
		}

		Repair repair = new Repair();
		repair.setRepairCondition(RepairCondition);
		repair.setRepairPerson(RepairPerson);
		repair.setRepairReason(RepairReason);
		repair.setRepairTime(RepairTime);
		repair.setRepairType(RepairType);

		if (repairService.addRepair(repair) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "添加失败!");
		return Util.RESULT_FAILED;
	}

	public String RepairEdit() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession()
				.setAttribute(Util.OPTTYPE, Util.OPTTYPE_EDITREPAIR); // 设置操作类型
		if ((RepairReason == null) || (RepairReason.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的维修理由!");
			return Util.RESULT_FAILED;
		}
		if ((RepairCondition == null) || (RepairCondition.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的状态!");
			return Util.RESULT_FAILED;
		}
		if ((RepairPerson == null) || (RepairPerson.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的维修人员!");
			return Util.RESULT_FAILED;
		}
		if ((RepairType == null) || (RepairType.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的维修类型!");
			return Util.RESULT_FAILED;
		}
		if (RepairTime == null) {
			addFieldError(Util.MSG, "请输入合法的维修时间!");
			return Util.RESULT_FAILED;
		}

		Repair repair = new Repair();
		repair.setId(id);
		repair.setRepairCondition(RepairCondition);
		repair.setRepairPerson(RepairPerson);
		repair.setRepairReason(RepairReason);
		repair.setRepairTime(RepairTime);
		repair.setRepairType(RepairType);

		if (repairService.editRepair(repair) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "更新失败!");
		return Util.RESULT_FAILED;
	}

	public String RepairDelete() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE,
				Util.OPTTYPE_DELETEREPAIR); // 设置操作类型

		Repair repair = repairService.getRepairById(id);

		if (repairService.deleteRepair(id) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "删除失败!");
		return Util.RESULT_FAILED;
	}

}
