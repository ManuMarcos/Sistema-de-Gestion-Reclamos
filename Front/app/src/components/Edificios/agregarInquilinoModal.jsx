import { useParams } from "react-router-dom";
import { baseUrl, token } from "../../shared";
import { useEffect, useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import Select from "react-select";

export const AgregarInquilinoModal = ({ unidad, refreshData }) => {
  const [show, setShow] = useState(false);
  const [options, setOptions] = useState(null);
  const [isPending, setIsPending] = useState(false);
  const [inquilinos, setInquilinos] = useState(null);
  const [inquilino, setInquilino] = useState(null);
  const [buttonState, setButtonState] = useState({
    buttonColor: "",
    isButtonDisable: false,
  });
  const { buttonColor, isButtonDisable } = buttonState;

  const getInquilinos = async () => {
    const urlGetInquilinos = baseUrl + "usuarios";
    setIsPending(true);
    try {
      const response = await fetch(urlGetInquilinos, {
        headers: {
          Authorization: "Bearer " + token,
        },
      });
      if (!response.ok) throw new Error(response.statusText);
      const respuesta = await response.json();
      setIsPending(false);
      const usuariosFiltrados = await respuesta.filter((user) => user.tipoUsuario == 'INQUILINO');
      setInquilinos(usuariosFiltrados);
    } catch (error) {
      console.log(error);
      setIsPending(false);
    }
  };

  const handleSubmit = (inquilino) => {
    const urlPostInquilino =
      baseUrl +
      `edificios/${unidad.edificioId}/unidad/${unidad.id}/inquilino/${inquilino}`;
    setIsPending(true);
    //console.log("Enviando Post: " + JSON.stringify({...unidad,propietarioId: propietario}));
    fetch(urlPostInquilino, {
      method: "POST",
      mode: "cors",
      headers: {
        "Access-Control-Allow-Origin": "htpp://localhost:3000/",
        Authorization: "Bearer " + token,
        "Access-Control-Allow-Methods": "POST, GET, PUT",
        "Access-Control-Allow-Headers": "Content-Type",
        "Content-Type": "application/json",
      },
    }).then(() => {
      setIsPending(false);
      handleClose();
      refreshData();
    });
  };

  const handleClose = () => {
    setShow(false);
    limpiarInputs();
  };

  const handleShow = () => {
    setShow(true);
  };

  const limpiarInputs = () => {
    setInquilino(null);
  };

  //useEffect para cambiar el estado y color del boton de Crear (pasado por props)
  useEffect(() => {
    if (inquilino == null) {
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
  }, [inquilino]);

  useEffect(() => {
    getInquilinos();
  }, []);

  useEffect(() => {
    convertirInquilinos();
  }, [inquilinos]);

  const convertirInquilinos = () => {
    if (inquilinos != null) {
        //setInquilinos(inquilinos.filter((inquilino) => inquilino.tipoUsuario = 'INQUILINO'));
      const options = inquilinos.map(function (d) {
        return {
          value: d.id,
          label: d.nombre,
        };
      });
      setOptions(options);
    }
  };

  return (
    <div>
      <Button variant="primary" id="btn-nuevoEdificio" onClick={handleShow}>
        Agregar Inquilino
      </Button>
      <Modal show={show} onHide={handleClose} animation={false}>
        <Modal.Header closeButton>
          <Modal.Title>Agregar Inquilino</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form
            id="agregarInquilinoForm"
            onSubmit={(e) => {
              e.preventDefault();
              handleSubmit(inquilino);
            }}
          >
            <Select
              options={options}
              onChange={(option) => setInquilino(option.value)}
            ></Select>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          {!isPending && (
            <Button
              variant={buttonColor}
              form="agregarInquilinoForm"
              disabled={isButtonDisable}
              type="submit"
            >
              Agregar
            </Button>
          )}
          {isPending && (
            <Button disabled variant="secondary">
              Agregando inquilino...
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
