

function Header()
{
    return (
        <header>
            <div className="container">
                <div className="imageContainer">
                    <img src="frontend\src\assets\logoFull.png" alt="Logo"></img>
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
                        <button className="signUpButton"></button>
                    </div>
                </div>
            </div>
        </header>
    )
}

export default Header;