import react from 'react';
import axios from 'axios';

const AxiosIntercept = axios.create({
  timeout: 5000,
  headers: {
    "Content-Type": "application/json",
  }
})

 axios.interceptors.request.use(
      config => {
        const token = localStorage.getItem('token');
        if (token) {
          config.headers['Authorization'] = 'Bearer ' + token;
        }
        config.headers['Content-Type'] = 'application/json';
        return config;
      },

      error => {
        console.log(error);
        Promise.reject(error);
  });

export default AxiosIntercept;
