<script setup>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faUser } from '@fortawesome/free-regular-svg-icons';
import { faHeart } from '@fortawesome/free-regular-svg-icons';
import { faShoppingBag } from '@fortawesome/free-solid-svg-icons';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { faPhone } from '@fortawesome/free-solid-svg-icons';
import { onMounted, ref } from 'vue';
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
    const response = await axios.post('http://localhost:8080/api/logout');
    if (response.status === 200) {
      alert('Logging out...');
      window.location.href = 'http://localhost:5173/home';
    }
  } catch (error) {
    if (error.response) {
      errorMessage.value = error.response.data.message || 'Logout failed';
    } else {
      errorMessage.value = 'An error occurred: ' + error.message;
    }
  }
};

onMounted(() => {
  checkSession();
  console.log(session.value);
});
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-dark" style="background: url('public/assets/images/bg_images/pexels-pixabay-326333.jpg');">
    <div class="container-fluid">
      <!-- Logo and Brand -->
      <a class="navbar-brand d-flex align-items-center" href="#">
        <router-link to="/home">
          <img src="/public/android-chrome-512x512.png" alt="logo" class="logo-img" />
        </router-link>
        <span class="ms-2 brand-name">nuance</span>
      </a>

      <!-- Navbar Toggler for Mobile -->
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- Navbar Links -->
      <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
        <ul class="navbar-nav mx-auto" style="font-size: 1rem;">
          <li class="nav-item">
            <router-link class="nav-link" to="/home">Home</router-link>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">New Arrivals</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Best Sellers</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Staff Picks</a>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/books">Books list</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/books">Events</router-link>
          </li>
        </ul>

        <!-- Profile, Wishlist, and Bag Icons -->
        <ul class="navbar-nav ms-auto">
          <li class="nav-item dropdown text-center me-2">
            <a class="nav-link" role="button" data-bs-toggle="dropdown" aria-expanded="false" href="#">
              <FontAwesomeIcon :icon="faUser" size="md" />
              <div>Profile</div>
            </a>
            <ul class="dropdown-content" aria-labelledby="profileDropdown">
              <li><router-link class="dropdown-item" to="/login">LogIn</router-link></li>
            </ul>
          </li>
          <li class="nav-item text-center me-2">
            <a class="nav-link" href="#">
              <FontAwesomeIcon :icon="faHeart" size="md" />
              <div>Wishlist</div>
            </a>
          </li>
          <li class="nav-item text-center me-2">
            <router-link class="nav-link" to="/bag">
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
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar-nav .nav-item .nav-link {
  color: #fff;
  transition: color 0.3s ease;
}

/* Header stays fixed at the top */
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 60px; /* Ensure a fixed height */
  z-index: 1000;
  box-shadow: 0 4px 2px -2px gray;
  background-color: rgba(0, 0, 0, 0.8); /* Add a background to avoid transparency issues */
}

/* Add padding to the section */
section {
  padding-top: 60px; /* Match the height of the header */
}

.navbar-nav .nav-link {
  margin-right: 15px;
  position: relative;
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

.navbar-nav .nav-item .nav-link .fa {
  color: inherit;
  transition: color 0.3s ease;
}

.dropdown-content {
  list-style: none;
  padding-left: 0;
}

.dropdown-content li {
  list-style: none;
}

.navbar-nav .nav-link:hover {
  color: #F0A500;
  transform: scale(1.1);
}

.navbar-nav .nav-link:hover::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 3px;
  background-color: #F0A500;
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

.dropdown-item {
  padding: 10px;
}

.dropdown-item:hover {
  background-color: transparent;
}

.dropdown-divider {
  margin: 5px 0;
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
