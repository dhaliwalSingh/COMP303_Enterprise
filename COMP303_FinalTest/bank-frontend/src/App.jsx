import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import BankList from './components/BankList';
import BankForm from './components/BankForm';
import BankDetail from './components/BankDetail';
import SearchResults from './components/SearchResults';
import NotFound from './components/NotFound';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './App.css'; // For global styling

const App = () => {
    return (
        <Router>
            <Navbar />
            <ToastContainer />
            <div className="app-container">
                <Routes>
                    <Route path="/" element={<BankList />} />
                    <Route path="/add" element={<BankForm />} />
                    <Route path="/edit/id/:id" element={<BankForm />} />
                    <Route path="/edit/name/:name" element={<BankForm />} />
                    <Route path="/detail/id/:id" element={<BankDetail />} />
                    <Route path="/detail/name/:name" element={<BankDetail />} />
                    <Route path="/search" element={<SearchResults />} />
                    {/* Fallback route */}
                    <Route path="*" element={<NotFound />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;