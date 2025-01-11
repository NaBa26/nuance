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

axios.defaults.withCredentials = true;

function checkSession(store) {
  if (store.getters.isAuthenticated) {
    console.log("User is already authenticated.");
  } else {
    console.log("User is not authenticated.");
  }
}


const app = createApp(App);
app.use(store);
app.use(router);
router.beforeEach((to, from, next) => {
  checkSession(store);
  next();
});
app.mount('#app');
