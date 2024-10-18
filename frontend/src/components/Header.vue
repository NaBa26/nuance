<script setup>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faUser } from '@fortawesome/free-regular-svg-icons';
import { faHeart } from '@fortawesome/free-regular-svg-icons';
import { faShoppingBag } from '@fortawesome/free-solid-svg-icons';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

</script>


<template>
  <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #1B263B;">
    <div class="container-fluid">

      <a class="navbar-brand d-flex align-items-center" href="#">
        <img src="/public/android-chrome-512x512.png" alt="logo" style="width:50px; height:50px;">
        <span class="ms-2" style="color: #F0A500;">nuance</span>
      </a>

      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
        <ul class="navbar-nav" style="font-size: 0.8rem;">
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

          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="genresDropdown" role="button" data-bs-toggle="dropdown"
              aria-expanded="false">
              Genres
            </a>
            <ul class="dropdown-menu" aria-labelledby="genresDropdown">
              <li><a class="dropdown-item" href="#">Historical Fiction</a></li>
              <li><a class="dropdown-item" href="#">Graphic Novels</a></li>
              <li><a class="dropdown-item" href="#">Fantasy</a></li>
              <li><a class="dropdown-item" href="#">Romance</a></li>
              <li><a class="dropdown-item" href="#">Self Help Books</a></li>
              <li><a class="dropdown-item" href="#">Science Fiction</a></li>
              <li><a class="dropdown-item" href="#">Non-Fiction</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><router-link class="dropdown-item" to="/books">More Genres</router-link></li>
            </ul>
          </li>

          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="servicesDropdown" role="button" data-bs-toggle="dropdown"
              aria-expanded="false">
              Services
            </a>
            <ul class="dropdown-menu" aria-labelledby="servicesDropdown">
              <li><a class="dropdown-item" href="#">Book Clubs</a></li>
              <li><a class="dropdown-item" href="#">Events</a></li>
              <li><a class="dropdown-item" href="#">Gift Cards</a></li>
              <li><a class="dropdown-item" href="#">Author Signings</a></li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <li><a class="dropdown-item" href="#">Contact Us</a></li>
            </ul>
          </li>
        </ul>

        <div class="searchBar-container justify-content-center">
          <input placeholder="Search for authors, titles and genres" class="desktop-searchBar" v-model="searchQuery">
          <span @click="searchValue" :disabled="!searchQuery.trim()">
            <FontAwesomeIcon :icon="faSearch" size="lg" style="color: #fff;" />
          </span>
        </div>

        <ul class="navbar-nav ms-auto" style="font-size: 0.8rem;">
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


<script>
import { onMounted } from 'vue';

export default {
  onMounted() {
    const dropdowns = document.querySelectorAll('.nav-item.dropdown');
    dropdowns.forEach((dropdown) => {
      dropdown.addEventListener('mouseover', function () {
        this.querySelector('.dropdown-menu').classList.add('show');
      });
      dropdown.addEventListener('mouseout', function () {
        this.querySelector('.dropdown-menu').classList.remove('show');
      });
    });
  },

  data() {
    return {
      searchQuery: ''
    };
  },

  methods:
  {
    searchValue() {
      alert("xyz")
    }
  },
};
</script>

<style>
.header {
  position: fixed;
  top: 0;
  right: 20px;
  padding: 10px;
  z-index: 100;
}

.navbar-nav .nav-link {
  margin-right: 10px;
}

.navbar-nav .dropdown-menu {
  transition: opacity 0.3s;
}

.navbar-nav .dropdown-menu.show {
  display: block;
  opacity: 1;
}

.searchBar-container {
  display: flex; 
  align-items: center; 
  width: 520px; 
  margin-left: 20px; 
  margin-right: 20px
}

.searchBar-container input {
  width: 460px;
    height: 40px;
    border-radius: 20px 0 0 20px;
    padding: 0 10px;
    border: 1px solid #ccc;
    outline: none;
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
}
</style>
