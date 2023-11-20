import React from "react";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from "../Home/home";
import AboutUs from "../AboutUs/aboutus";
import Error404 from "../Error/error404";
import NavBar from "../Navigation/navbar";
import NuevoReclamo from "../Reclamos/NuevoReclamo/nuevoReclamo";
import ListadoReclamos from "../Reclamos/ListadoReclamos/listadoReclamos";
import { ListadoEdificios } from "../Edificios/listadoEdificios";
import { EdificioDetalle } from "../Edificios/edificioDetalle";
import DetalleReclamo from "../Reclamos/DetalleReclamo/detalleReclamo"
import Login from "../LogIn/login";
import SignUp from "../SignUp/signup";
import AdminPermisos from "../adminPermisos/adminPermisos"
import EditarPermisos from "../adminPermisos/editarPermiso"

import Landing from "../Landing/landing";
import { UnidadDetalle } from "../Edificios/unidadDetalle";


function Navigation() {
    return (
        <Router>
            <div>
                <NavBar/>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/acerca-de" element={<AboutUs />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/admin-permisos" element={<AdminPermisos />} />
                    <Route path="/editar-permisos" element={<EditarPermisos />} />
                    <Route path="/signup" element={<SignUp />} />
                    <Route path="/landing" element={<Landing />} />

                    {/*Ruta de manejo de error 404 */}
                    <Route path="*" element={<Error404 />} />
                    <Route path="/Reclamos/Nuevo" element={<NuevoReclamo />} />
                    <Route path="/Reclamos/Listado" element={<ListadoReclamos />} />
                    <Route path="/Reclamos/Detalle" element={<DetalleReclamo />} />

                    

                    <Route path="/Edificios/Listado" element={<ListadoEdificios />} />
                    <Route path="/Edificios/Detalle/:edificioId" element={<EdificioDetalle />} />
                    <Route path="/Edificios/Unidades/:unidadId" element={<UnidadDetalle />} />
                    
                </Routes>
            </div>
        </Router>
    );
}

export default Navigation;