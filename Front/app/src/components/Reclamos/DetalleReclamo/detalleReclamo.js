import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { Buffer } from "buffer";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";

// TODO: fetch utils...
async function post_image(file) {
  const formData = new FormData();
  formData.append("file", file);
  // Default options are marked with *
  const response = await fetch("http://localhost:8080/imagen", {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    headers: {
      //"Content-type": "multipart/form-data",
      "Access-Control-Allow-Origin": "htpp://localhost:3000/",
      "Access-Control-Allow-Methods": "POST, GET, PUT",
      "Access-Control-Allow-Headers": "Content-Type",
      Authorization: "Bearer " + sessionStorage.getItem("accessToken"),
      "cache-control": "no-cache",
      "Content-Length": file.length,
    },
    body: formData,
  });
  return response.text(); // parses JSON response into native JavaScript objects
}

async function get_imagen(id_imagen) {
  const response = await fetch("http://localhost:8080/imagen/" + id_imagen, {
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
  });
  return response.json(); // parses JSON response into native JavaScript objects
}

async function put_reclamo_update(reqId, req_data) {
  const response = await fetch("http://localhost:8080/reclamos/" + reqId, {
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

const DetalleReclamo = () => {
  const isAdmin = sessionStorage.getItem("userType") === "PERSONAL_INTERNO";
  const location = useLocation();
  const { rec, edi } = location.state;
  if (rec["imagenesIds"] === null) rec["imagenesIds"] = [];
  //console.log(location);
  const [images, setImages] = useState([]);

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
    imagenesIds: rec["imagenesIds"],
    descripcion: rec["descripcion"],
    motivo: rec["motivo"],
    estado: rec["estado"],
  };

  useEffect(() => {
    //console.log(rec["imagenesIds"]);
    if (rec["imagenesIds"].length > 0) {
      for (let i = 0; i < rec["imagenesIds"].length; i++) {
        //console.log(i);
        get_imagen(rec["imagenesIds"][i])
          .then((data) => {
            const buff = Buffer.from(data["data"], "base64");
            let f = new File([buff], data["nombre"], { type: data["tipo"] });
            setImages((images) => [...images, f]);
          })
          .catch((err) => {
            mostrarModal("Ocurrió un error obteniendo las imagenes.");
          });
      }
    }
  }, [rec]);

  function loadFile(e) {
    e.preventDefault();
    if (e.target.files.length > 0) {
      setImages((images) => [...images, e.target.files[0]]);
    }
  }

  function removeImage(index){
    let ig = [...images]
    ig.splice(index, 1);
    setImages(ig);
  }

  function updateReclamo() {
    const img_promises = [];
    for (let i = rec["imagenesIds"].length; i < images.length; ++i) {
      img_promises.push(post_image(images[i]));
    }
    Promise.all(img_promises)
      .then((results) => {
        let files_ids = rec["imagenesIds"];
        files_ids = files_ids.concat(results.map((x) => parseInt(x)));
        updateReq["imagenesIds"] = files_ids;
        console.log(updateReq);
        put_reclamo_update(reqId, updateReq)
          .then((data) => {
            mostrarModal("Reclamo actualizado!");
          })
          .catch((err) => {
            mostrarModal("Error al actualizar el reclamo.");
          });
      })
      .catch((err) => {
        mostrarModal("Error al cargar imagenes del reclamo.");
      });
  }

  return (
    <div className="container">
      <h1>Detalles del reclamo</h1>
      <div>
        <hr />
        <div className="form-group">
          <label>Edificio:</label>
          <ul>
            <li>
              <label>
                Direccion:
                {edi ? edi["direccion"] : "No se encontraron edificios..."}
              </label>
            </li>
          </ul>
        </div>
        <hr />
        <div>
          <label>Unidad o Área común:</label>
          <br />
          {(() => {
            if (rec && rec["unidadId"] !== null) return <label>Unidad</label>;
            if (rec && rec["areaComunId"] !== null)
              return <label>Área Comun</label>;
            else return <label>No especificado</label>;
          })()}
        </div>
        <hr />

        <div>
          <label>Descripción del inconveniente:</label>
          <br />
          { isAdmin ?
          (<input defaultValue={updateReq["descripcion"]} onChange={(e) => updateReq["descripcion"] = e.target.value}></input>)
          :
          (<label>{rec["descripcion"]}</label>)
          }
        </div>
        <hr />
        <div>
          <label>Estado del inconveniente:</label>
          <br />
          { isAdmin ?
          (<select  name="ac" size="6" defaultValue={updateReq["estado"]} onChange={(e) => updateReq["estado"] = e.target.value}>
            <option value="NUEVO">NUEVO</option>
            <option value="ABIERTO">ABIERTO</option>
            <option value="EN_PROCESO">EN_PROCESO</option>
            <option value="DESESTIMADO">DESESTIMADO</option>
            <option value="ANULADO">ANULADO</option>
            <option value="TERMINADO">TERMINADO</option>
          </select>)
          :
          (<label>{rec["estado"]}</label>)
          }
        </div>
        <hr />
        <div>
          <label>Motivo del inconveniente:</label>
          <br />
          { isAdmin ?
          (<input defaultValue={updateReq["motivo"]} onChange={(e) => updateReq["motivo"] = e.target.value}></input>)
          :
          (<label>{rec["motivo"]}</label>)
          }
        </div>
        <hr />
        <div className="form-group">
          <label>Agregar imágenes</label>
          <input
            className="form-control"
            type="file"
            accept="image/*"
            onChange={loadFile}
          />
        </div>
        <hr />

        {
          <div className="form-group">
            <button
              type="submit"
              className="btn btn-primary"
              onClick={updateReclamo}
            >
              Actualizar Reclamo
            </button>
          </div>
        }
      </div>
      {/* imagenes */}
      <div>
        <hr />
        <h2>Imágenes</h2>
        {images.map((image, index) => {
          return (
            <span key={index} style={{ position: "relative" }}>
              <img
                src={URL.createObjectURL(image)}
                alt=""
                width="128px"
                height="128px"
                style={{ position: "relative" }}
              />
              {index >= rec["imagenesIds"].length ? (
                <button
                  type="button"
                  name="button"
                  style={{ position: "absolute", right: "0px" }}
                  onClick={() => {removeImage(index)}}
                >
                  X
                </button>
              ) : (
                <></>
              )}
            </span>
          );
        })}
        <hr />
      </div>
      <>
        <Modal
          show={show}
          onHide={cerrarModal}
          backdrop="static"
          keyboard={false}
        >
          <Modal.Header closeButton>
            <Modal.Title>Detalle reclamo</Modal.Title>
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

export default DetalleReclamo;
