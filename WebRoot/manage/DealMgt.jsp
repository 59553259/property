<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="loginverify.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html lang="zh">
<head>
<meta charset="utf-8">
<title>企业用户数据管理-系统管理员</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/script.js"></script>
<script type="text/javascript">
	function updateDeal(id, user, reason, date, state) {
		document.getElementById('id').value = id;
		document.getElementById('user').value = user;
		document.getElementById('reason').value = reason;
		document.getElementById('date').value = date;
		document.getElementById('state').value = state;
		document.getElementById('stateOption').innerText=state;
	}

	function deleteDeal(id) {
		window.location.href = "m_deleteDeal.action?id=" + id;
	}
</script>
</head>
<body style="font-size:20px">
	<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li><a href="home.jsp">主页</a></li>
				<li class="active">报修管理</li>
				<li class="active">报修申请信息</li>
			</ol>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="box">
				<div class="box-header">
					<h2>
						<i class="fa fa-rebel"></i>报修申请信息
					</h2>
				</div>
				<div class="box-content">
					<div class="row toolbar">
						<div class="col-xs-4">
							<form action="m_queryDeal.action" method="post">
								<div class="input-group">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default dropdown-toggle"
											id="searchTypeOption" data-toggle="dropdown">
											搜索类型 <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" aria-labelledby="searchTypeOption">
											<li data-option="query_DealUser"><a href="#">按 业主名</a></li>
											<li data-option="query_DealState"><a href="#">按 报修状态</a></li>
										</ul>
									</div>
									<!-- /btn-group -->
									<input type="hidden" value="" name="searchType" id="searchType">
									<input type="text" name="searchname" class="form-control">
									<span class="input-group-btn"><input
										class="btn btn-default" type="submit" value="搜索" /></span>
								</div>
							</form>
						</div>
						<div class="col-xs-8">
							<button type="button" data-toggle="modal" data-target="#addModal"
								class="btn btn-primary">
								<i class="fa fa-plus">新增报修信息</i>
							</button>
						</div>
					</div>
					<div class="table-responsive">
						<table
							class="table table-condensed table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>业主名</th>
									<th>报修原因</th>
									<th>报修时间</th>
									<th>报修状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="lst" id="dealmgt">
									<tr>
										<td><s:property value="#dealmgt.user" /></td>
										<td><s:property value="#dealmgt.reason" /></td>
									    <td><s:date name="#dealmgt.date" format="yyyy-MM-dd"/></td>
										<td><s:property value="#dealmgt.state" /></td>
										<td>
											<button class="btn btn-info btn-xs" data-toggle="modal"
												data-target="#editModal"
												onclick="updateDeal(
												'<s:property value = "#dealmgt.id"/>',
												'<s:property value = "#dealmgt.user"/>',
												'<s:property value = "#dealmgt.reason"/>',
												'<s:date name="#dealmgt.date" format="yyyy-MM-dd"/>',
												'<s:property value = "#dealmgt.state"/>')">
												<i class="fa fa-edit"></i>
											</button>
											<button class="btn btn-danger btn-xs"
												onclick="deleteDeal('<s:property value = "#dealmgt.id"/>')">
												<i class="fa fa-trash-o"></i>
											</button>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<s:url id="url_pre" value="m_queryDeal">
						<s:param name="currentPage" value="currentPage-1"></s:param>
					</s:url>
					<s:url id="url_first" value="m_queryDeal">
						<s:param name="currentPage" value="1"></s:param>
					</s:url>
					<s:url id="url_last" value="m_queryDeal">
						<s:param name="currentPage" value="totalPage"></s:param>
					</s:url>
					<s:url id="url_next" value="m_queryDeal">
						<s:param name="currentPage" value="currentPage+1"></s:param>
					</s:url>
					<div class="clearfix">
						<ul class="pagination" style="float: right">
							<s:if test="%{(totalPage>1)}">
								<li><s:a href="%{url_first}">首页</s:a></li>
								<s:if test="currentPage!=1">
									<li><s:a href="%{url_pre}">上一页</s:a></li>
								</s:if>
								<s:else></s:else>
								<s:if test="currentPage!=totalPage">
									<li><s:a href="%{url_next}">下一页</s:a></li>
								</s:if>
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

	<div class="modal fade" id="addModal" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">添加 报修信息</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="m_addDeal.action" method="post">
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label for="user">业主名</label> <input type="text"
										class="form-control" name="user">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label for="reason">报修原因</label> <input type="text"
										class="form-control" name="reason">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label for="date">报修时间</label> <input type="date"
										class="form-control" name="date">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label for="state">报修状态</label> <input type="text"
										class="form-control" name="state" value="未维修" readonly="readonly">
								</div>
							</div>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<input type="submit" class="btn btn-primary" value="确认添加" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="editModal" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">编辑 报修信息</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="m_editDeal.action"
						enctype="multipart/form-data" method="post">
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label for="user">业主名</label> <input type="text"
										class="form-control" name="user" id="user" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label for="reason">报修原因</label> <input type="text"
										class="form-control" name="reason" id="reason">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label for="date">报修时间</label> <input type="text"
										class="form-control" name="date" id="date">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
        								<label for="stateOption">报修状态</label>
        								<div class="form-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" id="stateOption" name="stateOption" data-toggle="dropdown"><span class="caret"></span></button>
											<ul class="dropdown-menu" aria-labelledby="stateOption">
												<li data-option="已维修" ><a href="#">已维修</a></li>
												<li data-option="未维修" ><a href="#">未维修</a></li>
												<li data-option="正在维修" ><a href="#">正在维修</a></li>
											</ul>
        								</div>
        								<input type="hidden" value="" name="state" id="state">
        							</div>
							</div>
						</div>
						<div class="modal-footer">
							<input type="hidden" name="id" id="id">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<input type="submit" class="btn btn-primary" value="确认更新" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>