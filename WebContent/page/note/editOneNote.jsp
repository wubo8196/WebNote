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
		action="${pageContext.request.contextPath }/oneNote_editSubmitOneNote.action"
		class="layui-border-box layui-form layui-form-pane layui-col-xs8 layui-col-xs-offset2">
		<!-- 笔记ID -->
		<input type="hidden" name="oneNote.noteId" value="${oneNote.noteId }"/>
		
		<div class="layui-form-item">
			<label class="layui-form-label">笔记标题</label>
			<div class="layui-input-block">
				<input name="oneNote.title" lay-verify="required" autocomplete="off"
					class="layui-input" type="text" value="${oneNote.title}" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">所属笔记本</label>
			<div class="layui-input-block">
				<select name="oneNote.noteBook.bookId" lay-verify="required">
					<c:forEach items="${loginUser.noteBookSet }" var="noteBook">
						<c:if test="${noteBook.noteType.typeId!=1 }">
							<option value="${noteBook.bookId}"
								<c:if test="${oneNote.noteBook.bookId==noteBook.bookId}">
							selected="selected"
							</c:if>>${noteBook.bookName }</option>
						</c:if>
					</c:forEach>
				</select>
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
					lay-filter="submit">保存</button>
			</div>
		</div>

	</form>
	<script>
		//Demo
		layui
				.use(
						[ 'layedit', 'form', 'layer' ],
						function() {
							var form = layui.form;
							var layer = layui.layer;
							var layedit = layui.layedit;
							//监听提交
							form.on('submit(submit)', function(data) {
								layer.msg("编辑成功");
								var index = parent.layer
										.getFrameIndex(window.name);
								parent.layer.close(index);
								return true;
							});
							layedit
									.set({
										uploadImage : {
											url : '${pageContext.request.contextPath}/upload_uploadOneNoteImg.action' //接口url
											,
											type : 'post' //默认post
											,
											field : "file"
										},
										 height:400
									});
							layedit.build('body');
						});
	</script>

</body>

</html>