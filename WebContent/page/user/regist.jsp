<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8" />
<title>注册</title>
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
			action="${pageContext.request.contextPath}/user_registSubmit.action"
			method="post">
			<div class="layui-form-item" style="text-align: center;">
				<h2>用户注册</h2>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">昵称</label>
				<div class="layui-input-block">
					<input name="userName" lay-verify="required" autocomplete="off"
						placeholder="请输入昵称" class="layui-input" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱地址</label>
				<div class="layui-input-block">
					<input name="userEmail" lay-verify="email|emailOnly" autocomplete="off"
						placeholder="请输入邮箱地址" class="layui-input" type="text">
				</div>
			</div>
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
				<label class="layui-form-label">头像</label>
				<div class="layui-input-block">
					<button id="userIconBtn" type="button" class="layui-btn"
						id="iconUpload">
						<i class="layui-icon">&#xe67c;</i>上传图片
					</button>
					<input id="userIcon" name="userIcon" type="hidden" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">自我描述</label>
				<div class="layui-input-block">
					<input name="userDesc" autocomplete="off"
						placeholder="请输入描述" class="layui-input" type="text">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-normal" lay-submit=""
						lay-filter="subButton">提交注册</button>
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
							var uploadInst = upload
									.render({
										elem : '#userIconBtn' //绑定元素
										,
										url : "${pageContext.request.contextPath}/upload_upload.action"//上传接口
										,
										method : "post",
										field : "file",
										done : function(res) {
											//上传完毕回调
											layer.msg("上传成功");
											$("#userIcon").val(res.fileName);
											var imgTag = "<img src=\"${pageContext.request.contextPath}/fileUpload/"+res.fileName+"  \" />"
											$("#userIconBtn").replaceWith(
													imgTag);
										},
										error : function() {
										}
									});
							form.verify({
								checkCodeInput : function(value, item) {
									if (!checkCode(value)) {
										return "验证码错误";
									}
								},
								name : function(value, item) {
									if (!/^.{6,20}$/.test(value)) {
										return '用户名必须6-20位'
									}
								},
								secure : function(value, item) {
									if (!/^[a-zA-Z]\w{5,17}$/.test(value)) {
										return '以字母开头，长度在6~18之间，只能包含字母、数字和下划线'
									}
								},
								repassword : function(value, item) {
									if (value != $("#pass").val()) {
										return '两处密码不一致';
									}
								},
								emailOnly:function(value,item){
									if(!checkEmail(value)){
										return "已被注册"
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
	<%--	<script type="text/javascript" src="${pageContext.request.contextPath}/js/cavase.js"/> --%>
</body>
</html>
