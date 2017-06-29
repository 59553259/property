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
			function updateuser(id,User,Name,Password,Sex,IdCard,Email,Phone,Pald,Hold,regdate){
				document.getElementById('id').value=id;
				document.getElementById('User').value=User;
				document.getElementById('Name').value=Name;
				document.getElementById('Password').value=Password;
				document.getElementById('Sex').value=Sex;
				document.getElementById('IdCard').value=IdCard;
				document.getElementById('Email').value = Email;
				document.getElementById('Phone').value = Phone;
				document.getElementById('Pald').value = Pald;
				document.getElementById('Hold').value = Hold;
				document.getElementById('regdate').value=regdate.split(" ")[0];;
			}
			
			function deleteUser(id){
				window.location.href = "m_deleteUser.action?id="+id;
			}
			
		</script>
	</head>
	<body style="font-size:20px">
		<div class="row">
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="home.jsp">主页</a></li>
					<li class="active">用户管理</li>
					<li class="active">用户信息管理</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="box">
					<div class="box-header"><h2><i class="fa fa-github-alt"></i>用户信息管理</h2></div>
					<div class="box-content">
						<div class="row toolbar">
							<div class="col-xs-4">
								<form action="m_queryUser.action" method="post">
									<div class="input-group">
										<div class="input-group-btn">
											<button type="button" class="btn btn-default dropdown-toggle" id="searchTypeOption" data-toggle="dropdown">搜索类型 <span class="caret"></span></button>
											<ul class="dropdown-menu" aria-labelledby="searchTypeOption">
												<li data-option="query_username" ><a href="#">按 账户名</a></li>
												<li data-option="query_Phone"><a href="#">按 手机号</a></li>
											</ul>
										</div><!-- /btn-group -->
										<input type="hidden" value="" name="searchType" id="searchType">
										<input type="text" name="searchname" class="form-control">
										<span class="input-group-btn"><input class="btn btn-default" type="submit" value="搜索" /></span>
									</div>
								</form>
							</div>
							<div class="col-xs-8"><button type="button" data-toggle="modal" data-target="#addModal" class="btn btn-primary"><i class="fa fa-plus">增加小区用户</i></button></div>
						</div>
						<div class="table-responsive">
							<table class="table table-condensed table-bordered table-hover table-striped">
								<thead>
									<tr>
										<th>账户名</th>
										<th>真实姓名</th>
										<th>性别</th>
										<th>身份证号码</th>
										<th>邮箱</th>
										<th>手机号</th>
										<th>楼栋</th>
										<th>门牌号</th>
										<th>注册时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value ="lst" id = "usermgt">
										<tr>
											<td><s:property value = "#usermgt.User"/></td>
											<td><s:property value = "#usermgt.Name"/></td>
											<td><s:property value = "#usermgt.Sex"/></td>
											<td><s:property value = "#usermgt.IdCard"/></td>
											<td><s:property value = "#usermgt.Email"/></td>
											<td><s:property value = "#usermgt.Phone"/></td>
											<td><s:property value = "#usermgt.Pald"/></td>
											<td><s:property value = "#usermgt.Hold"/></td>
											<td><s:date name="#usermgt.regdate" format="yyyy-MM-dd"/></td>
											<td>
												<button style="display:none" class="btn btn-info btn-xs" data-toggle="modal" data-target="#editModal" onclick="updateuser(
												'<s:property value = "#usermgt.id"/>',
												'<s:property value = "#usermgt.User"/>',
												'<s:property value = "#usermgt.Name"/>',
												'<s:property value = "#usermgt.Password"/>',
												'<s:property value = "#usermgt.Sex"/>',
												'<s:property value = "#usermgt.IdCard"/>',
												'<s:property value = "#usermgt.Email"/>',
												'<s:property value = "#usermgt.Phone"/>',
												'<s:property value = "#usermgt.Pald"/>',
												'<s:property value = "#usermgt.Hold"/>',
												'<s:property value = "#usermgt.regdate.toLocaleString()"/>')"><i class="fa fa-edit"></i></button>
												<button class="btn btn-danger btn-sm" onclick = "deleteUser('<s:property value = "#usermgt.id"/>')"><i class="fa fa-trash-o"></i></button>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<s:url id="url_pre" value="m_queryUser"><s:param name="currentPage" value="currentPage-1"></s:param></s:url>
						<s:url id="url_first" value="m_queryUser"><s:param name="currentPage" value="1"></s:param></s:url>
						<s:url id="url_last" value="m_queryUser"><s:param name="currentPage" value="totalPage"></s:param></s:url>
						<s:url id="url_next" value="m_queryUser"><s:param name="currentPage" value="currentPage+1"></s:param></s:url>
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
        				<h4 class="modal-title">添加 用户</h4>
        			</div>
        			<div class="modal-body">
        				<form role="form" action="m_addUser.action" method="post">
        					<div class="row">
        						<div class="col-lg-6">
        							<div class="form-group">
        								<label for="User">用户名</label>
        								<input type="text" class="form-control" name="User">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-6">
        							<div class="form-group">
        								<label for="Name">真实姓名</label>
        								<input type="text" class="form-control" name="Name">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Password">输入密码</label>
        								<input type="password" class="form-control" name="Password">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="password2">确认密码</label>
        								<input type="password" class="form-control" name="password2">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Sex">性别</label>
        								<input type="text" class="form-control" name="Sex">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="IdCard">身份证号</label>
        								<input type="text" class="form-control" name="IdCard">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Email">邮箱</label>
        								<input type="text" class="form-control" name="Email">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Phone">手机号</label>
        								<input type="text" class="form-control" name="Phone">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Pald">楼栋</label>
        								<input type="text" class="form-control" name="Pald">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Hold">门牌号</label>
        								<input type="text" class="form-control" name="Hold">
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
        				<h4 class="modal-title">编辑 用户</h4>
        			</div>
        			<div class="modal-body">
        				<form role="form" action="m_editUser.action" enctype="multipart/form-data" method="post">
        					<div class="row">
        						<div class="col-lg-6">
        							<div class="form-group">
        								<label for="User">用户名</label>
        								<input type="text" readonly="readonly" class="form-control" name="User" id="User">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-6">
        							<div class="form-group">
        								<label for="Name">真实姓名</label>
        								<input type="text" readonly="readonly" class="form-control" name="Name" id="Name">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Password">新密码</label>
        								<input type="password" class="form-control" name="Password" id="Password">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="password2">确认密码</label>
        								<input type="password" class="form-control" name="password2">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Sex">性别</label>
        								<input type="text" class="form-control" name="Sex" id="Sex" readonly="readonly">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="IdCard">身份证号</label>
        								<input type="text" class="form-control" name="IdCard" id="IdCard" readonly="readonly">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Email">邮箱</label>
        								<input type="text" class="form-control" name="Email" id="Email">
        							</div>
        						</div>
        						<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Phone">手机号</label>
        								<input type="text" class="form-control" name="Phone" id="Phone">
        							</div>
        						</div>
        					</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Pald">楼栋</label>
        								<input type="text" class="form-control" name="Pald" id="Pald" readonly="readonly">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="Hold">门牌号</label>
        								<input type="text" class="form-control" name="Hold" id="Hold" readonly="readonly">
        							</div>
        						</div>
        					</div>
        					<div class="row">
        						<div class="col-lg-12">
        							<div class="form-group">
        								<label for="regdate">注册日期</label>
        								<input type="text" readonly="readonly" class="form-control" name="regdate" id="regdate">
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