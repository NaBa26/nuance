<script setup>
import Footer from './components/Footer.vue';
import Header from './components/Header.vue';
</script>

<template>
  <header>
    <Header />
  </header>

  <main>
    <router-view />
  </main>

  <footer>
    <Footer />
  </footer>
</template>
