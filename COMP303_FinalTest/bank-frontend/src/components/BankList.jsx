import React, { useState, useEffect } from 'react';
import bankService from '../services/bankService';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import './BankList.css'; // For styling

const BankList = () => {
    const [banks, setBanks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [searchTerm, setSearchTerm] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [banksPerPage] = useState(10); // Number of banks per page

    useEffect(() => {
        fetchBanks();
    }, []);

    const fetchBanks = () => {
        setLoading(true);
        bankService
            .getAllBanks()
            .then((response) => {
                setBanks(response.data);
                setLoading(false);
            })
            .catch((error) => {
                console.error('Error fetching banks:', error);
                toast.error('Failed to fetch banks.');
                setLoading(false);
            });
    };

    const handleDelete = (id) => {
        if (window.confirm('Are you sure you want to delete this bank?')) {
            bankService
                .deleteBankById(id)
                .then(() => {
                    toast.success('Bank deleted successfully!');
                    fetchBanks();
                })
                .catch((error) => {
                    console.error('Error deleting bank:', error);
                    toast.error('Failed to delete bank.');
                });
        }
    };

    // Pagination Logic
    const indexOfLastBank = currentPage * banksPerPage;
    const indexOfFirstBank = indexOfLastBank - banksPerPage;
    const currentBanks = banks.slice(indexOfFirstBank, indexOfLastBank);
    const totalPages = Math.ceil(banks.length / banksPerPage);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div className="list-container">
            <h2>All Banks</h2>
            <input
                type="text"
                placeholder="Search by bank name..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="search-input"
            />
            {loading ? (
                <div className="loading">Loading banks...</div>
            ) : (
                <>
                    {currentBanks.length === 0 ? (
                        <p>No banks found. <Link to="/add">Add a new bank</Link>.</p>
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
                            {currentBanks.map((bank) => (
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
                    {/* Pagination Controls */}
                    {totalPages > 1 && (
                        <div className="pagination">
                            {Array.from({ length: totalPages }, (_, index) => (
                                <button
                                    key={index + 1}
                                    onClick={() => paginate(index + 1)}
                                    className={`page-button ${currentPage === index + 1 ? 'active' : ''}`}
                                >
                                    {index + 1}
                                </button>
                            ))}
                        </div>
                    )}
                </>
            )}
        </div>
    );
};

export default BankList;