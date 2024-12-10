import axios from 'axios';
import { toast } from 'react-toastify';

const axiosInstance = axios.create({
    baseURL: '/', // Vite proxy handles '/api' prefix
    headers: {
        'Content-Type': 'application/json',
    },
});

// Add a response interceptor
axiosInstance.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response) {
            // Server responded with a status other than 2xx
            switch (error.response.status) {
                case 400:
                    toast.error('Bad Request: Please check your input.');
                    break;
                case 403:
                    toast.error('Forbidden: You do not have permission to perform this action.');
                    break;
                case 404:
                    toast.error('Not Found: The requested resource does not exist.');
                    break;
                case 500:
                    toast.error('Internal Server Error: Please try again later.');
                    break;
                default:
                    toast.error('An unexpected error occurred.');
            }
        } else if (error.request) {
            // No response received from server
            toast.error('No response from server. Please try again later.');
        } else {
            // Error setting up the request
            toast.error(`Error: ${error.message}`);
        }
        return Promise.reject(error);
    }
);

export default axiosInstance;