<%--
  Created by IntelliJ IDEA.
  User: 14514
  Date: 2018/3/15
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta charset="utf-8" />
<title>登录</title>
<style type="text/css">
.login {
	border: 1px solid white;
	border-radius: 3%;
	box-shadow: 0px 0px 4px #ccc;
	width: 650px;
	padding: 50px;
	margin: auto;
	margin-top: 50px;
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/layui/css/layui.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cavase.css" />
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
	<div class="login" class="layui-form">
		<form class="layui-form"
			action="${pageContext.request.contextPath}/user_login.action"
			method="post">
			<div class="layui-form-item" style="text-align: center;">
				<h2>登录云笔记</h2>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-block">
					<input name="userEmail" lay-verify="required" autocomplete="off"
						placeholder="请输入邮箱地址" class="layui-input" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-block">
					<input name="userPassword" lay-verify="required" autocomplete="off"
						placeholder="请输入密码" class="layui-input" type="password">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">验证码</label>
				<div class="layui-input-block">
					<input id="checkCode" name="checkCode"
						lay-verify="required|checkCodeInput" placeholder="请输入验证码"
						autocomplete="off" class="layui-input"> <img
						id="checkCodeImg" class="layui-form-mid layui-word-aux"
						src="${pageContext.request.contextPath}/checkCode_getCheckCode.action"
						width="100px" />
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-normal" lay-submit=""
						lay-filter="loginSubmit">登录</button>
					<a href="${pageContext.request.contextPath}/page/user/regist.jsp"
						class="layui-btn layui-btn-warm">注册</a> 
						<a href="${pageContext.request.contextPath}/page/user/findPass.jsp" class="layui-btn layui-btn-primary">忘记密码</a>
				</div>
			</div>
		</form>
	</div>
	<script>
		function checkCode(value) {
			var url = "${pageContext.request.contextPath}/checkCode_checkCode.action";
			var flag = false;
			$.ajax({
				type : "post",
				url : url,
				data : "checkCode=" + value,
				async : false,
				success : function(data) {
					if (data == "error") {
						flag = false;
					} else {
						flag = true;
					}
				}
			});
			return flag;
		}

		layui.use([ 'form', 'layer' ], function() {
			var form = layui.form;
			var layer = layui.layer;
			form.verify({
				checkCodeInput : function(value, item) {
					if (!checkCode(value)) {
						return "验证码错误";
					}
				}
			});
			form.on('submit(loginSubmit)', function(data) {
				//layer.msg(JSON.stringify(data.field));
				console.log(data.elem);//被执行事件的元素DOM对象，一般为button对象
				console.log(data.form);//被执行提交的form对象，一般在存在form标签时才会返回
				console.log(data.field); //当前容器的全部表单字段，名值对形式：{name: value}
			});
		});
		$("#checkCodeImg").click(
				function() {
					$("#checkCodeImg").attr(
							"src",
							"${pageContext.request.contextPath}/checkCode_getCheckCode.action?time="
									+ new Date());
				});
	</script>
	<%-- <script type="text/javascript"
		src="${pageContext.request.contextPath}/js/cavase.js"></script> --%>
</body>
</html>
