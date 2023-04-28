<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <section class="py-5">
                <div class="container px-5">
                    <!-- Contact form-->
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
                            <h1 class="fw-bolder">Join</h1>
                            <p class="lead fw-normal text-muted mb-0">We'd love to hear from you</p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <form:form id="contactForm" modelAttribute="memberVO" action="./join" method="post" data-sb-form-api-token="API_TOKEN">
                                	<input type="hidden" name="enabled" value="1">
                                    <!-- User Name input-->
                                    <div class="form-floating mb-3">
                                        <form:input path="username" id="username" cssClass="form-control"/>
                                       	<form:label path="username">ID</form:label>
                                        <form:errors path="username"></form:errors>
                                    </div>
                                    <!-- Password input-->
                                    <div class="form-floating mb-3">
                                        <form:password path="password" id="password" cssClass="form-control"/>
                                        <form:label path="password">Password</form:label>
                                        <form:errors path="password"></form:errors>
                                    </div>
                                    <!-- Password Check input-->
                                    <div class="form-floating mb-3">
                                        <form:password path="passwordCheck" id="passwordCheck" cssClass="form-control"/>
                                        <form:label path="passwordCheck">Password Check</form:label>
                                        <form:errors path="passwordCheck"></form:errors>
                                    </div>
                                    <!-- Name input-->
                                    <div class="form-floating mb-3">
                                    	<form:input path="name" id="name" cssClass="form-control"/>
                                    	<form:label path="name">Name</form:label>
                                        <form:errors path="name"></form:errors>
                                    </div>
                                    <!-- Email input-->
                                    <div class="form-floating mb-3">
                                        <form:input path="email" id="email" cssClass="form-control"/>
                                        <form:label path="email">Email</form:label>
                                        <form:errors path="email"></form:errors>
                                    </div>
                                    <!-- Birth input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="birth" name="birth" type="date" placeholder="Enter your Birthday..." data-sb-validations="required" />
                                        <label for="birth">Birth</label>
                                        <form:errors path="birth"></form:errors>
                                    </div>
                                    <!-- Submit Button-->
                                    <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Login</button></div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>        
	</main>
	<!-- Footer 적용 -->
	<c:import url="../temp/footer.jsp"></c:import>
	<!-- Footer 끝 -->
</body>