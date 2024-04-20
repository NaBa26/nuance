import React from "react";

function Header()
{
    return (
            <div className="container">
                <div className="imageContainer">
                    <img src="./src/assets/logo.png" alt="logo" />
                </div>
                <div className="panelContainer">
                    <div className="genres">
                        <a href="#"><button className="genreButton">Genre</button></a>
                    </div>
                    <div id="authors">
                        <a href="#"><button className="authorButton">Author</button></a>
                    </div>
                    <div className="aboutus">
                        <a href="#"><button className="aboutUsButton">About Us</button></a>
                    </div>
                    <div id="signUp">
                        <a href="#"><button className="signUpButton">Sign Up</button></a>
                    </div>
                    <div className="logIn">
                        <a href="#"><button className="logInButton">Log In</button></a>
                    </div>
                </div>
            </div>
    )
}

export default Header;