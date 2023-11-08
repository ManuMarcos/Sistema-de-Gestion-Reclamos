import React, { useState } from "react";

// TODO: datos del usuario. salen del login supongo...
// los sacaria de un context... por ahora hardcodeo masomenos...
const usuario = {
  id: 2,
};

// TODO: datos del edificio asociado al usuario. salen del login supongo...
// los sacaria de un context... por ahora hardcodeo masomenos...

const datosEdificio = {
  id: 444,
  direccion: "Calle falsa 123",
  areasComunes: [
    {
      id: 24,
      edificioId: 444,
      nombre: "pasillo 1er piso",
    },
    {
      id: 24,
      edificioId: 444,
      nombre: "escaleras 2do piso",
    },
  ],
  unidades: [
    {
      id: 1,
      piso: 2,
      numero: 3,
      edificioId: 444,
      propietarioId: 12,
      estado: "ALQUILADA",
    },
    {
      id: 2,
      piso: 3,
      numero: 2,
      edificioId: 444,
      propietarioId: 71,
      estado: "ALQUILADA",
    },
  ],
};

async function login_sacar_luego() {
  // Default options are marked with *
  const response = await fetch("http://localhost:8080/auth/login", {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    headers: {
      "Content-type": "application/json",
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods' : "POST, GET, PUT",
      'Access-Control-Allow-Headers' : "Content-Type",
    },
    body: JSON.stringify({
      nombre : "ivo",
      password : "ivo"
    }) // body data type must match "Content-Type" header
  });
  return response.json(); // parses JSON response into native JavaScript objects
}

async function post_reclamo_nuevo(token, req_data) {
  // Default options are marked with *
  const response = await fetch("http://localhost:8080/reclamos", {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    headers: {
      "Content-type": "application/json",
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods' : "POST, GET, PUT",
      'Access-Control-Allow-Headers' : "Content-Type",
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify(req_data)
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

  function SubmitEvent(e){
    e.preventDefault()
    console.log(e);

    // TODO: submit images, get Ids...
    let descripcion = e.target.elements[2].value;

    // TODO: fijarse que datos realmente necesito. se estan pidiendo cosas de mas me parece.
    let req_data = {}
    req_data["numero"] = 0;
    req_data["imagenesIds"] = [];
    req_data["descripcion"] = descripcion;
    req_data["motivo"] = "motivo_fruta";
    req_data["estado"] = 0;
    req_data["usuarioId"] = usuario.id; //TODO: del contexto.
    if(chosenRadioOption === RadioOption.AreaComun)
    {
      req_data["unidadId"] = -1; //TODO
      req_data["areaComunId"] = -1; //TODO
    } else {
      req_data["unidadId"] = -1; //TODO
      req_data["areaComunId"] = -1; //TODO
    }
    console.log("Mensajillo saliente:")
    console.log(req_data)

    // TODO: consigo el accessToken depsues ver que hacer...
    login_sacar_luego()
    .then((data) => {
      console.log("token: " + data["accessToken"] )
      post_reclamo_nuevo(data["accessToken"], req_data)
      .then((data) => {console.log(data)})
      .catch((err) => console.log(err))
    })
    .catch((err) => console.log(err))

  }

  function loadFile(e){
    e.preventDefault()
    if(e.target.files.length > 0){
      setImages((images) => [...images, e.target.files[0]])
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
              <label>Direccion: {datosEdificio["direccion"]}</label>
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
              defaultChecked={chosenRadioOption === RadioOption.Unidad }
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
              defaultChecked={chosenRadioOption === RadioOption.AreaComun }
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
              <ul>
                <li>
                  <label>
                    Nombre: {datosEdificio["areasComunes"][0]["nombre"]}
                  </label>
                </li>
              </ul>
            </div>
          ) : (
            <div id="unidadInfo">
              <label>Unidad:</label>
              <ul>
                <li>
                  <label>Piso: {datosEdificio["unidades"][0]["piso"]}</label>
                </li>
                <li>
                  <label>
                    Número: {datosEdificio["unidades"][0]["numero"]}
                  </label>
                </li>
              </ul>
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
          <input className="form-control" type="file" accept="image/*" onChange={loadFile} />
        </div>
        <hr />
        <div className="form-group">
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </div>
      </form>
      <div>
        {/* TODO: agregar button para eliminar foto. */}
        {
          images.map((image) => {
            return <img key={image.name} src={URL.createObjectURL(image)} alt="" width="128px" height="128px" />          
          })
        }
      </div>
    </div>
  );
};

export default NuevoReclamo;
