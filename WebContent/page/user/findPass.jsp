<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8" />
<title>找回密码</title>
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
			action="${pageContext.request.contextPath}/user_findPass.action"
			method="post">
			<div class="layui-form-item" style="text-align: center;">
				<h2>找回密码</h2>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱地址</label>
				<div class="layui-input-block">
					<input name="userEmail" lay-verify="email|emailOnly" autocomplete="off"
						placeholder="请输入邮箱地址" class="layui-input" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">验证码</label>
				<div class="layui-input-block">
					<input id="checkCode" name="checkCode" lay-verify="required|checkCodeInput" placeholder="请输入验证码"
						autocomplete="off" class="layui-input"> <img
						id="checkCodeImg" class="layui-form-mid layui-word-aux"
						src="${pageContext.request.contextPath}/checkCode_getCheckCode.action"
						width="100px" />
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
		function checkEmail(value){
			var url = "${pageContext.request.contextPath}/checkEmail_checkEmailAddr.action";
			var flag = false;
			$.ajax({
				type : "post",
				url : url,
				data : "emailAddr=" + value,
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
		layui
				.use(
						[ 'form', 'layer', 'upload' ],
						function() {
							var form = layui.form;
							var layer = layui.layer;
							var upload = layui.upload;
							form.verify({
								checkCodeInput : function(value, item) {
									if (!checkCode(value)) {
										return "验证码错误";
									}
								},
								emailOnly:function(value,item){
									if(checkEmail(value)){
										return "邮箱地址不存在";
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
		$("#checkCodeImg").click(
				function() {
					$("#checkCodeImg").attr(
							"src",
							"${pageContext.request.contextPath}/checkCode_getCheckCode.action?time="
									+ new Date());
				});
	</script>
</body>
</html>
