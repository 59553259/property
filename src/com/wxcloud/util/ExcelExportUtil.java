package com.wxcloud.util;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelExportUtil {
	@SuppressWarnings("deprecation")
	public void ExcelExport(List<List<String>> lists, String fileName,
			HttpServletResponse response) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(fileName);
		// 第三步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		if (lists == null || lists.size() == 0) {
			fileDownload(response, wb, fileName);
		} else {
			int i = 0;
			for (List<String> ls : lists) {
				HSSFRow row = sheet.createRow((int) i);
				int j = 0;
				for (String string : ls) {
					HSSFCell cell = row.createCell((short) j);
					cell.setCellValue(string);
					cell.setCellStyle(style);
					j++;
				}
				if (i == 0) {
					row.setZeroHeight(true);// 隐藏第一行
				}
				i++;
			}
			fileDownload(response, wb, fileName);
		}
	}

	/**
	 * 导出excel.
	 * <p>
	 * 
	 * @param response
	 * @param file
	 *            文件
	 * @param fileName
	 *            文件名
	 */
	public void fileDownload(HttpServletResponse response, HSSFWorkbook wb,
			String fileName) {
		FileInputStream fis = null;
		OutputStream outputStream = null;
		try {
			// fileName = URLEncoder.encode(fileName, "UTF-8");
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			response.reset();
			response.setContentType("application/octet-stream;charset=UTF-8");
			// response.setHeader(
			// "Content-Disposition",
			// "attachment;filename*=UTF-8''"
			// +fileName) ;// 设置文件名
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName + ".xls");
			// response.addHeader("Content-Length", "" + bs.length); // 设置文件大小
			outputStream = new BufferedOutputStream(response.getOutputStream());
			wb.write(outputStream);
			outputStream.flush();

		} catch (Exception e) {
			// logger.debug("文件下载出错", e);

		} catch (Throwable e) {
			// logger.debug("文件下载出错");
		} finally {
			if (fis != null) {
				try {
					// 关闭流
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
				}
			}
			if (outputStream != null) {
				try {
					// 关闭流
					outputStream.close();
				} catch (IOException e) {
					// logger.debug("关闭流出错");
				}
			}
		}
	}
}
