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
						<i class="layui-icon " style="font-size: 16px; color: #1E9FFF;">&#xe705;</i>
						<span>${oneNote.noteBook.bookName }</span>
					</div>
					<div class="layui-col-md4">
						<c:if test="${empty oneNote.noteFavorite }">
							<a href="javascript:likeOrCancle(${oneNote.noteId })" title="收藏">
								<i id="likeIcon" class="layui-icon" style="font-size: 20px; color: #e2e2e2;">&#xe658;</i>
							</a>
						</c:if>
						<c:if test="${!empty oneNote.noteFavorite }">
							<a href="javascript:likeOrCancle(${oneNote.noteId})" title="取消收藏">
								<i id="likeIcon" class="layui-icon" style="font-size: 20px; color: #FF5722;">&#xe658;</i>
							</a>
						</c:if>
						
						<a href="javascript:editOneNote(${oneNote.noteId },${noteBook.bookId});" id="editOneNote" title="编辑">
						<i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe642;</i>
						</a> 
						<c:if test="${oneNote.noteBook.noteType.typeId==1 }">
						<a class="editBtn" href="javascript:deleteOneNote(${oneNote.noteId});" id="deleteOneNote" title="彻底删除">
							<i class="layui-icon" style="font-size: 20px; color: #FF5722;">&#xe640;</i>
						</a>
						<a class="editBtn" href="javascript:restoreOneNote(${oneNote.noteId});" id="deleteOneNote" title="还原">
							<i class="layui-icon" style="font-size: 20px; color: #1E9FFF;">&#xe603;</i>
						</a>
						</c:if>
						<c:if test="${oneNote.noteBook.noteType.typeId!=1 }">
						<c:if test="${empty oneNote.shareNote }">
							<a id="shareNoteLink${oneNote.noteId}" class="editBtn"
								href="javascript:shareOneNote(${oneNote.noteId});"
								 title="分享"> <i id="shareOneNote${oneNote.noteId }" class="layui-icon"
								style="font-size: 20px; color: #e2e2e2;">&#xe641;</i>
							</a>
						</c:if>
						<c:if test="${!empty oneNote.shareNote }">
							<a id="shareNoteLink${oneNote.noteId}" class="editBtn" href="javascript:cancleShareOneNote(${oneNote.noteId});" 
								title="取消分享"> <i id="shareOneNote${oneNote.noteId }" class="layui-icon"
								style="font-size: 20px; color: #FFB800;">&#xe641;</i>
							</a>
						</c:if>
						<a class="editBtn" href="javascript:deleteOneNote(${oneNote.noteId});" id="deleteOneNote" title="删除">
							<i class="layui-icon" style="font-size: 20px; color: #FF5722;">&#xe640;</i>
						</a>
						</c:if>
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
		function editOneNote(noteId, bookId) {
			var layer = layui.layer;
			var index = layer
					.open({
						area : [ '900px', '500px' ],
						title : '修改笔记',
						type : 2,
						maxmin : true,
						content : '${pageContext.request.contextPath}/oneNote_editOneNotePre.action?oneNote.noteId='
								+ noteId + '',
						end : function() {
							$(location)
									.attr(
											"href",
											"${pageContext.request.contextPath }/oneNote_oneNoteDetail.action?oneNote.noteId=${ oneNote.noteId }");
						}
					});
			layer.full(index);
		};
		//收藏或取消收藏
		function likeOrCancle(noteId) {
			var layer = layui.layer;
			$.ajax({
						async:false,
						data:{"oneNote.noteId":noteId},
						type:"post",
						url : "${pageContext.request.contextPath}/oneNote_likeOrCancle.action",
						success : function(result) {
							if(result=="likeOk"){
								$("#likeIcon").css("color","#FF5722")
								layer.msg("收藏成功");
							}else if(result="cancleOk"){
								$("#likeIcon").css("color","#e2e2e2")
								layer.msg("取消收藏成功")
							}else{
								layer.msg("操作失败");
							}
						}
					});
		}
		//删除
		function deleteOneNote(noteId) {
			layer.msg('你确定删除吗？', {
				time : 0 //不自动关闭
				,
				shadeClose:false,
				btn : [ '确认', '取消' ],
				yes : function(index) {
					$
					.ajax({
						async : false,
						data : {
							"oneNote.noteId" : noteId
						},
						type : "post",
						url : "${pageContext.request.contextPath}/oneNote_deleteOneNote.action",
						success : function(result) {
							if (result == "deleteOk") {
								layer.msg("删除成功");
							} else {
								layer.msg("操作失败");
							}
						}
					});
				}
			});
		}
		//还原
		function restoreOneNote(noteId){
			var layer = layui.layer;
			$
					.ajax({
						async : false,
						data : {
							"oneNote.noteId" : noteId
						},
						type : "post",
						url : "${pageContext.request.contextPath}/oneNote_restoreOneNote.action",
						success : function(result) {
							if (result == "restoreOk") {
								layer.msg("还原成功");
								$("#oneNoteLi"+noteId+"").remove();
							} else {
								layer.msg("操作失败:"+result);
							}
						}
					});
		}
		//分享
		function shareOneNote(noteId) {
			var layer = layui.layer;
			layer
					.open({
						area : [ '900px', '300px' ],
						title : '分享笔记',
						type : 2,
						content : '${pageContext.request.contextPath}/shareNote_shareOneNotePre.action?oneNote.noteId='
								+ noteId + '',
						end : function() {
							layer.msg("分享成功");
							$("#shareOneNote"+noteId+"").css("color", "#FFB800");
							$("#shareNoteLink"+noteId+"").attr("href","javascript:cancleShareOneNote("+noteId+");");
							$("#shareNoteLink"+noteId+"").attr("title","取消分享");
						}
					});
		}
		//取消分享
		function cancleShareOneNote(noteId) {
			var layer = layui.layer;
			$
			.ajax({
				async : false,
				data : {
					"oneNote.noteId" : noteId
				},
				type : "post",
				url : "${pageContext.request.contextPath}/shareNote_cancleShareOneNote.action",
				success : function(result) {
					if(result=="cancleShareOk"){
						layer.msg("取消分享成功");
						$("#shareOneNote"+noteId+"").css("color", "#e2e2e2");
						$("#shareNoteLink"+noteId+"").attr("href","javascript:shareOneNote("+noteId+");");
						$("#shareNoteLink"+noteId+"").attr("title","分享");
					}else{
						layer.msg("取消分享失败");
					}
				}
			});
			
		}
	</script>
</body>

</html>