package com.wxcloud.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式转换类.
 * <p>
 */
public class FormatUtil {
	/**
	 * 字符串转Timestamp.
	 * <p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param p
	 *            转换规则
	 * @return 转换后Timestamp
	 */
	public static Timestamp stringToDate(String str, String p) {
		if (str == null || "".equals(str)) {
			return null;
		}
		str = str.trim();
		SimpleDateFormat sim = new SimpleDateFormat(p);
		Date d = null;
		try {
			d = sim.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		if (d == null)
			return null;
		return new Timestamp(d.getTime());
	}

	/**
	 * 字符串转BigDecimal.
	 * <p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 转换后BigDecimal
	 */
	public static BigDecimal StringToBigDecimal(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		str = str.trim();
		BigDecimal big = null;
		try {
			big = new BigDecimal(str);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return big;
	}

	/**
	 * 字符串转Integer.
	 * <p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 转换后Integer
	 */
	public static Integer StringToInt(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		str = str.trim();
		Integer i = null;
		try {
			Double d = Double.parseDouble(str);
			if (d != null) {
				double d2 = d;
				i = (int) d2;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return i;
	}

	/**
	 * 字符串转Integer.
	 * <p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return 转换后Integer
	 */
	public static Double StringToDouble(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		str = str.trim();
		Double d = null;
		try {
			d = Double.parseDouble(str);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return d;
	}

	/**
	 * 日期转字符串.
	 * <p>
	 * 
	 * @param time
	 *            要转换的日期
	 * @param p
	 *            转换规则
	 * @return 转换后字符串
	 */
	public static String DataToString(Timestamp time, String p) {
		if (time == null || p == null)
			return "";
		SimpleDateFormat sim = new SimpleDateFormat(p);
		String str = "";
		try {
			str = sim.format(new Date(time.getTime()));
		} catch (Exception e) {

		}
		return str;
	}

	/**
	 * 日期转字符串.
	 * <p>
	 * 
	 * @param time
	 *            要转换的日期
	 * @param p
	 *            转换规则
	 * @return 转换后字符串
	 */
	public static String DataToString(Date time, String p) {
		if (time == null || p == null)
			return "";
		SimpleDateFormat sim = new SimpleDateFormat(p);
		String str = "";
		try {
			str = sim.format(time);
		} catch (Exception e) {

		}
		return str;
	}

	/**
	 * Object转字符串.
	 * <p>
	 * 
	 * @param o
	 *            对象
	 * @return 转换后字符串
	 */
	public static String ObjToString(Object o) {
		if (o == null)
			return null;
		return o.toString();
	}

	/**
	 * Object转字符串.
	 * <p>
	 * 
	 * @param o
	 *            对象
	 * @return 转换后字符串
	 */
	public static Double ObjToDouble(Object o) {
		if (o == null)
			return null;
		try {
			double b = Double.parseDouble(o.toString());
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Object转Integer
	 * <p>
	 * 
	 * @param o
	 *            对象
	 * @return 转换后Integer
	 */
	public static Integer ObjToInteger(Object o) {
		if (o == null)
			return null;
		try {
			Integer i = Integer.parseInt(o.toString());
			return i;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param o
	 *            对象
	 * @return 转换后Integer
	 */
	public static Integer ObjToInteger2(Object o) {
		if (o == null)
			return null;
		try {
			Double d = Double.parseDouble(o.toString());
			int i = d.intValue();
			return i;
		} catch (Exception e) {
			return null;
		}
	}
}
