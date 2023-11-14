import React from "react";


const SignUp = () => {
    return (
        <div class="d-flex justify-content-center">
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
                    <div class="mb-3">
                        <select class="form-select" aria-label="Default select example">
                            <option selected>Tipo de usuario</option>
                            <option value="1">Inquilino</option>
                            <option value="2">Administrador</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <button type="submit" class="btn btn-primary">Sign Up</button>
                    </div>
                </form>
                <div>
                    <label class="form-check-label" for="exampleCheck1">Ya tenes cuenta?</label>
                    <a href="/login" class="btn btn-primary">Iniciar sesion</a>
                </div>
            </div>
        </div>
    );
}

export default SignUp;