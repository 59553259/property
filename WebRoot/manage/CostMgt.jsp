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
			function updateCost(id,User,Project,Receivable,Actual,Time,State){
			    document.getElementById('id').value=id;
				document.getElementById('User').value=User;
				document.getElementById('Project').value=Project;
				document.getElementById('Receivable').value=Receivable;
				document.getElementById('Actual').value=Actual;
				document.getElementById('Time').value=Time;
				document.getElementById('State').value=State;
				document.getElementById('stateOption').innerText=State;
			}
			
			function deleteCost(id){
				window.location.href = "m_deleteCost.action?id="+id;
			}
		</script>
	</head>
	<body style="font-size:20px">
		<div class="row">
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="home.jsp">主页</a></li>
					<li class="active">费用管理</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="box">
					<div class="box-header"><h2><i class="fa fa-cny"></i>费用管理</h2></div>
					<div class="box-content">
						<div class="row toolbar">
							<div class="col-xs-4">
								<form action="m_queryCost.action" method="post">
									<div class="input-group">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" id="searchTypeOption" data-toggle="dropdown">搜索类型 <span class="caret"></span></button>
											<ul class="dropdown-menu" aria-labelledby="searchTypeOption">								
												<li data-option="query_CostUser"><a href="#">按 用户</a></li>
												<li data-option="query_CostProject"><a href="#">按 收费项目</a></li>
												<li data-option="query_CostState"><a href="#">按 收费状态</a></li>
											</ul>
										</div><!-- /btn-group -->
										<input type="hidden" value="" name="searchType" id="searchType">
										<input type="text" name="searchname" class="form-control">
										<span class="input-group-btn"><input class="btn btn-default" type="submit" value="搜索" /></span>
									</div>
								</form>
							</div>
							<div class="col-xs-8"><button type="button" data-toggle="modal" data-target="#addModal" class="btn btn-primary"><i class="fa fa-plus">新增收费信息</i></button></div>
						</div>
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
											<td>
												<button class="btn btn-info btn-xs" data-toggle="modal" data-target="#editModal" 
												onclick="updateCost(
												'<s:property value = "#costmgt.id"/>',
												'<s:property value = "#costmgt.User"/>',
												'<s:property value = "#costmgt.Project"/>',
												'<s:property value = "#costmgt.Receivable"/>',
												'<s:property value = "#costmgt.Actual"/>',
												'<s:property value = "#costmgt.Time"/>',
												'<s:property value = "#costmgt.State"/>')">
												<i class="fa fa-edit"></i></button>
												<button class="btn btn-danger btn-xs" 
												onclick = "deleteCost('<s:property value = "#costmgt.id"/>')">
												<i class="fa fa-trash-o"></i></button>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<s:url id="url_pre" value="m_queryCost"><s:param name="currentPage" value="currentPage-1"></s:param></s:url>
						<s:url id="url_first" value="m_queryCost"><s:param name="currentPage" value="1"></s:param></s:url>
						<s:url id="url_last" value="m_queryCost"><s:param name="currentPage" value="totalPage"></s:param></s:url>
						<s:url id="url_next" value="m_queryCost"><s:param name="currentPage" value="currentPage+1"></s:param></s:url>
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
        				<h4 class="modal-title">添加 费用信息</h4>
        			</div>
        			<div class="modal-body">
        				<form role="form" action="m_addCost.action" method="post">
        					<div class="row">
        						<div class="col-lg-6">
        							<div class="form-group">
        								<label for="User">业主名</label>
        								<input type="text" class="form-control" name="User">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Project">收费项目</label>
        								<input type="text" class="form-control" name="Project">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Receivable">应收金额</label>
        								<input type="text" class="form-control" name="Receivable">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Actual">实收金额</label>
        								<input type="text" class="form-control" name="Actual">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Time">缴费时间</label>
        								<input type="text" class="form-control" name="Time">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="State">缴费状态</label>
        								<input type="text" class="form-control" name="State" value="未缴费" readonly="readonly">
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
        				<h4 class="modal-title">编辑 费用信息</h4>
        			</div>
        			<div class="modal-body">
        				<form role="form" action="m_editCost.action" enctype="multipart/form-data" method="post">
        					<div class="row">
        						<div class="col-lg-6">
        							<div class="form-group">
        								<label for="User">业主名</label>
        								<input type="text" class="form-control" name="User" id="User" readonly="readonly">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Project">收费项目</label>
        								<input type="text" class="form-control" name="Project" id="Project">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for=Receivable>应收金额</label>
        								<input type="text" class="form-control" name="Receivable" id="Receivable">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Actual">实收金额</label>
        								<input type="text" class="form-control" name="Actual" id="Actual">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Time">缴费时间</label>
        								<input type="text" class="form-control" name="Time" id="Time">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="stateOption">缴费状态</label>
        								<div class="form-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" id="stateOption" name="stateOption" data-toggle="dropdown"><span class="caret"></span></button>
											<ul class="dropdown-menu" aria-labelledby="stateOption">
												<li data-option="已缴费" ><a href="#">已缴费</a></li>
												<li data-option="未缴费" ><a href="#">未缴费</a></li>
												<li data-option="欠费" ><a href="#">欠费</a></li>
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