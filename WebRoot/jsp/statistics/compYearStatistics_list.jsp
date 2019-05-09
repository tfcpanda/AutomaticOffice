<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js" ></script>
<script type="text/javascript">
	/* $(function(){
		$('#chart_img').attr('src','deptMonStatistics_createDetailChart.action?year=2013&month=7');
	}); */
	<%-- year = <%=request.getAttribute("year")%>;
	selectMonth = <%=request.getAttribute("selectMonth")%>;
	action = "deptMonStatistics_createDetailChart.action?year="+year+"&selectMonth="+selectMonth;
	document.getElementById("chart_img").setAttribute("src", action); --%>
	
	function confirm(){
		if(isNaN(document.queryForm.startYear.value)){
			alert("输入的开始年份不合法！");
			return false;
		}
		if(isNaN(document.queryForm.endYear.value)){
			alert("输入的结束年份不合法！");
			return false;
		}
		return true;
	}
	
	function submitSearch(){
		if(confirm()){
			document.queryForm.submit();
		}
	}
</script>

<div class="action  divaction">
	<div class="t">年度统计详情</div>
	<div class="pages">
		<s:form action="compYearStatistics_getList.action" name="queryForm">
	       		<label for="time">开始年份</label>
	       		<s:textfield name="startYear" id="startYear" cssClass="nwinput" value=""></s:textfield>
	       		<label for="end-time">结束年份</label>
	       		<s:textfield name="endYear" id="endYear" cssClass="nwinput" value=""></s:textfield>
		        <%-- <s:submit cssClass="submit_01" value="查询"/> --%>
		        <input type="button" value="提交" class="submit_01"
								onclick="submitSearch()"/>
	     </s:form>
	     
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
		  <tr class="even">
	        <td>编号</td>
	        <td>报销总额</td>
	        <td>年份</td>
	        <td>操作</td>
	      </tr>
	    <s:iterator value="statisticsListOfCompYear" id="compVoucherStatistics" begin="0" status="s">
	      <tr>
	        <td><s:property value="#s.count"/></td>
	        <td>￥<s:property value="#compVoucherStatistics.totalCount"/></td>
	        <td><s:property value="#compVoucherStatistics.year"/>年</td>
	        <td>
	        <a href="compYearStatistics_getDetail.action?currYear=<s:property value="#compVoucherStatistics.year"/>">
	        <img src="${images}/search.gif" width="16" height="15" />
	        </a>
	        </td>
	      </tr>
	      </s:iterator>
	      
	    </table>        
       </div>
       <span style="display:none;"><iframe id="downloadIframe" src="" style="width:0px;height:0px;"></iframe></span>
	          <!--增加报销单 区域 结束-->
       </div>
       <div style="">
       	  <center>
       	  
       	  </center>
       </div>
       
      </div>