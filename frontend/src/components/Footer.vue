<script setup>
  import { ref } from 'vue';

  const currentDate = new Date();
  const currentYear = ref(currentDate.getFullYear());
</script>

<template>
  <footer class="footer text-center text-lg-start py-3 mb-0">
    <div class="container">
      <div class="text-center py-2 mt-4">
        © {{ currentYear }} nuance.com, Inc. or its affiliates
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background-image: url(/assets/images/bg_images/pexels-pixabay-326333.jpg) !important;
  font-family: 'Merriweather', serif;
}

footer .text-center {
  font-size: 0.9rem;
  font-weight: 500;
  color: #fff;
}
</style>
