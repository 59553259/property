package com.wxcloud.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.wxcloud.util.ExcelExportUtil;

public class ExcelTempletAction {
	/**
	 * 
	 * @param ls
	 *            获取模板的标题 第一行为实体属性 第二行为 实体属性的中文
	 * @return
	 */
	public List<List<String>> getExcelTilte() {
		return null;
	}

	/**
	 * 
	 * @param name
	 *            获取excel模板名
	 * @return
	 */
	public String getExcelName() {
		return null;
	}

	/**
	 * 导出excel模板
	 */
	public void exportExcelTemplet() {
		ExcelExportUtil e = new ExcelExportUtil();
		HttpServletResponse response = ServletActionContext.getResponse();
		e.ExcelExport(getExcelTilte(), getExcelName(), response);
	}

}
