// Global CSS Imports
import '/public/assets/css/bootstrap.css';
import '/public/assets/css/bootstrap.min.css';
import '/public/assets/css/bootstrap-grid.css';
import '/public/assets/css/bootstrap-grid.min.css';
import '/public/assets/css/bootstrap-grid.rtl.css';
import '/public/assets/css/bootstrap-grid.rtl.min.css';
import '/public/assets/css/bootstrap-icons.min.css';
import '/public/assets/css/custom.css';

//Global JS Imports
import '/public/assets/js/bootstrap.bundle.js';
import '/public/assets/js/bootstrap.bundle.min.js';
import '/public/assets/js/bootstrap.js';
import '/public/assets/js/bootstrap.min.js';
import '/public/assets/js/jquery.min.js'
import '/public/assets/js/popper.min.js';
import '/public/assets/js/slim.min.js';

import { createApp } from 'vue'
import App from './App.vue'
import router from './routes';
import store from './store';
import axios from 'axios';

axios.defaults.withCredentials = true;

const app=createApp(App);
app.use(store);
app.use(router);
app.mount('#app');