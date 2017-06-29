<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="loginverify.jsp" %>
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
		<script type="text/javascript">
			function updateExpress(id,CustomName,CustomPhone,ArriveTime,State){
			    document.getElementById('id').value=id;
				document.getElementById('CustomName').value=CustomName;
				document.getElementById('CustomPhone').value=CustomPhone;
				document.getElementById('ArriveTime').value=ArriveTime;
				document.getElementById('State').value=State;
				document.getElementById('stateOption').innerText=State;
			}
			
			function deleteExpress(id){
				window.location.href = "m_deleteExpress.action?id="+id;
			}
		</script>
	</head>
	<body style="font-size:20px">
		<div class="row">
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="home.jsp">主页</a></li>
					<li class="active">快递管理</li>
					<li class="active">快递信息管理</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="box">
					<div class="box-header"><h2><i class="fa fa-cube"></i>快递信息管理</h2></div>
					<div class="box-content">
						<div class="row toolbar">
							<div class="col-xs-4">
								<form action="m_queryExpress.action" method="post">
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
							<div class="col-xs-8"><button type="button" data-toggle="modal" data-target="#addModal" class="btn btn-primary"><i class="fa fa-plus">新增快递信息</i></button></div>
						</div>
						<div class="table-responsive">
							<table class="table table-condensed table-bordered table-hover table-striped">
								<thead>
									<tr>
										<th>收货人姓名</th>
										<th>收货人手机号</th>
										<th>快递到达时间</th>
										<th>快递提取状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value ="lst" id = "expressmgt">
										<tr>
											<td><s:property value = "#expressmgt.CustomName"/></td>
											<td><s:property value = "#expressmgt.CustomPhone"/></td>
											<td><s:date name = "#expressmgt.ArriveTime" format="yyyy-MM-dd"/></td>
											<td><s:property value = "#expressmgt.State"/></td>
											<td>
												<button class="btn btn-info btn-xs" data-toggle="modal" data-target="#editModal" 
												onclick="updateExpress(
												'<s:property value = "#expressmgt.id"/>',
												'<s:property value = "#expressmgt.CustomName"/>',
												'<s:property value = "#expressmgt.CustomPhone"/>',
												'<s:date name = "#expressmgt.ArriveTime" format="yyyy-MM-dd"/>',
												'<s:property value = "#expressmgt.State"/>')">
												<i class="fa fa-edit"></i></button>
												<button class="btn btn-danger btn-xs" 
												onclick = "deleteExpress('<s:property value = "#expressmgt.id"/>')">
												<i class="fa fa-trash-o"></i></button>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<s:url id="url_pre" value="m_queryExpress"><s:param name="currentPage" value="currentPage-1"></s:param></s:url>
						<s:url id="url_first" value="m_queryExpress"><s:param name="currentPage" value="1"></s:param></s:url>
						<s:url id="url_last" value="m_queryExpress"><s:param name="currentPage" value="totalPage"></s:param></s:url>
						<s:url id="url_next" value="m_queryExpress"><s:param name="currentPage" value="currentPage+1"></s:param></s:url>
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
        
        <div class="modal fade" id="addModal" role="dialog" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
        	<div class="modal-dialog">
        		<div class="modal-content">
        			<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        				<h4 class="modal-title">添加 快递信息</h4>
        			</div>
        			<div class="modal-body">
        				<form role="form" action="m_addExpress.action" method="post">
        					<div class="row">
        						<div class="col-lg-6">
        							<div class="form-group">
        								<label for="CustomName">收货人姓名</label>
        								<input type="text" class="form-control" name="CustomName">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="CustomPhone">收货人手机号</label>
        								<input type="text" class="form-control" name="CustomPhone">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="ArriveTime">快递到达时间</label>
        								<input type="date" class="form-control" name="ArriveTime">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="State">快递签收状态</label>
        								<input type="text" class="form-control" name="State" value="未签收" readonly="readonly">
        							</div>
        						</div>
        					</div>					
        					
        					<div class="modal-footer">
        						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        						<input type="submit" class="btn btn-primary" value="确认添加"/>
        					</div>
        				</form>
        			</div>
        		</div>
        	</div>
        </div>
        <div class="modal fade" id="editModal" role="dialog" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
        	<div class="modal-dialog">
        		<div class="modal-content">
        			<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        				<h4 class="modal-title">编辑 快递信息</h4>
        			</div>
        			<div class="modal-body">
        				<form role="form" action="m_editExpress.action" enctype="multipart/form-data" method="post">
        					<div class="row">
        						<div class="col-lg-6">
        							<div class="form-group">
        								<label for="CustomName">收货人姓名</label>
        								<input type="text" class="form-control" name="CustomName" id="CustomName">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="CustomPhone">收货人手机号</label>
        								<input type="text" class="form-control" name="CustomPhone" id="CustomPhone">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="ArriveTime">快递到达时间</label>
        								<input type="date" class="form-control" name="ArriveTime" id="ArriveTime" readonly="readonly">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="stateOption">快递签收状态</label>
        								<div class="form-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" id="stateOption" name="stateOption" data-toggle="dropdown"><span class="caret"></span></button>
											<ul class="dropdown-menu" aria-labelledby="stateOption">
												<li data-option="已签收" ><a href="#">已签收</a></li>
												<li data-option="未签收" ><a href="#">未签收</a></li>
												<li data-option="代签" ><a href="#">代签</a></li>
											</ul>
        								</div>
        								<input type="hidden" value="" name="State" id="State">
        							</div>
        						</div>
        					</div>
        					<div class="modal-footer">
        						<input type="hidden" name="id" id="id">
        						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        						<input type="submit" class="btn btn-primary" value="确认更新"/>
        					</div>
        				</form>
        			</div>
        		</div>
        	</div>
        </div>
	</body>
</html>