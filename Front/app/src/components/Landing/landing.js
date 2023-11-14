import React from "react"


const Landing = () => {
    return (
        <div>
            <div>
                <h1 className="display-1">Sistema de gestion de reclamos</h1>
            </div>
            <div>
                <a href="/login" className="btn btn-primary" role="button" data-bs-toggle="button">Iniciar Sesion</a>
                <a href="/signup" className="btn btn-primary" role="button" data-bs-toggle="button">Registrarse</a>
            </div>
        </div>
    )
};
export default Landing;
