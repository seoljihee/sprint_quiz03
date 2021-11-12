<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="../default/header.jsp"/>
<div class="wrap">
로그인 성공한 사용자만 게시판 글을 볼 수 있습니다.<hr>
<a href="write">글작성</a>
</div>
<c:import url="../default/footer.jsp"/>
</body>
</html>