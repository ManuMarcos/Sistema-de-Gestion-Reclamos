import React, { useEffect, useState } from "react";
import { useNavigate, Link } from "react-router-dom";

const NavBar = () => {
  const navigate = useNavigate();
  const [idEdificio, setIdEdificio] = useState(
    sessionStorage.getItem("edificioId")
  );

  function SubmitLogout(e) {
    e.preventDefault();
    sessionStorage.removeItem("accessToken");
    sessionStorage.removeItem("userId");
    sessionStorage.removeItem("userType");
    sessionStorage.removeItem("edificioId");
    setIdEdificio(null);
    navigate("/", { replace: true }); //TODO: ver si hacer una home o volver a root así nomá.
    navigate(0);
  }

  const [isLoggedIn, setLoggedIn] = useState(false);
  const [isAdmin, setAdmin] = useState(false);

  useEffect(() => {
    setLoggedIn(sessionStorage.getItem("userType") !== null);
    setAdmin(sessionStorage.getItem("userType") === "PERSONAL_INTERNO");
  }, []);

  return (
    <nav className="navbar navbar-expand-lg bg-body-tertiary">
      <div className="container-fluid">
        <a className="navbar-brand" href="/">
          Sistema de gestion de reclamos
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNavAltMarkup"
          aria-controls="navbarNavAltMarkup"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div className="navbar-nav">
            {isLoggedIn ? (
              <>
                <Link to={`/`}>
                  <div className="nav-link">Home</div>
                </Link>
                <Link to={`/Reclamos/Listado?edificio_id=${idEdificio}`}>
                  <div className="nav-link">Ver Reclamos</div>
                </Link>
                <Link to={`/Reclamos/Nuevo?edificio_id=${idEdificio}`}>
                  <div className="nav-link">Nuevo Reclamo</div>
                </Link>
                {isAdmin ? (
                  <>
                    <Link to="/Edificios/Listado">
                      <div className="nav-link">Edificios</div>
                    </Link>
                    <Link to="/admin-permisos">
                      <div className="nav-link">Permisos</div>
                    </Link>
                  </>
                ) : (
                  <></>
                )}
                <Link>
                  <div className="nav-link" onClick={SubmitLogout}>
                    Cerrar Sesión
                  </div>
                </Link>
              </>
            ) : (
              <Link to={`/login`}>
                <div className="nav-link">Iniciar Sesión</div>
              </Link>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
};
export default NavBar;
