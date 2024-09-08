<script setup>
import Footer from './components/Footer.vue';
import Header from './components/Header.vue';
</script>

<template>
  <header>
    <Header />
  </header>

  <main style="display: flex;">
    <sidebar style="position:sticky; display: grid; top: 0;">
    <router-view name="sidebar" />
  </sidebar>
    <content>
      <router-view name="book" />
    </content>
  </main>

  <footer>
    <Footer />
  </footer>
</template>
