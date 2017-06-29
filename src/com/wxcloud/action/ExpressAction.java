package com.wxcloud.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wxcloud.pojo.Express;
import com.wxcloud.service.ExpressService;
import com.wxcloud.util.Util;

@SuppressWarnings("unused")
public class ExpressAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	String msg;

	@Autowired
	private ExpressService expressService;

	private int id;
	private String CustomName;
	private String CustomPhone;
	private Date ArriveTime;
	private String State;

	private List<Express> lst;
	private int currentPage = 1;
	private int totalPage;
	private int totalSize;
	private int pageSize = Util.PAGE_SIZE;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ExpressService getExpressService() {
		return expressService;
	}

	public void setExpressService(ExpressService expressService) {
		this.expressService = expressService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomName() {
		return CustomName;
	}

	public void setCustomName(String customName) {
		CustomName = customName;
	}

	public String getCustomPhone() {
		return CustomPhone;
	}

	public void setCustomPhone(String customPhone) {
		CustomPhone = customPhone;
	}

	public Date getArriveTime() {
		return ArriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		ArriveTime = arriveTime;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public List<Express> getLst() {
		return lst;
	}

	public void setLst(List<Express> lst) {
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

	public String ExpressQuery() { // 查询
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		String searchType = request.getParameter("searchType");
		String key = request.getParameter("searchname");
		int count = 0;
		pageSize = Util.PAGE_SIZE;
		if ((searchType == null) || (searchType.equals(""))) {
			lst = expressService.getExpress(currentPage, count);
			count = expressService.getExpressCount();
		} else if (searchType.equals("query_ExpressPhone")) { // 根据手机号查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = expressService.getExpressByPhone(key, currentPage, count);
			count = expressService.getExpressByPhoneCount(key);
		} else if (searchType.equals("query_ExpressState")) { // 根据状态查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = expressService.getExpressByState(key, currentPage, count);
			count = expressService.getExpressByStateCount(key);
		}
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = (count / pageSize) + 1;
		}
		return Util.RESULT_SUCCESS;
	}

	public String ExpressAdd() { // 添加
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession()
				.setAttribute(Util.OPTTYPE, Util.OPTTYPE_ADDEXPRESS); // 设置操作类型
		id = 0;
		if ((CustomName == null) || (CustomName.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的名字!");
			return Util.RESULT_FAILED;
		}
		if ((CustomPhone == null) || (CustomPhone.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的手机号!");
			return Util.RESULT_FAILED;
		}
		if (ArriveTime == null) {
			addFieldError(Util.MSG, "请输入合法的到达时间!");
			return Util.RESULT_FAILED;
		}
		if ((State == null) || (State.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的状态!");
			return Util.RESULT_FAILED;
		}

		Express express = new Express();
		express.setId(id);
		express.setCustomName(CustomName);
		express.setCustomPhone(CustomPhone);
		express.setArriveTime(ArriveTime);
		express.setState(State);

		if (expressService.addExpress(express) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "添加失败!");
		return Util.RESULT_FAILED;
	}

	public String ExpressEdit() { // 更新
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE,
				Util.OPTTYPE_EDITEXPRESS); // 设置操作类型
		if ((CustomName == null) || (CustomName.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的名字!");
			return Util.RESULT_FAILED;
		}
		if ((CustomPhone == null) || (CustomPhone.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的手机号!");
			return Util.RESULT_FAILED;
		}
		if (ArriveTime == null) {
			addFieldError(Util.MSG, "请输入合法的到达时间!");
			return Util.RESULT_FAILED;
		}
		if ((State == null) || (State.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的状态!");
			return Util.RESULT_FAILED;
		}

		Express express = new Express();
		express.setId(id);
		express.setCustomName(CustomName);
		express.setCustomPhone(CustomPhone);
		express.setArriveTime(ArriveTime);
		express.setState(State);

		if (expressService.editExpress(express) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "更新失败!");
		return Util.RESULT_FAILED;
	}

	public String ExpressDelete() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE,
				Util.OPTTYPE_DELETEEXPRESS); // 设置操作类型

		Express Express = expressService.getExpressById(id);

		if (expressService.deleteExpress(id) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "删除失败!");
		return Util.RESULT_FAILED;
	}
}
