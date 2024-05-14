import React from "react";
import { Link } from 'react-router-dom';

function Header()
{
    return (
            <div className="container">
                <div className="imageContainer">
                    <img src="./src/assets/logo.png" alt="logo" />
                </div>
                <div className="panelContainer">
                    <div className="search">
                        <input type="text" className="searchBar" placeholder='Search'/>
                    </div>
                    <div id="contactus">
                        <a href="#"><button className="contactUsButton">Contact Us</button></a>
                    </div>
                    <div className="aboutus">
                        <a href="#"><button className="aboutUsButton">About Us</button></a>
                    </div>
                    <div id="signUp">
                        <a href="Form"><button className="signUpButton">Sign Up</button></a>
                    </div>
                    <div className="logIn">
                        <Link to="/login"><button className="logInButton">Log In</button></Link>
                    </div>
                </div>
            </div>
    )
}

export default Header;