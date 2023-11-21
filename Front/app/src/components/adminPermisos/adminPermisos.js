import React, { useState, useEffect } from "react";
import { Link, useSearchParams } from "react-router-dom";
import AgregarUsuarioModal from "../SignUp/AgregarUsuarioModal"

async function get_usuarios(id_usuario) {
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
  const [datosAdminPermisos, setAdminPermisos] = useState([]);
  const [searchParams] = useSearchParams();

  useEffect(() => {
    let id_elegido = parseInt(searchParams.get("usuario_id"));
    get_usuarios(id_elegido)
      .then((data) => {
        setAdminPermisos(data);
        console.log("GET OK");
        get_usuarios(id_elegido)
          .then((data) => {
            console.log("GET II OK");
            setAdminPermisos(data);
          });
      })
      .catch((err) => {
        alert("");
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
            <th scope="col">Id</th>
            <th scope="col">Usuario</th>
            <th scope="col">Contraseña</th>
            <th scope="col">Fecha de creación</th>
            <th scope="col">Tipo de usuario</th>
          </tr>
        </thead>
        <tbody>
          {datosAdminPermisos ? (
            datosAdminPermisos.map((usuario) => {
              return (
                <tr key={usuario["id"]}>
                  <th scope="row">{usuario["id"]}</th>
                  <td>{usuario["nombre"]}</td>
                  <td>{usuario["password"]}</td>
                  <td>{usuario["fechaCreacion"]}</td>
                  <td>{usuario["tipoUsuario"]}</td>
                  <td>
                    <Link
                      to="/editar-permisos"
                      state={{ rec: usuario, edi: datosAdminPermisos }}
                    >
                      <button type="button" className="btn btn-primary">
                        Editar
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
      <AgregarUsuarioModal refreshData={get_usuarios}></AgregarUsuarioModal>
    </div>
  );
};

export default AdminPermisos;
