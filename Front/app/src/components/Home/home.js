import React, { useState } from "react";
import { Link } from "react-router-dom";

const Home = () => {
  const [idEdificio, setIdEdificio] = useState(
    sessionStorage.getItem("edificioId")
  );

  async function login_sacar_luego(user, pass) {
    // Default options are marked with *
    const response = await fetch("http://localhost:8080/auth/login", {
      method: "POST", // *GET, POST, PUT, DELETE, etc.
      mode: "cors", // no-cors, *cors, same-origin
      headers: {
        "Content-type": "application/json",
        "Access-Control-Allow-Origin": "htpp://localhost:3000",
        "Access-Control-Allow-Methods": "POST, GET, PUT",
        "Access-Control-Allow-Headers": "Content-Type",
        "cache-control": "no-cache",
      },
      body: JSON.stringify({
        nombre: user,
        password: pass,
      }), // body data type must match "Content-Type" header
    });
    return response.json(); // parses JSON response into native JavaScript objects
  }

  function SubmitLogin(e) {
    e.preventDefault();
    console.log(e);
    login_sacar_luego(e.target[0].value, e.target[1].value)
      .then((data) => {
        sessionStorage.setItem("accessToken", data["accessToken"]);
        sessionStorage.setItem("userId", data["userId"]);
        sessionStorage.setItem("userType", data["tipo"]);
        sessionStorage.setItem("edificioId", data["edificioId"]);
        setIdEdificio(sessionStorage.getItem("edificioId"));
        alert("Login OK");
      })
      .catch((err) => alert("Error Login"));
  }

  function SubmitLogout(e) {
    e.preventDefault();
    sessionStorage.removeItem("accessToken");
    sessionStorage.removeItem("userId");
    sessionStorage.removeItem("userType");
    sessionStorage.removeItem("edificioId");
    setIdEdificio(null);
  }

  return (
    <div>
      <h1>Inicio</h1>
      <p>Bienvenido a la pagina de inicio</p>
      <div className="d-flex justify-content-center">
        <div className="d-grid gap-2 col-6">
          <Link to={`/Reclamos/Listado?edificio_id=${idEdificio}`}>
            <button className="btn btn-primary">Reclamos Listado</button>
          </Link>
          <Link to={`/Reclamos/Nuevo?edificio_id=${idEdificio}`}>
            <button className="btn btn-primary">Reclamos Nuevo</button>
          </Link>
          <Link to="/Edificios/Listado">
            <button className="btn btn-primary">Administrar Edificios</button>
          </Link>
          <a
            href="/admin-permisos"
            className="btn btn-primary"
            role="button"
            data-bs-toggle="button"
          >
            Administrar Permisos
          </a>
        </div>
      </div>
      {/* TODO: sacar el login truchanga cuando esté el posta */}
      <form onSubmit={SubmitLogin}>
        <label>Login truchanga (obtener token y +)</label>
        <br />
        <input type="text" id="user" />
        <br />
        <input type="text" id="pass" />
        <br />
        <input type="submit" value="Login" />
      </form>
      <form onSubmit={SubmitLogout}>
        <input type="submit" value="Logout" />
      </form>
            


    </div>
  );
};

export default Home;
