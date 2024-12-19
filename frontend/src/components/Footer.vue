<script setup>
  import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
  import { faFacebook, faInstagram, faLinkedin, faXTwitter } from '@fortawesome/free-brands-svg-icons';

  import { ref } from 'vue';

  const currentDate = new Date();
  const currentYear = ref(currentDate.getFullYear())
</script>

<template>
  <footer class="footer text-center text-lg-start p-5" style="background: url('public/assets/images/bg_images/pexels-pixabay-326333.jpg');">
    <!-- Social Media Links -->
    <div class="social-media py-3">
      <a href="#" class="text-decoration-none me-4 social-icon" style="color: #fff;">
        <FontAwesomeIcon :icon="faInstagram" size="lg" />
      </a>
      <a href="#" class="text-decoration-none me-4 social-icon" style="color: #fff;">
        <FontAwesomeIcon :icon="faFacebook" size="lg" />
      </a>
      <a href="#" class="text-decoration-none me-4 social-icon" style="color: #fff;">
        <FontAwesomeIcon :icon="faLinkedin" size="lg" />
      </a>
      <a href="#" class="text-decoration-none me-4 social-icon" style="color: #fff;">
        <FontAwesomeIcon :icon="faXTwitter" size="lg" />
      </a>
    </div>

    <!-- Copyright -->
    <div class="text-center py-3" style="color: #fff;">
      © {{ currentYear }} nuance.com, Inc. or its affiliates
    </div>
  </footer>
</template>

<style scoped>
/* Footer Styling */
.footer {
  background: linear-gradient(to right, #6B4F3B, #4C3A2A);
  color: #fff;
  font-family: 'Poppins', sans-serif;
  text-align: center;
}

/* Social Media Icons */
.social-media a {
  font-size: 1.5rem;
  transition: transform 0.3s ease, color 0.3s ease;
}

.social-media a:hover {
  transform: scale(1.2);
  color: #00BFAE; /* Soft cyan color on hover */
}

/* Copyright Text */
footer .text-center {
  font-size: 1rem;
  font-weight: 500;
  color: #fff;
  letter-spacing: 1px;
}

@media (max-width: 768px) {
  .social-media a {
    font-size: 1.2rem;
  }
}
</style>
