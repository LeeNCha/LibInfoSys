<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>도서관별 도서 정보 시스템</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="css/libinfosys.css" type="text/css"></link>
</head>
<body>
	<div class="sb-box">
		<div class="container">
			<div class="sb-wrap input-group">
				<input type="text" class="search-box"/>
				<span class="input-group-btn">
			        <button class="btn btn-sb" type="button"><span class="glyphicon glyphicon-search"></span></button>
			    </span>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<!-- 정렬방식 선택하는 부분 -->
			<div>
				
			</div>
			
			<!-- 리스트 -->
			<ul class="book-li clearfix">
			<c:forEach items="${bookList}" var="book">
				<li>
					<div class="pull-left bl-no">${book.book_no}</div>
					<dl class="pull-left clearfix">
						<dt class="pull-left">${book.book_name}</dt>
						<dd class="pull-left info">
							<div>${book.book_author}</div>
							<div>${book.book_isbn}</div>
							<div>${book.book_quantity}</div>
						</dd>
						<dd class="pull-left sum">책소개</dd>
					</dl>
				</li>
			</c:forEach>
				
			</ul>
		</div>
		<!-- pagination -->
		<jsp:include page="page.jsp" flush="false">
			<jsp:param value="${page}" name="page" />
			<jsp:param value="${tot}" name="totnum"/>
		</jsp:include>
		
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->	
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   <!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>