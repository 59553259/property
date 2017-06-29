<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="loginverify.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html lang="zh">
<head>
<meta charset="utf-8">
<title>智能物业管理系统-主页面</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<link href="../css/vendor/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
<link href="../css/minimal.css" rel="stylesheet">
<script type="text/javascript" src="../js/jquery.min.js"></script>
    <script src="../js/vendor/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/vendor/mmenu/js/jquery.mmenu.min.js"></script>
    <script type="text/javascript" src="../js/vendor/sparkline/jquery.sparkline.min.js"></script>
    <script type="text/javascript" src="../js/vendor/nicescroll/jquery.nicescroll.min.js"></script>
    <script src="../js/minimal.min.js"></script>
</head>
<body class="bg-6" >
	<div class="row">
		<div
			class="navbar navbar-default navbar-fixed-top navbar-transparent-black mm-fixed-top"
			role="navigation" id="navbar">
			<div class="navbar-header col-md-2">
				<a class="navbar-brand" href="index.jsp"> <strong style="font-size:25px">主页</strong>
				</a>
				<div class="sidebar-collapse">
					<a href="#"> <i class="fa fa-bars" style="font-size:22px"></i>
					</a>
				</div>
			</div>
			<div class="navbar-collapse">

				<ul class="nav navbar-nav refresh">
					<li class="divided" style="font-size:22px"><a href="#" class="page-refresh"><i
							class="fa fa-refresh"></i></a></li>
				</ul>
				<div class="search" id="main-search">
					<i class="fa fa-search" style="font-size:22px"></i> <input type="text"
						placeholder="Search...">
				</div>
				<ul class="nav navbar-nav quick-actions">
					<li class="dropdown divided"><a class="dropdown-toggle button"
						data-toggle="dropdown" href="#"> <i class="fa fa-tasks"></i> 
					</a>
						<ul class="dropdown-menu wide arrow nopadding bordered" style="font-size:22px">
							<li><h1 style="font-size:22px">
									使用的技术
								</h1></li>
							<li><a href="#">
									<div class="task-info">
										<div class="desc">Html</div>
										<div class="percent">80%</div>
									</div>
									<div class="progress progress-striped progress-thin">
										<div class="progress-bar progress-bar-green"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 80%">
											<span class="sr-only">40% Complete (success)</span>
										</div>
									</div>
							</a></li>
							<li><a href="#">
									<div class="task-info">
										<div class="desc">CSS</div>
										<div class="percent">15%</div>
									</div>
									<div class="progress progress-striped active progress-thin">
										<div class="progress-bar progress-bar-cyan" role="progressbar"
											aria-valuenow="45" aria-valuemin="0" aria-valuemax="100"
											style="width: 15%">
											<span class="sr-only">45% Complete</span>
										</div>
									</div>
							</a></li>
							<li><a href="#">
									<div class="task-info">
										<div class="desc">ajax</div>
										<div class="percent">5%</div>
									</div>
									<div class="progress progress-striped progress-thin">
										<div class="progress-bar progress-bar-orange"
											role="progressbar" aria-valuenow="45" aria-valuemin="0"
											aria-valuemax="100" style="width: 5%">
											<span class="sr-only">5% Complete (warning)</span>
										</div>
									</div>
							</a></li>
							<li><a href="#">
									<div class="task-info">
										<div class="desc">JavaScript</div>
										<div class="percent">30%</div>
									</div>
									<div class="progress progress-striped progress-thin">
										<div class="progress-bar progress-bar-red" role="progressbar"
											aria-valuenow="45" aria-valuemin="0" aria-valuemax="100"
											style="width: 30%">
											<span class="sr-only">30% Complete (danger)</span>
										</div>
									</div>
							</a></li>
							<li><a href="#">
									<div class="task-info">
										<div class="desc">bootstrap</div>
										<div class="percent">60%</div>
									</div>
									<div class="progress progress-striped progress-thin">
										<div class="progress-bar progress-bar-amethyst"
											role="progressbar" aria-valuenow="45" aria-valuemin="0"
											aria-valuemax="100" style="width: 60%">
											<span class="sr-only">60% Complete</span>
										</div>
									</div>
							</a></li>
						</ul></li>

					<li class="dropdown divided"><a class="dropdown-toggle button"
						data-toggle="dropdown" href="#"> <i class="fa fa-bell"></i> <span
							class="label label-transparent-black">5</span>
					</a>

						<ul class="dropdown-menu wide arrow nopadding bordered">
							<li><h1 style="font-size:22px">
									你有 <strong>5</strong> 新消息
								</h1></li>

							<li><a href="m_queryUser.action" target="content"> <span class="label label-green"><i
										class="fa fa-user"></i></span> 有新的用户信息. <span
									class="small">刚刚</span>
							</a></li>

							<li><a href="m_queryDeal.action" target="content"> <span class="label label-red"><i
										class="fa fa-rebel"></i></span> 有新的维修信息. <span class="small">27
										mins</span>
							</a></li>

							<li><a href="m_queryExpress.action" target="content"> <span class="label label-orange"><i
										class="fa fa-wrench"></i></span> 有新的投诉信息. <span class="small">36
										mins</span>
							</a></li>

							<li><a href="m_queryCost.action" target="content"> <span class="label label-amethyst"><i
										class="fa fa-cny"></i></span> 有新的费用信息. <span
									class="small">50 mins</span>
							</a></li>
							
								<li><a href="m_queryExpress.action" target="content"> <span class="label label-cyan"><i
										class="fa fa-cube"></i></span> 您有新的快递信息. <span
									class="small">2017.6.5</span>
							</a></li>

							<li><a href="#">更多...<i
									class="fa fa-angle-right"></i></a></li>
						</ul></li>

					<li class="dropdown divided user" id="current-user">
						<div class="profile-photo">
							<img src="../image/user.png" />
						</div> <a class="dropdown-toggle options" data-toggle="dropdown"
						href="#" style="font-size:20px"> <%=session.getAttribute("loginadminname")%> <i
							class="fa fa-caret-down"></i>
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
								href='m_queryAdminSelf.action?username=<%=session.getAttribute("loginadminname")%>' target="content"><i
									class="fa fa-user"></i> 个人信息</a></li>

							<li><a href="m_queryExpress.action" target="content"><i class="fa fa-calendar"></i> 快递管理</a></li>

							<li><a href="m_queryNotice.action" target="content"><i class="fa fa-envelope"></i> 发布通知 </a> </li>

							<li class="divider"></li>

							<li><a href="logout.jsp"><i class="fa fa-power-off"></i>
									登出</a></li>
						</ul>
					</li>
