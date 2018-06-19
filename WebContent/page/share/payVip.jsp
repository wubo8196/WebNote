<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员充值</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/layui/css/layui.css" />
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/layui/layui.js"></script>

</head>
<body>
<input type="hidden" value="${loginUser.userId}" id="userId"/>
<blockquote class="layui-elem-quote">高级会员立享每月20次特权下载,每月10元</blockquote>
	<form
		class="layui-border-box layui-form layui-form-pane layui-col-xs6 layui-col-xs-offset3">
		<div class="layui-form-item">
			<label class="layui-form-label">续费月数</label>
			<div class="layui-input-inline">
				<input id="month" lay-verify="required|number" autocomplete="off"
					placeholder="请输入充值月数 " class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">支付方式</label>
			<div class="layui-input-block">
				<input name="payType" value="1" title="支付宝" checked="checked" type="radio">
				<input name="payType" value="2" title="微信" type="radio">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block layui-col-xs12">
				<button class="layui-btn layui-layer-close" lay-submit
					lay-filter="submit">去支付</button>
			</div>
		</div>

	</form>
	<form style="display:none;" id="paySubmitForm" method="post" action="https://pay.bbbapi.com/">
		<input name="goodsname" id="goodsname" type="text" /> 
		<input name="istype" id="istype" type="text" /> 
		<input name="key" id='key' type="text" /> 
		<input name="notify_url" id="notify_url" />
		<input name="orderid" id="orderid" type="text" />
		<input name="orderuid" id='orderuid' type="text"/>
		<input name="price" id="price" type="text" /> 
		<input name="return_url" id="return_url" type='text' />
		<input name="uid" id="uid" type="text" />
		<input type='submit' id='paySubmitBtn'/>
	</form>
	
	<script>
		//Demo
		layui.use([ 'form', 'layer' ], function() {
			var form = layui.form;
			var layer = layui.layer;
			//监听提交
			form.on('submit(submit)', function(data) {
				$
				.ajax({
					async : true,
					data : {
						"order.userId" : $("#userId").val(),
						"order.month":$("#month").val(),
						"order.istype":$('input[name="payType"]:checked').val()
					},
					type : "post",
					url : "${pageContext.request.contextPath}/vipPayAction_returnPaySubmit.action",
					success : function(result) {
						$("#goodsname").val(result.data.goodsname);
						$("#istype").val(result.data.istype);
						$("#key").val(result.data.key);
						$("#notify_url").val(result.data.notify_url);
						$("#orderid").val(result.data.orderid);
						$("#orderuid").val(result.data.orderuid);
						$("#price").val(result.data.price);
						$("#return_url").val(result.data.return_url);
						$("#uid").val(result.data.uid);
						$("#paySubmitBtn").click();
					}
				});
				//$("#submit").click();
				//parent.layer.close(index);
				return false;
			});
		});
	</script>

</body>

</html>