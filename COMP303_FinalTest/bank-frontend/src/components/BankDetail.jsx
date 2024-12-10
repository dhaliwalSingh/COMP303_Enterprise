import React, { useState, useEffect } from 'react';
import bankService from '../services/bankService';
import { useParams, Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import './BankDetail.css'; // For styling

const BankDetail = () => {
    const { id, name } = useParams();
    const [bank, setBank] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (id) {
            bankService
                .getBankById(id)
                .then((response) => {
                    setBank(response.data);
                    setLoading(false);
                })
                .catch((error) => {
                    console.error('Error fetching bank:', error);
                    toast.error('Failed to fetch bank details.');
                    setError('Failed to fetch bank details.');
                    setLoading(false);
                });
        } else if (name) {
            bankService
                .getBankByName(name)
                .then((response) => {
                    setBank(response.data);
                    setLoading(false);
                })
                .catch((error) => {
                    console.error('Error fetching bank:', error);
                    toast.error('Failed to fetch bank details.');
                    setError('Failed to fetch bank details.');
                    setLoading(false);
                });
        }
    }, [id, name]);

    if (loading) {
        return <div className="loading">Loading bank details...</div>;
    }

    if (error) {
        return (
            <div className="error-message">
                <p>{error}</p>
                <Link to="/">Go back to the list</Link>
            </div>
        );
    }

    if (!bank) {
        return (
            <div className="error-message">
                <p>Bank not found.</p>
                <Link to="/">Go back to the list</Link>
            </div>
        );
    }

    return (
        <div className="detail-container">
            <h2>Bank Details</h2>
            <ul>
                <li><strong>ID:</strong> {bank.bankID}</li>
                <li><strong>Name:</strong> {bank.bankName}</li>
                <li><strong>Year Established:</strong> {bank.bankYear}</li>
                <li><strong>Number of Employees:</strong> {bank.bankEmp}</li>
                <li><strong>Address:</strong> {bank.bankAddress}</li>
                <li><strong>Number of Branches:</strong> {bank.bankBranches}</li>
                <li><strong>Number of ATMs:</strong> {bank.bankATMs}</li>
            </ul>
            <div className="detail-actions">
                <Link to={`/edit/id/${bank.bankID}`} className="action-link">Edit</Link>
                <Link to="/" className="back-link">Back to List</Link>
            </div>
        </div>
    );
};

export default BankDetail;