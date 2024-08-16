<script setup>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faUser } from '@fortawesome/free-regular-svg-icons';
import { faHeart } from '@fortawesome/free-regular-svg-icons';
import { faShoppingBag } from '@fortawesome/free-solid-svg-icons';
</script>

<template>
  <nav class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar" style="background-color: #1B263B">
      <div class="row gy-4 justify-content-center">
        <a class="navbar-brand" href="#">
          <img src="/public/android-chrome-512x512.png" style="width:50px; height:50px"; alt="logo">
          <span style="color: #F0A500;">nuance</span>
        </a>
      </div>
      <div class="row gy-4 justify-content-center">
        <div class="navbar-nav-scroll" id="navbarSupportedContent">
          <ul class="navbar-nav bd-navbar-nav flex-row">
            <li class="nav-item">
              <a class="nav-link" href="#">Home</a>
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
              <a class="nav-link" href="#">Upcoming Releases</a>
            </li>

            <!-- Dropdown for Genres -->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="genresDropdown" role="button">
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
                <li><a class="dropdown-item" href="#">More Genres</a></li>
              </ul>
            </li>

            <!-- Dropdown for Services -->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="servicesDropdown" role="button">
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
        </div>

        <!-- <form class="d-flex ms-2 my-2 my-lg-0">
          <input class="form-control me-2" type="search" placeholder="Search by name, author or genre"
            aria-label="Search">
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form> -->

        <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
          <li class="nav-item text-center me-3">
            <a class="nav-link" href="#">
              <FontAwesomeIcon :icon="faUser" size="lg" />
              <div>Profile</div>
            </a>
          </li>
          <li class="nav-item text-center me-3">
            <a class="nav-link" href="#">
              <FontAwesomeIcon :icon="faHeart" size="lg" />
              <div>Wishlist</div>
            </a>
          </li>
          <li class="nav-item text-center me-3">
            <a class="nav-link" href="#">
              <FontAwesomeIcon :icon="faShoppingBag" size="lg" />
              <div>Bag</div>
            </a>
          </li>
        </ul>
      </div>
  </nav>
</template>

<script scoped>
export default {
  mounted() {
    // Enable hover dropdown
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
};
</script>

<style>
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
</style>
