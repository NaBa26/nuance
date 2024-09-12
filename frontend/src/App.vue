<script setup>
import Footer from './components/Footer.vue';
import Header from './components/Header.vue';
</script>

<template>
  <header>
    <Header />
  </header>

  <main style="display: flex;">
    <aside style="position:sticky; display: grid; top: 0;">
      <router-view name="sidebar" />
    </aside>

    <section style="flex-grow: 1;">
      <router-view name="books" />
      <router-view name="home" />
      <router-view name="book" />
    </section>
  </main>

  <footer>
    <Footer />
  </footer>
</template>

