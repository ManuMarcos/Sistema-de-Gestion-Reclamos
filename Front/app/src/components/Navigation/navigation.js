import React from "react";
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import Home from "../Home/home";
import AboutUs from "../AboutUs/aboutus";

function Navigation() {
    return (
        <Router>
            <div>
                <header>
                    <nav>
                        <ul>
                            <li>
                                <Link to="/">Inicio</Link>
                            </li>
                            <li>
                                <Link to="/acerca-de">Acerca de nosotros</Link>
                            </li>
                        </ul>
                    </nav>
                </header>

                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/acerca-de" element={<AboutUs />} />
                </Routes>
            </div>
        </Router>

    );
}

export default Navigation;