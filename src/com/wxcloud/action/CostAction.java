package com.wxcloud.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wxcloud.pojo.Cost;
import com.wxcloud.pojo.Cost;
import com.wxcloud.pojo.Cost;
import com.wxcloud.service.CostService;
import com.wxcloud.util.Util;

@SuppressWarnings("unused")
public class CostAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	String msg;

	@Autowired
	private CostService costService;
	private int id;
	private String User;
	private String Project;
	private String Receivable;
	private String Actual;
	private String Time;
	private String State;

	private List<Cost> lst;
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

	public String getUser() {
		return User;
	}

	public void setUser(String User) {
		this.User = User;
	}

	public String getProject() {
		return Project;
	}

	public void setProject(String Project) {
		this.Project = Project;
	}

	public String getReceivable() {
		return Receivable;
	}

	public void setReceivable(String Receivable) {
		this.Receivable = Receivable;
	}

	public String getActual() {
		return Actual;
	}

	public void setActual(String Actual) {
		this.Actual = Actual;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String Time) {
		this.Time = Time;
	}

	public String getState() {
		return State;
	}

	public void setState(String State) {
		this.State = State;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Cost> getLst() {
		return lst;
	}

	public void setLst(List<Cost> lst) {
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

	public String CostQuery() { // 查询
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		String searchType = request.getParameter("searchType");
		String key = request.getParameter("searchname");
		int count = 0;
		pageSize = Util.PAGE_SIZE;
		if ((searchType == null) || (searchType.equals(""))) {
			lst = costService.getCost(currentPage, count);
			count = costService.getCostCount();
		} else if (searchType.equals("query_CostUser")) { // 根据业主查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = costService.getCostByUser(key, currentPage, count);
			count = costService.getCostByUserCount(key);
		} else if (searchType.equals("query_CostProject")) { // 根据项目查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = costService.getCostByProject(key, currentPage, count);
			count = costService.getCostByProjectCount(key);
		} else if (searchType.equals("query_CostState")) { // 根据状态查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = costService.getCostByState(key, currentPage, count);
			count = costService.getCostByStateCount(key);
		}
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = (count / pageSize) + 1;
		}
		return Util.RESULT_SUCCESS;
	}

	public String CostsQuery() { // 查询
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		String key = User;
		int count = 0;
		pageSize = Util.PAGE_SIZE;
		lst = costService.getCostByUser(key, currentPage, count);
		count = costService.getCostByUserCount(key);
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = (count / pageSize) + 1;
		}
		return Util.RESULT_SUCCESS;
	}

	public String CostAdd() { // 添加
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_ADDCOST); // 设置操作类型
		id = 0;
		if ((User == null) || (User.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的名字!");
			return Util.RESULT_FAILED;
		}
		if ((Project == null) || (Project.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的收费项目!");
			return Util.RESULT_FAILED;
		}
		if ((Receivable == null) || (Receivable.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的应收金额!");
			return Util.RESULT_FAILED;
		}
		if ((Actual == null) || (Actual.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的实收金额!");
			return Util.RESULT_FAILED;
		}
		if ((Time == null) || (Time.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的缴费时间!");
			return Util.RESULT_FAILED;
		}
		if ((State == null) || (State.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的缴费状态!");
			return Util.RESULT_FAILED;
		}

		Cost cost = new Cost();
		cost.setActual(Actual);
		cost.setId(id);
		cost.setProject(Project);
		cost.setReceivable(Receivable);
		cost.setState(State);
		cost.setTime(Time);
		cost.setUser(User);

		if (costService.addCost(cost) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "添加失败!");
		return Util.RESULT_FAILED;
	}

	public String CostEdit() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_EDITCOST); // 设置操作类型
		if ((User == null) || (User.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的名字!");
			return Util.RESULT_FAILED;
		}
		if ((Project == null) || (Project.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的收费项目!");
			return Util.RESULT_FAILED;
		}
		if ((Receivable == null) || (Receivable.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的应收金额!");
			return Util.RESULT_FAILED;
		}
		if ((Actual == null) || (Actual.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的实收金额!");
			return Util.RESULT_FAILED;
		}
		if ((Time == null) || (Time.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的缴费时间!");
			return Util.RESULT_FAILED;
		}
		if ((State == null) || (State.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的缴费状态!");
			return Util.RESULT_FAILED;
		}

		Cost cost = new Cost();
		cost.setActual(Actual);
		cost.setId(id);
		cost.setProject(Project);
		cost.setReceivable(Receivable);
		cost.setState(State);
		cost.setTime(Time);
		cost.setUser(User);

		if (costService.editCost(cost) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "更新失败!");
		return Util.RESULT_FAILED;
	}

	public String CostDelete() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession()
				.setAttribute(Util.OPTTYPE, Util.OPTTYPE_DELETECOST); // 设置操作类型

		Cost cost = costService.getCostById(id);

		if (costService.deleteCost(id) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "删除失败!");
		return Util.RESULT_FAILED;
	}

}
