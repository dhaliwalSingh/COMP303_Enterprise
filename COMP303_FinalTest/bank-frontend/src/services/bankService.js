import axios from './axiosConfig';

const API_URL = '/api/banks';

// Add a new bank
const addBank = (bank) => {
    return axios.post(API_URL, bank);
};

// Get a bank by ID
const getBankById = (id) => {
    return axios.get(`${API_URL}/id/${id}`);
};

// Get a bank by name
const getBankByName = (name) => {
    return axios.get(`${API_URL}/name/${encodeURIComponent(name)}`);
};

// Get all banks
const getAllBanks = () => {
    return axios.get(API_URL);
};

// Delete a bank by ID
const deleteBankById = (id) => {
    return axios.delete(`${API_URL}/id/${id}`);
};

// Delete a bank by name
const deleteBankByName = (name) => {
    return axios.delete(`${API_URL}/name/${encodeURIComponent(name)}`);
};

// Update a bank by ID
const updateBankById = (id, bank) => {
    return axios.put(`${API_URL}/id/${id}`, bank);
};

// Update a bank by name
const updateBankByName = (name, bank) => {
    return axios.put(`${API_URL}/name/${encodeURIComponent(name)}`, bank);
};

export default {
    addBank,
    getBankById,
    getBankByName,
    getAllBanks,
    deleteBankById,
    deleteBankByName,
    updateBankById,
    updateBankByName,
};