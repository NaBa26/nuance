<script setup>
  import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
  import { faUser } from '@fortawesome/free-regular-svg-icons';
  import { faHeart } from '@fortawesome/free-regular-svg-icons';
  import { faShoppingBag } from '@fortawesome/free-solid-svg-icons';
</script>

<template>
  <footer class="text-center text-lg-start" style="background-color: #F0A500;">
    <!-- Social Media Links (optional) -->
    <div class="py-3">
      <a href="#" class="text-decoration-none me-3" style="color: #1B263B;">
        <i class="fa fa-user"></i>
      </a>
      <a href="#" class="text-decoration-none me-3" style="color: #1B263B;">
        <i class="fa fa-twitch" aria-hidden="true"></i>
      </a>
      <a href="#" class="text-decoration-none" style="color: #1B263B;">
        <i class="fa fa-instagram"></i>
      </a>
    </div>

    <!-- Copyright -->
    <div class="text-center p-3" style="background-color: #F0A500;">
      © {{ currentYear }} Copyright:
      <a class="text-body" href="http://localhost:5173/" style="color: #1B263B;">nuance.com</a>
    </div>
  </footer>
</template>

<script>
export default {
  data() {
    return {
      currentYear: new Date().getFullYear() // Dynamically get the current year
    };
  }
};
</script>