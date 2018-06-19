<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fieldset class="layui-elem-field layui-field-title"
	style="margin-top: 30px;">
	<legend>最近分享</legend>
</fieldset>
<ul class="layui-timeline">
	<c:forEach items="${shareNoteList }" var="shareNote">
		<li class="layui-timeline-item"><i
			class="layui-icon layui-timeline-axis">&#xe63f;</i>
			<div
				class="layui-timeline-content layui-text  layui-row layui-col-space12">
				<div class="layui-col-md6">
					<a href="javascript:toViewOneNote(${shareNote.shareId })">${shareNote.shareTitle }</a>
					<div class="layui-row">
						<div class="layui-col-md3" style="text-align: center;">
							<img
								src="${pageContext.request.contextPath }/fileUpload/${shareNote.noteUser.userIcon }"
								class="layui-circle layui-inline" height="18px" width="18px">
							<p class="layui-inline">${shareNote.noteUser.userName }</p>
						</div>
						<div class="layui-col-md6">
							<i class="layui-icon ">&#xe60e;</i>
							<fmt:formatDate value="${shareNote.shareDate}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</div>
						<div class="layui-col-md3">
							<i class="layui-icon" style="color: #FFB800;">&#xe65e;</i>
							<c:if test="${shareNote.currency==0 }">
							免费
						</c:if>
							<c:if test="${shareNote.currency!=0 }">
							${shareNote.currency }云币
						</c:if>
						</div>
					</div>
				</div>
				<div class="layui-col-md3 layui-col-md-offset1"
					style="text-align: center;">
					<i class="layui-icon" style="color: #01AAED; display: block;">&#xe705;</i>
					<p>${shareNote.viewTotal }</p>
				</div>
			</div>
			<hr class="layui-bg-gray"></li>
	</c:forEach>
</ul>
<script type="text/javascript">
	$(function() {
		layui.use([ 'util', 'layer' ], function() {
			var util = layui.util;
			var layer = layui.layer;
			//执行
			util.fixbar({
				click : function(type) {
					console.log(type);
				}
			});
		});
	});

	//阅读或购买
	function toViewOneNote(shareId) {
		$.ajax({
			async : false,
			data : {
				"shareNote.shareId" : shareId
			},
			dataType:"json",
			type : "post",
			url : "${pageContext.request.contextPath}/shareNote_checkShareNote.action",
			success : function(result) {
				//免费或已购买
				if(result.type=="free"||result.hasPay=="yes"){
					window.open("${pageContext.request.contextPath}/shareNote_toOneNote.action?shareNote.shareId="+shareId+"&payType=0");
				//收费并且不是会员
				}else if(result.type=="purchase"&&result.vip=="no"){
					//云币不足
					if(result.userCurrency<result.currency){
						layer.confirm('您的云币不足，请分享笔记赚取或充值成vip享受特权查看', {
							  btn: ['成为VIP','取消'] ,//按钮
						      icon: 0
							}, function(){
								window.open("${pageContext.request.contextPath }/vipPayAction_payPre?noteUser.userId=${loginUser.userId}");
								return;
							}, function(){
							  return;
							});
					}
					//云币充足
					if(result.userCurrency>=result.currency){
						layer.confirm("该条笔记付费,需"+result.currency+"云币,余额"+result.userCurrency+"云币,是否购买", {
							  btn: ['购买','取消'] ,//按钮
						      icon: 0
							}, function(){
								window.open("${pageContext.request.contextPath}/shareNote_toOneNote.action?shareNote.shareId="+shareId+"&payType=1");
								return;
							}, function(){
							  return;
							});
					}
				}else if(result.type=="purchase"&&result.vip=="yes"){
					if(result.privilege==0){
						//云币不足
						if(result.userCurrency<result.currency){
							layer.msg('您的云币不足，VIP特权次数0，请分享笔记赚取云币或等待下月特权次数');
						}else if(result.userCurrency>=result.currency){
							layer.confirm("该条笔记付费,需"+result.currency+"云币,余额"+result.userCurrency+"云币,是否购买", {
								  btn: ['购买','取消'] ,//按钮
							      icon: 0
								}, function(){
									window.open("${pageContext.request.contextPath}/shareNote_toOneNote.action?shareNote.shareId="+shareId+"&payType=1");
									return;
								}, function(){
								  return;
								});
						}
					}else if(result.privilege>0){
						//云币不足
						if(result.userCurrency<result.currency){
							layer.confirm("该条笔记付费,是否使用vip特权购买,剩余特权次数"+result.privilege+"", {
								  btn: ['购买','取消'] ,//按钮
							      icon: 0
								}, function(){
									window.open("${pageContext.request.contextPath}/shareNote_toOneNote.action?shareNote.shareId="+shareId+"&payType=2");
									return;
								}, function(){
								  return;
								});
						}else if(result.userCurrency>result.currency){
							layer.confirm("该条笔记付费,需使用云币,余额"+result.userCurrency+"或使用VIP特权,余额"+result.privilege+",购买", {
								  btn: ['使用云币','使用VIP特权','取消'] ,//按钮
							      icon: 0
								}, function(){
									window.open("${pageContext.request.contextPath}/shareNote_toOneNote.action?shareNote.shareId="+shareId+"&payType=1");
									return;
								}, function(){
									window.open("${pageContext.request.contextPath}/shareNote_toOneNote.action?shareNote.shareId="+shareId+"&payType=2");
									return;
								}, function(){
								  return;
								});
						}
					}
					
				}
			}
		});
	}
</script>