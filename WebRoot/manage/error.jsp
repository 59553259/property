<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.wxcloud.util.Util" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style type="text/css">
<!--
.STYLE10 {
	font-size: 14px;
	color: #FF0000;
}
-->
</style>
<%
	int type = (Integer)session.getAttribute(Util.OPTTYPE);
	switch (type) {
	case Util.OPTTYPE_EDITSELFADMIN:	/* 个人设置（系统管理员） */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=home.jsp' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_ADDADMIN:	/* 添加系统管理员 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryAdmin.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_EDITADMIN:	/* 更新系统管理员 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryAdmin.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_DELETEADMIN:	/* 删除系统管理员 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryAdmin.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_ADDUSER:	/* 添加企业用户 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryUser.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_EDITUSER:	/* 更新企业用户 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryUser.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_EDITUSERS:	/* 更新企业用户 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=userhome.jsp' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_DELETEUSER:	/* 删除企业用户 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryUser.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_ADDHOUSE:	/* 添加房产用户 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryHouse.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_EDITHOUSE:	/* 更新房产用户 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryHouse.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_DELETEHOUSE:	/* 删除房产用户 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryHouse.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_ADDREPAIR:	/* 添加维修 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryRepair.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_EDITREPAIR:	/* 更新维修 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryRepair.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_DELETEREPAIR:	/* 删除维修 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryRepair.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_ADDCOST:	/* 添加Cost*/
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryCost.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_EDITCOST:	/* 更新Cost */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryCost.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_DELETECOST:	/* 删除COSt */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryCost.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_ADDNOTICE:	/* 添加Notice*/
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryNotice.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_EDITNOTICE:	/* 更新Notice */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryNotice.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_DELETENOTICE:	/* 删除Notice */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryNotice.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_ADDEXPRESS:	/* 添加Express*/
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryExpress.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_EDITEXPRESS:	/* 更新Express */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryExpress.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_DELETEEXPRESS:	/* 删除Express */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryExpress.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_ADDDEAL:	/* 添加报修信息*/
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryDeal.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_EDITDEAL:	/* 更新报修信息 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryDeal.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_DELETEDEAL:	/* 删除报修信息 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryDeal.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_ADDCOMPLAINT:	/* 添加投诉信息*/
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryComplaint.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_EDITCOMPLAINT:	/* 更新投诉信息 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryComplaint.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	case Util.OPTTYPE_DELETECOMPLAINT:	/* 删除投诉信息 */
		out.println( "<html><head><meta http-equiv='refresh' content='2; URL=m_queryComplaint.action' target=content></head><body bgcolor='#FFFFFF'></body></html>");
		break;
	}
%>
<br /><br /><br /><br /><br /><br /><br /><br />
<center>
	<table width="300" border="1" bordercolor="#34383C" style="border-collapse:collapse" cellpadding="0" cellspacing="0">
		<!--DWLayoutTable-->
		<tr bgcolor="#34383C">
			<td height="13">&nbsp;</td>
		</tr>
		<tr>
			<td height="60" align="center"><span class="STYLE10">操作失败</span></td>
		</tr>
		<tr>
			<td><font size="2" face="宋体" style="font-size: 9pt" color="red"><s:fielderror key="msg"></s:fielderror></font></td>
		</tr>
	</table>
</center>

