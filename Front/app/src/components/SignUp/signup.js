import React from "react";


const SignUp = () => {
    return (
        <div className="d-flex justify-content-center">
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
                    <div className="mb-3">
                        <select className="form-select" aria-label="Default select example">
                            <option selected>Tipo de usuario</option>
                            <option value="1">Inquilino</option>
                            <option value="2">Administrador</option>
                        </select>
                    </div>
                    <div className="mb-3">
                        <button type="submit" className="btn btn-primary">Sign Up</button>
                    </div>
                </form>
                <div>
                    <label className="form-check-label" for="exampleCheck1">Ya tenes cuenta?</label>
                    <a href="/login" className="btn btn-primary">Iniciar sesion</a>
                </div>
            </div>
        </div>
    );
}

export default SignUp;