import React from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();

    async function login_func(user, pass) {
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
        login_func(e.target[0].value,  e.target[1].value)
        .then((data) => {
            if(data["userId"] === -1){
                alert("Credenciales inválidas")
                return
            } 
            if(data["userId"] === -2){
                alert("Usuario no habilitado. Contacte al administrador.")
                return
            }             
            if(data["userId"] === -3){
                alert("Usuario con anomalías. Contancte al administrador")
                return
            }
            sessionStorage.setItem("accessToken", data["accessToken"]);
            sessionStorage.setItem("userId", data["userId"]);
            sessionStorage.setItem("userType", data["tipo"]);
            sessionStorage.setItem("edificioId", data["edificioId"]);
            navigate("/", { replace: true });
            navigate(0)
        })
        .catch((err) => alert("Ocurrió un error:" + err))
    }

    return (
        <div class ="d-flex justify-content-center">
            <div className="d-grid gap-2 col-6 ">
                <form onSubmit={SubmitLogin}>
                    <div className="mb-3">
                        <label for="exampleInputEmail1" className="form-label">Usuario</label>
                        <input type="text" className="form-control" id="user" aria-describedby="emailHelp" />
                    </div>
                    <div className="mb-3">
                        <label for="exampleInputPassword1" className="form-label">Contraseña</label>
                        <input type="text" className="form-control" id="pass" />
                    </div>
                    <div>
                    <input type="submit" className="btn btn-primary" value="Login"/>
                    </div>
                </form>
                <div className="mb-3">
                    <label className="form-check-label" for="exampleCheck1">No tenes cuenta?</label>
                    <a href="/signup" className="btn btn-primary">Registrarse</a>
                </div>                    
            </div>
        </div>
    );
}

export default Login;