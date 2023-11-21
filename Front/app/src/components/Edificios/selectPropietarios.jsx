import { useEffect, useState } from "react";
import { baseUrl, token } from "../../shared";
import Select from "react-select";

export const SelectPropietarios = ({ setPropietario }) => {
  
  const [options, setOptions] = useState(null);
  const [isPending, setIsPending] = useState(false);
  const [propietarios, setPropietarios] = useState(null);

  const getPropietarios = async () => {
    const urlPropietariosSinUnidad = `${baseUrl}usuarios/propietariosSinUnidad`;
    setIsPending(true);
    try {
      const response = await fetch(urlPropietariosSinUnidad, {
        headers: {
          Authorization: "Bearer " + token,
        },
      });
      if (!response.ok) throw new Error(response.statusText);
      const propietariosSinUnidad = await response.json();
      setIsPending(false);
      setPropietarios(propietariosSinUnidad);
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
      noOptionsMessage={() => "No hay propietarios"}
      options={options}
      onChange={(option) => setPropietario(option.value)}
    ></Select>
  );
};
