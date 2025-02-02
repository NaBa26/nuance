<script setup lang="ts">
import router from './routes';

</script>

<template>
  <header>
    <router-view name="header" />
  </header>

  <main style="display: flex; flex-direction: column;">
    <section style="flex-grow: 1; overflow: auto">
      <router-view name="home" />
      <router-view name="books" />
      <router-view name="book" />
      <router-view name="bag" />
      <router-view name="profile" />
      <router-view name="contactUs" />
      <router-view name="login" />
      <router-view name="signup" />
      <router-view name="forgotPassword" />
      <router-view name="events" />
    </section>
  </main>

  <footer>
    <router-view name="footer" />
  </footer>
</template>
