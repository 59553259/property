<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="userloginverify.jsp"%>
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
<style>
.wrapper {
	width: 300px;
	margin: 10px auto;
	font: 14px/1.5 arial;
}
/*tab*/
#star {
	overflow: hidden;
}

#star li {
	float: left;
	width: 20px;
	height: 20px;
	margin: 2px;
	display: inline;
	color: #999;
	font: bold 18px arial;
	cursor: pointer
}

#star .act {
	color: #c00
}

#star_word {
	width: 80px;
	height: 30px;
	line-height: 30px;
	border: 1px solid #ccc;
	margin: 10px;
	text-align: center;
	display: none
}
</style>
<script>
	window.onload = function() {

		var star = document.getElementById("star");
		var star_li = star.getElementsByTagName("li");
		var star_word = document.getElementById("star_word");
		var result = document.getElementById("result");
		var i = 0;
		var j = 0;
		var len = star_li.length;
		var word = [ '很差', '差', '一般', "好", "很好" ]

		for (i = 0; i < len; i++) {
			star_li[i].index = i;

			star_li[i].onmouseover = function() {
				star_word.style.display = "block";
				star_word.innerHTML = word[this.index];
				for (i = 0; i <= this.index; i++) {
					star_li[i].className = "act";

				}
			}

			star_li[i].onmouseout = function() {
				star_word.style.display = "none";
				for (i = 0; i < len; i++) {
					star_li[i].className = "";
				}
			}

			star_li[i].onclick = function() {
				result.innerHTML = (this.index + 1) + "分";
			}

		}

	}
</script>
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li><a href="userhome.jsp">主页</a></li>
				<li class="active">投诉申请信息</li>
			</ol>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="box">
				<div class="box-header">
					<h2>
						<i class="fa fa-wrench"></i>投诉申请信息
					</h2>
				</div>
				<div class="box-content">
					<div class="table-responsive">
						<table
							class="table table-condensed table-bordered table-hover table-striped">
							<thead>
								<tr>
									<th>业主名</th>
									<th>投诉原因</th>
									<th>投诉时间</th>
									<th>投诉状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="lst" id="Complaintmgt">
									<tr>
										<td><s:property value="#Complaintmgt.user" /></td>
										<td><s:property value="#Complaintmgt.reason" /></td>
									    <td><s:date name="#Complaintmgt.date" format="yyyy-MM-dd"/></td>
										<td><s:property value="#Complaintmgt.state" /></td>
										<td><button class="btn btn-success btn-xs"
												data-toggle="modal" data-target="#starModal">
												<i class="fa fa-wrench">进行评价</i>
											</button></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<s:url id="url_pre" value="e_queryComplaint">
						<s:param name="currentPage" value="currentPage-1"></s:param>
					</s:url>
					<s:url id="url_first" value="e_queryComplaint">
						<s:param name="currentPage" value="1"></s:param>
					</s:url>
					<s:url id="url_last" value="e_queryComplaint">
						<s:param name="currentPage" value="totalPage"></s:param>
					</s:url>
					<s:url id="url_next" value="e_queryComplaint">
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
<div class="modal fade" id="starModal" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">进行评价</h4>
				</div>
				<form role="form" action="success.jsp"
						enctype="multipart/form-data" method="post">
				<div class="wrapper">
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group">
								<label for="date">请评价此次投诉处理结果(选填)</label> <input type="text"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group">
								<label for="date">请为此次服务打分</label> 
								<span style="font-size:20px;margin-left:20px" id="result"></span>
								<ul id="star">
						<li>★</li>
						<li>★</li>
						<li>★</li>
						<li>★</li>
						<li>★</li>
					</ul>
					<div id="star_word">一般</div>
							</div>
						</div>
					</div>
					<br/>
				<br/>
					
					<input type="submit" class="btn btn-primary"  value="确认评分" />
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>