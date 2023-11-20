import React, { useState} from "react";
import { useLocation } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";

async function put_usuario_update(reqId, req_data) {
    const response = await fetch("http://localhost:8080/usuarios/" + reqId, {
        method: "PUT",
        mode: "cors",
        headers: {
            "Content-type": "application/json",
            "Access-Control-Allow-Origin": "htpp://localhost:3000/",
            "Access-Control-Allow-Methods": "POST, GET, PUT",
            "Access-Control-Allow-Headers": "Content-Type",
            Authorization: "Bearer " + sessionStorage.getItem("accessToken"),
            "cache-control": "no-cache",
        },
        body: JSON.stringify(req_data),
    });
    return response.json(); // parses JSON response into native JavaScript objects
}

const EditarPermisos = () => {
    const isAdmin = sessionStorage.getItem("userType") === "PERSONAL_INTERNO";
    const location = useLocation();
    const { rec, edi } = location.state;
    //console.log(location);
    // modal
    const [show, setShow] = useState(false);
    const [text, setText] = useState("");
    const cerrarModal = () => setShow(false);
    const mostrarModal = (mensaje) => {
        setShow(true);
        setText(mensaje);
    };

    const reqId = rec["id"];
    let updateReq = {
        id: rec["id"],
        nombre: rec["nombre"],
        pass: rec["password"],
        fechaCreacion: rec["fechaCreacion"],
        tipoUsuario: rec["tipoUsuario"]
    };


      function updateUsuario() {
        // const tipoUsuario=  rec["tipoUsuario"];
        // Promise.all(tipoUsuario)
        //   .then((results) => {
        //     let files_ids = rec["tipoUsuario"];
        //     files_ids = files_ids.concat(results.map((x) => parseInt(x)));
        //     updateReq["tipoUsuario"] = files_ids;
        //     console.log(updateReq);
        //     put_usuario_update(reqId, updateReq)
        //       .then((data) => {
        //         mostrarModal("Usuario actualizado!");
        //       })
        //       .catch((err) => {
        //         mostrarModal("Error al actualizar usuario.");
        //       });
        //   });
      }

    return (
        <div className="container">
            <h1 >Detalles del usuario</h1>
            <br />
            <div>
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
                    {isAdmin ?
                        (<select class="form-select form-select-lg mb-3" aria-label="Large select example"defaultValue={updateReq["tipoUsuario"]} onChange={(e) => updateReq["tipoUsuario"] = e.target.value}>
                            <option value="PROPIETARIO">PROPIETARIO</option>
                            <option value="INQUILINO">INQUILINO</option>
                            <option value="PERSONAL_INTERNO">PERSONAL_INTERNO</option>
                        </select>)
                        :
                        (<label>{rec["tipoUsuario"]}</label>)
                    }
                </div>
                <hr />
                {
                    <div className="form-group">
                        <button
                            type="submit"
                            className="btn btn-primary"
                        //   onClick={updateUsuario}
                        >
                            Actualizar Usuario
                        </button>
                    </div>
                }
            </div>
            <>
                <Modal
                    show={show}
                    onHide={cerrarModal}
                    backdrop="static"
                    keyboard={false}
                >
                    <Modal.Header closeButton>
                        <Modal.Title>Detalle usuario</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>{text}</Modal.Body>
                    <Modal.Footer>
                        <Button variant="primary" onClick={cerrarModal}>
                            OK
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        </div>
    );
};

export default EditarPermisos;
