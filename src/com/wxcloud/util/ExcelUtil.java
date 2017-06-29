package com.wxcloud.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel解析类.
 * <p>
 * 
 */
@SuppressWarnings("deprecation")
public class ExcelUtil {

	private static POIFSFileSystem fs;
	private static HSSFWorkbook wb;
	private static XSSFWorkbook xwb;
	private static XSSFSheet xsheet;
	private static HSSFSheet sheet;
	private static HSSFRow row;
	private static XSSFRow xrow;

	/**
	 * 读取Excel表格表头的内容.
	 * <p>
	 * 
	 * @param InputStream
	 * @return String 表头内容的数组
	 */
	public static String[] readExcelTitle(InputStream is) {
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = getCellFormatValue(row.getCell((short) i));
		}
		return title;
	}

	/**
	 * 读取Excel数据内容.
	 * <p>
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 * @throws IOException
	 */
	public static List<List<String>> readExcelContent(InputStream is)
			throws Exception {
		List<List<String>> content = new ArrayList<List<String>>();
		String str = "";
		int rowNum = 0;
		fs = new POIFSFileSystem(is);
		wb = new HSSFWorkbook(fs);
		sheet = wb.getSheetAt(0);
		// 得到总行数
		rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);

		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 0; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			List<String> list = new ArrayList<String>();
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
				str = getCellFormatValue(row.getCell((short) j)).trim();
				list.add(str);
				j++;
			}
			content.add(list);
			str = "";
		}
		return content;
	}

	/**
	 * 读取Excel2007数据内容.
	 * <p>
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 * @throws IOException
	 */
	public static List<List<String>> readExcelContent2007(InputStream is)
			throws Exception {
		List<List<String>> content = new ArrayList<List<String>>();
		String str = "";
		int rowNum = 0;
		xwb = new XSSFWorkbook(is);
		xsheet = xwb.getSheetAt(0);
		rowNum = xsheet.getLastRowNum();
		xrow = xsheet.getRow(0);
		int colNum = xrow.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 0; i <= rowNum; i++) {
			xrow = xsheet.getRow(i);
			int j = 0;
			List<String> list = new ArrayList<String>();
			while (j < colNum) {
				str = getCellFormatValue2007(xrow.getCell((short) j)).trim();
				list.add(str);
				j++;
			}
			content.add(list);
			str = "";
		}
		return content;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据.
	 * <p>
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	@SuppressWarnings("unused")
	private static String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据.
	 * <p>
	 * 
	 * @param cell
	 *            Excel2007单元格
	 * @return String 单元格数据内容
	 */
	@SuppressWarnings("unused")
	private static String getStringCellValue2007(XSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据.
	 * <p>
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	@SuppressWarnings("unused")
	private static String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据.
	 * <p>
	 * 
	 * @param cell
	 * @return 单元格名
	 */
	private static String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}

	/**
	 * 根据XSSFCell类型设置数据.
	 * <p>
	 * 
	 * @param cell
	 * @return 单元格名
	 */
	private static String getCellFormatValue2007(XSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}

	/**
	 * 
	 * @param filename
	 *            文件名
	 * @param is
	 *            Excel
	 * @return Excel 解析内容
	 * @throws Exception
	 */
	public static List<List<String>> getExcelContent(String filename,
			InputStream is) throws Exception {
		List<List<String>> content = new ArrayList<List<String>>();
		if (filename.endsWith(".xls")) {
			content = ExcelUtil.readExcelContent(is);
		} else if (filename.endsWith(".xlsx")) {
			content = ExcelUtil.readExcelContent2007(is);
		} else {
			return null;
		}
		return content;
	}

}
