<template>
  <nav class="navbar navbar-expand-lg navbar-dark" style="background: url('/assets/images/bg_images/pexels-pixabay-326333.jpg');">
    <div class="container-fluid">
      <a class="navbar-brand d-flex align-items-center" href="#">
        <router-link to="/home">
          <img src="/public/android-chrome-512x512.png" alt="logo" class="logo-img" />
        </router-link>
        <span class="ms-2 brand-name">nuance</span>
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
        <ul class="navbar-nav mx-auto" style="font-size: 1rem;">
          <li class="nav-item"><router-link class="nav-link" to="/home"><b>Home</b></router-link></li>
          <li class="nav-item"><a class="nav-link" href="#"><b>Best Sellers</b></a></li>
          <li class="nav-item"><a class="nav-link" href="#"><b>Staff Picks</b></a></li>
          <li class="nav-item"><router-link class="nav-link" to="/books"><b>Books List</b></router-link></li>
          <li class="nav-item"><router-link class="nav-link" to="/books"><b>Events</b></router-link></li>
        </ul>

        <ul class="navbar-nav ms-auto">
          <li class="nav-item text-center me-2">
            <router-link class="nav-link" :to="routeFunction('profile')">
              <FontAwesomeIcon :icon="faUser" size="md" />
              <div>Profile</div>
            </router-link>
          </li>
          <li class="nav-item text-center me-2">
            <router-link class="nav-link" :to="routeFunction('bag')">
              <FontAwesomeIcon :icon="faShoppingBag" size="md" />
              <div>Bag</div>
            </router-link>
          </li>
          <li class="nav-item text-center me-2">
            <router-link class="nav-link" to="/contact_us">
              <FontAwesomeIcon :icon="faPhone" size="md" />
              <div>Contact Us</div>
            </router-link>
          </li>
          <li v-if="session" class="nav-item text-center me-2">
            <button class="nav-link" @click="logOut">
              <FontAwesomeIcon :icon="faSignOut" size="md" />
              <div>Logout</div>
            </button>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faUser, faShoppingBag, faSignOut, faPhone } from '@fortawesome/free-solid-svg-icons';
import { ref, onMounted } from 'vue';
import axios from 'axios';

const session = ref(false);
const errorMessage = ref('');

const checkSession = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/check-session');
    session.value = response.data.active;
  } catch (error) {
    errorMessage.value = 'Failed to check session: ' + error.message;
  }
};

const logOut = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/log-out');
    if (response.status === 200) {
      alert('Logging out...');
      window.location.href = 'http://localhost:5173/home';
    }
  } catch (error) {
    errorMessage.value = error.response ? error.response.data.message : 'An error occurred: ' + error.message;
  }
};

const routeFunction = (route) => session.value ? '/' + encodeURIComponent(route) : '/login';

onMounted(() => {
  checkSession();
});
</script>

<style scoped>
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100px;
  z-index: 1000;
  box-shadow: 0 4px 2px -2px gray;
  background-color: rgba(0, 0, 0, 0.8);
}

.navbar-nav .nav-link {
  margin-right: 15px;
  text-transform: uppercase;
  font-weight: 200;
  font-family: 'Merriweather', serif;
  letter-spacing: 1px;
  color: #fff;
  transition: color 0.3s ease, transform 0.3s ease;
}

.navbar-nav .nav-link:hover {
  color: #F0A500;
  transform: scale(1.1);
}

.navbar-nav .nav-link .fa {
  color: inherit;
  transition: color 0.3s ease;
}

.navbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 1000;
  box-shadow: 0 4px 2px -2px gray;
  background-color: rgba(0, 0, 0, 0.8);
}

.navbar-nav .nav-item .nav-link .fa {
  color: inherit;
  transition: color 0.3s ease;
}

.navbar-nav .dropdown-content {
  display: none;
  position: absolute;
  background-color: #fff;
  min-width: 200px;
  box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
  z-index: 1;
  border-radius: 10px;
  padding: 0;
}

.navbar-nav .dropdown-content a {
  color: #333;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  border-bottom: 1px solid #ddd;
  border-radius: 8px;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.navbar-nav .dropdown-content a:hover {
  background-color: #F0A500;
  color: #fff;
  transform: translateX(5px);
}

.navbar-nav .dropdown:hover .dropdown-content {
  display: block;
}

.brand-name {
  font-size: 2rem;
  font-family: 'Merriweather', serif;
  color: #fff;
}

.logo-img {
  width: 50px;
  height: 50px;
}

@media (max-width: 992px) {
  .searchBar-container {
    display: none;
  }
}

@media (max-width: 576px) {
  .navbar-nav .nav-link {
    font-size: 0.9rem;
  }

  .searchBar-container input {
    width: 70%;
  }
}
</style>
