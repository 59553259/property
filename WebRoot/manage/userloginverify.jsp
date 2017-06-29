<!DOCTYPE html>
<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript">
	var login = <%=session.getAttribute("loginusername")%>;
	if (login == null){
		window.open('userlogin.jsp','_top');
	}
</script>
