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
		<!-- Header 적용 -->
        <c:import url="../temp/header.jsp"></c:import>
        <!-- Header 끝 -->
        <input type="hidden" name="num" value="${boardVO.num}">
        <section class="py-5">
			<div class="container px-5 my-5">
				<div class="row gx-5">
					<div class="col-lg-3">
                            <div class="d-flex align-items-center mt-lg-5 mb-4">
                                <img class="img-fluid rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." />
                                <div class="ms-3">
                                    <div class="fw-bold">${boardVO.writer}</div>
                                    <div class="text-muted">아기 토끼</div>
                                </div>
                            </div>
                        </div>
					<div class="col-lg-9">
						<!-- Post content-->
						<article>
							<!-- Post header-->
							<header class="mb-4">
								<!-- Post title-->
								<h1 class="fw-bolder mb-1">${boardVO.title}</h1>
								<!-- Post meta content-->
								<div class="text-muted fst-italic mb-2">${boardVO.regDate}</div>
								<!-- Post categories-->
								<a class="badge bg-secondary text-decoration-none link-light" href="#!">Web Design</a>
								<a class="badge bg-secondary text-decoration-none link-light" href="#!">Freebies</a>
							</header>
							<!-- Preview image figure-->
								<c:forEach items="${boardVO.boardFileVO}" var="boardFileVO">
							<figure class="mb-4">
								<img class="img-fluid rounded" src="/file/${board}/${boardFileVO.fileName}" alt="..." />
								<a href="./fileDownload?fileNum=${boardFileVO.fileNum}">${boardFileVO.originalName}</a>	
							</figure>
								</c:forEach>
							<!-- Post content-->
							<section class="mb-5">
								<p class="fs-5 mb-4">${boardVO.contents}</p>
							</section>
						</article>
					</div>
				</div>
			</div>
		</section>
	</main>
	<!-- Footer 적용 -->
	<c:import url="../temp/footer.jsp"></c:import>
	<!-- Footer 끝 -->
</body>