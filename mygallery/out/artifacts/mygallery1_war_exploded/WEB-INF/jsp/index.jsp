<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="css\common.css">
	<script type="text/javascript" src="js\js1.js"></script>
</head>
<body>
	<c:if test="${param.c!=null}">
		<c:set var="categoryParam" value="&c=${param.c}"></c:set>
	</c:if>
	<c:if test="${param.c==null}">
		<c:set var="categoryParam" value=""></c:set>
	</c:if>
	<div class="header">
		<div class="logo">
			<img src="image\logo.png">
		</div>
		<div class="menu"   onclick="show_menu()" onmouseleave="show_menu1()">
			 <div class="menu_title" ><a href="###">内容分类</a></div>
			 <ul id="title">
				<li><a href="/page?c=1">现实主义</a></li>
				<li><a href="/page?c=2">抽象主义</a></li>
			 </ul>
		</div>
		<div class="auth">
			<ul>
				<li><a href="#">登录</a></li>
				<li><a href="#">注册</a></li>
			</ul>
		</div>
	</div>
	<div class="content">
	  <div class="banner">
  		<img src="image/welcome.png" class="banner-img">
	  </div>
	  <div class="img-content">
		<ul>
			<c:forEach items="${PageModel.pageData}" var="painting">
			<li>
				<img src="${painting.preview}" class="img-li">
				<div class="info">
					<h3>${painting.pname}</h3>
					<p>
						${painting.description}
					</p>
					<div class="img-btn">
						<div class="price"><fmt:formatNumber pattern="￥0.00" value="${painting.price}"></fmt:formatNumber> </div>
							<a href="#" class="cart">
						       <div class="btn">
							      <img src="image/cart.svg">
						       </div>
						    </a>
					</div>
				</div>
			</li>
			</c:forEach>
		</ul>
	  </div>
	  <div class="page-nav">
		<ul>
			<li><a href="/page?p=1${categoryParam}">首页</a></li>
			<li><a href="/page?p=${PageModel.hasPreviousPage?PageModel.page-1:1}${categoryParam}">上一页</a></li>
			<c:forEach begin="1" end="${PageModel.totalPages}" var="pno" step="1">
			<li>
				<span ${pno==PageModel.page?"class='first-page'":""}>

					<a href="/page?p=${pno}${categoryParam}">${pno}</a>
				</span>
			</li>
			</c:forEach>
			<li><a href="/page?p=${PageModel.hasNextPage?PageModel.page+1:PageModel.page}${categoryParam}">下一页</a></li>
			<li><a href="/page?p=${PageModel.totalPages}${categoryParam}">尾页</a></li>
		</ul>
	  </div>
	</div>
	<div class="footer">
		<p><span>M-GALLARY</span>©2020 POWERED BY IMOOC.INC</p>
	</div>
</body>
</html>