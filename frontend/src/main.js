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

const verifySession = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/verify-session');
    console.log(response.data);
  } catch (error) {
    console.error('Session is invalid or expired:', error.response?.data || error.message);
    this.$store.dispatch('logout');

    Swal.fire({
      icon: 'info',
      title: 'Session Expired',
      timer: 3000,
      text: 'Your session has expired. Please log in again.',
      footer: 'You will be redirected to the login page shortly.'
    });
    this.$router.push('/login');
  }
};

setInterval(verifySession, 5 * 60 * 1000);



axios.defaults.withCredentials = true;

const app = createApp(App);
app.use(store);
app.use(router);

app.mount('#app');
