import React from "react";


const Login = () => {
    return (
        <div class ="d-flex justify-content-center">
            <div class="d-grid gap-2 col-6 ">
                <form>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Email</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" />
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" />
                    </div>
                    <div>
                        <a href="/" class="btn btn-primary">Login</a>
                    </div>
                </form>
                <div class="mb-3">
                    <label class="form-check-label" for="exampleCheck1">No tenes cuenta?</label>
                    <a href="/signup" class="btn btn-primary">Registrarse</a>
                </div>                    
            </div>
        </div>
    );
}

export default Login;