import '/public/assets/css/bootstrap.min.css';
import '/public/assets/css/bootstrap-icons.min.css';
import '/public/assets/css/custom.css';

import '/public/assets/js/bootstrap.bundle.min.js';
import '/public/assets/js/jquery.min.js';

import { createApp } from 'vue';
import App from './App.vue';
import router from './routes';
import axios from 'axios';
import store from './store';
import Swal from 'sweetalert2';
import {clearCookies} from './util.js';

const checkBackendAndSession = async () => {
  try {
    await axios.head('http://localhost:8080');
    console.log('Backend is operational.');

    const response = await axios.get('http://localhost:8080/api/verify-session');
    console.log('Session is valid:', response.data);
  } catch (error) {
    if (!error.response) {
      console.error('Backend is down or unreachable:', error.message);

      Swal.fire({
        icon: 'error',
        title: 'Server Unreachable',
        text: 'The backend server is currently offline. You have been logged out.',
        timer: 5000,
      });
    } else if (error.response.status === 401) {
      console.error('Session expired:', error.response.data);

      Swal.fire({
        icon: 'info',
        title: 'Session Expired',
        text: 'Your session has expired. Please log in again.',
        timer: 3000,
        footer: 'You will be redirected to the login page shortly.',
      });
    } else {
      console.error('An unexpected error occurred:', error.response.data || error.message);

      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Something went wrong. You have been logged out.',
        timer: 5000,
      });
    }
  }
  finally {
    store.dispatch("logout");
    clearCookies();
    window.location.href = '/login';
  }
};

setInterval(checkBackendAndSession, 5 * 60 * 1000);

axios.defaults.withCredentials = true;

const app = createApp(App);
app.use(store);
app.use(router);

app.mount('#app');
