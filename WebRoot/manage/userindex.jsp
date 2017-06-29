<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="userloginverify.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html lang="zh">
<head>
<meta charset="utf-8">
<title>智能物业管理系统-主页面</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<link href="../css/vendor/bootstrap/bootstrap.min.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
<link href="../css/minimal.css" rel="stylesheet">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script src="../js/vendor/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../js/vendor/mmenu/js/jquery.mmenu.min.js"></script>
<script type="text/javascript"
	src="../js/vendor/sparkline/jquery.sparkline.min.js"></script>
<script type="text/javascript"
	src="../js/vendor/nicescroll/jquery.nicescroll.min.js"></script>
<script src="../js/minimal.min.js"></script>
</head>
<body class="bg-1">
	<div class="row">
		<div
			class="navbar navbar-default navbar-fixed-top navbar-transparent-black mm-fixed-top"
			role="navigation" id="navbar">
			<div class="navbar-header col-md-2">
				<a class="navbar-brand" href="userindex.jsp"> <strong style="font-size:25px">主页</strong>
				</a>
				<div class="sidebar-collapse">
					<a href="#"> <i class="fa fa-bars"></i>
					</a>
				</div>
			</div>
			<div class="navbar-collapse">

				<ul class="nav navbar-nav refresh">
					<li class="divided"><a href="#" class="page-refresh"><i
							class="fa fa-refresh"></i></a></li>
				</ul>
				<div class="search" id="main-search">
					<i class="fa fa-search"></i> <input type="text"
						placeholder="Search...">
				</div>
				<ul class="nav navbar-nav quick-actions">
					<li class="dropdown divided"><a class="dropdown-toggle button"
						data-toggle="dropdown" href="#"> <i class="fa fa-bell"></i> <span
							class="label label-transparent-black">4</span>
					</a>

						<ul class="dropdown-menu wide arrow nopadding bordered">
							<li><h1>
									你有 <strong>4</strong> 新消息
								</h1></li>

							<li><a
								href="e_queryUser.action?User=<%=session.getAttribute("loginusername")%>"
								target="content"> <span class="label label-green"><i
										class="fa fa-user"></i></span> 个人中心. <span class="small">刚刚</span>
							</a></li>

							<li><a
								href="e_queryDeal.action?user=<%=session.getAttribute("loginusername")%>"
								target="content"> <span class="label label-red"><i
										class="fa fa-rebel"></i></span> 新的报修信息. <span class="small">27
										mins</span>
							</a></li>

							<li><a
								href="e_queryComplaint.action?user=<%=session.getAttribute("loginusername")%>"
								target="content"> <span class="label label-orange"><i
										class="fa fa-wrench"></i></span> 您的投诉处理. <span class="small">36
										mins</span>
							</a></li>

							<li><a
								href="e_queryCost.action?User=<%=session.getAttribute("loginusername")%>"
								target="content"> <span class="label label-amethyst"><i
										class="fa fa-cny"></i></span> 缴费中心. <span class="small">50
										mins</span>
							</a></li>


							<li><a href="#">更多...<i class="fa fa-angle-right"></i></a></li>
						</ul></li>

					<li class="dropdown divided user" id="current-user">
						<div class="profile-photo">
							<img src="../image/user.png" />
						</div> <a class="dropdown-toggle options" data-toggle="dropdown"
						href="#" style="font-size:20px"> <%=session.getAttribute("loginusername")%>
							<i class="fa fa-caret-down"></i>
					</a>

						<ul class="dropdown-menu arrow settings">

							<li>

								<h3>背景色:</h3>
								<ul id="color-schemes">
									<li><a href="#" class="bg-1"></a></li>
									<li><a href="#" class="bg-2"></a></li>
									<li><a href="#" class="bg-3"></a></li>
									<li><a href="#" class="bg-4"></a></li>
									<li><a href="#" class="bg-5"></a></li>
									<li><a href="#" class="bg-6"></a></li>
								</ul>
							</li>

							<li class="divider"></li>

							<li><a
								href='e_queryUser.action?User=<%=session.getAttribute("loginusername")%>'
								target="content"><i class="fa fa-user"></i> 个人信息管理</a></li>

							<li><a
								href="e_queryDeal.action?user=<%=session.getAttribute("loginusername")%>"
								target="content"><i class="fa fa-calendar"></i> 报修状态查看</a></li>
								<li><a
								href="e_queryComplaint.action?user=<%=session.getAttribute("loginusername")%>"
								target="content"><i class="fa fa-wrench"></i> 投诉信息查看</a></li>

							<li><a href="e_queryNotice.action" target="content"><i
									class="fa fa-envelope"></i> 查看物业公告 <span
									class="badge badge-red" id="user-inbox">5</span></a></li>

							<li class="divider"></li>

							<li><a href="userloginout.jsp"><i
									class="fa fa-power-off"></i> 登出</a></li>
						</ul>
					</li>
					<li><a href="#mmenu"><i class="fa fa-comments"></i></a></li>
				</ul>
				<ul class="nav navbar-nav side-nav" id="sidebar">

					<li class="collapsed-content">
						<ul>
							<li class="search"></li>
						</ul>
					</li>

					<li class="navigation" id="navigation"><a href="#"
						class="sidebar-toggle" data-toggle="#navigation"
						style="color:black;font-size:25px">导航菜单 <i
							class="fa fa-angle-up"></i></a>

						<ul class="menu">
							<li><a
								href="e_queryUser.action?User=<%=session.getAttribute("loginusername")%>"
								target="content"> <i class="fa fa-user"></i> 个人中心
							</a></li>

							<li><a
								href="e_queryCost.action?User=<%=session.getAttribute("loginusername")%>"
								target="content"> <i class="fa fa-cny"></i> 费用查询
							</a></li>
							<li><a href="e_queryNotice.action" target="content"> <i
									class="fa fa-desktop"></i> 物业通告
							</a></li>
							<li><a
								href="dealadd.jsp"
								target="content"> <i class="fa fa-tint"></i> 报修申请
							</a></li>
							<li><a
								href="comadd.jsp"
								target="content" " target="content"> <i class="fa fa-wrench"></i>
									进行投诉
							</a></li>



						</ul></li>
				</ul>
			</div>

		</div>

	</div>
	<div id="content" style="margin-top:50px">
	<iframe scrolling="no" name="content" frameborder="0" src="userhome.jsp"
					style="width: 100%"
					onload="this.height=0;var fdh=(this.Document?this.Document.body.scrollHeight:this.contentDocument.body.offsetHeight);this.height=(fdh>800?fdh:800)"></iframe>
	  
	</div>
</body>
</html>