import React from "react";

const Home = () => {
    return (
        <div >
            <h1>Home</h1>
            <div class="d-flex justify-content-center">
                <div class="d-grid gap-2 col-6">
                    <a href="/reclamos" class="btn btn-primary" role="button" data-bs-toggle="button">Ver Reclamos</a>
                    <a href="/reclamosNew" class="btn btn-primary" role="button" data-bs-toggle="button">Hacer Reclamos</a>
                    <a href="/admin-edificios" class="btn btn-primary" role="button" data-bs-toggle="button">Administrar Edificios</a>
                    <a href="/admin-permisos" class="btn btn-primary" role="button" data-bs-toggle="button">Administrar Permisos</a>
                </div>
            </div>
        </div>
    );
}

export default Home;