<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html lang="zh">
<head>
<meta charset="utf-8">
<title>智能物业管理管理系统</title>
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
<script type="text/javascript">
	function loadimage() {
		document.getElementById("randImage").src = "image.jsp?" + Math.random();
	}
</script>
</head>
<body class="bg-1">
	<div id="wrap">
		<div class="row">
			<div id="content" class="col-md-12 full-page login">
				<div class="inside-block" style="width:22%">
					<!-- <img src="../image/logo-big.png" alt class="logo"> -->
					<i class="fa fa-users" style="font-size:120px;margin-top:100px"></i>
					<h1>智能物业管理系统</h1>
					<h5 style="float:right">
						<a href="login.jsp" style="color:#FFFFFF;text-decoration:none">管理员登陆&nbsp&nbsp&nbsp&nbsp&nbsp</a>
						<a href="register.jsp" style="color:#FFFFFF;text-decoration:none">注册</a>
					</h5>
					<form class="form-signin" action="e_login.action" Method="post"
						id="loginform" name="loginform" role="form"
						onSubmit="check_login(this);">
						<section>
							<div class="input-group">
								<input type="text" name="User" class="form-control" 
									placeholder="请输入账户">
								<div class="input-group-addon">
									<i class="fa fa-user"></i>
								</div>
							</div>
							<div class="input-group">
								<input type="password" name="Password" id="Password"
									 class="form-control" placeholder="请输入密码">
								<div class="input-group-addon">
									<i class="fa fa-key"></i>
								</div>
							</div>
							<div class="input-group" style="margin-bottom: -20px">
								<input type="text" class="form-control" name="confirm"
									placeholder="验证码">
								<div class="input-group-addon">
									<i class="fa fa-check-circle-o"></i>
								</div>
							</div>
							<div class="input-group" style="float:right">
								<img alt="code..." name="randImage" id="randImage"
									src="image.jsp" width="60" height="20" border="1"> <span>
									<a href="javascript:loadimage();"><h4 style="color:pink">换一张</h4></a>
								</span>
							</div>
						</section>
						<section class="log-in">
							<button type="submit"
								class="btn btn-greensea col-md-5 col-md-offset-7">登陆</button>
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