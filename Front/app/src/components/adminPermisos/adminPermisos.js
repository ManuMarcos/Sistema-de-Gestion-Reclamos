import React, { useState, useEffect } from "react";
import { Link, useSearchParams } from "react-router-dom";

async function get_edificio(id_usuario) {
  const response = await fetch(
    "http://localhost:8080/usuarios" + id_usuario,
    {
      method: "GET", // *GET, POST, PUT, DELETE, etc.
      mode: "cors", // no-cors, *cors, same-origin
      headers: {
        "Content-type": "application/json",
        "Access-Control-Allow-Origin": "htpp://localhost:3000/",
        "Access-Control-Allow-Methods": "POST, GET, PUT",
        "Access-Control-Allow-Headers": "Content-Type",
        Authorization: "Bearer " + sessionStorage.getItem("accessToken"),
        "cache-control": "no-cache",
      },
    }
  );
  return response.json(); // parses JSON response into native JavaScript objects
}

async function get_reclamos(id_usuario) {
  const response = await fetch(
    "http://localhost:8080/usuarios?usuarioId=" + id_usuario,
    {
      method: "GET", // *GET, POST, PUT, DELETE, etc.
      mode: "cors", // no-cors, *cors, same-origin
      headers: {
        "Content-type": "application/json",
        "Access-Control-Allow-Origin": "htpp://localhost:3000/",
        "Access-Control-Allow-Methods": "POST, GET, PUT",
        "Access-Control-Allow-Headers": "Content-Type",
        Authorization: "Bearer " + sessionStorage.getItem("accessToken"),
        "cache-control": "no-cache",
      },
    }
  );
  return response.json();
}

const AdminPermisos = () => {
  const [datosEdificio, setDatosEdificio] = useState([]);
  const [datosListadoReclamos, setDatosListadoReclamos] = useState([]);
  const [searchParams] = useSearchParams();

  useEffect(() => {
    let id_elegido = parseInt(searchParams.get("usuario_id"));
    get_edificio(id_elegido)
      .then((data) => {
        setDatosEdificio(data);
        console.log("GET OK");
        get_reclamos(id_elegido)
          .then((data) => {
            console.log("GET II OK");
            setDatosListadoReclamos(data);
          })
          .catch((err) => {
            alert("No se pudo obtener reclamos del edificio");
          });
      })
      .catch((err) => {
        alert("No se pudo obtener datos del edificio");
      });
  }, [searchParams]);

  return (
    <div className="container">
      <h1>Administrador de usuarios</h1>
      <div className="form-group">
      </div>
      <hr />
      <table className="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Usuario</th>
            <th scope="col">Contrasena</th>
            <th scope="col">Tipo de usuario</th>
          </tr>
        </thead>
        <tbody>
          {datosListadoReclamos ? (
            datosListadoReclamos.map((usuario) => {
              return (
                <tr key={usuario["numero"]}>
                  <th scope="row">{usuario["numero"]}</th>
                  <td>{usuario["username"]}</td>
                  <td>{usuario["pass"]}</td>
                  <td>{usuario["tipouser"]}</td>
                  <td>
                    <Link
                      to="/Reclamos/Detalle"
                      state={{ rec: usuario, edi: datosEdificio }}
                    >
                      <button type="button" className="btn btn-primary">
                        Ver detalles
                      </button>
                    </Link>
                  </td>
                </tr>
              );
            })
          ) : (
            <h2>Sin Datos...</h2>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default AdminPermisos;
