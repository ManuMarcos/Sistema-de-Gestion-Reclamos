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
      const json = await response.json();
      setIsPending(false);
      setPropietarios(json);
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
    <Select
      options={options}
      onChange={(option) =>
        setPropietario(option.value)
      }
    ></Select>
  );
};
