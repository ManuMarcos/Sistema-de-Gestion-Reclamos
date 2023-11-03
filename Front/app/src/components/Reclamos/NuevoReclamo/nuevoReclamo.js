import React from "react";

// TODO: datos del usuario. salen del login supongo...
// los sacaria de un context... por ahora hardcodeo masomenos...
/*const usuario = {
  id: 12,
};*/

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

const NuevoReclamo = () => {
  return (
    <div className="container">
      <h1>Nuevo Reclamo</h1>
      <form>
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
            />
            <label className="form-check-label" for="flexRadioDefault1">
              Unidad
            </label>
          </div>
          <div className="form-check">
            <input
              className="form-check-input"
              type="radio"
              name="flexRadioDefault"
              id="flexRadioDefault2"
              checked
            />
            <label className="form-check-label" for="flexRadioDefault2">
              Área Común
            </label>
          </div>
        </div>
        <hr />
        <div className="form-group">
          {/* TODO: ver como condicionar esto, y además permitir elegir opciones...*/}
          <div id="areaComunInfo" hidden="true">
            <label>Área común:</label>
            <ul>
              <li>
                <label>
                  Nombre: {datosEdificio["areasComunes"][0]["nombre"]}
                </label>
              </li>
            </ul>
          </div>
          <div id="unidadInfo">
            <label>Unidad:</label>
            <ul>
              <li>
                <label>Piso: {datosEdificio["unidades"][0]["piso"]}</label>
              </li>
              <li>
                <label>Número: {datosEdificio["unidades"][0]["numero"]}</label>
              </li>
            </ul>
          </div>
        </div>
        <hr />
        <div className="form-group">
          <label>Descripción del inconveniente:</label>
          <textarea class="form-control"></textarea>
        </div>
        <hr />
        <div className="form-group">
          <label>Adjuntar imágenes</label>
          <input className="form-control" type="file" />
          <br />
          <div className="row">
            {/* TODO: condicionar...*/}
            <div className="col-md-4">
              <div className="thumbnail">
                <img
                  src="https://qph.cf2.quoracdn.net/main-qimg-9cd107d630080ed15ce5205a07db7d16-pjlq"
                  alt="Lights"
                />
              </div>
            </div>
          </div>
        </div>
        <hr/>
        <div className="form-group">
            <button type="submit" class="btn btn-primary">
              Submit
            </button>
        </div>
      </form>
    </div>
  );
};

export default NuevoReclamo;
