import { useEffect, useState } from "react";
import { baseUrl, token } from "../../shared";
import {
  Button,
  Col,
  Container,
  Form,
  Row,
  Stack,
  Table,
} from "react-bootstrap";
import { Link, useParams } from "react-router-dom";
import { AgregarUnidadModal } from "./agregarUnidadModal";
import { AgregarAreaComunModal } from "./agregarAreaComunModal";

export const EdificioDetalle = () => {
  const [isPending, setIsPending] = useState(false);
  const [detalle, setDetalle] = useState(null);
  const { edificioId } = useParams();
  //const [nombrePropietario, setNombrePropietario] = useState();

  const getDetalle = async () => {
    const urlEdificio = baseUrl + "edificios/" + edificioId;
    setIsPending(true);
    try {
      const response = await fetch(urlEdificio, {
        headers: {
          Authorization: "Bearer " + token,
        },
      });
      if (!response.ok) throw new Error(response.statusText);
      const edificio = await response.json();
      setDetalle(edificio);
      setIsPending(false);
      console.log(JSON.stringify(edificio));
    } catch (error) {
      console.log(error);
      setIsPending(false);
    }
  };

  /*
  const setPropietario = (edificio) => {
    if(edificio != null){
      edificio.unidades.forEach(unidad => {
        getNombrePropietario(unidad.propietarioId);
        Object.assign(unidad, {propietario: propietario})
        console.log(unidad);
        setDetalle(edificio);
      })
    }
  }
  */

  /*
  const getNombrePropietario = async (propietarioId) => {
    const urlUsuario = baseUrl + "usuarios/" + propietarioId;
    setIsPending(true);
    try{
      const response = await fetch(urlUsuario,{
        headers: {
            Authorization: "Bearer " + token}
      });
      if (!response.ok) throw new Error(response.statusText);
      const propietario = await response.json();
      setIsPending(false);
      setNombrePropietario(propietario.nombre);
    }catch(error){
      console.log(error);
      setIsPending(false);
    }
  }
  */

  const deleteUnidad = async (unidadId) => {
    const urlDeleteUnidad = baseUrl + "unidad/" + unidadId;
    fetch(urlDeleteUnidad, {
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
      getDetalle();
    });
  };

  const deleteAreaComun = async (areaComunId) => {
    const urlDeleteAreaComun = baseUrl + "areas-comunes/" + areaComunId;
    fetch(urlDeleteAreaComun, {
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
      getDetalle();
    });
  };

  useEffect(() => {
    getDetalle();
  }, []);

  return (
    <Container className="edificio-detalle-container">
      <Col>
        <Row id="flex-row">
          <label>Dirección:</label>{" "}
          {detalle != null && (
            <Form.Control
              disabled
              type="text"
              placeholder="Normal text"
              value={detalle.direccion}
            />
          )}
        </Row>
        <hr></hr>
        <Row>
          <div className="nav-bar">
            <h4 className="titulos-tablas">Unidades</h4>
            <AgregarUnidadModal refreshData={getDetalle}></AgregarUnidadModal>
          </div>
          <Table striped bordered hover variant="dark">
            <thead>
              <tr>
                <th>Piso</th>
                <th>Número</th>
                <th>Propietario</th>
                <th>Estado</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {detalle != null &&
                detalle.unidades.map((unidad) => {
                  return (
                    <tr id={unidad.id}>
                      <td>{unidad.piso}</td>
                      <td>{unidad.numero}</td>
                      <td>{unidad.propietarioId}</td>
                      <td>{unidad.estado}</td>
                      <td width={50}>
                        <div className="link-cell">
                          <Button
                            variant="danger"
                            size="sm"
                            onClick={() => {
                              deleteUnidad(unidad.id);
                            }}
                          >
                            Eliminar
                          </Button>
                          <Link to={"/Edificios/Unidades/" + unidad.id}>
                            <Button className="margin-rigth" size="sm">
                              Editar
                            </Button>
                          </Link>
                        </div>
                      </td>
                    </tr>
                  );
                })}
            </tbody>
          </Table>
          {detalle != null && detalle.unidades.length == 0 && (
            <span>Sin unidades...</span>
          )}
          {isPending && <div>Cargando....</div>}
        </Row>
        <hr></hr>
        <Row>
          <div className="nav-bar">
            <h4 className="titulos-tablas">Areas Comunes</h4>
            <AgregarAreaComunModal
              refreshData={getDetalle}
            ></AgregarAreaComunModal>
          </div>
          <Table striped bordered hover variant="dark">
            <thead>
              <tr>
                <th>Nombre</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {detalle != null &&
                detalle.areasComunes.map((areaComun) => {
                  return (
                    <tr id={areaComun.id}>
                      <td>{areaComun.nombre}</td>
                      <td width={50}>
                        <div className="link-cell">
                          <Button
                            variant="danger"
                            size="sm"
                            onClick={() => {
                              deleteAreaComun(areaComun.id);
                            }}
                          >
                            Eliminar
                          </Button>
                          <Link to={"/Edificios/Detalle/" + areaComun.id}>
                            <Button className="margin-rigth" size="sm">
                              Editar
                            </Button>
                          </Link>
                        </div>
                      </td>
                    </tr>
                  );
                })}
            </tbody>
          </Table>
          {detalle != null && detalle.areasComunes.length == 0 && (
            <span>Sin areas comunes...</span>
          )}
          {isPending && <div>Cargando....</div>}
        </Row>
      </Col>
    </Container>
  );
};
