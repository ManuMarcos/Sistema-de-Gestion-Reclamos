import React from "react"

const NavBar = () => {
    return (
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
                <a className="navbar-brand" href="/">Sistema de gestion de reclamos</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <a class="nav-link active" aria-current="page" href="/">Home</a>
                        <a class="nav-link" href="/reclamos">Reclamos</a>
                        <a class="nav-link" href="/edificios">Edificios</a>
                        <a class="nav-link" href="/login">Cerrar Sesion</a>
                    </div>
                </div>
            </div>
        </nav>
    )
};
export default NavBar;
