import React, { useState } from 'react';
import { NavLink, Link, useNavigate } from 'react-router-dom';
import './Navbar.css'; // For styling

const Navbar = () => {
    const [isMobile, setIsMobile] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');
    const navigate = useNavigate();

    const handleToggle = () => {
        setIsMobile(!isMobile);
    };

    const closeMobileMenu = () => {
        setIsMobile(false);
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault();
        if (searchTerm.trim()) {
            navigate(`/search?query=${encodeURIComponent(searchTerm.trim())}`);
            setSearchTerm('');
            closeMobileMenu();
        }
    };

    return (
        <nav className="navbar" role="navigation" aria-label="main navigation">
            <Link to="/" className="logo" onClick={closeMobileMenu}>
                <h1>Bank Management</h1>
            </Link>
            <form className="search-form" onSubmit={handleSearchSubmit} aria-label="Search Banks">
                <input
                    type="text"
                    placeholder="Search Banks..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    aria-label="Search Banks"
                />
                <button type="submit" aria-label="Search">Search</button>
            </form>
            <button className="hamburger" onClick={handleToggle} aria-label="Toggle navigation">
                {/* Hamburger icon */}
                <span className={isMobile ? "bar change" : "bar"}></span>
                <span className={isMobile ? "bar change" : "bar"}></span>
                <span className={isMobile ? "bar change" : "bar"}></span>
            </button>
            <ul className={isMobile ? "nav-links-mobile" : "nav-links"} onClick={closeMobileMenu}>
                <li>
                    <NavLink exact="true" to="/" className={({ isActive }) => (isActive ? "active" : "")}>
                        Home
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/add" className={({ isActive }) => (isActive ? "active" : "")}>
                        Add Bank
                    </NavLink>
                </li>
            </ul>
        </nav>
    );
};

export default Navbar;