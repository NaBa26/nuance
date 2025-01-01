<template>
  <header>
    <router-view name="header" />
  </header>

  <main style="display: flex; flex-direction: column;">
    <section style="flex-grow: 1; overflow: auto;">
      <router-view name="home" />
      <router-view name="books" />
      <router-view name="book" />
      <router-view name="bag" />
      <router-view name="profile" />
      <router-view name="contactUs" />
      <router-view name="login" />
      <router-view name="signup" />
      <router-view name="forgotPassword" />
    </section>
  </main>

  <footer>
    <router-view name="footer" />
  </footer>
</template>

<script lang="ts">
export default {
  computed: {
    isBooksOrBookRoute(): boolean {
      return ['Books', 'Book', 'Bag', 'Profile', 'ContactUs'].includes(this.$route.name as string);
    },
    isAuthRoute(): boolean {
      return ['LogIn', 'SignUp', 'ForgotPassword'].includes(this.$route.name as string);
    }
  }
}
</script>
