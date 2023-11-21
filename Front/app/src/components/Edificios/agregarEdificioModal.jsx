import { Modal, Button, Form } from "react-bootstrap";
import { useEffect, useState } from "react";
import { useFetch } from "../../hooks/useFetch";
import { baseUrl, token } from "../../shared";

export const AgregarEdificioModal = ({ refreshData }) => {
  const [show, setShow] = useState(false);
  const [isPending, setIsPending] = useState(false);
  const [direccion, setDireccion] = useState('');

  const [buttonState, setButtonState] = useState({
    buttonColor: "",
    isButtonDisable: false,
  });

  const { buttonColor, isButtonDisable } = buttonState;

  const handleClose = () => {
    setShow(false);
  };

  const handleShow = () => {
    setShow(true);
  };
  
  //useEffect para cambiar el estado y color del boton de Crear (pasado por props)
  useEffect(() => {
    if(direccion == ''){
      setButtonState({
        buttonColor : 'secondary',
        isButtonDisable : true
      })
    }
    else{
      setButtonState({
        buttonColor : 'success',
        isButtonDisable : false
      })
    }
  }, [direccion] )

  const handleSubmit = (direccion) => {
    const edificio = {
      direccion: direccion,
    };

    setIsPending(true);

    console.log(JSON.stringify(edificio));
    fetch(baseUrl + "edificios", {
      method: "POST",
      mode: "cors",
      headers: {
        "Access-Control-Allow-Origin": "htpp://localhost:3000/",
        Authorization: "Bearer " + token,
        "Access-Control-Allow-Methods": "POST, GET, PUT",
        "Access-Control-Allow-Headers": "Content-Type",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(edificio),
    }).then(() => {
      console.log("Nuevo edificio agregado");
      setIsPending(false);
      setDireccion('');
      handleClose();
      refreshData();
    });
  };

  return (
    <div>
      <Button variant="success" size="sm" id="btn-nuevoEdificio" onClick={handleShow}>
        Agregar edificio
      </Button>
      <Modal show={show} onHide={handleClose} animation={false}>
        <Modal.Header closeButton>
          <Modal.Title>Nuevo edificio</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form
            id="agregarEdificioForm"
            onSubmit={(e) => {
              e.preventDefault();
              handleSubmit(direccion);
            }}
          >
            <Form.Group controlId="formBasicEmail">
              <Form.Label>Dirección</Form.Label>
              <Form.Control
                type="text"
                placeholder="Ingrese la dirección"
                name="direccion"
                value={direccion}
                onChange={(event) => setDireccion(event.target.value)}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          {!isPending && (
            <Button
              variant={buttonColor}
              form="agregarEdificioForm"
              disabled={isButtonDisable}
              type="submit" /*onClick={hookParaEjecutarPost}*/
            >
              Crear
            </Button>
          )}
          {isPending && (
            <Button disabled variant="secondary">
              Creando edificio...
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
