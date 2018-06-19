<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<form id="class_add" method="post"
		action="${pageContext.request.contextPath }/shareNote_shareNoteSubmit.action"
		class="layui-border-box layui-form layui-form-pane layui-col-xs8 layui-col-xs-offset2">
		<!-- 笔记ID -->
		<input type="hidden" name="shareNote.oneNote.noteId" value="${oneNote.noteId }"/>
		
		<div class="layui-form-item">
			<label class="layui-form-label">分享标题</label>
			<div class="layui-input-block">
				<input name="shareNote.shareTitle" lay-verify="required" autocomplete="off"
					class="layui-input" type="text" value="${oneNote.title}" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">价格(0免费)</label>
			<div class="layui-input-block">
				<input name="shareNote.currency" lay-verify="required|number" autocomplete="off"
					class="layui-input" type="text"  />
			</div>
		</div>
		
		<div class="layui-form-item">
			<textarea name="oneNote.body" id="body" style="display: none;">
			${oneNote.body}
			</textarea>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block layui-col-xs12">
				<button class="layui-btn layui-layer-close" lay-submit
					lay-filter="submit">分享</button>
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
			layer.msg("分享");
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
			return true;
		});
	});
	</script>

</body>

</html>