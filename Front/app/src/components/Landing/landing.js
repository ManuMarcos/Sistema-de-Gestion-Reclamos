import React from "react"


const Landing = () => {
    return (
        <div>
            <div>
                <h1 class="display-1">Sistema de gestion de reclamos</h1>
            </div>
            <div>
                <a href="/login" class="btn btn-primary" role="button" data-bs-toggle="button">Iniciar Sesion</a>
                <a href="/signup" class="btn btn-primary" role="button" data-bs-toggle="button">Registrarse</a>
            </div>
        </div>
    )
};
export default Landing;
