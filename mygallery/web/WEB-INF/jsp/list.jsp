<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>油画列表</title>
	<script src="js/jquery-3.4.1.min.js" type="text/javascript"></script>
	<script src="js/sweetalert2.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="css\list.css">
	<script type="text/javascript">
		//对话框显示预览图
		function showPreview(previewObj){
			var preview = $(previewObj).attr("data-preview");
			var pname = $(previewObj).attr("data-pname");
			Swal.fire({
				title:pname,
				html:"<img src='"+preview+"' style='width:361px;height:240px'/>",
				showCloseButton: true,
				showConfirmButton: true
			})
		}
	</script>
</head>
<body>
	<div class="container">
		<fieldset>
			<legend>油画列表</legend>
			<div style="height: 40px">
				<a href="/management?method=show_create" class="btn-button">新增</a>
			</div>
			<!-- 油画列表 -->
			<table cellspacing="0px">
				<thead>
					<tr style="width: 150px;">
						<th style="width: 100px">分类</th>
						<th style="width: 150px;">名称</th>
						<th style="width: 100px;">价格</th>
						<th style="width: 400px">描述</th>
						<th style="width: 100px">操作</th>
					</tr>
				</thead>
				<c:forEach items="${PageModel.pageData}" var="painting">
					<tr>
						<td>
							<c:choose>
								<c:when test="${painting.category==1}">现实主义</c:when>
								<c:when test="${painting.category==2}">抽象主义</c:when>
								<c:otherwise>未知类型 </c:otherwise>
							</c:choose>
						</td>
						<td>${painting.pname}</td>
						<td><fmt:formatNumber pattern="¥0.00" value="${painting.price}"></fmt:formatNumber></td>
						<td>${painting.description}</td>
						<td>
							<%--自定义属性以data开头--%>
							<a class="oplink" data-preview="${painting.preview}" data-pname="${painting.pname}" href="javascript:void(0)" onclick="showPreview(this)">预览</a>
							<a class="oplink" href="/management?method=show_update&id=${painting.id}">修改</a>
							<a class="oplink" href="javascript:void(0)">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<!-- 分页组件 -->
			<ul class="page">
				<li><a href="/management?method=list&p=1">首页</a></li>
				<li><a href="/management?method=list&p=${PageModel.hasPreviousPage?PageModel.page-1:1}">上页</a></li>
				<c:forEach begin="1" end="${PageModel.totalPages}" var="pno" step="1">
				<li ${pno==PageModel.page?"class='active'":""}>
					<a href="/management?method=list&p=${pno}">${pno}</a></li>
				</c:forEach>
				<li><a href="/management?method=list&p=${PageModel.hasNextPage?PageModel.page+1:PageModel.page}">下页</a></li>
				<li><a href="/management?method=list&p=${PageModel.totalPages}">尾页</a></li>
			</ul>
		</fieldset>
	</div>

</body>
</html>
