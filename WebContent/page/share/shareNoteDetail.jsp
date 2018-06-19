<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${oneNote.title }</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/layui/css/layui.css" />
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layui/layui.js"></script>

</head>
<body>
	<div class="layui-container">
		<div class="layui-row">
			<div class="layui-col-md8 layui-col-md-offset2">
				<div class="layui-row">
					<h1 style="padding: 10px 0 15px 0;">${oneNote.title }</h1>
					<div class="layui-col-md2">
						<img
							src="${pageContext.request.contextPath }/fileUpload/${oneNote.noteUser.userIcon}"
							class="layui-circle layui-inline" height="18px" width="18px">
						<p class="layui-inline">${oneNote.noteUser.userName}</p>
					</div>
					<div class="layui-col-md3">
						<i class="layui-icon " style="font-size: 16px; color: #1E9FFF;">&#xe60e;</i>
						<fmt:formatDate value="${ oneNote.createDate }"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</div>
					<div class="layui-col-md3">
						点击右侧克隆到自己的笔记本
					</div>
					<div class="layui-col-md4">
					<a href="javascript:cloneOneNote(${shareNote.shareId });" id="editOneNote" title="编辑">
						<i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe656;</i>
						</a> 
					</div>
				</div>
				<hr class="layui-bg-gray">
				<div class="layui-row">
					<div class="layui-col-md12">${oneNote.body}</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			layui.use([ 'layer' ], function() {

			});
		});
		function cloneOneNote(shareId){
			var layer = layui.layer;
			$
			.ajax({
				async : false,
				data : {
					"shareNote.shareId" : shareId
				},
				type : "post",
				url : "${pageContext.request.contextPath}/shareNote_cloneOneNote.action",
				success : function(result) {
					if(result=="cloneOk"){
						layer.msg("克隆成功");
					}else if(result=="noNoteBook"){
						layer.msg("没有笔记本,请先创建笔记本");
					}else if(result=="noPay"){
						layer.msg("没有购买");
					}else{
						layer.msg(result);
					}
				}
			});
		}
	</script>
</body>

</html>