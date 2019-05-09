<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<style>
.box-a{ width:400px;height:20px;float:left;margin-right:200px}
.box-b{ width:100px;height:20px;float:left}
</style>
<div class="top">
	<div class="global-width">
		<img src="images/logo.gif" class="logo" />
	</div>
</div>

<div class="status">
	<div class="global-width">
		<div class="box-a">
		<span class="usertype">【登录角色：${sessionScope.employee_position}】</span>${sessionScope.employee.name}你好！欢迎访问JBOA办公管理系统！
		</div>
		<div  class="box-b">
			<form action="emp-logout.action" id="logout_form" target="_top">
				<span style="color:red;" onclick="document:logout_form.submit();">注销</span>
			</form>
		</div>
	</div>
</div>
