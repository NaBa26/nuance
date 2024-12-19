<script setup>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faUser } from '@fortawesome/free-regular-svg-icons';
import { faHeart } from '@fortawesome/free-regular-svg-icons';
import { faShoppingBag } from '@fortawesome/free-solid-svg-icons';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
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
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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

          <!-- Dropdown for Genres -->
          <li class="nav-item dropdown">
            <button class="nav-link dropbtn" id="genresDropdown" role="button" data-bs-toggle="dropdown"
              aria-expanded="false">
              Genres
            </button>
            <ul class="dropdown-content" aria-labelledby="genresDropdown">
              <li><a class="dropdown-item" href="#">Historical Fiction</a></li>
              <li><a class="dropdown-item" href="#">Graphic Novels</a></li>
              <li><a class="dropdown-item" href="#">Fantasy</a></li>
              <li><a class="dropdown-item" href="#">Romance</a></li>
              <li><a class="dropdown-item" href="#">Self Help Books</a></li>
              <li><a class="dropdown-item" href="#">Science Fiction</a></li>
              <li><a class="dropdown-item" href="#">Non-Fiction</a></li>
                <hr class="dropdown-divider">
              <li><router-link class="dropdown-item" to="/books">More Genres</router-link></li>
            </ul>
          </li>

          <!-- Dropdown for Services -->
          <li class="nav-item dropdown">
            <a class="nav-link dropbtn" id="servicesDropdown" role="button" data-bs-toggle="dropdown"
              aria-expanded="false" href="#">
              Services
            </a>
            <ul class="dropdown-content" aria-labelledby="servicesDropdown">
              <li><a class="dropdown-item" href="#">Book Clubs</a></li>
              <li><a class="dropdown-item" href="#">Events</a></li>
              <li><a class="dropdown-item" href="#">Gift Cards</a></li>
              <li><a class="dropdown-item" href="#">Author Signings</a></li>
                <hr class="dropdown-divider">
              <li><router-link class="dropdown-item" to="#">Contact Us</router-link></li>
            </ul>
          </li>
        </ul>

        <!-- Search Bar -->
        <div class="searchBar-container d-none d-lg-flex justify-content-center align-items-center">
          <input placeholder="Search for authors, titles and genres" class="desktop-searchBar" v-model="searchQuery">
          <span @click="searchValue" class="search-icon">
            <FontAwesomeIcon :icon="faSearch" size="lg" style="color: #fff;" />
          </span>
        </div>

        <!-- Profile, Wishlist, and Bag Icons -->
        <ul class="navbar-nav ms-auto">
          <li class="nav-item text-center me-2">
            <router-link class="nav-link" to="/profile">
              <FontAwesomeIcon :icon="faUser" size="md" />
              <div>Profile</div>
            </router-link>
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
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped>
/* Common styles for navbar links */
.navbar-nav .nav-item .nav-link {
  color: #fff; /* Default text color */
  transition: color 0.3s ease; /* Smooth transition for color */
}

.navbar-nav .nav-item .nav-link .fa {
  color: inherit; /* Ensure icons inherit the same color as text */
  transition: color 0.3s ease; /* Smooth transition for icon color */
}

.dropdown-content {
  list-style: none;
  padding-left: 0;
}

.dropdown-content li {
  list-style: none;
}

.navbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 1000; /* Ensures the navbar stays on top of other elements */
  box-shadow: 0 4px 2px -2px gray; /* Optional: Adds a subtle shadow under the navbar */
}

/* Navbar styling */
.navbar-nav .nav-link {
  margin-right: 15px;
  position: relative;
  text-transform: uppercase;
  font-weight: 600;
  font-family: 'Poppins', sans-serif;
  letter-spacing: 1px;
  color: #fff;
  transition: color 0.3s ease, transform 0.3s ease;
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

.searchBar-container {
  display: flex;
  align-items: center;
  width: 100%;
  margin: 0 20px;
}

.searchBar-container input {
  width: 90%;
  height: 40px;
  border-radius: 20px 0 0 20px;
  padding: 0 10px;
  border: 1px solid #ccc;
  outline: none;
  transition: border-color 0.3s ease;
}

.searchBar-container input:focus {
  border-color: #F0A500;
}

.searchBar-container span {
  background-color: #F0A500;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  border-radius: 0 20px 20px 0;
  transition: background-color 0.3s ease;
}

.searchBar-container span:hover {
  background-color: #1B263B;
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
  font-family: 'Poppins', sans-serif;
  color: #fff;
}

.logo-img {
  width: 50px;
  height: 50px;
}

@media (max-width: 992px) {
  .searchBar-container {
    display: none; /* Hide search on smaller screens */
  }
}

@media (max-width: 576px) {
  .navbar-nav .nav-link {
    font-size: 0.9rem; /* Adjust font size for smaller screens */
  }

  .searchBar-container input {
    width: 70%;
  }
}
</style>

