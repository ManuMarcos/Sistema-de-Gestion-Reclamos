import { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { useFetch } from "../../hooks/useFetch";
import { baseUrl, token } from "../../shared";
import { useParams } from "react-router-dom";
import { SelectPropietarios } from "./selectPropietarios";

export const AgregarUnidadModal = ({ refreshData }) => {
  const [show, setShow] = useState(false);
  const [isPending, setIsPending] = useState(false);
  const { edificioId } = useParams();
  const [buttonState, setButtonState] = useState({
    buttonColor: "",
    isButtonDisable: false,
  });
  const [propietario, setPropietario] = useState(null);
  const [unidad, setUnidad] = useState({
    piso: "",
    numero: ""
  });
  


  const { buttonColor, isButtonDisable } = buttonState;

  const handleClose = () => {
    setShow(false);
    limpiarInputs();
  };

  const handleShow = () => {
    setShow(true);
  };

  const limpiarInputs = () => {
    setUnidad({
      piso: "",
      numero: "",
      estado: ""
    });
    setPropietario(null);
  };

  

  //useEffect para cambiar el estado y color del boton de Crear (pasado por props)
  useEffect(() => {
    if (unidad.piso == "" || unidad.numero == "" || unidad.estado == "" || propietario == null) {
      setButtonState({
        buttonColor: "secondary",
        isButtonDisable: true,
      });
    } else {
      setButtonState({
        buttonColor: "success",
        isButtonDisable: false,
      });
    }
  }, [unidad, propietario]);

  const handleSubmit = () => {
    setIsPending(true);

    console.log("Enviando Post: " + JSON.stringify({...unidad,propietarioId: propietario}));
    fetch(baseUrl + "edificios/" + edificioId + "/unidad", {
      method: "POST",
      mode: "cors",
      headers: {
        "Access-Control-Allow-Origin": "htpp://localhost:3000/",
        Authorization: "Bearer " + token,
        "Access-Control-Allow-Methods": "POST, GET, PUT",
        "Access-Control-Allow-Headers": "Content-Type",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({...unidad,propietarioId: propietario}),
    }).then(() => {
      setIsPending(false);
      handleClose();
      refreshData();
    });
  };

  

  return (
    <div>
      <Button variant="success" id="btn-nuevoEdificio" onClick={handleShow}>
        Agregar
      </Button>
      <Modal show={show} onHide={handleClose} animation={false}>
        <Modal.Header closeButton>
          <Modal.Title>Nueva unidad</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form
            id="agregarUnidadForm"
            onSubmit={(e) => {
              e.preventDefault();
              handleSubmit(unidad);
            }}
          >
            <Form.Group controlId="formBasicEmail">
              <Form.Label>Piso</Form.Label>
              <Form.Control
                type="number"
                placeholder="Ingrese el piso"
                name="piso"
                value={unidad.piso}
                onChange={(event) =>
                  setUnidad({
                    ...unidad,
                    piso: event.target.value,
                  })
                }
              />
              <Form.Label>Numero</Form.Label>
              <Form.Control
                type="number"
                placeholder="Ingrese el numero"
                name="numero"
                value={unidad.numero}
                onChange={(event) =>
                  setUnidad({
                    ...unidad,
                    numero: event.target.value,
                  })
                }
              />
              <Form.Label>Estado</Form.Label>
              <Form.Select onChange={(event) => 
                setUnidad({
                    ...unidad,
                    estado: event.target.value
                })
                } aria-label="Default select example">
                <option value=''>Seleccione el estado</option>
                <option value="ALQUILADA">Alquilada</option>
                <option value="SIN_ALQUILAR">Sin alquilar</option>
              </Form.Select>
              <Form.Label>Propietario</Form.Label>
              <SelectPropietarios setPropietario={setPropietario}/>
            </Form.Group>   
          </Form>
        </Modal.Body>
        <Modal.Footer>
          {!isPending && (
            <Button
              variant={buttonColor}
              form="agregarUnidadForm"
              disabled={isButtonDisable}
              type="submit" /*onClick={hookParaEjecutarPost}*/
            >
              Crear
            </Button>
          )}
          {isPending && (
            <Button disabled variant="secondary">
              Creando unidad...
            </Button>
          )}
          <Button variant="danger" onClick={handleClose}>
            Cancelar
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};
