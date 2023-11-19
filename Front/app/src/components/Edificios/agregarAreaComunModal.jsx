import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { baseUrl, token } from "../../shared";
import { Button, Form, Modal } from "react-bootstrap";



export const AgregarAreaComunModal = ({refreshData}) => {
  const [show, setShow] = useState(false);
  const [isPending, setIsPending] = useState(false);
  const { edificioId } = useParams();
  const [buttonState, setButtonState] = useState({
    buttonColor: "",
    isButtonDisable: false,
  });
  const [areaComun, setAreaComun] = useState({
    nombre: ''
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
    setAreaComun({
        nombre: ''
    });
  }

  //useEffect para cambiar el estado y color del boton de Crear (pasado por props)
  useEffect(() => {
    if (areaComun.nombre == "") {
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
  }, [areaComun]);

  const handleSubmit = (areaComun) => {
    setIsPending(true);

    console.log(JSON.stringify(areaComun));
    fetch(baseUrl + "edificios/" + edificioId + "/area-comun", {
      method: "POST",
      mode: "cors",
      headers: {
        "Access-Control-Allow-Origin": "htpp://localhost:3000/",
        Authorization: "Bearer " + token,
        "Access-Control-Allow-Methods": "POST, GET, PUT",
        "Access-Control-Allow-Headers": "Content-Type",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(areaComun),
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
          <Modal.Title>Nueva Area Comun</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form
            id="agregarAreaComunForm"
            onSubmit={(e) => {
              e.preventDefault();
              handleSubmit(areaComun);
            }}
          >
            <Form.Group controlId="formBasicEmail">
              <Form.Label>Nombre</Form.Label>
              <Form.Control
                type="text"
                placeholder="Ingrese el nombre"
                name="nombre"
                value={areaComun.nombre}
                onChange={(event) =>
                  setAreaComun({
                    ...areaComun,
                    nombre: event.target.value,
                  })
                }
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          {!isPending && (
            <Button
              variant={buttonColor}
              form="agregarAreaComunForm"
              disabled={isButtonDisable}
              type="submit" /*onClick={hookParaEjecutarPost}*/
            >
              Crear
            </Button>
          )}
          {isPending && (
            <Button disabled variant="secondary">
              Creando araa comun...
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
