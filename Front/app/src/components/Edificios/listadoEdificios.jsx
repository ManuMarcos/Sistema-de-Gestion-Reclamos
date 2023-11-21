import { useEffect, useState } from "react";
import Table from "react-bootstrap/Table";
import "./styles.css";
import { AgregarEdificioModal } from "./agregarEdificioModal";
import { useFetch } from "../../hooks/useFetch";
import { Button } from "react-bootstrap";
import { baseUrl, token } from "../../shared";
import { Link } from "react-router-dom";

export const ListadoEdificios = () => {
  const [edificio, setEdificio] = useState(null);
  const [url, setUrl] = useState(baseUrl + "edificios");
  const [isPending, setIsPending] = useState(true);

  const getEdificios = async () => {
    setIsPending(true);
    try {
      const response = await fetch(url, {
        headers: {
          Authorization: "Bearer " + token,
        },
      });
      if (!response.ok) throw new Error(response.statusText);
      const json = await response.json();
      setIsPending(false);
      setEdificio(json);
    } catch (error) {
      console.log(error);
      setIsPending(false);
    }
  };

  const deleteEdificio = async (edificioId) => {
    const urlDeleteEdificio = baseUrl + "edificios/" + edificioId;
    fetch(urlDeleteEdificio, {
      method: "DELETE",
      mode: "cors",
      headers: {
        "Access-Control-Allow-Origin": "htpp://localhost:3000/",
        Authorization: "Bearer " + token,
        "Access-Control-Allow-Methods": "POST, GET, PUT, DELETE",
        "Access-Control-Allow-Headers": "Content-Type",
        "Content-Type": "application/json",
      },
    }).then(() => {
      getEdificios();
    });
  };

  useEffect(() => {
    getEdificios();
  }, []);

  return (
    <div>
      <div className="nav-bar">
        <h2>Edificios</h2>
        <AgregarEdificioModal refreshData={getEdificios}></AgregarEdificioModal>
      </div>
      <Table bordered hover striped variant="dark">
        <thead>
          <tr>
            <th>Dirección</th>
            <th>Unidades</th>
            <th>Areas Comunes</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {edificio != null &&
            edificio.map((edificio) => {
              return (
                <tr id={edificio.id}>
                  <td>{edificio.direccion}</td>
                  <td>{edificio.unidades.length}</td>
                  <td>{edificio.areasComunes.length}</td>
                  <td width={300}>
                    <div className="link-cell">
                      <Button
                        variant="danger"
                        size="sm"
                        onClick={() => {
                          deleteEdificio(edificio.id);
                        }}
                      >
                        Eliminar
                      </Button>
                      <Link to={`/Edificios/Detalle/${edificio.id}`}>
                        <Button variant="primary" size="sm" className="margin-rigth">Editar</Button>
                      </Link>
                      <Link to={`/Reclamos/Listado?edificio_id=${edificio.id}`}>
                        <Button variant="success" size="sm" className="margin-rigth">Ver Reclamos</Button>
                      </Link>
                    </div>
                  </td>
                </tr>
              );
            })}
        </tbody>
      </Table>
      {(edificio != null  && edificio.length == 0) && <h5>No hay edificios...</h5>}
      {isPending && <div>Loading....</div>}
    </div>
  );
};
