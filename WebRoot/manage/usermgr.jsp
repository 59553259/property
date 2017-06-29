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
        <h2><i class="fa fa-user" style="line-height: 48px;padding-left: 1px;"></i> 个人中心 <span> 个人信息修改...</span></h2>
    </div>
    <div class="main">
        <div class="row">
            <div class="col-md-10">
                <section class="tile color transparent-black">
                    <div class="tile-header">
                        <h1><strong>个人基本信息</strong></h1>
                    </div>
                    <div class="tile-body">
                        <form class="form-horizontal" action="e_editUser.action" role="form" parsley-validate id="basicvalidations">
                            <div style="display:none;" class="form-group">
                                <label for="fullname" class="col-sm-4 control-label">id *</label>
                                <div class="col-sm-8">
                                    <input type="text" readonly="readonly" class="form-control" name="id" id="id" value="<%=session.getAttribute("id")%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="fullname" class="col-sm-4 control-label">账户名 *</label>
                                <div class="col-sm-8">
                                    <input type="text" readonly="readonly" class="form-control" name="User" id="User" value="<%=session.getAttribute("loginusername")%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-sm-4 control-label">真实姓名 *</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="Name" id="Name" value="<%=session.getAttribute("Name")%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-4 control-label">新密码 *</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" name="Password" id="Password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="passwordconfirm" class="col-sm-4 control-label">确认密码 *</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" name="password2">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="website" class="col-sm-4 control-label">性别</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="Sex" id="Sex" value="<%=session.getAttribute("Sex")%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="birthdate" class="col-sm-4 control-label">身份证号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="IdCard" id="IdCard" value="<%=session.getAttribute("IdCard")%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="birthdate" class="col-sm-4 control-label">邮箱</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="Email" id="Email" value="<%=session.getAttribute("Email")%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="birthdate" class="col-sm-4 control-label">手机号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="Phone" id="Phone" value="<%=session.getAttribute("Phone")%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="birthdate" class="col-sm-4 control-label">楼栋</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="Pald" id="Pald" value="<%=session.getAttribute("Pald")%>">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="birthdate" class="col-sm-4 control-label">门牌号</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="Hold" id="Hold" value="<%=session.getAttribute("Hold")%>">
                                </div>
                            </div>
                            <div style="display:none;" class="form-group">
                                <label for="birthdate" class="col-sm-4 control-label">注册日期</label>
                                <div class="col-sm-8">
                                    <input type=text class="form-control" name="regdate" id="regdate" value="<%=session.getAttribute("regdate")%>" readonly="readonly">
                                </div>
                            </div>

                            <div class="form-group form-footer">
                                <div class="col-sm-offset-6 col-sm-8">
                                    <input type="submit" class="btn btn-primary" value="确认更新"/>
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