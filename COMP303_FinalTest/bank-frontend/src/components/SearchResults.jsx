import React, { useState, useEffect } from 'react';
import { useLocation, Link } from 'react-router-dom';
import bankService from '../services/bankService';
import { toast } from 'react-toastify';
import './SearchResults.css'; // For styling

const useQuery = () => {
    return new URLSearchParams(useLocation().search);
};

const SearchResults = () => {
    const query = useQuery();
    const searchTerm = query.get('query') || '';
    const [banks, setBanks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (searchTerm) {
            setLoading(true);
            // Option 1: Fetch all banks and filter on frontend
            bankService
                .getAllBanks()
                .then((response) => {
                    const filtered = response.data.filter(bank =>
                        bank.bankName.toLowerCase().includes(searchTerm.toLowerCase())
                    );
                    setBanks(filtered);
                    setLoading(false);
                })
                .catch((error) => {
                    console.error('Error fetching banks:', error);
                    toast.error('Failed to fetch banks.');
                    setError('Failed to fetch banks.');
                    setLoading(false);
                });

            // Option 2: If backend supports search endpoint, use it
            // bankService.searchBanks(searchTerm)
            //     .then((response) => {
            //         setBanks(response.data);
            //         setLoading(false);
            //     })
            //     .catch((error) => {
            //         console.error('Error fetching banks:', error);
            //         toast.error('Failed to fetch banks.');
            //         setError('Failed to fetch banks.');
            //         setLoading(false);
            //     });
        } else {
            setBanks([]);
            setLoading(false);
        }
    }, [searchTerm]);

    const handleDelete = (id) => {
        if (window.confirm('Are you sure you want to delete this bank?')) {
            bankService
                .deleteBankById(id)
                .then(() => {
                    toast.success('Bank deleted successfully!');
                    setBanks(banks.filter(bank => bank.bankID !== id));
                })
                .catch((error) => {
                    console.error('Error deleting bank:', error);
                    toast.error('Failed to delete bank.');
                });
        }
    };

    return (
        <div className="search-results-container">
            <h2>Search Results for "{searchTerm}"</h2>
            {loading ? (
                <div className="loading">Loading search results...</div>
            ) : error ? (
                <div className="error-message">{error}</div>
            ) : banks.length === 0 ? (
                <p>No banks found matching your search.</p>
            ) : (
                <table className="bank-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Year Established</th>
                        <th>Employees</th>
                        <th>Address</th>
                        <th>Branches</th>
                        <th>ATMs</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {banks.map((bank) => (
                        <tr key={bank.bankID}>
                            <td>{bank.bankID}</td>
                            <td>{bank.bankName}</td>
                            <td>{bank.bankYear}</td>
                            <td>{bank.bankEmp}</td>
                            <td>{bank.bankAddress}</td>
                            <td>{bank.bankBranches}</td>
                            <td>{bank.bankATMs}</td>
                            <td>
                                <Link to={`/detail/id/${bank.bankID}`} className="action-link">
                                    View
                                </Link>
                                {' | '}
                                <Link to={`/edit/id/${bank.bankID}`} className="action-link">
                                    Edit
                                </Link>
                                {' | '}
                                <button
                                    onClick={() => handleDelete(bank.bankID)}
                                    className="delete-button"
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
            <Link to="/" className="back-link">Back to Home</Link>
        </div>
    );
};

export default SearchResults;