<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8" />
<title>密码重置</title>
<style type="text/css">
.regist {
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
	<%-- <canvas id="Mycanvas"></canvas> --%>
	<div class="regist" class="layui-form">
		<form class="layui-form"
			action="${pageContext.request.contextPath}/user_updatePassSubmit.action"
			method="post">
			<div class="layui-form-item" style="text-align: center;">
				<h2>密码重置</h2>
			</div>
			<input type="hidden" name="userId" value="${userId }"/>
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-block">
					<input id="pass" name="userPassword" lay-verify="required|secure"
						autocomplete="off" placeholder="请输入密码" class="layui-input"
						type="password">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">确认密码</label>
				<div class="layui-input-block">
					<input name="repassword" lay-verify="repassword" autocomplete="off"
						placeholder="请输入密码" class="layui-input" type="password">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-normal" lay-submit=""
						lay-filter="subButton">提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>
	<script>
		layui.use([ 'form', 'layer'],
						function() {
							var form = layui.form;
							var layer = layui.layer;
							form.verify({
								secure : function(value, item) {
									if (!/^[a-zA-Z]\w{5,17}$/.test(value)) {
										return '以字母开头，长度在6~18之间，只能包含字母、数字和下划线'
									}
								},
								repassword : function(value, item) {
									if (value != $("#pass").val()) {
										return '两处密码不一致';
									}
								}
							
							});
							form.on('submit(subButton)', function(data) {
								//layer.msg(JSON.stringify(data.field));
								console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
								console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
								console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}

							});
						});
	</script>
	<%--	<script type="text/javascript" src="${pageContext.request.contextPath}/js/cavase.js"/> --%>
</body>
</html>
