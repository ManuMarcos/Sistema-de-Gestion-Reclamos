import { useParams } from "react-router";
import { baseUrl, token } from "../../shared";
import { useEffect, useState } from "react";
import { Col, Container, Form, InputGroup, Row, Table } from "react-bootstrap";

export const UnidadDetalle = () => {
  const { unidadId } = useParams();
  const [isPending, setIsPending] = useState(false);
  const [unidad, setUnidad] = useState(null);
  const [selectedOption, setSelectedOption] = useState('none');
  
  const url = baseUrl + "unidad/" + unidadId;




  const getUnidad = async () => {
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
      console.log(JSON.stringify(unidad));
    } catch (error) {
      console.log(error);
      setIsPending(false);
    }
  };

  useEffect(() => {
    getUnidad();
  }, []);

  return (
    <Container className="unidad-detalles">
      <Row>
        <Col>
          <InputGroup className="mb-3">
            <InputGroup.Text id="basic-addon1">Piso: </InputGroup.Text>
            <Form.Control
              aria-label="pisio"
              aria-describedby="basic-addon1"
              value={unidad != null && unidad.piso}
            />
          </InputGroup>
          <InputGroup className="mb-3">
            <InputGroup.Text id="basic-addon1">Número: </InputGroup.Text>
            <Form.Control
              aria-label="Username"
              aria-describedby="basic-addon1"
              value={unidad != null && unidad.numero}
            />
          </InputGroup>
          <InputGroup className="mb-3">
            <InputGroup.Text id="basic-addon1">Estado: </InputGroup.Text>
            <Form.Select value={unidad!= null && unidad.estado} onChange={(event) => 
                setUnidad({
                    ...unidad,
                    estado: event.target.value
                })}
                aria-label="Default select example">
                <option value="ALQUILADA">Alquilada</option>
                <option value="SIN_ALQUILAR">Sin alquilar</option>
            </Form.Select>
            
          </InputGroup>
        </Col>
        <Col>
          <InputGroup className="mb-3">
            <InputGroup.Text id="basic-addon1">Propietario: </InputGroup.Text>
            <Form.Control
              aria-label="Username"
              aria-describedby="basic-addon1"
              value={unidad!=null && unidad.propietario}
            />
          </InputGroup>
        </Col>
      </Row>
      <hr></hr>
      <Row>
        <h4>Inquilinos</h4>
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>#</th>
              <th>Nombre</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td width={100}>1</td>
              <td>Mark</td>
            </tr>
          </tbody>
        </Table>
      </Row>
    </Container>
  );
};
