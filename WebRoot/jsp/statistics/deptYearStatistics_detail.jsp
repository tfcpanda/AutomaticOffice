<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	/* $(function(){
		$('#chart_img').attr('src','deptMonStatistics_createDetailChart.action?year=2013&month=7');
	}); */
	<%-- year = <%=request.getAttribute("year")%>;
	selectMonth = <%=request.getAttribute("selectMonth")%>;
	action = "deptMonStatistics_createDetailChart.action?year="+year+"&selectMonth="+selectMonth;
	document.getElementById("chart_img").setAttribute("src", action); --%>
</script>

<div class="action  divaction">
	<div class="t">月度统计详情</div>
	<div class="pages">
		<s:form action="deptYearStatistics_createDetailExcel.action" name="queryForm">
	       		<label for="time">年份:</label>
	       		<s:property value="currYear"/>
	       		<s:hidden name="currYear" value="%{currYear}"/>
	       		<s:hidden name="departmentId" value="%{departmentId}"/>
		        <s:submit cssClass="submit_01" value="导出报表"/>
	     </s:form>
	     
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
		  <tr class="even">
	        <td>编号</td>
	        <td>报销人</td>
	        <td>报销总额</td>
	        <td>年份</td>
	        <td>部门</td>
	      </tr>
	     <s:iterator value="statisticsDetailOfDeptYear" id="claimVouyearStatistics" begin="0" status="s">
	      <tr>
	      	<td><s:property value="#claimVouyearStatistics.creator.sn"/></td>
	      	<td bgcolor="yellow"><s:property value="#claimVouyearStatistics.creator.name"/></td>
	      	<td bgcolor="yellow">￥<s:property value="#claimVouyearStatistics.totalAccount"/></td>
	      	<td><s:property value="currYear"/></td>
	      	<td><s:property value="#claimVouyearStatistics.creator.department.name"/></td>
	      </tr>		
		</s:iterator>
	      
	    </table>        
       </div>
       <span style="display:none;"><iframe id="downloadIframe" src="" style="width:0px;height:0px;"></iframe></span>
	          <!--增加报销单 区域 结束-->
       </div>
       <div style="">
       	  <center><img src="<%=path%>/jsp/statistics/deptYearStatistics_createDetailChart.action?currYear=<%=request.getAttribute("currYear")%>&departmentId=<%=request.getAttribute("departmentId")%>"></center>
       </div>
       
      </div>