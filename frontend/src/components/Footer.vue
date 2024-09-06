<script setup>
  import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
  import { faFacebook } from '@fortawesome/free-brands-svg-icons';
  import { faTwitter } from '@fortawesome/free-brands-svg-icons';
  import { faInstagram } from '@fortawesome/free-brands-svg-icons';
  import { faLinkedin } from '@fortawesome/free-brands-svg-icons';
</script>

<template>
  <footer class="text-center text-lg-start justify-content-center" style="background-color: #F0A500;">
    <!-- Social Media Links (optional) -->
    <div class="py-3 text-center">
      <a href="#" class="text-decoration-none me-2" style="color: #1B263B;">
        <FontAwesomeIcon :icon="faTwitter" size="lg" />
      </a>
      <a href="#" class="text-decoration-none me-2" style="color: #1B263B;">
        <FontAwesomeIcon :icon="faInstagram" size="lg" />
      </a>
      <a href="#" class="text-decoration-none me-2" style="color: #1B263B;">
        <FontAwesomeIcon :icon="faFacebook" size="lg" />
      </a>
      <a href="#" class="text-decoration-none me-2" style="color: #1B263B;">
        <FontAwesomeIcon :icon="faLinkedin" size="lg" />
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