import React from "react";
import { Link } from 'react-router-dom';

const Home = () => {

    async function login_sacar_luego(user, pass) {
        // Default options are marked with *
        const response = await fetch("http://localhost:8080/auth/login", {
          method: "POST", // *GET, POST, PUT, DELETE, etc.
          mode: "cors", // no-cors, *cors, same-origin
          headers: {
            "Content-type": "application/json",
            'Access-Control-Allow-Origin': 'htpp://localhost:3000',
            'Access-Control-Allow-Methods' : "POST, GET, PUT",
            'Access-Control-Allow-Headers' : "Content-Type",
            'cache-control' : 'no-cache'
          },
          body: JSON.stringify({
            nombre : user,
            password : pass
          }) // body data type must match "Content-Type" header
        });
        return response.json(); // parses JSON response into native JavaScript objects
      }

    function SubmitLogin(e){
        e.preventDefault()
        console.log(e);
        login_sacar_luego(e.target[0].value,  e.target[1].value)
        .then((data) => {
            console.log("token: " + data["accessToken"] )
            sessionStorage.setItem("accessToken", data["accessToken"]);
            sessionStorage.setItem("userId", data["userId"]);
        })
        .catch((err) => console.log(err))
    }

    return (
        <div >
            <h1>Inicio</h1>
            <p>Bienvenido a la pagina de inicio</p>
            <div className="d-flex justify-content-center">
                <div className="d-grid gap-2 col-6">
                    <Link to="/Reclamos/Listado?edificio_id=1"><button className="btn btn-primary">Reclamos Listado</button></Link>
                    <Link to="/Reclamos/Nuevo?edificio_id=1"><button className="btn btn-primary" >Reclamos Nuevo</button></Link>
                    <a href="/admin-edificios" className="btn btn-primary" role="button" data-bs-toggle="button">Administrar Edificios</a>
                    <a href="/admin-permisos" className="btn btn-primary" role="button" data-bs-toggle="button">Administrar Permisos</a>
                </div>
            </div>
            {/* TODO: sacar el login truchanga cuando esté el posta */}
            <form onSubmit={SubmitLogin}>
                <label>Login truchanga</label><br/>
                <input type="text" id="user"/><br/>
                <input type="text" id="pass"/><br/>
                <input type="submit" value="Submit"/>
            </form>
        </div>
    );
}

export default Home;