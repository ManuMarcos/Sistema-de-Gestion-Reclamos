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
        <div>
            <h2>Inicio</h2>
            <p>Bienvenido a la página de inicio</p>
            <form onSubmit={SubmitLogin}>
                <label>Login truchanga</label><br/>
                <input type="text" id="user"/><br/>
                <input type="text" id="pass"/><br/>
                <input type="submit" value="Submit"/>
            </form>
            {/* TODO: hardcodeo el query param edificio_id */}
            <Link to="/Reclamos/Nuevo?edificio_id=1"><button>Reclamos Nuevo</button></Link>
            <Link to="/Reclamos/Listado"><button>Reclamos Listado</button></Link>
            

            <Link to="/Edificios/Listado"><button>Edificios Listado</button></Link>

        </div>
    );
}

export default Home;