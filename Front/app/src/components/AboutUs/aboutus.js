import React from "react";
import { useNavigate } from "react-router-dom";

const AboutUs = () => {
    const nav = useNavigate();

    function navegarAtras() {
        nav(-1);
    }

    return (
        <div>
            <h2>Acerca de Nosotros</h2>
            <p>Poner nombres de integrantes TPO?</p>
            <div>
            <button className="btn btn-primary" type="submit"onClick={navegarAtras}>Volver</button>
            </div>
        </div>
    );
}

export default AboutUs;