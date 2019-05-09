<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>JOBA办公自动化管理系统</title>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body {
	font: 12px 宋体;
	background: #4BB8EF url(images/bg.gif) repeat-x;
}

img {
	border: 0;
}

.login-top {
	width: 100%;
	height: 186px;
	margin: 147px auto 0;
	background: url(images/login_01.gif) no-repeat center 0;
}

.login-area {
	width: 100%;
	height: 140px;
	margin: 0 auto;
	background: url(images/login_02.gif) no-repeat center 0;
}

.login-area form {
	width: 290px;
	margin: 0 auto;
}

.login-area label {
	clear: left;
	float: left;
	margin-top: 13px;
	width: 60px;
	font: 600 14px 宋体;
}

.login-area  input {
	width: 122px;
	height: 16px;
	margin-top: 11px;
	border: 1px #767F94 solid;
	font: 12px/16px 宋体;
}

input.login-sub {
	width: 104px;
	height: 34px;
	border: 0;
	background: url(images/login_sub.gif) no-repeat 0px 1px; *
	margin-top: 5px;
}

.login-copyright {
	width: 100%;
	height: 30px;
	margin: 18px auto 0;
	background: url(images/copyright.gif) no-repeat center 0;
}
</style>
<script type="text/javascript">
	function check() {
		var msg = document.getElementById("msg").value;
		if (msg.length != 0) {
			alert(msg);
			document.getElementById("msg").value = "";
		}
	}
</script>
</head>
<body onload="check()">
	<div class="login-top"></div>
	<div class="login-area">
		<form action="emp-login.action" method="post">
			<table>
				<tbody>
					<tr>
						<td><label> 工&nbsp;号： </label></td>
						<td colspan="2"><input type="text" name="employee.sn" /></td>
					</tr>
					<tr>
						<td><label> 密&nbsp;码： </label></td>
						<td colspan="2"><input type="password"
							name="employee.password" /></td>
					</tr>
					<tr>
						<td><label> 验证码： </label></td>
						<td><input type="text" name="validationCode" size="4" /></td>
						<td valign="bottom">
						
						
						<img style="width:60px"
							src="validationCode"
							onclick="this.src='validationCode?code='+Date.parse(new Date())" /></td>
					</tr>
					<tr>
						<td colspan="3" align="center"><input type="submit"
							class="login-sub" value="" /> <s:actionerror
								cssStyle="margin-top: 10px;color: red;" /><input type="hidden" id="msg"
							value="${requestScope.msg }" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="login-copyright"></div>
</body>
</html>