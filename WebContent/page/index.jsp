<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>${loginUser.userName}的主页</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/layui/layui.js"></script>
</head>
<body>
	<div class="layui-container">
		<div class="layui-row layui-col-space12">
			<div class="layui-col-md3">
				<div class="layui-row">
					<div class="layui-col-md12"
						style="text-align: center; margin-top: 10px;">
						<div class="layui-inline">
							<img
								src="${pageContext.request.contextPath }/fileUpload/${loginUser.userIcon}"
								class="layui-circle" height="100px" width="100px">
						</div>

						<div style="margin-top: 10px;">
							
							<a href="javascript:editNoteUser(${loginUser.userId});">
								<h1>${loginUser.userName}</h1>
							</a>
							<c:if test="${vip eq 'yes'}">
								<span class="layui-badge">高级会员</span>
								<span class="layui-badge layui-bg-green">云币&nbsp;${loginUser.currency }</span>
								<span class="layui-badge layui-bg-orange">特权&nbsp;${loginUser.privilege}</span>

							</c:if>
							<c:if test="${vip eq 'no'}">
								<span class="layui-badge layui-bg-blue">普通会员</span>
								<span class="layui-badge layui-bg-green">云币&nbsp;${loginUser.currency }</span>
							</c:if>
								<a title="会员结束日期<fmt:formatDate value='${loginUser.vipEndDate}' pattern='yyyy-MM-dd'/>" target="_blank" href="${pageContext.request.contextPath }/vipPayAction_payPre?noteUser.userId=${loginUser.userId}" style="color: #FFB800;" >续费vip<span class="layui-badge-dot"></span></a>
						</div>
						<h2>${loginUser.userDesc}</h2>
					</div>
					<hr class="layui-bg-orange">
					<div class="layui-col-md12" style="text-align: center;">
						<ul>

							<li id="indexSelect"><a
								href="${pageContext.request.contextPath }/system_index.action">主页</a></li>
							<hr class="layui-bg-gray">
							<li id="favoriteSelect"><a id="loadFavoriteNoteList"
								href="javascript:void(0);">收藏夹</a></li>
							<hr class="layui-bg-gray">
							<c:forEach items="${loginUser.noteBookSet }" var="noteBook">
								<li id="${noteBook.bookId }Select"><a
									href="javascript:loadOneNoteList(${noteBook.bookId})">${noteBook.bookName }</a>
									<c:if test="${noteBook.noteType.typeId!=1}">
										<a href="javascript:editNoteBook(${noteBook.bookId});"
											title="编辑"><i class="layui-icon"
											style="font-size: 16px; color: #1E9FFF;">&#xe642;</i></a>
										<a href="javascript:deleteNoteBook(${noteBook.bookId});"
											title="删除"><i class="layui-icon"
											style="font-size: 16px; color: #FF5722;">&#xe640;</i></a>
									</c:if></li>
								<hr class="layui-bg-gray">
							</c:forEach>
							<li>
								<button id="addOneNote"
									class="layui-btn layui-btn-sm layui-btn-normal">添加笔记</button>
								<button id="addNoteBook"
									class="layui-btn layui-btn-sm layui-btn-normal">添加笔记本</button>
							</li>
							<hr class="layui-bg-gray">
						</ul>
					</div>
				</div>

			</div>
			<div class="layui-col-md9" id="content">
				<!-- 笔记类型 用于区分搜索范围 S 分享的笔记 F 收藏夹中的笔记 其余表示笔记本ID -->
				<input type="hidden" id="searchNoteType" />
				<!-- 笔记本id -->
				<input type="hidden" id="searchBookId" />
				<hr class="layui-bg-gray">
				<div id="searchBox">
				<input type="text" placeholder="搜索笔记" id="title"
					class="layui-input-inline layui-input" style="width: 50%" /> <a
					id="searchOneNote" href="javascript:void(0);"><i
					class="layui-icon" style="font-size: 16px; color: #1E9FFF;">&#xe615;</i>
				</a>
				</div>
				<div id="noteList"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		layui.use([ 'layer' ], function() {

		});
		$("#searchNoteType").val("S");
		$("#noteList").load(
				"${pageContext.request.contextPath}/shareNote_findAllShareNote.action?"
						+ "&time=" + new Date().getTime());
		$("li[id$='Select']").css("background", "");
		$("#indexSelect").css("background", "#5FB878");
		$("#searchBox").hide();
		var layer = layui.layer;
	/* 	if(${payResult}=="1"){
			layer.msg("支付成功");
		}else if(${payResult}=="2"){
			layer.msg("支付失败");
		} */
	});
	//新增笔记本
	$("#addNoteBook")
			.click(
					function() {
						var layer = layui.layer;
						layer
								.open({
									area : [ '500px', '200px' ],
									title : '新增笔记本',
									type : 2,
									content : '${pageContext.request.contextPath}/page/note/addNoteBook.jsp',
									end : function() {
										$(location)
												.attr("href",
														"${pageContext.request.contextPath}/system_index.action");
									}
								});
					});
	//添加笔记
	$("#addOneNote")
			.click(
					function() {
						var layer = layui.layer;
						layer
								.open({
									area : [ '900px', '600px' ],
									title : '新增笔记',
									type : 2,
									content : '${pageContext.request.contextPath}/page/note/addOneNote.jsp',
									end : function() {
										$(location)
												.attr("href",
														"${pageContext.request.contextPath}/system_index.action");
									}
								});
					});
	function editNoteUser(userId){
		var layer = layui.layer;
		layer
		.open({
			area : [ '900px', '600px' ],
			title : '个人信息修改',
			type : 2,
			content : '${pageContext.request.contextPath}/page/user/editUser.jsp',
			end : function() {
				$(location)
						.attr("href",
								"${pageContext.request.contextPath}/system_index.action");
			}
		});
		
	}
	//删除笔记本
	function deleteNoteBook(bookId) {
		var layer = layui.layer;
		$
				.ajax({
					async : false,
					data : {
						"noteBook.bookId" : bookId
					},
					type : "post",
					url : "${pageContext.request.contextPath}/noteBook_checkNoteBook.action",
					success : function(result) {
						if (result == "isNull") {
							$
									.ajax({
										async : false,
										data : {
											"noteBook.bookId" : bookId
										},
										type : "post",
										url : "${pageContext.request.contextPath}/noteBook_deleteOneNoteBook.action",
										success : function(result) {
											if (result == "deleteOk") {
												layer.msg("删除成功");
												$(location)
														.attr("href",
																"${pageContext.request.contextPath}/system_index.action");
											} else {
												layer.msg("删除失败");
											}
										}
									});
						} else if (result == "notNull") {
							layer.msg('该笔记本非空,不可删除', {
								icon : 5
							});
						}
					}
				});
	}
	//编辑笔记本
	function editNoteBook(bookId) {
		var layer = layui.layer;
		layer
				.open({
					area : [ '500px', '200px' ],
					title : '修改笔记本',
					type : 2,
					content : '${pageContext.request.contextPath}/noteBook_toUpdateNoteBook.action?bookId='
							+ bookId,
					end : function() {
						$(location)
								.attr("href",
										"${pageContext.request.contextPath}/system_index.action");
					}
				});
	}
	//获得指定笔记本的笔记
	function loadOneNoteList(bookId) {
		$("#searchBox").show();
		$("#searchNoteType").val(bookId);
		$("#searchBookId").val(bookId);
		$("#noteList")
				.load(
						"${pageContext.request.contextPath}/oneNote_findNoteList.action?oneNote.noteBook.bookId="
								+ bookId
								+ "&searchNoteType="
								+ $("#searchNoteType").val()
								+ "&time="
								+ new Date().getTime() + "");
		$("li[id$='Select']").css("background", "");
		$("#" + bookId + "Select").css("background", "#5FB878");

	}
	//取得收藏夹
	$("#loadFavoriteNoteList")
			.click(
					function() {
						$("#searchNoteType").val("F");
						$("#noteList")
								.load(
										"${pageContext.request.contextPath}/oneNote_findNoteList.action?&searchNoteType="
												+ $("#searchNoteType").val()
												+ "&time="
												+ new Date().getTime());
						$("li[id$='Select']").css("background", "");
						$("#favoriteSelect").css("background", "#5FB878");
					});
	//搜索笔记本
	$("#searchOneNote")
			.click(
					function() {
						$("#noteList")
								.load(
										"${pageContext.request.contextPath}/oneNote_findNoteList.action?&searchNoteType="
												+ $("#searchNoteType").val()
												+ "&oneNote.title="
												+ $("#title").val()
												+ "&oneNote.noteBook.bookId="
												+ $("#searchBookId").val()
												+ "&time="
												+ new Date().getTime());
					});
	
</script>
</html>
