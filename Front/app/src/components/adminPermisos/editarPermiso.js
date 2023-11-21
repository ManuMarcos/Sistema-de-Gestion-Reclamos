import React, { useState } from "react";
import { Link, useLocation } from "react-router-dom";


async function put_usuario_update(reqId, req_data) {
    const response = await fetch("http://localhost:8080/usuarios/" + reqId, {
        method: "PUT",
        mode: "cors",
        headers: {
            "Content-type": "application/json",
            "Access-Control-Allow-Origin": "http://localhost:3000",
            "Access-Control-Allow-Methods": "POST, GET, PUT",
            "Access-Control-Allow-Headers": "Content-Type",
            Authorization: "Bearer " + sessionStorage.getItem("accessToken"),
            "cache-control": "no-cache",
        },
        body: JSON.stringify(req_data),
    });
    return response.json();
}

const EditarPermisos = () => {
    const isAdmin = sessionStorage.getItem("userType") === "PERSONAL_INTERNO";
    const location = useLocation();
    const { rec, edi } = location.state;

    const reqId = rec["id"];
    let updateReq = {
        id: rec["id"],
        nombre: rec["nombre"],
        pass: rec["password"],
        fechaCreacion: rec["fechaCreacion"],
        tipoUsuario: rec["tipoUsuario"],
    };

    const [updateMessage, setUpdateMessage] = useState("");
    const [redirect, setRedirect] = useState(false);

    async function updateUsuario() {
        try {
            await put_usuario_update(reqId, updateReq);
            setUpdateMessage("Usuario actualizado exitosamente.");
            setTimeout(() => {
                setUpdateMessage("");
                setRedirect(true); // Establecer la redirección después de unos segundos
            }, 2000); // 3000 milisegundos = 3 segundos
        } catch (error) {
            console.error("Error al actualizar el usuario:", error);
        }
    }

    return (
        <div className="container">
            <h1>Detalles del usuario</h1>
            <br />
            <div className="form-group">
                <h4 >Id:</h4>
                <label>{rec["id"]}</label>
            </div>
            <hr />
            <div className="form-group">
                <h4>Nombre:</h4>
                <label>{rec["nombre"]}</label>
            </div>
            <hr />
            <div>
                <h4>Contraseña:</h4>
                <br />
                <label>{rec["password"]}</label>
            </div>
            <div>
                <hr />
                <h4>Fecha de creación:</h4>
                <br />
                <label>{rec["fechaCreacion"]}</label>
            </div>
            <hr />
            <div>
                <h4>Tipo de usuario:</h4>
                <br />
                {isAdmin ? (
                    <select
                        className="form-select form-select-lg mb-3"
                        aria-label="Large select example"
                        defaultValue={updateReq["tipoUsuario"]}
                        onChange={(e) => (updateReq["tipoUsuario"] = e.target.value)}
                    >
                        <option value="PROPIETARIO">PROPIETARIO</option>
                        <option value="INQUILINO">INQUILINO</option>
                        <option value="PERSONAL_INTERNO">PERSONAL_INTERNO</option>
                    </select>
                ) : (
                    <label>{rec["tipoUsuario"]}</label>
                )}
            </div>
            <hr />
            <div className="form-group">
                <button
                    type="button"
                    className="btn btn-primary"
                    onClick={updateUsuario}
                >
                    Actualizar Usuario
                </button>
            </div>
            {updateMessage && (
                <div className="alert alert-success" role="alert">
                    {updateMessage}
                </div>
            )}
        </div>
    );
};

export default EditarPermisos;
