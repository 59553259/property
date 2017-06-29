package com.wxcloud.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wxcloud.pojo.Deal;
import com.wxcloud.service.DealService;
import com.wxcloud.util.Util;

@SuppressWarnings("unused")
public class DealAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	String msg;

	@Autowired
	private DealService dealService;
	private int id;
	private String user;
	private String reason;
	private Date date;
	private String state;

	private List<Deal> lst;
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

	public DealService getDealservice() {
		return dealService;
	}

	public void setDealservice(DealService dealservice) {
		this.dealService = dealservice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Deal> getLst() {
		return lst;
	}

	public void setLst(List<Deal> lst) {
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

	public String DealQuery() { // 查询
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		String searchType = request.getParameter("searchType");
		String key = request.getParameter("searchname");
		int count = 0;
		pageSize = Util.PAGE_SIZE;
		if ((searchType == null) || (searchType.equals(""))) {
			lst = dealService.getDeal(currentPage, count);
			count = dealService.getDealCount();
		} else if (searchType.equals("query_DealUser")) { // 根据业主查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = dealService.getDealByUser(key, currentPage, count);
			count = dealService.getDealByUserCount(key);
		} else if (searchType.equals("query_DealState")) { // 根据状态查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = dealService.getDealByState(key, currentPage, count);
			count = dealService.getDealByStateCount(key);
		}
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = (count / pageSize) + 1;
		}
		return Util.RESULT_SUCCESS;
	}

	public String DealsQuery() { // 查询
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		String searchType = request.getParameter("searchType");
		String key = user;
		int count = 0;
		pageSize = Util.PAGE_SIZE;
		lst = dealService.getDealByUser(key, currentPage, count);
		count = dealService.getDealByUserCount(key);
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = (count / pageSize) + 1;
		}
		return Util.RESULT_SUCCESS;
	}

	public String DealAdd() { // 添加
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_ADDDEAL); // 设置操作类型
		id = 0;
		if ((user == null) || (user.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的用户!");
			return Util.RESULT_FAILED;
		}
		if ((reason == null) || (reason.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的原因!");
			return Util.RESULT_FAILED;
		}
		if ((state == null) || (state.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的状态!");
			return Util.RESULT_FAILED;
		}
		if (date == null) {
			addFieldError(Util.MSG, "请输入合法的时间!");
			return Util.RESULT_FAILED;
		}

		Deal deal = new Deal();
		deal.setDate(date);
		deal.setId(id);
		deal.setReason(reason);
		deal.setState(state);
		deal.setUser(user);

		if (dealService.addDeal(deal) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "添加失败!");
		return Util.RESULT_FAILED;
	}

	public String DealsAdd() { // 添加
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_EDITUSERS); // 设置操作类型
		id = 0;
		if ((user == null) || (user.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的用户!");
			return Util.RESULT_FAILED;
		}
		if ((reason == null) || (reason.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的原因!");
			return Util.RESULT_FAILED;
		}
		if ((state == null) || (state.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的状态!");
			return Util.RESULT_FAILED;
		}
		if (date == null) {
			addFieldError(Util.MSG, "请输入合法的时间!");
			return Util.RESULT_FAILED;
		}

		Deal deal = new Deal();
		deal.setDate(date);
		deal.setId(id);
		deal.setReason(reason);
		deal.setState(state);
		deal.setUser(user);

		if (dealService.addDeal(deal) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "添加失败!");
		return Util.RESULT_FAILED;
	}

	public String DealsEdit() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_EDITUSERS); // 设置操作类型
		if ((user == null) || (user.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的用户!");
			return Util.RESULT_FAILED;
		}
		if ((reason == null) || (reason.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的原因!");
			return Util.RESULT_FAILED;
		}
		if ((state == null) || (state.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的状态!");
			return Util.RESULT_FAILED;
		}
		if (date == null) {
			addFieldError(Util.MSG, "请输入合法的时间!");
			return Util.RESULT_FAILED;
		}

		Deal deal = new Deal();
		deal.setDate(date);
		deal.setId(id);
		deal.setReason(reason);
		deal.setState(state);
		deal.setUser(user);

		if (dealService.editDeal(deal) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "更新失败!");
		return Util.RESULT_FAILED;
	}

	public String DealEdit() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_EDITDEAL); // 设置操作类型
		if ((user == null) || (user.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的用户!");
			return Util.RESULT_FAILED;
		}
		if ((reason == null) || (reason.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的原因!");
			return Util.RESULT_FAILED;
		}
		if ((state == null) || (state.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的状态!");
			return Util.RESULT_FAILED;
		}
		if (date == null) {
			addFieldError(Util.MSG, "请输入合法的时间!");
			return Util.RESULT_FAILED;
		}

		Deal deal = new Deal();
		deal.setDate(date);
		deal.setId(id);
		deal.setReason(reason);
		deal.setState(state);
		deal.setUser(user);

		if (dealService.editDeal(deal) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "更新失败!");
		return Util.RESULT_FAILED;
	}

	public String DealDelete() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession()
				.setAttribute(Util.OPTTYPE, Util.OPTTYPE_DELETEDEAL); // 设置操作类型

		Deal Deal = dealService.getDealById(id);

		if (dealService.deleteDeal(id) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "删除失败!");
		return Util.RESULT_FAILED;
	}

}
