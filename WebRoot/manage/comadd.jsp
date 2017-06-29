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
<body class="bg-1" style="font-size:25px">
<div id="content" class="col-md-12" style="margin-top:-50px">
    <div class="pageheader">
        <h2><i class="fa fa-tint" style="line-height: 48px;padding-left: 1px;"></i> 进行投诉 </h2>
    </div>
    <div class="main">
        <div class="row">
            <div class="col-md-10">
                <section class="tile color transparent-black">
                    <div class="tile-header">
                        <h1><strong>投诉基本信息填写</strong></h1>
                    </div>
                    <div class="tile-body">
                        <form class="form-horizontal" action="e_addComplaint.action" role="form" parsley-validate id="basicvalidations">
                            <div class="form-group">
                                <label for="fullname" class="col-sm-4 control-label">业主账户 *</label>
                                <div class="col-sm-8">
                                   <input type="text"
										class="form-control" name="user" id="user" value="<%=session.getAttribute("loginusername")%>" readonly="readonly">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fullname" class="col-sm-4 control-label">投诉原因 *</label>
                                <div class="col-sm-8">
                                    <input type="text"
										class="form-control" name="reason">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-sm-4 control-label">投诉时间 *</label>
                                <div class="col-sm-8">
                                    <input type="date"
										class="form-control" name="date">
                                </div>
                            </div>
                             <div class="form-group">
                                <label for="email" class="col-sm-4 control-label">详细描述</label>
                                <div class="col-sm-8">
                                    <input type="text"
										class="form-control" name="date">
                                </div>
                            </div>
                             <div class="form-group">
                                <label for="email" class="col-sm-4 control-label">希望处理方式</label>
                                <div class="col-sm-8">
                                    <input type="text"
										class="form-control" name="date">
                                </div>
                            </div>
                             <div class="form-group">
                                <label for="email" class="col-sm-4 control-label">联系人</label>
                                <div class="col-sm-8">
                                    <input type="text"
										class="form-control" name="date">
                                </div>
                            </div>
                              <div class="form-group">
                                <label for="email" class="col-sm-4 control-label">联系方式</label>
                                <div class="col-sm-8">
                                    <input type="text"
										class="form-control" name="date">
                                </div>
                            </div>
                            <div style="display:none;" class="form-group">
                                <label for="birthdate" class="col-sm-4 control-label">投诉状态</label>
                                <div class="col-sm-8">
                                    <input type="text"
										class="form-control" name="state" value="未处理" readonly="readonly">
                                </div>
                            </div>

                            <div class="form-group form-footer">
                                <div class="col-sm-offset-6">
                                    <input type="submit" class="btn btn-primary" value="确定提交"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>
</body>
</html>