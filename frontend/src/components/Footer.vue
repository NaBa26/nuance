<script setup>
  import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
  import { faFacebook, faInstagram, faLinkedin, faXTwitter } from '@fortawesome/free-brands-svg-icons';
  import { ref } from 'vue';

  const currentDate = new Date();
  const currentYear = ref(currentDate.getFullYear());
</script>

<template>
  <footer class="footer text-center text-lg-start py-5" style="background-image: url(assets/images/bg_images/pexels-pixabay-326333.jpg);">
    <div class="container">
      <div class="row">
        <!-- Social Media Links -->
        <div class="col-12 d-flex justify-content-center mb-4">
          <div class="social-media py-3">
            <a href="javascript:void(0)" class="text-decoration-none mx-2 social-icon">
              <FontAwesomeIcon :icon="faInstagram" size="lg" />
            </a>
            <a href="javascript:void(0)" class="text-decoration-none mx-2 social-icon">
              <FontAwesomeIcon :icon="faFacebook" size="lg" />
            </a>
            <a href="javascript:void(0)" class="text-decoration-none mx-2 social-icon">
              <FontAwesomeIcon :icon="faLinkedin" size="lg" />
            </a>
            <a href="javascript:void(0)" class="text-decoration-none mx-2 social-icon">
              <FontAwesomeIcon :icon="faXTwitter" size="lg" />
            </a>
          </div>
        </div>

        <!-- Company Information -->
        <div class="col-md-3">
          <h5 class="text-uppercase mb-3">Company</h5>
          <ul class="list-unstyled">
            <li><a href="#" class="text-decoration-none">About Us</a></li>
            <li><a href="#" class="text-decoration-none">Careers</a></li>
            <li><a href="#" class="text-decoration-none">Press</a></li>
            <li><a href="#" class="text-decoration-none">Blog</a></li>
          </ul>
        </div>

        <!-- Support -->
        <div class="col-md-3">
          <h5 class="text-uppercase mb-3">Support</h5>
          <ul class="list-unstyled">
            <li><a href="#" class="text-decoration-none">Contact Us</a></li>
            <li><a href="#" class="text-decoration-none">Help Center</a></li>
            <li><a href="#" class="text-decoration-none">FAQ</a></li>
            <li><a href="#" class="text-decoration-none">Return Policy</a></li>
          </ul>
        </div>

        <!-- Legal -->
        <div class="col-md-3">
          <h5 class="text-uppercase mb-3">Legal</h5>
          <ul class="list-unstyled">
            <li><a href="#" class="text-decoration-none">Terms of Service</a></li>
            <li><a href="#" class="text-decoration-none">Privacy Policy</a></li>
            <li><a href="#" class="text-decoration-none">Cookie Policy</a></li>
          </ul>
        </div>
      </div>

      <!-- Copyright -->
      <div class="text-center py-3 mt-4">
        © {{ currentYear }} nuance.com, Inc. or its affiliates
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background: linear-gradient(to right, #6b4f3b, #4c3a2a);
  color: #fff;
  font-family: 'Merriweather', serif;
}

.social-media {
  display: flex;
  gap: 1rem;
}

.social-media a {
  font-size: 1.5rem;
  color: #fff;
  transition: transform 0.3s ease, color 0.3s ease;
}

.social-media a:hover {
  transform: scale(1.2);
  color: #F0A500;
}

ul li a {
  font-size: 1rem;
  font-weight: 500;
  color: #fff;
  letter-spacing: 1px;
  transition: color 0.3s ease;
}

ul li a:hover {
  color: #F0A500;
}

footer .text-center {
  font-size: 0.9rem;
  font-weight: 500;
  color: #fff;
}
</style>
