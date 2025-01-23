<template>
  <nav
    class="navbar navbar-expand-lg navbar-dark"
    style="
      background: url('/assets/images/bg_images/pexels-pixabay-326333.jpg');
    "
  >
    <div class="container-fluid">
      <a class="navbar-brand d-flex align-items-center" href="#">
        <router-link to="/home">
          <img
            src="/android-chrome-512x512.png"
            alt="logo"
            class="logo-img"
          />
        </router-link>
        <span class="ms-2 brand-name">nuance</span>
      </a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div
        class="collapse navbar-collapse justify-content-center"
        id="navbarNav"
      >
        <ul class="navbar-nav mx-auto" style="font-size: 1rem">
          <li class="nav-item">
            <router-link class="nav-link" to="/home"><b>Home</b></router-link>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><b>Best Sellers</b></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><b>Staff Picks</b></a>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/books"
              ><b>Books List</b></router-link
            >
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/books"
              ><b>Events</b></router-link
            >
          </li>
        </ul>

        <ul class="navbar-nav ms-auto">
          <li class="nav-item text-center me-2">
            <router-link
              class="nav-link"
              :to="routeFunction({ path: 'profile' })"
            >
              <FontAwesomeIcon :icon="faUser" size="md" />
              <div>Profile</div>
            </router-link>
          </li>
          <li class="nav-item text-center me-2">
            <!-- Dynamically generate the URL with userId -->
            <router-link class="nav-link" :to="routeFunction({ path: 'bag' })">
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
          <li v-if="isAuthenticated" class="nav-item text-center me-2">
            <a role="button" class="nav-link" @click.prevent="logOut">
              <FontAwesomeIcon :icon="faSignOut" size="md" />
              <div>Logout</div>
            </a>
          </li>
          <li v-else class="nav-item text-center me-2">
            <router-link class="nav-link" to="/login">
              <FontAwesomeIcon :icon="faSignIn" size="md" />
              <div>LogIn</div>
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import {
  faUser,
  faShoppingBag,
  faSignOut,
  faPhone,
  faSignIn,
} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { useStore } from "vuex";
import { computed } from "vue";
import CryptoJS from "crypto-js";
import Swal from "sweetalert2";

const store = useStore();
const isAuthenticated = computed(() => store.getters.isAuthenticated);

function encryptData(data) {
  const encryptionKey = import.meta.env.VITE_USER_ID_ENCRYPTION_KEY;
  if (!encryptionKey) {
    throw new Error("Encryption key is not set in the environment variables.");
  }
  return CryptoJS.AES.encrypt(JSON.stringify(data), encryptionKey).toString();
}

const logOut = async () => {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/log-out",
      null,
      { withCredentials: true }
    );
    if (response.status === 200) {
      await Swal.fire({
        icon: "info",
        title: "User will be logged out now!",
        showConfirmButton: false,
        timer: 2000,
      });
      await store.dispatch("logout", response);
      window.location.href = "http://localhost:5173/login";
    }
  } catch (error) {
    Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Failed to log out!",
        footer:"Please try again later"
      });
  }
};

function routeFunction(route) {
  if (isAuthenticated.value) {
    return `/${route.path}?id=${encryptData(store.getters.userId)}`;
  } else {
    return "/login";
  }
}
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
  font-family: "Merriweather", serif;
  letter-spacing: 1px;
  color: #fff;
  transition: color 0.3s ease, transform 0.3s ease;
}

.navbar-nav .nav-link:hover {
  color: #f0a500;
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

.brand-name {
  font-size: 2rem;
  font-family: "Merriweather", serif;
  color: #fff;
}

.logo-img {
  width: 50px;
  height: 50px;
}

@media (max-width: 576px) {
  .navbar-nav .nav-link {
    font-size: 0.9rem;
  }
}
</style>
