package com.wxcloud.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	public static final String JSON_INFO_VERSION = "201728760";
	public static final String JSON_INFO_NAME = "test menu";

	public static final String RESULT_SUCCESS = "success";
	public static final String RESULT_FAILED = "failed";

	public static final int PAGE_SIZE = 10; // 单页数据个数
	public static final int PAGE_MAXSIZE = 100; // 单页数据最大个数

	public static final String MSG = "msg"; // 页面提示信息
	public static final String RAND = "rand"; // 验证码
	public static final String OPTTYPE = "opttype"; // 操作类型
	public static final String LOGIN_ADMINNAME = "loginadminname"; // 系统管理员用户。如果不为空，则代表是系统管理员用户登录
	public static final String LOGIN_USERNAME = "loginusername"; // 企业用户名。如果不为空，则代表是企业用户登录

	// 操作类型
	public static final int OPTTYPE_EDITSELFADMIN = 1; // 个人设置（系统管理员）
	public static final int OPTTYPE_ADDADMIN = 10; // 添加系统管理员
	public static final int OPTTYPE_EDITADMIN = 11; // 更新系统管理员
	public static final int OPTTYPE_DELETEADMIN = 12; // 删除系统管理员
	public static final int OPTTYPE_ADDUSER = 13; // 添加企业用户
	public static final int OPTTYPE_EDITUSER = 14; // 更新企业用户
	public static final int OPTTYPE_EDITUSERS = 141; // 更新企业用户
	public static final int OPTTYPE_EDITUSERS1 = 1411; // 更新企业用户
	public static final int OPTTYPE_DELETEUSER = 15; // 删除企业用户
	public static final int OPTTYPE_ADDHOUSE = 16; // 添加房产
	public static final int OPTTYPE_EDITHOUSE = 17; // 更新房产
	public static final int OPTTYPE_DELETEHOUSE = 18; // 删除房产
	public static final int OPTTYPE_ADDREPAIR = 19; // 添加维修
	public static final int OPTTYPE_EDITREPAIR = 20; // 更新维修
	public static final int OPTTYPE_DELETEREPAIR = 21; // 删除维修
	public static final int OPTTYPE_ADDCOST = 22; // 添加费用
	public static final int OPTTYPE_EDITCOST = 23; // 更新费用
	public static final int OPTTYPE_DELETECOST = 24; // 删除费用
	public static final int OPTTYPE_ADDNOTICE = 25; // 添加公告
	public static final int OPTTYPE_EDITNOTICE = 26; // 更新公告
	public static final int OPTTYPE_DELETENOTICE = 27; // 删除公告
	public static final int OPTTYPE_ADDEXPRESS = 28; // 添加快递
	public static final int OPTTYPE_EDITEXPRESS = 29; // 更新快递
	public static final int OPTTYPE_DELETEEXPRESS = 30; // 删除快递
	public static final int OPTTYPE_ADDDEAL = 31; // 添加报修
	public static final int OPTTYPE_EDITDEAL = 32; // 更新报修
	public static final int OPTTYPE_DELETEDEAL = 33; // 删除报修
	public static final int OPTTYPE_ADDCOMPLAINT = 34; // 添加投诉
	public static final int OPTTYPE_EDITCOMPLAINT = 35; // 更新投诉
	public static final int OPTTYPE_DELETECOMPLAINT = 36; // 删除投诉

	public static final int OPTTYPE_EDITSELFUSER = 1; // 个人设置（企业用户）

	public static final String PATH_AVATAR = "/data/avatar/";

	public static boolean checkEmail(String email) {
		Pattern pattern = Pattern
				.compile("^\\w+([-.]\\w+)*@\\w+([-]\\w+)*\\.(\\w+([-]\\w+)*\\.)*[a-z]{2,3}$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static String getNo() {
		// 系统当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
		String nowDate = sdf.format(new Date());
		// 随机数
		String iRandom = Math.round(Math.random() * 900) + 100 + "";
		// 整合一个code
		return nowDate + iRandom;
	}

	public static long getFileSize(File f) {
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
				s = fis.available();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}

	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public final static int getWordCount(String s) {
		int len = 0;
		for (int i = 0; i < s.length(); i++) {
			int ascii = Character.codePointAt(s, i);
			if ((ascii >= 0) && (ascii <= 255)) {
				len++;
			} else {
				len += 2;
			}
		}
		return len;
	}
}