package com.wxcloud.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxcloud.service.HouseService;
import com.wxcloud.util.ExcelUtil;
import com.wxcloud.util.FormatUtil;

public class ExcelAction {
	@Autowired
	private HouseService houseService;

	public HouseService getHouseService() {
		return houseService;
	}

	public void setHouseService(HouseService houseService) {
		this.houseService = houseService;
	}

	private File excelFile; // 上传的文件
	private String fileName; // 文件名称

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void reportexcel() {
		String msg = "";
		if (excelFile == null || fileName == null)
			msg = "文件不能为空 ";
		if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
			try {
				FileInputStream is = new FileInputStream(excelFile);
				List<List<String>> lss = ExcelUtil
						.getExcelContent(fileName, is);
				List<Object> ls = getExcelInfo(lss);
				saveExcel(ls);
				msg = "导入成功";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "导入失败 ";
			}
		} else {
			msg = "文件类型错误";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return 获取实体class
	 */
	public Class<Object> getEntityCalss() {
		return null;
	}

	/**
	 * 
	 * @param ls
	 * @return 保存导入前excel前操作
	 */
	public List<Object> beforeSave(List<Object> ls) {
		return ls;
	}

	/**
	 * 
	 * @param ls
	 *            保存导入后excel前操作
	 */
	public void afterSave(List<Object> ls) {

	}

	public void saveExcel(List<Object> ls) {
		if (ls != null && ls.size() > 0) {
			ls = beforeSave(ls);
			if (ls != null && ls.size() > 0) {
				houseService.addHouses(ls);
				afterSave(ls);
			}
		}
	}

	/**
	 * 
	 * @param lss
	 * @return 将excel的数据放到实体中 只支持属性是String, int
	 * @throws Exception
	 */
	public List<Object> getExcelInfo(List<List<String>> lss) throws Exception {
		List<Object> ls = new ArrayList<Object>();
		if (lss == null || lss.size() < 2)
			return ls;
		List<String> l = lss.get(0);
		if (l == null || l.size() == 0)
			return ls;
		int size = l.size();
		Class<Object> c = getEntityCalss();
		int i = 0;
		for (List<String> list : lss) {
			if (list == null)
				continue;
			int size2 = list.size();
			if (size != size2)
				continue;
			if (i == 1 || i == 0) {
				i++;
				continue;
			}
			i++;
			Object o = c.newInstance();
			int j = 0;
			for (String string : list) {
				String m = l.get(j);
				excMothed(o, m, string);
				j++;
			}
			ls.add(o);
		}
		return ls;
	}

	/**
	 * 
	 * @param o
	 * @param m
	 * @param value
	 *            执行set方法赋值
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public void excMothed(Object o, String m, String value)
			throws IllegalArgumentException, IllegalAccessException {
		@SuppressWarnings("rawtypes")
		Class clazz = o.getClass();
		Field[] fs = clazz.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			String name = f.getName();
			if (!name.equals(m))
				continue;
			f.setAccessible(true); // 设置些属性是可以访问的
			String type = f.getType().toString();// 得到此属性的类型
			if (type.endsWith("String")) {
				f.set(o, value); // 给属性设值
			} else if (type.endsWith("int") || type.endsWith("Integer")) {
				f.set(o, FormatUtil.StringToInt(value)); // 给属性设值
			}
		}
	}
}
