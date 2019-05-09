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
		<s:form action="compMonStatistics_getList.action" name="queryForm">
	     		<label for="time">年份</label>
	     		<s:select name="year" list="#{2013:'2013',2014:'2014',2015:'2015',2016:'2016',2017:'2017',2018:'2018'}" 
	  			listKey="key" listValue="value" headerKey="0" headerValue="无" theme="simple"></s:select>
	       		<label for="time">开始月份</label>
	       		<s:select name="startMonth" list="#{1:'1月份',2:'2月份',3:'3月份',4:'4月份',5:'5月份',6:'6月份',7:'7月份',8:'8月份',9:'9月份',10:'10月份',11:'11月份',12:'12月份' }" 
	  			listKey="key" listValue="value" headerKey="0" headerValue="无" theme="simple"></s:select>
	       		<label for="end-time">结束月份</label>
	       		<s:select name="endMonth" list="#{1:'1月份',2:'2月份',3:'3月份',4:'4月份',5:'5月份',6:'6月份',7:'7月份',8:'8月份',9:'9月份',10:'10月份',11:'11月份',12:'12月份' }" 
	  			listKey="key" listValue="value" headerKey="0" headerValue="无" theme="simple"></s:select>
		 	    <!-- <input type="hidden" name="year" value="2013"/> -->
		        <s:submit cssClass="submit_01" value="查询"/>
	     </s:form>
	     
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
		  <tr class="even">
	        <td>编号</td>
	        <td>报销总额</td>
	        <td>年份</td>
	        <td>月份</td>
	        <td>操作</td>
	      </tr>
	    <s:iterator value="statisticsListOfCompMonth" id="compVoucherStatistics" begin="0" status="s">
	      <tr>
	        <td><s:property value="#s.count"/></td>
	        <td>￥<s:property value="#compVoucherStatistics.totalCount"/></td>
	        <td><s:property value="#compVoucherStatistics.year"/>年</td>
	        <td><s:property value="#compVoucherStatistics.month"/>月</td>
	        <td>
	        <a href="compMonStatistics_getDetail.action?year=<s:property value="#compVoucherStatistics.year"/>&currMonth=<s:property value="#compVoucherStatistics.month"/>">
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