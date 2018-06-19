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
	<form id="class_add" method="post" action="${pageContext.request.contextPath }/noteBook_saveOneNoteBook.action"
		class="layui-border-box layui-form layui-form-pane layui-col-xs6 layui-col-xs-offset3">
		<div class="layui-form-item">
			<label class="layui-form-label">笔记本名称</label>
			<div class="layui-input-block">
				<input name="bookName" lay-verify="required" autocomplete="off"
					placeholder="请输入笔记本名称 " class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">笔记本说明</label>
			<div class="layui-input-block">
				<input name="bookDesc" lay-verify="required" autocomplete="off"
					placeholder="输入笔记本说明" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block layui-col-xs12">
				<button class="layui-btn layui-layer-close" lay-submit
					lay-filter="submit">保存</button>
			</div>
		</div>

	</form>
	<script>
		//Demo
		layui.use([ 'form', 'layer'], function() {
			var form = layui.form;
			var layer = layui.layer;
			//监听提交
			form.on('submit(submit)', function(data) {
				layer.msg("添加成功");
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				return true;
			});
		});
	</script>

</body>

</html>