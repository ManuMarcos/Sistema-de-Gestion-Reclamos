import { useEffect, useState } from "react";
import Table from 'react-bootstrap/Table';
import "./styles.css";
import { AgregarEdificioModal } from "./agregarEdificioModal";
import { useFetch } from "../../hooks/useFetch";
import { Button } from "react-bootstrap";
import { baseUrl, token } from "../../shared";
import { Link } from "react-router-dom";


export const ListadoEdificios = () => {
  
  const [edificio, setEdificio] = useState(null);
  const [url, setUrl] = useState(baseUrl + 'edificios');
  const [isPending, setIsPending] = useState(true);
  const [error, setError] = useState(null);
  const [refresh, setRefresh] = useState(0);


  const getEdificios = async () =>{
    setIsPending(true);
    try {
      const response = await fetch(url,{
          headers: {
              Authorization: "Bearer " + token}
      });
      if (!response.ok) throw new Error(response.statusText);
      const json = await response.json();
      setIsPending(false);
      setEdificio(json);
      setError(null);
    }catch(error){
      setError(`${error} Could not Fetch edificio `);
      setIsPending(false);
    }};


  useEffect(() => {
    getEdificios();
  },[]);


  return (
    <div>
      <div className="nav-bar">
        <h2>Edificios</h2>
        <AgregarEdificioModal refreshData={getEdificios}></AgregarEdificioModal>  
      </div>
      <Table bordered hover striped variant="dark">
        <thead>
          <tr>
            <th>Direccion</th>
            <th>Unidades</th>
            <th>Areas Comunes</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {edificio != null && 
              edificio.map((edificio) => {
                return (
                    <tr id={edificio.id}>
                      <th>{edificio.direccion}</th>
                      <td>{edificio.unidades.length}</td>
                      <td>{edificio.areasComunes.length}</td>
                      <td><Link to={'/Edificios/Detalle/' + edificio.id}><Button>Editar</Button></Link></td>
                    </tr>
                );
              })
            }
        </tbody>
      </Table>
      {edificio == null &&
        <h3>Sin datos...</h3>
      }
      {isPending && <div>Loading....</div>}
      {error && <div>{error}</div>}
    </div>  
    );
};
