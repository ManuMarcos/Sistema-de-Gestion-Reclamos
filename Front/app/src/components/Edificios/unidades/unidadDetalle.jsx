import { useParams } from "react-router";
import { useEffect, useState } from "react";
import {
  Button,
  Col,
  Container,
  Form,
  InputGroup,
  Row,
  Table,
} from "react-bootstrap";
import { AgregarInquilinoModal } from "../agregarInquilinoModal";
import { baseUrl, token } from "../../../shared";

export const UnidadDetalle = () => {
  const { unidadId } = useParams();
  const [isPending, setIsPending] = useState(false);
  const [unidad, setUnidad] = useState(null);
  const [inquilinos, setInquilinos] = useState(null);
  const [isUnidadCargada, setUnidadCargada] = useState(false);
  const [editado, setEditado] = useState(false);

  const getUnidad = async () => {
    const url = baseUrl + "unidad/" + unidadId;
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
      setUnidad(json);
      setUnidadCargada(true);
    } catch (error) {
      console.log(error);
      setIsPending(false);
    }
  };

  const getInquilinos = async () => {
    if (unidad != null) {
      const urlGetInquilinos = `${baseUrl}edificios/${unidad.edificioId}/inquilinos/${unidad.id}`;
      setIsPending(true);
      try {
        const response = await fetch(urlGetInquilinos, {
          headers: {
            Authorization: "Bearer " + token,
          },
        });
        if (!response.ok) throw new Error(response.statusText);
        const inquilinos = await response.json();
        setIsPending(false);
        setInquilinos(inquilinos);
      } catch (error) {
        console.log(error);
        setIsPending(false);
      }
    }
  };

  const getDatosPropietario = async () => {
    if (unidad != null) {
      const urlGetUsuarioById = `${baseUrl}usuarios/${unidad.propietarioId}`;
      setIsPending(true);
      try {
        const response = await fetch(urlGetUsuarioById, {
          headers: {
            Authorization: "Bearer " + token,
          },
        });
        if (!response.ok) throw new Error(response.statusText);
        const propietario = await response.json();
        setIsPending(false);
        setUnidad({ ...unidad, propietario: propietario.nombre });
      } catch (error) {
        console.log(error);
        setIsPending(false);
      }
    }
  };

  const quitarInquilino = async (inquilinoId) => {
    const quitarInquilinoUnidad = `${baseUrl}edificios/${unidad.edificioId}/unidad/${unidad.id}/inquilino/${inquilinoId}`;
    fetch(quitarInquilinoUnidad, {
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
      getInquilinos();
    });
  };

  useEffect(() => {
    getUnidad();
    console.log("useEffect1");
  }, [inquilinos]);

  useEffect(() => {
    getInquilinos();
    getDatosPropietario();
    console.log("useEffect2");
  }, [isUnidadCargada]);

  return (
    <Container className="unidad-detalles">
      <Row>
        <Col>
          <InputGroup className="mb-3">
            <InputGroup.Text data-bs-theme="dark" id="basic-addon1">
              Piso:{" "}
            </InputGroup.Text>
            <Form.Control
              aria-label="pisio"
              aria-describedby="basic-addon1"
              value={unidad != null && unidad.piso}
            />
          </InputGroup>
          <InputGroup className="mb-3">
            <InputGroup.Text data-bs-theme="dark" id="basic-addon1">
              Número:{" "}
            </InputGroup.Text>
            <Form.Control
              aria-label="Username"
              aria-describedby="basic-addon1"
              value={unidad != null && unidad.numero}
            />
          </InputGroup>
          <InputGroup className="mb-3">
            <InputGroup.Text data-bs-theme="dark" id="basic-addon1">
              Estado:{" "}
            </InputGroup.Text>
            <Form.Control value={unidad != null && unidad.estado} />
          </InputGroup>
        </Col>
        <Col>
          <InputGroup className="mb-3">
            <InputGroup.Text data-bs-theme="dark" id="propietario">
              Propietario:
            </InputGroup.Text>
            <Form.Control
              aria-describedby="propietario"
              value={unidad != null && unidad.propietario}
            />
          </InputGroup>
        </Col>
      </Row>
      {editado == true && <Button variant="success">Guardar</Button>}
      <hr></hr>
      <Row>
        <div className="nav-bar">
          <h4 className="titulos-tablas">Inquilinos</h4>
          <AgregarInquilinoModal unidad={unidad} refreshData={getInquilinos} />
        </div>
        <Table variant="dark" striped bordered hover>
          <thead>
            <tr>
              <th>Nombre</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {inquilinos != null &&
              inquilinos.map((inquilino) => {
                return (
                  <tr id={inquilino.id}>
                    <td>{inquilino.nombre}</td>
                    <td width={80}>
                      <Button
                        variant="danger"
                        size="sm"
                        onClick={() => {
                          quitarInquilino(inquilino.id);
                        }}
                      >
                        Eliminar
                      </Button>
                    </td>
                  </tr>
                );
              })}
          </tbody>
        </Table>
        {inquilinos == null && <span>Sin datos...</span>}
        {isPending && <div>Loading....</div>}
      </Row>
    </Container>
  );
};
