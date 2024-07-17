<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sign Up Form by Sayub Shakya</title>
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">
<link rel="stylesheet" href="css/style.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet"
	href="https://unpkg.com/sweetalert/dist/sweetalert.css">
</head>
<body>

	<!-- Hidden input to hold status -->
	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">

	<div class="main">
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
						<form method="post" action="RegistrationServlet"
							class="register-form" id="register-form">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Your Name" />
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" />
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pass" id="pass" placeholder="Password" />
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="password" name="re_pass" id="re_pass"
									placeholder="Repeat your password" />
							</div>
							<div class="form-group">
								<label for="contact"><i class="zmdi zmdi-phone"></i></label> <input
									type="text" name="contact" id="contact"
									placeholder="Contact no" />
							</div>
							<div class="form-group">
								<input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" /> <label for="agree-term"
									class="label-agree-term"><span><span></span></span>I
									agree all statements in <a href="#" class="term-service">Terms
										of service</a></label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sign up image">
						</figure>
						<a href="login.jsp" class="signup-image-link">I am already a
							member</a>
					</div>
				</div>
			</div>
		</section>
	</div>

	<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        var status = document.getElementById("status").value;
        if (status === "success") {
            swal({
                title: "Congrats",
                text: "Account Created Successfully",
                icon: "success"
            }).then((value) => {
                window.location.href = "login.jsp";
            });
        }
    });
</script>
</body>
</html>
