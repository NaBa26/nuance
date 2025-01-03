<script setup>
import { useStore } from 'vuex';
import { onMounted, ref } from 'vue';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faGoogle, faTwitter, faGoodreads } from '@fortawesome/free-brands-svg-icons';
import { computed } from 'vue';

const formData = ref({
  username: '',
  password: ''
});

const errorMessage = ref('');
const successMessage = ref('');

const store = useStore(); // Access Vuex store

const handleLogin = async () => {
  if(computed(() => store.getters.isAuthenticated).value) {
    alert('User is already logged in');
    return;
  }
  errorMessage.value = '';
  successMessage.value = '';
  try {
    const response = await axios.post('http://localhost:8080/api/process-login', formData.value);
    if (response.status === 200) {
    store.dispatch('login', response);
    window.location.href = 'http://localhost:5173/home';
    }
  } catch (error) {
    if (error.response) {
      if (error.response.status === 404) {
        errorMessage.value = 'User not found.';
      } else if (error.response.status === 401) {
        errorMessage.value = 'Incorrect password.';
      } else if (error.response.status === 403) {
        errorMessage.value = 'User is already logged in';
      } else {
        errorMessage.value = 'An error occurred: ' + (error.response.data || 'Unknown error.');
      }
    } else {
      errorMessage.value = 'An error occurred: ' + error.message;
    }
  }
};

onMounted(() => {
  console.log(store.getters.isAuthenticated);
});
</script>


<template>
<section class="fullscreen" style="background-color: #1B263B;">
  <div class="container centered-content">
    <div class="row justify-content-center align-items-center" style="width: 60vw;">
      <div class="col-12 col-sm-10 col-md-8 col-lg-6">
        <div class="card bg-dark text-white" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
            <img src="/apple-touch-icon.png" alt="logo" class="logo-img" />
            <hr>
            <h2 class="fw-bold mb-4 text-uppercase" style="color: #F0A500">Login</h2>
            <p class="text-white-50 mb-5">Please enter your username and password!</p>

             <form @submit.prevent="handleLogin">
            <div class="form-outline form-white mb-4">
              <label class="form-label" for="username">Username</label>
              <input v-model="formData.username" type="text" id="username" name="username" class="form-control form-control-lg" required />
            </div>

            <div class="form-outline form-white mb-4">
              <label class="form-label" for="password">Password</label>
              <input v-model="formData.password" type="password" id="password" name="password" class="form-control form-control-lg" required />
            </div>
            
            <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>

            <div class="d-flex justify-content-center mt-4">
                <div class="auth-footer">
                    <h6>Or login with</h6>
                        <a href="http://localhost:8080/oauth2/authorization/google" class="text-decoration-none me-2"><FontAwesomeIcon :icon="faGoogle" size="xl" /></a>
                        <a href="#" class="text-decoration-none me-2"><FontAwesomeIcon :icon="faTwitter" size="xl" /></a>
                        <a href="#" class="text-decoration-none me-2"><FontAwesomeIcon :icon="faGoodreads" size="xl" /></a>
                </div>
            </div>

        </form>

            <div class="mt-3">
              <p class="mb-5" >Don't have an account? <router-link to="/signup" class="text-white-50 fw-bold">Sign Up</router-link></p>
            </div>


            <p class="small mb-0"><router-link class="text-white-50" to="/forgot_password">Forgot password?</router-link></p>

          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</template>

<style scoped>
.fullscreen {
  width: 100vw; 
  height: 100vh; 
  margin: 0;
  padding: 0;
  overflow: hidden;
}

.centered-content {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
.form-outline {
  margin-bottom: 1.5rem;
}

.form-control {
  padding: 0.5rem 0.75rem;
  border: 1px solid #ccc;
  border-radius: 0.5rem;
  background-color: #2c2c2c;
  color: #fff;
}

.form-label {
  display: block;
  font-size: 0.875rem;
  color: #fff;
  margin-bottom: 0.5rem;
  transition: color 0.2s ease;
}

.btn {
  width:fit-content;
}

.btn:hover{
    background-color: #F0A500;
}

.d-flex a {
  color: #F0A500; 
}

.auth-footer a:hover{
  color: white;
}
</style>
