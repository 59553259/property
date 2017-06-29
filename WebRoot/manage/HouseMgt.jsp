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
		<script type="text/javascript" src="../js/HouseMgt.js"></script>
		<script type="text/javascript">
			function updateHouse(id,HouseNumber,Residential,Building,Unit,Park,Location,Facilities,HouseType){
				document.getElementById('id').value=id;
				document.getElementById('HouseNumber').value=HouseNumber;
				document.getElementById('Residential').value=Residential;
				document.getElementById('Building').value=Building;
				document.getElementById('Unit').value=Unit;
				document.getElementById('Park').value=Park;
				document.getElementById('Location').value=Location;
				document.getElementById('Facilities').value=Facilities;
				document.getElementById('HouseType').value=HouseType;
			}
			
			function deleteHouse(id){
				window.location.href = "m_deleteHouse.action?id="+id;
			}
		</script>
	</head>
	<body style="font-size:21px">
		<div class="row">
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="home.jsp">主页</a></li>
					<li class="active">房产信息管理</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="box">
					<div class="box-header"><h2><i class="fa fa-home"></i>房产信息管理</h2></div>
					<div class="box-content">
						<div class="row toolbar">
							<div class="col-xs-4">
								<form action="m_queryHouse.action" method="post" id="from1">
									<div class="input-group">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" id="searchTypeOption" data-toggle="dropdown">搜索类型 <span class="caret"></span></button>
											<ul class="dropdown-menu" aria-labelledby="searchTypeOption">								
												<li data-option="query_Residential"><a href="#">按 小区</a></li>
											</ul>
										</div><!-- /btn-group -->
										<input type="hidden" value="" name="searchType" id="searchType">
										<input type="text" name="searchname" class="form-control">
										<span class="input-group-btn"><input class="btn btn-default" type="submit" value="搜索" /></span>
									</div>
								</form>
							</div>
							<div class="col-xs-8">
							<button type="button" data-toggle="modal" data-target="#addModal" class="btn btn-primary">新增</button>
							<button type="button"  class="btn btn-primary" onclick="exportExcelTemplet()">获取excel 导入模板</button>
							<button type="button"  class="btn btn-primary" onclick="reoprtExcel()">导入excel</button>
							</div>
						</div>
						<div class="table-responsive">
							<table class="table table-condensed table-bordered table-hover">
								<thead>
									<tr>
										<th>房号</th>
										<th>小区</th>
										<th>楼盘</th>
										<th>住户单元</th>
										<th>停车场</th>
										<th>车位的位置</th>
										<th>小区设施分布</th>
										<th>户型</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody >
									<s:iterator value ="lst" id = "housemgt">
										<tr>
											<td><s:property value = "#housemgt.HouseNumber"/></td>
											<td><s:property value = "#housemgt.Residential"/></td>
											<td><s:property value = "#housemgt.Building"/></td>
											<td><s:property value = "#housemgt.Unit"/></td>
											<td><s:property value = "#housemgt.Park"/></td>
											<td><s:property value = "#housemgt.Location"/></td>
											<td><s:property value = "#housemgt.Facilities"/></td>
											<td><s:property value = "#housemgt.HouseType"/></td>
											<td>
												<button class="btn btn-info btn-xs" data-toggle="modal" data-target="#editModal" 
												onclick="updateHouse(
												'<s:property value = "#housemgt.id"/>',
												'<s:property value = "#housemgt.HouseNumber"/>',
												'<s:property value = "#housemgt.Residential"/>',
												'<s:property value = "#housemgt.Building"/>',
												'<s:property value = "#housemgt.Unit"/>',
												'<s:property value = "#housemgt.Park"/>',
												'<s:property value = "#housemgt.Location"/>',
												'<s:property value = "#housemgt.Facilities"/>',
												'<s:property value = "#housemgt.HouseType"/>')">
												<i class="fa fa-edit"></i></button>
												<button class="btn btn-danger btn-xs" 
												onclick = "deleteHouse('<s:property value = "#housemgt.id"/>')">
												<i class="fa fa-trash-o"></i></button>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<s:url id="url_pre" value="m_queryHouse"><s:param name="currentPage" value="currentPage-1"></s:param></s:url>
						<s:url id="url_first" value="m_queryHouse"><s:param name="currentPage" value="1"></s:param></s:url>
						<s:url id="url_last" value="m_queryHouse"><s:param name="currentPage" value="totalPage"></s:param></s:url>
						<s:url id="url_next" value="m_queryHouse"><s:param name="currentPage" value="currentPage+1"></s:param></s:url>
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
        				<h4 class="modal-title">添加 房产信息</h4>
        			</div>
        			<div class="modal-body">
        				<form role="form" action="m_addHouse.action" method="post">
        					<div class="row">
        						<div class="col-lg-6">
        							<div class="form-group">
        								<label for="Residential">小区</label>
        								<input type="text" class="form-control" name="Residential">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Building">楼盘</label>
        								<input type="text" class="form-control" name="Building">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Unit">住户单元</label>
        								<input type="text" class="form-control" name="Unit">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Park">停车场</label>
        								<input type="text" class="form-control" name="Park">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Location">车位的位置</label>
        								<input type="text" class="form-control" name="Location">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Facilities">小区设施分布</label>
        								<input type="text" class="form-control" name="Facilities">
        							</div>
        						</div>
        					</div>					
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="HouseNumber">房号</label>
        								<input type="text" class="form-control" name="HouseNumber">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="HouseType">户型</label>
        								<textarea name="HouseType" cols="100" rows="4" style="width:100%;height:50px;" class="form-control"></textarea>
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
        				<h4 class="modal-title">编辑 房产信息</h4>
        			</div>
        			<div class="modal-body">
        				<form role="form" action="m_editHouse.action" enctype="multipart/form-data" method="post">
        					<div class="row">
        						<div class="col-lg-6">
        							<div class="form-group">
        								<label for="Residential">小区</label>
        								<input type="text" class="form-control" name="Residential" id="Residential">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Building">楼盘</label>
        								<input type="text" class="form-control" name="Building" id="Building">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Unit">住户单元</label>
        								<input type="text" class="form-control" name="Unit" id="Unit">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Park">停车场</label>
        								<input type="text" class="form-control" name="Park" id="Park">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Location">车位的位置</label>
        								<input type="text" class="form-control" name="Location" id="Location">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Facilities">小区设施分布</label>
        								<input type="text" class="form-control" name="Facilities" id="Facilities">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="HouseNumber">房号</label>
        								<input type="text" class="form-control" name="HouseNumber" id="HouseNumber">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="HouseType">户型</label>
        								<textarea name="HouseType" id="HouseType" cols="100" rows="4" style="width:100%;height:50px;" class="form-control"></textarea>
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
        <input name="fjfile" id='fjfile'
					type="file" style="display: none" onchange="exprotExcel()" 
					accept=".xls,.xlsx"/>
	</body>
</html>