<li>
                <a href="#mmenu"><i class="fa fa-comments"></i></a>
              </li>
				</ul>
				<ul class="nav navbar-nav side-nav" id="sidebar">

					<li class="collapsed-content">
						<ul>
							<li class="search">
							</li>
						</ul>
					</li>

					<li class="navigation" id="navigation"><a href="#"
						class="sidebar-toggle" data-toggle="#navigation" style="color:black;font-size:22px">导航菜单 <i
							class="fa fa-angle-up"></i></a>

						<ul class="menu">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <i class="fa fa-user" style="font-size:22px"></i> 用户管理 <b
									class="fa fa-plus dropdown-plus"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="m_queryAdmin.action" target="content"> <i
											class="fa fa-users"></i> 管理员信息管理
									</a></li>
									<li><a href="m_queryUser.action" target="content"> <i
											class="fa fa-github-alt"></i> 用户信息管理
									</a></li>
								</ul></li>

							<li><a href="m_queryHouse.action" target="content"> <i class="fa fa-home"></i>房产信息管理
							</a></li>
							<li><a href="m_queryRepair.action" target="content"> <i
									class="fa fa-wrench"></i> 公共设施维修
							</a></li>
							<li><a href="m_queryCost.action" target="content"> <i class="fa fa-cny"></i>
									费用管理
							</a></li>
							<li><a href="m_queryNotice.action" target="content"> <i
									class="fa fa-desktop"></i> 物业通告
							</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <i class="fa fa-bell"></i>
									客服管理 <b class="fa fa-plus dropdown-plus"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="m_queryDeal.action" target="content"> <i
											class="fa fa-tint"></i> 报修申请
									</a></li>
									<li><a href="m_queryComplaint.action" target="content"> <i
											class="fa fa-wrench"></i> 进行投诉
									</a></li>
								</ul></li>


							<li><a href="m_queryExpress.action" target="content"> <i class="fa fa-play-circle"></i>
									快递管理
							</a></li>

							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <i class="fa fa-edit"></i>
									报表管理 <b class="fa fa-plus dropdown-plus"></b>
							</a>
								<ul class="dropdown-menu">
									<li><a href="echarts.jsp" target="content"> <i
											class="fa fa-wrench"></i> 维修信息报表
									</a></li>
									<li><a href="ReportMgt.jsp" target="content"> <i
											class="fa fa-user"></i> 投诉信息报表
									</a></li>
								</ul></li>


						</ul></li>
				</ul>
			</div>
			
		</div>
		
	</div>
	<div id="content" style="margin-top:50px">
				<iframe scrolling="no" name="content" frameborder="0" src="home.jsp"
					style="width: 100%"
					onload="this.height=0;var fdh=(this.Document?this.Document.body.scrollHeight:this.contentDocument.body.offsetHeight);this.height=(fdh>800?fdh:800)"></iframe>
			</div>
</body>
</html>