<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html lang="zh">
<head>
<meta charset="utf-8">
<title>智能物业管理管理系统注册页面</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<link href="../css/vendor/bootstrap/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../css/vendor/bootstrap-checkbox.css">
<link href="../css/minimal.css" rel="stylesheet">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/script.js"></script>
<script type="text/javascript" src="../js/register.js"></script>
</head>
<body class="bg-1">
	<div class="main">
		<div class="row">
			<div id="content" class="col-md-12 full-page register">
				<div class="inside-block" style="width:22%">
				<i class="fa fa-user" style="font-size:120px;margin-top:50px"></i>
					<h1>注册	</h1>
					<h5 style="float:right"><span style="color:#DCDCDC;font-size:1px">已有用户名？</span><a href="userlogin.jsp" style="color:#FFFFFF;text-decoration:none">点击登陆</a></h5>
					<form role="form" action="register.action" method="post">
						<section>
							<div class="input-group">
								<input type="text"
										class="form-control" name="User" placeholder="请输入用户名" onkeyup="checkUser(this);">
								<div class="input-group-addon">
								</div>
							</div>
							<div class="input-group">
								<input type="text"
										class="form-control" name="Name" placeholder="请输入真实姓名">
								<div class="input-group-addon">
								</div>
							</div>
							<div class="input-group">
								<input type="password"
										class="form-control" name="Password" id="password1"  placeholder="请输入密码"
										onkeyup="checkPassword(this);">
								<div class="input-group-addon">
								</div>
							</div>
							<div class="input-group">
								<input type="password"
										class="form-control" name="password2" id="password2" placeholder="请再次输入密码"
										onkeyup="checkPasswords(this);">
								<div class="input-group-addon">
								</div>
							</div>
							<div class="input-group">
								<input type="text" placeholder="请输入性别"
										class="form-control" name="Sex" >
								<div class="input-group-addon">
								</div>
							</div>
							<div class="input-group">
								<input type="text"
										class="form-control" name="IdCard" placeholder="请输入您的身份证号"
										onkeyup="checkIdCard(this);">
								<div class="input-group-addon">
								</div>
							</div>
							<div class="input-group">
								<input type="text"
										class="form-control" name="Email" placeholder="请输入邮箱">
								<div class="input-group-addon">
								</div>
							</div>
							<div class="input-group">
								<input type="text"
										class="form-control" name="Phone" placeholder="请输入您的手机号" onkeyup="checkPhone(this);">
								<div class="input-group-addon">
								</div>
							</div>
							<div class="input-group">
								<input type="text"
										class="form-control" name="Pald" placeholder="请输入您居住的楼栋">
								<div class="input-group-addon">
								</div>
							</div>
							<div class="input-group">
								<input type="text"
										class="form-control" name="Hold" placeholder="请输入您房屋的门牌号">
								<div class="input-group-addon">
								</div>
							</div>
						</section>
						<section class="log-in col-md-7" >
							<button type="submit"
								class="btn btn-greensea col-md-5 col-md-offset-7">确定注册</button>
						</section>
						<div>
							<font size="2" face="宋体" style="font-size: 10pt" color="red"><s:fielderror
									key="msg"></s:fielderror></font>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>