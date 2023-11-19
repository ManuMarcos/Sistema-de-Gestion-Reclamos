import React from "react";


const Login = () => {
    return (
        <div class ="d-flex justify-content-center">
            <div className="d-grid gap-2 col-6 ">
                <form>
                    <div className="mb-3">
                        <label for="exampleInputEmail1" className="form-label">Email</label>
                        <input type="email" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" />
                    </div>
                    <div className="mb-3">
                        <label for="exampleInputPassword1" className="form-label">Contraseña</label>
                        <input type="password" className="form-control" id="exampleInputPassword1" />
                    </div>
                    <div>
                        <a href="/" className="btn btn-primary">Login</a>
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