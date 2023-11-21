import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Button, Form, Modal } from "react-bootstrap";
import { baseUrl, token } from "../../../shared";

//Mejora: como hubo bugs decidi hacer un fetch, lo ideal seria recibir el areaComun desde el padre

export const EditarAreaComunModal = ({handleSubmit, areaComunId}) => {
    const [show, setShow] = useState(false);
    const [isPending, setIsPending] = useState(false);
    const [buttonState, setButtonState] = useState({
      buttonColor: "",
      isButtonDisable: false,
    });
    const [areaComun, setAreaComun] = useState({
      nombre: ""
    }
    );
  

    const { buttonColor, isButtonDisable } = buttonState;


    const getAreaComun = async (areaComunId) => {
      const urlGetAreaComun = baseUrl + "areas-comunes/" + areaComunId;
      setIsPending(true);
      try {
        const response = await fetch(urlGetAreaComun, {
          headers: {
            Authorization: "Bearer " + token,
          },
        });
        if (!response.ok) throw new Error(response.statusText);
        const areaComun = await response.json();
        setAreaComun(areaComun);
        setIsPending(false);
      } catch (error) {
        console.log(error);
        setIsPending(false);
      }
    };

    useEffect(()=> {
      getAreaComun(areaComunId);
    }, [areaComunId])
  
    const handleClose = () => {
      setShow(false);
    };
  
    const handleShow = () => {
      setShow(true);
    };
  
    //useEffect para cambiar el estado y color del boton de Crear (pasado por props)
    useEffect(() => {
      if (areaComun.nombre === "" ) {
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


  
  
    return (
      <div>
        <Button  size="sm" variant="primary" className="margin-rigth" onClick={handleShow}>
          Editar
        </Button>
        <Modal show={show} onHide={handleClose} animation={false}>
          <Modal.Header closeButton>
            <Modal.Title>Editar Area Común</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form
              id="agregarAreaComunForm"
              onSubmit={(e) => {
                e.preventDefault();
                handleSubmit({areaComun, setIsPending, handleClose});
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
                type="submit"
              >
                Guardar
              </Button>
            )}
            {isPending && (
              <Button disabled variant="secondary">
                Guardando cambios...
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