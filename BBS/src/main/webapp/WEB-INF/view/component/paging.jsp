<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="pageSize" value="${articlePage.endPage - articlePage.startPage + 1 }"/>

<button onclick="location.href='${command}?page=1'">맨앞으로</button>
<c:if test="${articlePage.currentPage le pageSize}">
	<button disabled="disabled">이전</button>
</c:if>
<c:if test="${articlePage.currentPage gt pageSize }">
	<button onclick="location.href='${command }?pageNo=${articlePage.currentPage - pageSize }'">이전</button>
</c:if>

<c:if test="${articlePage.currentPage eq 1 }">
	<button disabled="disabled">◀</button>
</c:if>
<c:if test="${articlePage.currentPage ne 1 }">
	<button onclick="location.href='${command }?pageNo=${articlePage.currentPage - 1}'">◀</button>
</c:if>

<c:forEach var="i" begin="${articlePage.startPage }" end="${articlePage.endPage }">
	<c:if test="${i eq articlePage.currentPage }">
		<a href="${command }?pageNo=${i }"><button>${i }</button></a>
	</c:if>
	<c:if test="${i ne articlePage.currentPage }">
		<a href="${command }?pageNo=${i}">${i }</a>
	</c:if>
</c:forEach>
<c:if test="${articlePage.currentPage eq articlePage.totalPages }">
	<button disabled="disabled">▶</button>
</c:if>
<c:if test="${articlePage.currentPage ne articlePage.totalPages }">
	<button onclick="location.href='${command }?pageNo=${articlePage.currentPage + 1 }'">▶</button>
</c:if>
<c:if test="${articlePage.currentPage lt articlePage.totalPages - pageSize - 1 }">
	<button onclick="location.href='${command }?pageNo=${articlePage.currentPage + pageSize }'">다음</button>
</c:if>
<c:if test="${articlePage.currentPage gt articlePage.totalPages - pageSize }">
	<button disabled="disabled">다음</button>
</c:if>
<button onclick="location.href='${command }?pageNo=${articlePage.totalPages }'">끝으로</button>