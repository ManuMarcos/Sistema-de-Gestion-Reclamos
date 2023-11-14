import React from "react";
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import Home from "../Home/home";
import AboutUs from "../AboutUs/aboutus";
import Error404 from "../Error/error404";
import NavBar from "../Navigation/navbar";
import Login from "../LogIn/login";
import SignUp from "../SignUp/signup";
import AdminPermisos from "../adminPermisos/adminPermisos"
import Landing from "../Landing/landing";


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
                    <Route path="/signup" element={<SignUp />} />
                    <Route path="/landing" element={<Landing />} />

                    {/*Ruta de manejo de error 404 */}
                    <Route path="*" element={<Error404 />} />
                </Routes>
            </div>
        </Router>
    );
}

export default Navigation;