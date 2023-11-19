import { useEffect, useState } from "react";
import { baseUrl, token } from "../../shared";
import { Button, Col, Container, Form, Row, Stack, Table } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";
import { AgregarUnidadModal } from "./agregarUnidadModal";
import { AgregarAreaComunModal } from "./agregarAreaComunModal";


export const EdificioDetalle = () => {

    const [areaComun, setAreaComun] = useState(null);
    const [unidad, setUnidad] = useState(null);
    const [isPending, setIsPending] = useState(false);
    const [error, setError] = useState(null);
    const [detalle, setDetalle] = useState(null);
    const [refresh, setRefresh] = useState(0);

    const {edificioId} = useParams();

    const url = baseUrl + 'edificios/' + edificioId;

    const getDetalle = async () =>{
      setIsPending(true);
      try {
        const response = await fetch(url,{
            headers: {
                Authorization: "Bearer " + token}
        });
        if (!response.ok) throw new Error(response.statusText);
        const json = await response.json();
        setIsPending(false);
        setDetalle(json);
        setError(null);
      }catch(error){
        setError(`${error} Could not Fetch edificio `);
        setIsPending(false);
      }};

      useEffect(() => {
        getDetalle();
      },[]);

  
    return(
        <Container className="edificio-detalle-container">
          <Col>
          <Row id="flex-row">
            <label>Dirección:</label> {detalle != null && <Form.Control disabled type="text" placeholder="Normal text" value={detalle.direccion}/>}
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
                          <td>{unidad.propietario}</td>
                          <td>{unidad.estado}</td>
                          <td><Link className="link-cell" to={'/Edificios/Detalle/' + unidad.id}><Button size="sm">Editar</Button></Link></td>
                        </tr>
                    );
                    })
                  }
                </tbody>
              </Table>
          </Row>
          <hr></hr>
          <Row>
              <div className="nav-bar">
                <h4 className="titulos-tablas">Areas Comunes</h4>
                <AgregarAreaComunModal refreshData={getDetalle}></AgregarAreaComunModal>  
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
                          <tr id={areaComun.id} >
                            <td>{areaComun.nombre}</td>
                            <td><Link className="link-cell" to={'/Edificios/Detalle/' + areaComun.id}><Button size="sm">Editar</Button></Link></td>
                          </tr>
                        );
                      })
                  }
                </tbody>
              </Table>
          </Row>
        </Col>
        </Container>
    )

}