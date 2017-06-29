package com.wxcloud.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wxcloud.pojo.Notice;
import com.wxcloud.service.NoticeService;
import com.wxcloud.util.Util;

@SuppressWarnings("unused")
public class NoticeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String msg;

	@Autowired
	private NoticeService noticeService;
	private int id;
	private String InfoTitle;
	private String InfoContent;
	private Date InfoTime;

	private List<Notice> lst;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfoTitle() {
		return InfoTitle;
	}

	public void setInfoTitle(String InfoTitle) {
		this.InfoTitle = InfoTitle;
	}

	public String getInfoContent() {
		return InfoContent;
	}

	public void setInfoContent(String InfoContent) {
		this.InfoContent = InfoContent;
	}

	public Date getInfoTime() {
		return InfoTime;
	}

	public void setInfoTime(Date InfoTime) {
		this.InfoTime = InfoTime;
	}

	public List<Notice> getLst() {
		return lst;
	}

	public void setLst(List<Notice> lst) {
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

	public String NoticeQuery() { // 查询
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		String searchType = request.getParameter("searchType");
		String key = request.getParameter("searchname");
		int count = 0;
		pageSize = Util.PAGE_SIZE;
		if ((searchType == null) || (searchType.equals(""))) {
			lst = noticeService.getNotice(currentPage, count);
			count = noticeService.getNoticeCount();
		} else if (searchType.equals("query_InfoTitle")) { // 根据标题查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = noticeService.getNoticeByInfoTitle(key, currentPage, count);
			count = noticeService.getNoticeByInfoTitleCount(key);
		}
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = (count / pageSize) + 1;
		}
		return Util.RESULT_SUCCESS;
	}

	public String NoticeAdd() { // 添加
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_ADDNOTICE); // 设置操作类型
		id = 0;
		if ((InfoTitle == null) || (InfoTitle.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的标题!");
			return Util.RESULT_FAILED;
		}
		if ((InfoContent == null) || (InfoContent.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的内容!");
			return Util.RESULT_FAILED;
		}

		Notice notice = new Notice();
		notice.setId(id);
		notice.setInfoContent(InfoContent);
		notice.setInfoTitle(InfoTitle);
		notice.setInfoTime(new Date());

		if (noticeService.addNotice(notice) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "添加失败!");
		return Util.RESULT_FAILED;
	}

	public String NoticeEdit() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession()
				.setAttribute(Util.OPTTYPE, Util.OPTTYPE_EDITNOTICE); // 设置操作类型
		if ((InfoTitle == null) || (InfoTitle.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的标题!");
			return Util.RESULT_FAILED;
		}
		if ((InfoContent == null) || (InfoContent.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的内容!");
			return Util.RESULT_FAILED;
		}

		Notice notice = new Notice();
		notice.setId(id);
		notice.setInfoContent(InfoContent);
		notice.setInfoTitle(InfoTitle);
		notice.setInfoTime(new Date());

		if (noticeService.editNotice(notice) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "更新失败!");
		return Util.RESULT_FAILED;
	}

	public String NoticeDelete() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE,
				Util.OPTTYPE_DELETENOTICE); // 设置操作类型

		Notice notice = noticeService.getNoticeById(id);

		if (noticeService.deleteNotice(id) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "删除失败!");
		return Util.RESULT_FAILED;
	}

}
