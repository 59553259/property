package com.wxcloud.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wxcloud.pojo.House;
import com.wxcloud.service.HouseService;
import com.wxcloud.util.Util;

@SuppressWarnings("unused")
public class HouseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String msg;

	@Autowired
	private HouseService houseService;
	private int id;
	private int HouseNumber;
	private String Residential;
	private String Building;
	private String Unit;
	private String Park;
	private String Location;
	private String Facilities;
	private String HouseType;

	private List<House> lst;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getHouseNumber() {
		return HouseNumber;
	}

	public void setHouseNumber(int HouseNumber) {
		this.HouseNumber = HouseNumber;
	}

	public String getResidential() {
		return Residential;
	}

	public void setResidential(String Residential) {
		this.Residential = Residential;
	}

	public String getBuilding() {
		return Building;
	}

	public void setBuilding(String Building) {
		this.Building = Building;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String Unit) {
		this.Unit = Unit;
	}

	public String getPark() {
		return Park;
	}

	public void setPark(String Park) {
		this.Park = Park;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String Location) {
		this.Location = Location;
	}

	public String getFacilities() {
		return Facilities;
	}

	public void setFacilities(String Facilities) {
		this.Facilities = Facilities;
	}

	public String getHouseType() {
		return HouseType;
	}

	public void setHouseType(String HouseType) {
		this.HouseType = HouseType;
	}

	public List<House> getLst() {
		return lst;
	}

	public void setLst(List<House> lst) {
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

	public String HouseQuery() { // 查询
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		String searchType = request.getParameter("searchType");
		String key = request.getParameter("searchname");
		int count = 0;
		pageSize = Util.PAGE_SIZE;
		if ((searchType == null) || (searchType.equals(""))) {
			lst = houseService.getHouse(currentPage, pageSize);
			count = houseService.getHouseCount();
		} else if (searchType.equals("query_Residential")) { // 根据小区查询
			pageSize = Util.PAGE_MAXSIZE;
			lst = houseService.getHouseByResidential(key, currentPage, count);
			count = houseService.getHouseByResidentialCount(key);
		}
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = (count / pageSize) + 1;
		}
		return Util.RESULT_SUCCESS;
	}

	public String HouseAdd() { // 添加
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_ADDHOUSE); // 设置操作类型
		id = 0;
		if (HouseNumber < 0) {
			addFieldError(Util.MSG, "请输入正确的房号!");
			return Util.RESULT_FAILED;
		}
		if ((Residential == null) || (Residential.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的小区!");
			return Util.RESULT_FAILED;
		}
		if ((Building == null) || (Building.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的楼盘!");
			return Util.RESULT_FAILED;
		}
		if ((Unit == null) || (Unit.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的住户单元!");
			return Util.RESULT_FAILED;
		}
		if ((Park == null) || (Park.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的停车场!");
			return Util.RESULT_FAILED;
		}
		if ((Location == null) || (Location.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的车位的置!");
			return Util.RESULT_FAILED;
		}
		if ((Facilities == null) || (Facilities.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的小区设施分布!");
			return Util.RESULT_FAILED;
		}
		if ((HouseType == null) || (HouseType.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的户型!");
			return Util.RESULT_FAILED;
		}

		House house = new House();
		house.setBuilding(Building);
		house.setFacilities(Facilities);
		house.setHouseNumber(HouseNumber);
		house.setHouseType(HouseType);
		house.setLocation(Location);
		house.setPark(Park);
		house.setResidential(Residential);
		house.setUnit(Unit);
		house.setId(id);

		if (houseService.addHouse(house) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "添加失败!");
		return Util.RESULT_FAILED;
	}

	public String HouseEdit() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE, Util.OPTTYPE_EDITHOUSE); // 设置操作类型
		if (HouseNumber < 0) {
			addFieldError(Util.MSG, "请输入正确的房号!");
			return Util.RESULT_FAILED;
		}
		if ((Residential == null) || (Residential.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的小区!");
			return Util.RESULT_FAILED;
		}
		if ((Building == null) || (Building.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的楼盘!");
			return Util.RESULT_FAILED;
		}
		if ((Unit == null) || (Unit.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的住户单元!");
			return Util.RESULT_FAILED;
		}
		if ((Park == null) || (Park.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的停车场!");
			return Util.RESULT_FAILED;
		}
		if ((Location == null) || (Location.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的车位的置!");
			return Util.RESULT_FAILED;
		}
		if ((Facilities == null) || (Facilities.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的小区设施分布!");
			return Util.RESULT_FAILED;
		}
		if ((HouseType == null) || (HouseType.isEmpty())) {
			addFieldError(Util.MSG, "请输入合法的户型!");
			return Util.RESULT_FAILED;
		}

		House house = new House();
		house.setBuilding(Building);
		house.setFacilities(Facilities);
		house.setHouseNumber(HouseNumber);
		house.setHouseType(HouseType);
		house.setId(id);
		house.setLocation(Location);
		house.setPark(Park);
		house.setResidential(Residential);
		house.setUnit(Unit);

		if (houseService.editHouse(house) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "更新失败!");
		return Util.RESULT_FAILED;
	}

	public String HouseDelete() {
		ActionContext cxt = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) cxt
				.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().setAttribute(Util.OPTTYPE,
				Util.OPTTYPE_DELETEHOUSE); // 设置操作类型

		House house = houseService.getHouseById(id);

		if (houseService.deleteHouse(id) > 0) {
			return Util.RESULT_SUCCESS;
		}
		addFieldError(Util.MSG, "删除失败!");
		return Util.RESULT_FAILED;
	}
}
