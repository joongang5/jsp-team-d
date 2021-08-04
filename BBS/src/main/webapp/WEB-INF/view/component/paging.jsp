<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="pageSize" value="${page.endPage - page.startPage + 1 }"/>

<button onclick="location.href='${command}?pageNo=1'">맨앞으로</button>
<c:if test="${page.currentPage le pageSize}">
	<button disabled="disabled">이전</button>
</c:if>
<c:if test="${page.currentPage gt pageSize }">
	<button onclick="location.href='${command }?pageNo=${page.currentPage - pageSize }'">이전</button>
</c:if>

<c:if test="${page.currentPage eq 1 }">
	<button disabled="disabled">◀</button>
</c:if>
<c:if test="${page.currentPage ne 1 }">
	<button onclick="location.href='${command }?pageNo=${page.currentPage - 1}'">◀</button>
</c:if>

<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
	<c:if test="${i eq page.currentPage }">
		<a href="${command }?pageNo=${i }"><button>${i }</button></a>
	</c:if>
	<c:if test="${i ne page.currentPage }">
		<a href="${command }?pageNo=${i}">${i }</a>
	</c:if>
</c:forEach>
<c:if test="${page.currentPage eq page.totalPages }">
	<button disabled="disabled">▶</button>
</c:if>
<c:if test="${page.currentPage ne page.totalPages }">
	<button onclick="location.href='${command }?pageNo=${page.currentPage + 1 }'">▶</button>
</c:if>
<c:if test="${page.currentPage lt page.totalPages - pageSize - 1 }">
	<button onclick="location.href='${command }?pageNo=${page.currentPage + pageSize }'">다음</button>
</c:if>
<c:if test="${page.currentPage gt page.totalPages - pageSize }">
	<button disabled="disabled">다음</button>
</c:if>
<button onclick="location.href='${command }?pageNo=${page.totalPages }'">끝으로</button>