import React from "react";


function Header()
{
    return (
        <div className="containerMain">
            <div className="container">
                <div className="imageContainer">
                    <img></img>
                </div>
                <div className="panelContainer">
                    <div className="genres">
                        <button className="genreButton">Genre</button>
                    </div>
                    <div id="authors">
                        <button className="authorButton">Author</button>
                    </div>
                    <div className="aboutus">
                        <button className="aboutUsButton">About Us</button>
                    </div>
                    <div id="signUp">
                        <button className="signUpButton">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Header;