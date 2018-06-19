<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/layui/css/layui.css" />
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layui/layui.js"></script>

</head>
<body>
	<form method="post" action="${pageContext.request.contextPath }/user_updateSubmit.action"
		class="layui-border-box layui-form layui-form-pane layui-col-xs6 layui-col-xs-offset3">
		    <div class="layui-form-item">
				<label class="layui-form-label">昵称</label>
				<div class="layui-input-block">
					<input value="${loginUser.userName}" name="userName" lay-verify="required" autocomplete="off"
						placeholder="请输入昵称" class="layui-input" type="text">
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
					<input id="userIcon" name="userIcon" type="hidden" value="${loginUser.userIcon }" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">自我描述</label>
				<div class="layui-input-block">
					<input name="userDesc" autocomplete="off"
						placeholder="请输入描述" class="layui-input" type="text" value="${loginUser.userDesc }">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-normal" lay-submit=""
						lay-filter="subButton">提交修改</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
	</form>
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
								}
							});
							form.on('submit(subButton)', function(data) {
								//layer.msg(JSON.stringify(data.field));
								console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
								console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
								console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
								var index = parent.layer.getFrameIndex(window.name);
								parent.layer.close(index);
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