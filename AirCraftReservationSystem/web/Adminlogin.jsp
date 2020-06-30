<%-- 
   Document   : Adminlogin
   Created on : Jun 25, 2020, 9:56:39 PM
   Author     : isuru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Sign In</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">

        <style>
            * {box-sizing: border-box;}
            body {font-family: Verdana, sans-serif;}
            .mySlides {display: none;}
            img {vertical-align: middle;}

            /* Slideshow container */
            .slideshow-container {
                max-width: 4000px;
                position: relative;
                margin: auto;
            }

            /* Caption text */
            .text {
                color: #f2f2f2;
                font-size: 15px;
                padding: 8px 12px;
                position: absolute;
                bottom: 8px;
                width: 100%;
                text-align: center;
            }

            /* Number text (1/3 etc) */
            .numbertext {
                color: #f2f2f2;
                font-size: 12px;
                padding: 8px 12px;
                position: absolute;
                top: 0;
            }

            /* The dots/bullets/indicators */
            .dot {
                height: 15px;
                width: 15px;
                margin: 0 2px;
                background-color: #bbb;
                border-radius: 50%;
                display: inline-block;
                transition: background-color 0.6s ease;
            }

            .active {
                background-color: #717171;
            }

            /* Fading animation */
            .fade {
                -webkit-animation-name: fade;
                -webkit-animation-duration: 1.5s;
                animation-name: fade;
                animation-duration: 1.5s;
            }

            @-webkit-keyframes fade {
                from {opacity: .4} 
                to {opacity: 1}
            }

            @keyframes fade {
                from {opacity: .4} 
                to {opacity: 1}
            }

            /* On smaller screens, decrease text size */
            @media only screen and (max-width: 300px) {
                .text {font-size: 11px}
            }
            .topic-container {
                max-width: 4000px;
                padding-top: 8px;
                padding-bottom: 10px;
                background: url(https://jackrugile.com/images/misc/noise-diagonal.png), linear-gradient(#777, #444);
            }
            .maintopic {
                font-family: "Arial Black", Gadget, sans-serif0;
                font-size: 30px;
                font-weight: bold;
                color: whitesmoke;
                vertical-align: middle;
                display: inline-block;

            }
        </style>

    </head>
    <body>
        <div class="topic-container" style="white-space:nowrap">
            <div style="white-space:nowrap">
                <img src="Images/logo2-1.png" alt="logo" width="100" height="50">
                <p class="maintopic">Smart Air Titcket Reservation</p>
                <img src="Images/logo2.png" alt="logo" width="100" height="50">
            </div>
           
        </div>
        <div class="slideshow-container">
            <div class="mySlides fade">
                <img src="Images/image01.jpg" style="width:100%">
            </div>
            <div class="mySlides fade">
                <img src="Images/image02.png" style="width:100%">
            </div>
            <div class="mySlides fade">
                <img src="Images/image03.jpg" style="width:100%">
            </div>
            <div class="mySlides fade">
                <img src="Images/image04.jpg" style="width:100%">
            </div>
            <div class="mySlides fade">
                <img src="Images/image05.jpg" style="width:100%">
            </div>
        </div>
        <br>

        <div style="text-align:center">
            <span class="dot"></span> 
            <span class="dot"></span> 
            <span class="dot"></span>
            <span class="dot"></span> 
            <span class="dot"></span>
        </div>

        <script>
            var slideIndex = 0;
            showSlides();

            function showSlides() {
                var i;
                var slides = document.getElementsByClassName("mySlides");
                var dots = document.getElementsByClassName("dot");
                for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";
                }
                slideIndex++;
                if (slideIndex > slides.length) {
                    slideIndex = 1
                }
                for (i = 0; i < dots.length; i++) {
                    dots[i].className = dots[i].className.replace(" active", "");
                }
                slides[slideIndex - 1].style.display = "block";
                dots[slideIndex - 1].className += " active";
                setTimeout(showSlides, 1000); // Change image every 2 seconds
            }
        </script>

        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <form class="login100-form validate-form p-l-55 p-r-55 p-t-178" method="POST" action="SignInAdmin">
                        <span class="login100-form-title">Admin Sign In</span>
                        <div class="wrap-input100 validate-input m-b-16">
                            <input class="input100" type="email" name="adminemail" id="adminemail" placeholder="User Email">
                            <span class="focus-input100"></span>
                        </div>
                        <div class="wrap-input100 validate-input">
                            <input class="input100" type="password" name="adminpassword" id="adminpassword" placeholder="Password">
                            <span class="focus-input100"></span>
                        </div>
                        <br><br>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" type="submit" value="Sign In">Admin Sign in</button>
                        </div>
                        <div class="flex-col-c p-t-10 p-b-10">
                            <span class="txt1 p-b-9">Are you Not an Admin</span>
                            <a href="index.html" class="txt3">Sign in as User</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
