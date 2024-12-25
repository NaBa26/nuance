<template>
  <header>
    <router-view name="header" />
  </header>

  <main style="display: flex;">

    <section style="display: flex; flex-grow: 1; width: 100vw; height: 100vh; overflow: auto;">
      <router-view name="login" />
      <router-view name="signup" />
      <router-view name="home" />
      <router-view name="forgotPassword" />
    </section>

    <section style="display: flex; flex-grow: 1;">
      <router-view name="books" />
      <router-view name="book" />
      <router-view name="notfound" />
      <router-view name="bag" />
      <router-view name="profile" />
      <router-view name="oAuthRedirectHandler" />
      <router-view name="contactUs" />
    </section>

  </main>

  <footer>
    <router-view name="footer" />
  </footer>
</template>
