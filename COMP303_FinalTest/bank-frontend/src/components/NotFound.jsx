import React from 'react';
import { Link } from 'react-router-dom';
import './NotFound.css'; // For styling

const NotFound = () => {
    return (
        <div className="notfound-container">
            <h2>404 - Page Not Found</h2>
            <p>The page you're looking for does not exist.</p>
            <Link to="/" className="back-link">Go to Home</Link>
        </div>
    );
};

export default NotFound;