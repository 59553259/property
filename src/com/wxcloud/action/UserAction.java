package com.wxcloud.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wxcloud.pojo.User;
import com.wxcloud.service.UserService;
import com.wxcloud.util.Util;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String msg;

	@Autowired
	private UserService userService;

	private int id;
	private String User;
	private String Name;
	private String Password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getIdCard() {
		return IdCard;
	}

	public void setIdCard(String idCard) {
		IdCard = idCard;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getPald() {
		return Pald;
	}

	public void setPald(String pald) {
		Pald = pald;
	}

	public String getHold() {
		return Hold;
	}

	public void setHold(String hold) {
		Hold = hold;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	private String Sex;
	private String IdCard;
	private String Email;
	private String Phone;
	private String Pald;
	private String Hold;
	private int state;
	private Date regdate;

	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	private List<User> lst;
	private int currentPage = 1;
	private int totalPage;
	private int totalSize;
	private int pageSize = Util.PAGE_SIZE;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<User> getLst() {
		return lst;
	}

	public void setLst(List<User> lst) {
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String userLogin() { // 企业用户登录
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		String rand = (String) request.getSession().getAttribute(Util.RAND);
		String code = request.getParameter("confirm");
		if ((rand == null) || (code == null) || (!rand.equals(code))) {
			addFieldError(Util.MSG, "验证码错误!");
			return Util.RESULT_FAILED;
		}
		if ((User == null) || (User.isEmpty()) || (User.length() < 4)) {
			addFieldError(Util.MSG, "无效的用户名!");
			return Util.RESULT_FAILED;
		}
		if ((Password == null) || (Password.isEmpty())
				|| (Password.length() < 4)) {
			addFieldError(Util.MSG, "无效的密码!");
			return Util.RESULT_FAILED;
		}
		// 验证用户
		User user = userService.login(User, Password);
		if (user == null) {
			addFieldError(Util.MSG, "登陆失败!");
			return Util.RESULT_FAILED;
		}
		request.getSession().setAttribute(Util.LOGIN_USERNAME, user.getUser());
		request.getSession().setAttribute("id", user.getId());
		request.getSession().setAttribute("Name", user.getName());
		request.getSession().setAttribute("Sex", user.getSex());
		request.getSession().setAttribute("IdCard", user.getIdCard());
		request.getSession().setAttribute("Email", user.getEmail());
		request.getSession().setAttribute("Phone", user.getPhone());
		request.getSession().setAttribute("Pald", user.getPald());
		request.getSession().setAttribute("Hold", user.getHold());
		request.getSession().setAttribute("regdate", user.getRegdate());
		id = user.getId();
		Name = user.getName();
		Sex = user.getSex();
		IdCard = user.getIdCard();
		Email = user.getEmail();
		Phone = user.getPhone();
		Pald = user.getPald();
		Hold = user.getHold();
		state = user.getState();
		regdate = user.getRegdate();
		return Util.RESULT_SUCCESS;
	}

	public String userQuery() { // 查询
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		String searchType = request.getParameter("searchType");
		String key = request.getParameter("searchname");
		int count = 0;
		pageSize = Util.PAGE_SIZE;
		if ((searchType == null) || (searchType.equals(""))) {
			lst = userService.getUser(currentPage, pageSize);
			count = userService.getUserCount();
		} else if (searchType.equals("query_username")) { // 根据用户名查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = new ArrayList<User>();
			User tmp = userService.getUserByUsername(key);
			if (tmp != null) {
				lst.add(tmp);
			}
			count = lst.size();
		} else if (searchType.equals("query_Phone")) { // 根据手机号查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = userService.getUserByPublicname(key, currentPage, pageSize);
			count = userService.getUserByPublicnameCount(key);
		}
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = (count / pageSize) + 1;
		}
		return Util.RESULT_SUCCESS;
	}

	public String sselfQuery() {
		if ((User == null) || (User.isEmpty()) || (User.length() < 4)) {
			addFieldError(Util.MSG, "无效的用户名!");
			return Util.RESULT_FAILED;
		}
		User user = userService.getUserByUsername(User);
		if (User == null) {
			addFieldError(Util.MSG, "该用户不存在!");
			return Util.RESULT_FAILED;
		}
		id = user.getId();
		Name = user.getName();
		Sex = user.getSex();
		IdCard = user.getIdCard();
		Email = user.getEmail();
		Phone = user.getPhone();
		Pald = user.getPald();
		Hold = user.getHold();
		state = user.getState();
		regdate = user.getRegdate();
		return Util.RESULT_SUCCESS;
	}

	@SuppressWarnings({ "unused" })
	public String usersQuery() { // 查询
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		String key = User;
		int count = 0;
		pageSize = Util.PAGE_SIZE;
		lst = new ArrayList<User>();
		User tmp = userService.getUserByUsername(key);
		if (tmp != null) {
			lst.add(tmp);
		}
		count = lst.size();

		return Util.RESULT_SUCCESS;
	}

	public String userAdd() { // 添加用户
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_ADDUSER); // 设置操作类型
		if ((User == null) || (User.isEmpty()) || (User.length() < 4)) {
			addFieldError(Util.MSG, "请输入合法的用户名!");
			return Util.RESULT_FAILED;
		}
		if ((Name == null) || (Name.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的姓名!");
			return Util.RESULT_FAILED;
		}
		String pass = request.getParameter("password2");
		if ((Password == null) || (Password.isEmpty())
				|| (Password.length() < 4) || (pass == null)
				|| (!Password.equals(pass))) {
			addFieldError(Util.MSG, "输入的密码不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((Sex == null) || (Sex.isEmpty())) {
			addFieldError(Util.MSG, "输入的性别不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((IdCard == null) || (IdCard.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的身份证号!");
			return Util.RESULT_FAILED;
		}
		if ((Email == null) || (Email.isEmpty()) || (!Util.checkEmail(Email))) {
			addFieldError(Util.MSG, "输入的电子邮箱不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((Phone == null) || (Phone.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的手机号!");
			return Util.RESULT_FAILED;
		}
		if ((Pald == null) || (Pald.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的楼栋!");
			return Util.RESULT_FAILED;
		}
		if ((Hold == null) || (Hold.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的门牌号!");
			return Util.RESULT_FAILED;
		}

		User user = new User();
		user.setUser(User);
		user.setName(Name);
		user.setPassword(Password);
		user.setSex(Sex);
		user.setIdCard(IdCard);
		user.setEmail(Email);
		user.setPhone(Phone);
		user.setPald(Pald);
		user.setHold(Hold);
		user.setState(1);
		user.setRegdate(new Date());

		if (userService.addUser(user) > 0) {
			addFieldError(Util.MSG, "注册成功!请登录");
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "添加用户失败!");
		return Util.RESULT_FAILED;
	}

	public String userEdit() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_EDITUSER); // 设置操作类型
		if ((User == null) || (User.isEmpty()) || (User.length() < 4)) {
			addFieldError(Util.MSG, "请输入合法的用户名!");
			return Util.RESULT_FAILED;
		}
		if ((Name == null) || (Name.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的姓名!");
			return Util.RESULT_FAILED;
		}
		String pass = request.getParameter("password2");
		if ((Password == null) || (Password.isEmpty())
				|| (Password.length() < 4) || (pass == null)
				|| (!Password.equals(pass))) {
			addFieldError(Util.MSG, "输入的密码不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((Sex == null) || (Sex.isEmpty())) {
			addFieldError(Util.MSG, "输入的性别不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((IdCard == null) || (IdCard.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的身份证号!");
			return Util.RESULT_FAILED;
		}
		if ((Email == null) || (Email.isEmpty()) || (!Util.checkEmail(Email))) {
			addFieldError(Util.MSG, "输入的电子邮箱不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((Phone == null) || (Phone.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的手机号!");
			return Util.RESULT_FAILED;
		}
		if ((Pald == null) || (Pald.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的楼栋!");
			return Util.RESULT_FAILED;
		}
		if ((Hold == null) || (Hold.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的门牌号!");
			return Util.RESULT_FAILED;
		}

		User user = new User();
		user.setId(id);
		user.setUser(User);
		user.setName(Name);
		user.setPassword(Password);
		user.setSex(Sex);
		user.setIdCard(IdCard);
		user.setEmail(Email);
		user.setPhone(Phone);
		user.setPald(Pald);
		user.setHold(Hold);
		user.setState(1);

		user.setRegdate(regdate);
		if (userService.editUser(user) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "更新用户失败!");
		return Util.RESULT_FAILED;
	}

	public String usersEdit() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_EDITUSERS1); // 设置操作类型
		if ((User == null) || (User.isEmpty()) || (User.length() < 4)) {
			addFieldError(Util.MSG, "请输入合法的用户名!");
			return Util.RESULT_FAILED;
		}
		if ((Name == null) || (Name.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的姓名!");
			return Util.RESULT_FAILED;
		}
		String pass = request.getParameter("password2");
		if ((Password == null) || (Password.isEmpty())
				|| (Password.length() < 4) || (pass == null)
				|| (!Password.equals(pass))) {
			addFieldError(Util.MSG, "输入的密码不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((Sex == null) || (Sex.isEmpty())) {
			addFieldError(Util.MSG, "输入的性别不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((IdCard == null) || (IdCard.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的身份证号!");
			return Util.RESULT_FAILED;
		}
		if ((Email == null) || (Email.isEmpty()) || (!Util.checkEmail(Email))) {
			addFieldError(Util.MSG, "输入的电子邮箱不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((Phone == null) || (Phone.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的手机号!");
			return Util.RESULT_FAILED;
		}
		if ((Pald == null) || (Pald.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的楼栋!");
			return Util.RESULT_FAILED;
		}
		if ((Hold == null) || (Hold.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的门牌号!");
			return Util.RESULT_FAILED;
		}

		User user = new User();
		user.setId(id);
		user.setUser(User);
		user.setName(Name);
		user.setPassword(Password);
		user.setSex(Sex);
		user.setIdCard(IdCard);
		user.setEmail(Email);
		user.setPhone(Phone);
		user.setPald(Pald);
		user.setHold(Hold);
		user.setState(1);
	    user.setRegdate(regdate);
		if (userService.editUser(user) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "更新用户失败!");
		return Util.RESULT_FAILED;
	}

	public String userEditSelf() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE,
				Util.OPTTYPE_EDITSELFUSER); // 设置操作类型
		if ((Name == null) || (Name.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的姓名!");
			return Util.RESULT_FAILED;
		}
		String pass = request.getParameter("password2");
		if ((Password == null) || (Password.isEmpty())
				|| (Password.length() < 4) || (pass == null)
				|| (!Password.equals(pass))) {
			addFieldError(Util.MSG, "输入的密码不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((Sex == null) || (Sex.isEmpty())) {
			addFieldError(Util.MSG, "输入的性别不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((IdCard == null) || (IdCard.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的身份证号!");
			return Util.RESULT_FAILED;
		}
		if ((Email == null) || (Email.isEmpty()) || (!Util.checkEmail(Email))) {
			addFieldError(Util.MSG, "输入的电子邮箱不符合要求!");
			return Util.RESULT_FAILED;
		}
		if ((Phone == null) || (Phone.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的手机号!");
			return Util.RESULT_FAILED;
		}
		if ((Pald == null) || (Pald.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的楼栋!");
			return Util.RESULT_FAILED;
		}
		if ((Hold == null) || (Hold.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的门牌号!");
			return Util.RESULT_FAILED;
		}
		User user = new User();
		user.setUser(User);
		user.setName(Name);
		user.setPassword(Password);
		user.setSex(Sex);
		user.setIdCard(IdCard);
		user.setEmail(Email);
		user.setPhone(Phone);
		user.setPald(Pald);
		user.setHold(Hold);

		if (userService.editUser(user) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "更新个人信息失败!");
		return Util.RESULT_FAILED;
	}

	@SuppressWarnings("unused")
	public String userDelete() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession()
				.setAttribute(Util.OPTTYPE, Util.OPTTYPE_DELETEUSER); // 设置操作类型

		User user = userService.getUserById(id);

		if (userService.deleteUser(id) > 0) {
			return Util.RESULT_SUCCESS;
		}

		addFieldError(Util.MSG, "删除用户失败!");
		return Util.RESULT_FAILED;
	}
}