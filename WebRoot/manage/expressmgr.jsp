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
		<link href="../css/font-awesome.min.css" rel="stylesheet">
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../js/script.js"></script>
	</head>
	<body style="font-size:20px">
		<div class="row">
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="userhome.jsp">主页</a></li>
					<li class="active">快递查询</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="box">
					<div class="box-header"><h2><i class="fa fa-cube"></i>快递查询</h2></div>
					<div class="box-content">
						<div class="row toolbar">
							<div class="col-xs-4">
								<form action="e_queryExpress.action" method="post">
									<div class="input-group">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" id="searchTypeOption" data-toggle="dropdown">搜索类型 <span class="caret"></span></button>
											<ul class="dropdown-menu" aria-labelledby="searchTypeOption">								
												<li data-option="query_ExpressPhone"><a href="#">按 手机号</a></li>
												<li data-option="query_ExpressState"><a href="#">按 状态</a></li>
											</ul>
										</div><!-- /btn-group -->
										<input type="hidden" value="" name="searchType" id="searchType">
										<input type="text" name="searchname" class="form-control">
										<span class="input-group-btn"><input class="btn btn-default" type="submit" value="搜索" /></span>
									</div>
								</form>
							</div>
						</div>
						<div class="table-responsive">
							<table class="table table-condensed table-bordered table-hover table-striped">
								<thead>
									<tr>
									    <th>编号</th>
										<th>收货人姓名</th>
										<th>收货人手机号</th>
										<th>快递到达时间</th>
										<th>快递提取状态</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value ="lst" id = "expressmgt">
										<tr>
										    <td><s:property value = "#expressmgt.id"/></td>
											<td><s:property value = "#expressmgt.CustomName"/></td>
											<td><s:property value = "#expressmgt.CustomPhone"/></td>
											<td><s:property value = "#expressmgt.ArriveTime"/></td>
											<td><s:property value = "#expressmgt.State"/></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<s:url id="url_pre" value="e_queryExpress"><s:param name="currentPage" value="currentPage-1"></s:param></s:url>
						<s:url id="url_first" value="e_queryExpress"><s:param name="currentPage" value="1"></s:param></s:url>
						<s:url id="url_last" value="e_queryExpress"><s:param name="currentPage" value="totalPage"></s:param></s:url>
						<s:url id="url_next" value="e_queryExpress"><s:param name="currentPage" value="currentPage+1"></s:param></s:url>
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