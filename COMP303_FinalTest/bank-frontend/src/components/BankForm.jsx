import React, { useState, useEffect } from 'react';
import bankService from '../services/bankService';
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import './BankForm.css'; // For styling

const BankForm = () => {
    const [bank, setBank] = useState({
        bankName: '',
        bankYear: '',
        bankEmp: '',
        bankAddress: '',
        bankBranches: '',
        bankATMs: '',
    });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [validationErrors, setValidationErrors] = useState({});

    const navigate = useNavigate();
    const { id, name } = useParams();
    const isEdit = Boolean(id || name);

    useEffect(() => {
        if (isEdit) {
            if (id) {
                setLoading(true);
                bankService
                    .getBankById(id)
                    .then((response) => {
                        setBank(response.data);
                        setLoading(false);
                    })
                    .catch((error) => {
                        console.error('Error fetching bank:', error);
                        toast.error('Failed to fetch bank details.');
                        setLoading(false);
                    });
            } else if (name) {
                setLoading(true);
                bankService
                    .getBankByName(name)
                    .then((response) => {
                        setBank(response.data);
                        setLoading(false);
                    })
                    .catch((error) => {
                        console.error('Error fetching bank:', error);
                        toast.error('Failed to fetch bank details.');
                        setLoading(false);
                    });
            }
        }
    }, [id, name, isEdit]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setBank((prevBank) => ({
            ...prevBank,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setError(null);
        setValidationErrors({});
        setLoading(true);

        // Convert numeric fields
        const bankData = {
            ...bank,
            bankYear: parseInt(bank.bankYear, 10),
            bankEmp: parseInt(bank.bankEmp, 10),
            bankBranches: parseInt(bank.bankBranches, 10),
            bankATMs: parseInt(bank.bankATMs, 10),
        };

        if (isEdit) {
            if (id) {
                bankService
                    .updateBankById(id, bankData)
                    .then(() => {
                        setLoading(false);
                        toast.success('Bank updated successfully!');
                        navigate('/');
                    })
                    .catch((error) => {
                        setLoading(false);
                        console.error('Error updating bank:', error);
                        if (error.response && error.response.data) {
                            setValidationErrors(error.response.data);
                            toast.error('Failed to update bank.');
                        } else {
                            setError('Failed to update bank. Please try again.');
                            toast.error('Failed to update bank. Please try again.');
                        }
                    });
            } else if (name) {
                bankService
                    .updateBankByName(name, bankData)
                    .then(() => {
                        setLoading(false);
                        toast.success('Bank updated successfully!');
                        navigate('/');
                    })
                    .catch((error) => {
                        setLoading(false);
                        console.error('Error updating bank:', error);
                        if (error.response && error.response.data) {
                            setValidationErrors(error.response.data);
                            toast.error('Failed to update bank.');
                        } else {
                            setError('Failed to update bank. Please try again.');
                            toast.error('Failed to update bank. Please try again.');
                        }
                    });
            }
        } else {
            bankService
                .addBank(bankData)
                .then(() => {
                    setLoading(false);
                    toast.success('Bank added successfully!');
                    navigate('/');
                })
                .catch((error) => {
                    setLoading(false);
                    console.error('Error adding bank:', error);
                    if (error.response && error.response.data) {
                        setValidationErrors(error.response.data);
                        toast.error('Failed to add bank.');
                    } else {
                        setError('Failed to add bank. Please try again.');
                        toast.error('Failed to add bank. Please try again.');
                    }
                });
        }
    };

    return (
        <div className="form-container">
            <h2>{isEdit ? 'Edit Bank' : 'Add New Bank'}</h2>
            {error && <div className="error-message">{error}</div>}
            {validationErrors && Object.keys(validationErrors).length > 0 && (
                <div className="error-message">
                    {Object.values(validationErrors).map((err, index) => (
                        <p key={index}>{err}</p>
                    ))}
                </div>
            )}
            {loading ? (
                <div className="loading">Processing...</div>
            ) : (
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Bank Name:</label>
                        <input
                            type="text"
                            name="bankName"
                            value={bank.bankName}
                            onChange={handleChange}
                            required
                            className={validationErrors.bankName ? 'input-error' : ''}
                        />
                        {validationErrors.bankName && (
                            <span className="error-text">{validationErrors.bankName}</span>
                        )}
                    </div>
                    <div className="form-group">
                        <label>Establishment Year:</label>
                        <input
                            type="number"
                            name="bankYear"
                            value={bank.bankYear}
                            onChange={handleChange}
                            required
                            min="1800"
                            max={new Date().getFullYear()}
                            className={validationErrors.bankYear ? 'input-error' : ''}
                        />
                        {validationErrors.bankYear && (
                            <span className="error-text">{validationErrors.bankYear}</span>
                        )}
                    </div>
                    <div className="form-group">
                        <label>Number of Employees:</label>
                        <input
                            type="number"
                            name="bankEmp"
                            value={bank.bankEmp}
                            onChange={handleChange}
                            required
                            min="1"
                            className={validationErrors.bankEmp ? 'input-error' : ''}
                        />
                        {validationErrors.bankEmp && (
                            <span className="error-text">{validationErrors.bankEmp}</span>
                        )}
                    </div>
                    <div className="form-group">
                        <label>Bank Address:</label>
                        <input
                            type="text"
                            name="bankAddress"
                            value={bank.bankAddress}
                            onChange={handleChange}
                            required
                            className={validationErrors.bankAddress ? 'input-error' : ''}
                        />
                        {validationErrors.bankAddress && (
                            <span className="error-text">{validationErrors.bankAddress}</span>
                        )}
                    </div>
                    <div className="form-group">
                        <label>Number of Branches:</label>
                        <input
                            type="number"
                            name="bankBranches"
                            value={bank.bankBranches}
                            onChange={handleChange}
                            required
                            min="1"
                            className={validationErrors.bankBranches ? 'input-error' : ''}
                        />
                        {validationErrors.bankBranches && (
                            <span className="error-text">{validationErrors.bankBranches}</span>
                        )}
                    </div>
                    <div className="form-group">
                        <label>Number of ATMs:</label>
                        <input
                            type="number"
                            name="bankATMs"
                            value={bank.bankATMs}
                            onChange={handleChange}
                            required
                            min="0"
                            className={validationErrors.bankATMs ? 'input-error' : ''}
                        />
                        {validationErrors.bankATMs && (
                            <span className="error-text">{validationErrors.bankATMs}</span>
                        )}
                    </div>
                    <button type="submit" className="submit-button">
                        {isEdit ? 'Update' : 'Add'} Bank
                    </button>
                </form>
            )}
        </div>
    );
};

export default BankForm;