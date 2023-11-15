import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import {Buffer} from 'buffer';

// TODO: cuando se arregle (o entienda como funciona) el POST con lista de imagenes, sacarlo
const ImagenFruta = {
    "id": 1,
    "nombre": "test.jpg",
    "tipo": "image/jpeg",
    "data": "iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAIAAADTED8xAAADMElEQVR4nOzVwQnAIBQFQYXff81RUkQCOyDj1YOPnbXWPmeTRef+/3O/OyBjzh3CD95BfqICMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMO0TAAD//2Anhf4QtqobAAAAAElFTkSuQmCC",
    "reclamo": null
}

const DetalleReclamo = () => {
  const location = useLocation();
  const { rec, edi } = location.state;
  console.log(location)
  const [images, setImages] = useState([]);

  useEffect(() => {
    console.log(rec["imagenesIds"])
    if(rec["imagenesIds"] != null && rec["imagenesIds"].length > 0){
      for(let i=0; i < rec["imagenesIds"].length; i++ ){
        console.log(i)
        //TODO: setImages((images) => [...images, e.target.files[0]])
      }
    } else {
      // TODO: quitar luego, imagen fruta para probar
      const buff = Buffer.from(ImagenFruta["data"], 'base64');
      let f = new File([buff], ImagenFruta["nombre"], {type:ImagenFruta["tipo"]});
      setImages((images) => [...images, f])
    }
  }, [rec]);

  return (
    <div className="container">
      <h1>Detalles del reclamo</h1>
      <div>
        <hr />
        <div className="form-group">
          <label>Edificio:</label>
          <ul>
            <li>
              <label>
                Direccion:
                {edi
                  ? edi["direccion"]
                  : "No se encontraron edificios..."}
              </label>
            </li>
          </ul>
        </div>
        <hr />
        <div>
          <label>Unidad o Área común:</label>
          <br/>
          {(() => {
            if (rec && rec["unidadId"] !== null)
              return <label>Unidad</label>;
            if (rec && rec["areaComunId"] !== null)
              return <label>Área Comun</label>;
            else return <label>No especificado</label>;
          })()}
        </div>
        <hr />
        
        <div>
          <label>Descripción del inconveniente:</label>
          <br/>
          <label>{rec["descripcion"]}</label>
        </div>
        <hr />
        <div>
          <label>Estado del inconveniente:</label>
          <br/>
          <label>{rec["estado"]}</label>
        </div>
        <hr />
        <div>
          <label>Motivo del inconveniente:</label>
          <br/>
          <label>{rec["motivo"]}</label>
        </div>
        <hr />
        <div className="form-group">
          <label>Agregar imágenes</label>
          <input
            className="form-control"
            type="file"
            accept="image/*"
            //onChange={loadFile}
          />
        </div>
        <hr />

          {/* TODO: hacer la parte de edicion, y subir nuevas imagenes */}

        {/*<div className="form-group">
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </div>
        */}
      </div>
      {/* imagenes */}
      <div>
        {images.map((image) => {
          return (
            <img
              key={image.name}
              src={URL.createObjectURL(image)}
              alt=""
              width="128px"
              height="128px"
            />
          );
        })}
      </div>
    </div>
  );
};

export default DetalleReclamo;
