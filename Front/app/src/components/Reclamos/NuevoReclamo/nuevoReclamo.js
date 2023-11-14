import React, { useState, useEffect } from "react";
import { useSearchParams } from 'react-router-dom'

async function post_image(file)
{
  const formData = new FormData();
  formData.append('file', file);
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
      'Content-Length': file.length,
    },
    body: formData,
  });
  return response.text(); // parses JSON response into native JavaScript objects
}

async function post_reclamo_nuevo(req_data) {
  // Default options are marked with *
  const response = await fetch("http://localhost:8080/reclamos", {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
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

async function get_edificio(id_edificio) {
  // TODO: el controller no tiene un filtro por id por el momento... lo filtro por front...
  // Default options are marked with *
  const response = await fetch("http://localhost:8080/edificios", {
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

const NuevoReclamo = () => {
  const RadioOption = {
    AreaComun: 0,
    Unidad: 1,
  };
  const [chosenRadioOption, setRadioOption] = useState(RadioOption.AreaComun);
  const [images, setImages] = useState([]);
  const [datosEdificio, setDatosEdificio] = useState([]);
  const [searchParams] = useSearchParams()

  useEffect(() => {
    let id_elegido = parseInt(searchParams.get("edificio_id"))
    get_edificio(id_elegido).then((data) => {
      let edificio = data.find(function (ed) {
        return ed.id === id_elegido;
      });
      setDatosEdificio(edificio);
    });
  }, [searchParams]);

  function SubmitEvent(e) {
    e.preventDefault();

    let files_ids = []
    for(let i=0; i<images.length; ++i)
    {
      post_image(images[i])
      .then((data) => {
        files_ids.push(data)
      })
      .catch((err) => console.log(err))
    }

    console.log(files_ids)

    console.log(e.target.elements)
    let select_id = parseInt(e.target.elements[2].value)
    console.log(select_id)
    if(isNaN(select_id))
    {
      console.log("select_id invalido")
      return;
    }
    let descripcion = e.target.elements[3].value;

    let req_data = {};
    req_data["numero"] = 0;
    req_data["imagenesIds"] = files_ids
    req_data["descripcion"] = descripcion;
    req_data["motivo"] = "motivo_fruta";
    req_data["estado"] = 0;
    req_data["usuarioId"] = sessionStorage.getItem("userId");

    if (chosenRadioOption === RadioOption.AreaComun) {
      req_data["unidadId"] = -1; //TODO
      req_data["areaComunId"] = select_id //TODO
    } else {
      req_data["unidadId"] = select_id //TODO
      req_data["areaComunId"] = -1; //TODO
    }
    console.log(req_data);

    post_reclamo_nuevo(req_data)
      .then((data) => {console.log(data)})
      .catch((err) => console.log(err))
  }

  function loadFile(e) {
    e.preventDefault();
    if (e.target.files.length > 0) {
      setImages((images) => [...images, e.target.files[0]]);
    }
  }

  return (
    <div className="container">
      <h1>Nuevo Reclamo</h1>
      <form onSubmit={SubmitEvent}>
        <hr />
        <div className="form-group">
          <label>Edificio:</label>
          <ul>
            <li>
              <label>
                Direccion:{" "}
                {datosEdificio
                  ? datosEdificio["direccion"]
                  : "No se encontraron edificios..."}
              </label>
            </li>
          </ul>
        </div>
        <hr />
        <div className="form-group">
          <label>Unidad o Área común:</label>
          <div className="form-check">
            <input
              className="form-check-input"
              type="radio"
              name="flexRadioDefault"
              id="flexRadioDefault1"
              defaultChecked={chosenRadioOption === RadioOption.Unidad}
              onClick={() => setRadioOption(RadioOption.Unidad)}
            />
            <label className="form-check-label" htmlFor="flexRadioDefault1">
              Unidad
            </label>
          </div>
          <div className="form-check">
            <input
              className="form-check-input"
              type="radio"
              name="flexRadioDefault"
              id="flexRadioDefault2"
              defaultChecked={chosenRadioOption === RadioOption.AreaComun}
              onClick={() => setRadioOption(RadioOption.AreaComun)}
            />
            <label className="form-check-label" htmlFor="flexRadioDefault2">
              Área Común
            </label>
          </div>
        </div>
        <hr />
        <div className="form-group">
          {chosenRadioOption === RadioOption.AreaComun ? (
            <div id="areaComunInfo">
              <label>Área común:</label>
              {datosEdificio && datosEdificio["areasComunes"] ? (
                <select className="form-select" name="ac" size="3">
                  {datosEdificio["areasComunes"].map((ac) => {
                    return (
                      <option key={ac["id"]} value={ac["id"]}>
                        Id: {ac["id"]} - Nombre: {ac["nombre"]}
                      </option>
                    );
                  })}
                </select>
              ) : (
                <label>No hay Areas en el edificio</label>
              )}
            </div>
          ) : (
            <div id="unidadInfo">
              <label>Unidad:</label>
              {datosEdificio && datosEdificio["unidades"] ? (
                <select className="form-select" name="uni" size="3">
                {datosEdificio["unidades"].map((uni) => {
                  return (
                    <option key={uni["id"]} value={uni["id"]}>
                      Data: {JSON.stringify(uni)}
                    </option>
                  );
                })}
              </select>
              ) : (
                <label>No hay Unidades en el edificio</label>
              )}
            </div>
          )}
        </div>
        <hr />
        <div className="form-group">
          <label>Descripción del inconveniente:</label>
          <textarea className="form-control"></textarea>
        </div>
        <hr />
        <div className="form-group">
          <label>Adjuntar imágenes</label>
          <input
            className="form-control"
            type="file"
            accept="image/*"
            onChange={loadFile}
          />
        </div>
        <hr />
        <div className="form-group">
          {/* TODO: logica para habilitar el boton... */}
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </div>
      </form>
      <div>
        {/* TODO: agregar button para eliminar foto. */}
        {images.map((image) => {
          return (
            <img
              key={image.name}
              src={URL.createObjectURL(image)}
              alt=""
              width="128px"
              height="128px"
            />
          );
        })}
      </div>
    </div>
  );
};

export default NuevoReclamo;
