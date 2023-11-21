import { useEffect, useState } from "react";
import { baseUrl, token } from "../../shared";
import Select from "react-select";



export const SelectPropietarios = ({setPropietario}) => {
  const urlPropietarios = baseUrl + "usuarios";
  const [options, setOptions] = useState(null);
  const [isPending, setIsPending] = useState(false);
  const [propietarios, setPropietarios] = useState(null);

  const getPropietarios = async () => {
    setIsPending(true);
    try {
      const response = await fetch(urlPropietarios, {
        headers: {
          Authorization: "Bearer " + token,
        },
      });
      if (!response.ok) throw new Error(response.statusText);
      const respuesta = await response.json();
      setIsPending(false);
      const propietarios = await respuesta.filter((user) => user.tipoUsuario == 'PROPIETARIO');
      setPropietarios(propietarios);
    } catch (error) {
      console.log(error);
      setIsPending(false);
    }
  };

  useEffect(() => {
    getPropietarios();
  }, []);

  useEffect(() => {
    convertirPropietarios();
  }, [propietarios]);

  const convertirPropietarios = () => {
    if (propietarios != null) {
      const options = propietarios.map(function (d) {
        return {
          value: d.id,
          label: d.nombre,
        };
      });
      setOptions(options);
    }
  };

  return (
    <Select noOptionsMessage={() => "No hay propietarios"}
      options={options}
      onChange={(option) =>
        setPropietario(option.value)
      }
    ></Select>
  );
};
