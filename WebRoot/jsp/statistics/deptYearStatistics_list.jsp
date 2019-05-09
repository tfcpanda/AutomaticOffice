<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js">
</script>

<div class="action  divaction">
	<div class="t">年度统计列表</div>
	<div class="pages">
	     <s:form action="deptYearStatistics_getDeptStatisticsByYear.action" name="queryForm">
	       		<label for="time">开始年份</label>
	       		<s:select name="startYear" list="#{2011:'2011年',2012:'2012年',2013:'2013年',2014:'2014年',2015:'2015年',2016:'2016年',2017:'2017年',2018:'2018年',2019:'2019年',2020:'2020年',2021:'2021年',2022:'2022年' }" 
	  			listKey="key" listValue="value" headerKey="0" headerValue="无" theme="simple"></s:select>
	       		<label for="end-time">结束年份</label>
	       		<s:select name="endYear" list="#{2011:'2011年',2012:'2012年',2013:'2013年',2014:'2014年',2015:'2015年',2016:'2016年',2017:'2017年',2018:'2018年',2019:'2019年',2020:'2020年',2021:'2021年',2022:'2022年' }" 
	  			listKey="key" listValue="value" headerKey="0" headerValue="无" theme="simple"></s:select>
		        <input type="hidden" name="pageNo" value="1"/>
		 	    <input type="hidden" name="pageSize" value="5"/>
		 	    <!-- <input type="hidden" name="year" value="2013"/> -->
		        <s:submit cssClass="submit_01" value="查询"/>
	     </s:form>
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>总计</td>
	        <td>年份</td>
	        <td>部门</td>
	        <td>操作</td>
	      </tr>
	      <s:iterator value="pageUtil.items" id="claimVouyearStatistics" begin="0" status="s">
	      <tr>
	        <td><s:property value="#claimVouyearStatistics.id"/></td>
	        <td>￥<s:property value="#claimVouyearStatistics.totalCount"/></td>
	        <td><s:property value="#claimVouyearStatistics.year"/>年</td>
	        <td><s:property value="#claimVouyearStatistics.department.name"/></td>
	        <td>
	        <a href="deptYearStatistics_getDeptVoucherDetailByYear.action?currYear=<s:property value="#claimVouyearStatistics.year"/>&departmentId=<s:property value="#claimVouyearStatistics.department.id"/>">
	        <img src="${images}/search.gif" width="16" height="15" />
	        </a>
	        </td>
	      </tr>
	      </s:iterator>
	      <tr>
	        <td colspan="6" align="center">
		      	<c:import
						url="../common/rollPage.jsp" charEncoding="UTF-8">
						<c:param name="formName" value="document.forms[0]" />
						<c:param name="totalRecordCount" value="${pageUtil.totalCount}" />
						<c:param name="totalPageCount" value="${pageUtil.totalPageCount}" />
						<c:param name="pageNo" value="${pageUtil.pageNo}" />
					</c:import></td>
  			</td>
	      </tr>
	    </table>        
       </div>
      </div>