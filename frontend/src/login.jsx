import React from "react";

function Login()
{
    return(
    <div class="inner">
        <div class="image-holder">
        <img src="loginImage.jpeg" alt="loginPage" />
        </div>
        <form action="">
            <h3>Login Form</h3>
            <div class="form-wrapper">
                <input type="text" placeholder="Username" class="form-control" />
                <i class="zmdi zmdi-account"></i>
            </div>
            <div class="form-wrapper">
                <input type="password" placeholder="Password" class="form-control" />
                <i class="zmdi zmdi-lock"></i>
            </div>
                <button>Login<i class="zmdi zmdi-arrow-right"></i></button>
        </form>
        </div>
    );
}

export default Login;