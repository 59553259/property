<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="userloginverify.jsp" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html lang="zh">
	<head>
		<meta charset="utf-8">
		<title>企业用户数据管理-系统管理员</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
		<link href="../css/bootstrap.min.css" rel="stylesheet">
		<link href="../css/style.css" rel="stylesheet">
		<link href="../css/minimal.css" rel="stylesheet">
		<link href="../css/font-awesome.min.css" rel="stylesheet">
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../js/script.js"></script>
	</head>
	<body style="font-size:25px">
		<div class="row">
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="userhome.jsp">主页</a></li>
					<li class="active">费用管理</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="box">
					<div class="box-header"><h2><i class="fa fa-cny"></i>缴费信息查询</h2></div>
					<div class="box-content">
						<div class="table-responsive">
							<table class="table table-condensed table-bordered table-hover table-striped">
								<thead>
									<tr>
										<th>用户</th>
										<th>收费项目</th>
										<th>应收金额</th>
										<th>实收金额</th>
										<th>缴费时间</th>
										<th>缴费状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value ="lst" id = "costmgt">
										<tr>
											<td><s:property value = "#costmgt.User"/></td>
											<td><s:property value = "#costmgt.Project"/></td>
											<td><s:property value = "#costmgt.Receivable"/></td>
											<td><s:property value = "#costmgt.Actual"/></td>
											<td><s:property value = "#costmgt.Time"/></td>
											<td><s:property value = "#costmgt.State"/></td>
											<td><button class="btn btn-danger" 
												>
												<i class="fa fa-rmb">进行缴费</i></button>
												</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<s:url id="url_pre" value="e_queryCost"><s:param name="currentPage" value="currentPage-1"></s:param></s:url>
						<s:url id="url_first" value="e_queryCost"><s:param name="currentPage" value="1"></s:param></s:url>
						<s:url id="url_last" value="e_queryCost"><s:param name="currentPage" value="totalPage"></s:param></s:url>
						<s:url id="url_next" value="e_queryCost"><s:param name="currentPage" value="currentPage+1"></s:param></s:url>
						<div class="clearfix">
							<ul class="pagination" style="float: right">
								<s:if test="%{(totalPage>1)}">
								<li><s:a href="%{url_first}">首页</s:a></li>
								<s:if test="currentPage!=1"><li><s:a href="%{url_pre}">上一页</s:a></li></s:if>
								<s:else></s:else>
								<s:if test="currentPage!=totalPage"><li><s:a href="%{url_next}">下一页</s:a></li></s:if>
								<s:else></s:else>
								<li><s:a href="%{url_last}">尾页</s:a></li>
								</s:if>
							</ul>
						</div>
					</div>
					<p>当前是第${currentPage}页,共有${totalPage}页</p>
				</div>
			</div>
        </div>
	</body>
</html>