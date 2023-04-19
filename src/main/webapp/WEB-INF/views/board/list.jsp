<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>Modern Business - Start Bootstrap Template</title>
	<!-- css favicon 적용 -->
	<c:import url="../temp/style.jsp"></c:import>
	<!-- css favicon 끝 -->
</head>
<body class="d-flex flex-column h-100">
	<main class="flex-shrink-0">
		<!-- Navigation-->
        <c:import url="../temp/header.jsp"></c:import>
        <!-- Header-->
        
		<section class="bg-light py-5">
			<div class="container px-5 my-5">
				<div class="text-center mb-5">
                        <h1 class="fw-bolder" style="text-transform: capitalize;">${board} List</h1>
                        <p class="lead fw-normal text-muted mb-0">I want to go home</p>
                    </div>
                    
				<div class="row gx-5">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>No.</th>
								<th>Title</th>
								<th>Writer</th>
								<th>Date</th>
								<th>Hit</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="boardVO">
								<tr>
									<td>${boardVO.num}</td>
									<td><a href="./detail?num=${boardVO.num}">${boardVO.title}</a></td>
									<td>${boardVO.writer}</td>
									<td>${boardVO.regDate}</td>
									<td>${boardVO.hit}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row g-3 align-items-center">
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<c:if test="${pagination.pre}">
								<li class="page-item">
									<a class="page-link" href="./list?page=${pagination.startNum - 1}&condition=${pagination.condition}&search=${pagination.search}" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:if>
							<c:forEach begin="${pagination.startNum}" end="${pagination.lastNum}" var="page">
								<li class="page-item"><a class="page-link" href="./list?page=${page}&condition=${pagination.condition}&search=${pagination.search}">${page}</a></li>
							</c:forEach>
							<c:if test="${pagination.next}">
								<li class="page-item">
									<a class="page-link" href="./list?page=${pagination.lastNum + 1}&condition=${pagination.condition}&search=${pagination.search}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:if>
						</ul>
					</nav>
				</div>
				<form action="./list">
					<div class="row g-3 align-items-center">
						<select class="form-select" aria-label="Default select example" name="condition">
							<option selected>검색 </option>
							<option value="title">Title</option>
							<option value="contents">Contents</option>
							<option value="writer">Writer</option>
						</select>
						<input class="form-control" type="text" name="search">
						<button class="btn btn-primary" type="submit">검색</button>
					</div>
				</form>
			</div>
		</section>
		<div class="row g-3 align-items-center">
			<a href="./add" class="btn btn-primary">+</a>
		</div>
	</main>
	<!-- Footer 적용 -->
	<c:import url="../temp/footer.jsp"></c:import>
	<!-- Footer 끝 -->
</body>