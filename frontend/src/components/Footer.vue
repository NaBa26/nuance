<script setup>
  import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
  import { faFacebook, faInstagram, faLinkedin, faXTwitter } from '@fortawesome/free-brands-svg-icons';

  import { ref } from 'vue';

    const currentDate = new Date();
    const currentYear = ref(currentDate.getFullYear())
</script>

<template>
  <footer class="text-center text-lg-start justify-content-center" style="background-color: #F0A500;">
    <!-- Social Media Links (optional) -->
    <div class="py-3 text-center">
      <a href="#" class="text-decoration-none me-2" style="color: #1B263B;">
        <FontAwesomeIcon :icon="faInstagram" size="lg" />
      </a>
      <a href="#" class="text-decoration-none me-2" style="color: #1B263B;">
        <FontAwesomeIcon :icon="faFacebook" size="lg" />
      </a>
      <a href="#" class="text-decoration-none me-2" style="color: #1B263B;">
        <FontAwesomeIcon :icon="faLinkedin" size="lg" />
      </a>
      <a href="#" class="text-decoration-none me-2" style="color: #1B263B;">
        <FontAwesomeIcon :icon="faXTwitter" size="lg" />
      </a>
    </div>

    <!-- Copyright -->
    <div class="text-center p-3 md-2" style="background-color: #F0A500;">
      © {{ currentYear }}, nuance.com, Inc. or its affiliates
    </div>
  </footer>
</template>