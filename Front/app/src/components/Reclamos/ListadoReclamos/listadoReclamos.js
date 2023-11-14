import React, { useState, useEffect } from "react";
import { Link, useSearchParams } from "react-router-dom";

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

let EdificioFruta = {
  id: 1,
  direccion: "Calle Fruta 700",
  areasComunes: [
    {
      id: 1,
      edificioId: 1,
      nombre: "Pasillito",
    },
    {
      id: 2,
      edificioId: 1,
      nombre: "Pasillito",
    },
  ],
  unidades: [
    {
      id: 1,
      piso: 1,
      numero: 0,
      edificioId: 1,
      propietarioId: null,
      estado: "ALQUILADA",
    },
    {
      id: 2,
      piso: 1,
      numero: 0,
      edificioId: 1,
      propietarioId: null,
      estado: "ALQUILADA",
    },
  ],
};

let ReclamosFruta = [
  {
    id: 1,
    numero: 1,
    imagenesIds: null,
    descripcion: "basura",
    motivo: "motibasura",
    estado: "NUEVO",
    usuarioId: 2,
    unidadId: null,
    areaComunId: null,
  },
  {
    id: 2,
    numero: 2,
    imagenesIds: null,
    descripcion: "basura",
    motivo: "motibasura",
    estado: "NUEVO",
    usuarioId: 2,
    unidadId: null,
    areaComunId: null,
  },
];

const ListadoReclamos = () => {
  const [datosEdificio, setDatosEdificio] = useState([]);
  const [datosListadoReclamos, setDatosListadoReclamos] = useState([]);
  const [searchParams] = useSearchParams();

  useEffect(() => {
    let id_elegido = parseInt(searchParams.get("edificio_id"));
    get_edificio(id_elegido)
      .then((data) => {
        let edificio = data.find(function (ed) {
          return ed.id === id_elegido;
        });
        setDatosEdificio(edificio);
        console.log("GET OK");
        /* TODO: fetch listado reclamos*/
      })
      .catch((err) => {
        console.log(err);
        /* TODO: sacarlo despues. es por paja de levantar spring boot y armar el caso... */
        setDatosEdificio(EdificioFruta);
        setDatosListadoReclamos(ReclamosFruta);
      });
  }, [searchParams]);

  return (
    <div className="container">
      <h1>Listado de Reclamos</h1>
      <div className="form-group">
        <label>Edificio:</label>
        <ul>
          <li>
            <label>
              Direccion:
              {datosEdificio
                ? datosEdificio["direccion"]
                : "No se encontraron edificios..."}
            </label>
          </li>
        </ul>
      </div>
      <hr />
      <table className="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Descripción</th>
            <th scope="col">Motivo</th>
            <th scope="col">Estado</th>
            <th scope="col">Ver Detalles</th>
          </tr>
        </thead>
        <tbody>
          {datosListadoReclamos ? (
            datosListadoReclamos.map((reclamo) => {
              return (
                <tr id={reclamo["numero"]}>
                  <th scope="row">{reclamo["numero"]}</th>
                  <td>{reclamo["descripcion"]}</td>
                  <td>{reclamo["motivo"]}</td>
                  <td>{reclamo["estado"]}</td>
                  <td>
                    <Link to="/Reclamos/Detalle" state={{ rec: reclamo, edi: datosEdificio }}>
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

export default ListadoReclamos;
