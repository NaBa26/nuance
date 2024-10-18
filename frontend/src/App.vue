<script setup>
import Footer from './components/Footer.vue';
import Header from './components/Header.vue';
</script>

<template>
  <header>
    <router-view name="header" />
  </header>

  <main style="display: flex;">
    <aside style="position:sticky; display: grid; top: 0;">
      <router-view name="sidebar" />
    </aside>

    <section style="flex-grow: 1;" >
      <router-view name="books" />
      <router-view name="home" />
      <router-view name="book" />
      <router-view name="login" />
      <router-view name="signup" />
      <router-view name="notfound" />
      <router-view name="bag" />
      <router-view name="profile" />
    </section>
  </main>

  <footer>
    <router-view name="footer" />
  </footer>
</template>

