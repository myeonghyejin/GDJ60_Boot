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
                            <h1 class="fw-bolder">Login</h1>
                            <p class="lead fw-normal text-muted mb-0">We'd love to hear from you</p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <form id="contactForm" action="./login" method="post" data-sb-form-api-token="API_TOKEN">
                                    <!-- User Name input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="username" value="${cookie.remember.value}" name="username" type="text" placeholder="Enter your ID..." data-sb-validations="required" />
                                        <label for="username">ID</label>
                                        <div class="invalid-feedback" data-sb-feedback="name:required">A name is required.</div>
                                    </div>
                                    <!-- Password input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="password" name="password" type="password" placeholder="Enter your Password..." data-sb-validations="required" />
                                        <label for="password">Password</label>
                                        <div class="invalid-feedback" data-sb-feedback="name:required">A name is required.</div>
                                    </div>
                                    <div class="mb-3">
                                    	<input id="remember" name="remember" value="remember" type="checkbox">
                                    	<label for="remember">ID 기억하기</label>
                                    </div>
                                    <!-- Submit Button-->
                                    <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Login</button></div>
                                </form>
                                <a href="./findPassword">비밀번호를 잊으셨나요?</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>        
	</main>
	<!-- Footer 적용 -->
	<c:import url="../temp/footer.jsp"></c:import>
	<!-- Footer 끝 -->
	<script>
		const exampleModal = document.getElementById('exampleModal')
		if (exampleModal) {
		  exampleModal.addEventListener('show.bs.modal', event => {
		    // Button that triggered the modal
		    const button = event.relatedTarget
		    // Extract info from data-bs-* attributes
		    const recipient = button.getAttribute('data-bs-whatever')
		    // If necessary, you could initiate an Ajax request here
		    // and then do the updating in a callback.
	
		    // Update the modal's content.
		    const modalTitle = exampleModal.querySelector('.modal-title')
		    const modalBodyInput = exampleModal.querySelector('.modal-body input')
	
		    modalTitle.textContent = `New message to ${recipient}`
		    modalBodyInput.value = recipient
		  })
		}
	</script>
</body>