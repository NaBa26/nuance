<script setup>
import { useStore } from 'vuex';
import { ref, computed } from 'vue';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faGoogle, faXTwitter, faGoodreads } from '@fortawesome/free-brands-svg-icons';

const formData = ref({
  username: '',
  password: '',
});

const errorMessage = ref('');
const store = useStore();
const isAuthenticated = computed(() => store.getters.isAuthenticated);

const handleLogin = async () => {
  if (isAuthenticated.value) {
    alert('User is already logged in');
    return;
  }
  errorMessage.value = '';
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
        errorMessage.value = 'User is already logged in.';
      } else {
        errorMessage.value = 'An error occurred: ' + (error.response.data || 'Unknown error.');
      }
    } else {
      errorMessage.value = 'An error occurred: ' + error.message;
    }
  }
};
</script>

<template>
  <section class="fullscreen">
    <div class="container centered-content">
      <div class="row justify-content-center align-items-center" style="width: 100%;">
        <div class="col-lg-6 col-md-8 col-sm-10">
          <div class="card bg-dark text-white shadow-lg">
            <div class="card-body p-5">
              <div class="text-center">
                <img src="/apple-touch-icon.png" alt="logo" class="logo-img mb-4" />
                <h2 class="fw-bold text-uppercase mb-4" style="color: #F0A500;">Login</h2>
                <p class="text-white-50 mb-4">Enter your username and password below to login</p>
              </div>

              <!-- Login Form -->
              <form @submit.prevent="handleLogin">
                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="username">Username</label>
                  <input
                    v-model="formData.username"
                    type="text"
                    id="username"
                    class="form-control form-control-lg"
                    required
                  />
                </div>

                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="password">Password</label>
                  <input
                    v-model="formData.password"
                    type="password"
                    id="password"
                    class="form-control form-control-lg"
                    required
                  />
                </div>

                <button
                  type="submit"
                  class="btn btn-outline-light btn-lg w-100 mb-3"
                >
                  Login
                </button>
              </form>

              <!-- Social Login -->
              <div class="text-center mt-3">
                <p class="text-white-50">Or login with</p>
                <div class="d-flex justify-content-center gap-3">
                  <a href="http://localhost:8080/oauth2/authorization/google" class="social-icon">
                    <FontAwesomeIcon :icon="faGoogle" size="lg" />
                  </a>
                  <a href="#" class="social-icon">
                    <FontAwesomeIcon :icon="faXTwitter" size="lg" />
                  </a>
                  <a href="#" class="social-icon">
                    <FontAwesomeIcon :icon="faGoodreads" size="lg" />
                  </a>
                </div>
              </div>

              <!-- Error Message -->
              <div v-if="errorMessage" class="alert alert-danger mt-4" role="alert">
                {{ errorMessage }}
              </div>

              <!-- Footer Links -->
              <div class="mt-4 text-center">
                <p class="text-white-50">
                  Don't have an account?
                  <router-link to="/signup" class="text-white fw-bold">Sign Up</router-link>
                </p>
                <p>
                  <router-link to="/forgot_password" class="text-white-50">Forgot password?</router-link>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.fullscreen {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #1B263B;
  padding: 20px;
}

.container {
  width: 100%;
}

.card {
  border-radius: 1rem;
  max-width: 500px;
  margin: 0 auto;
}

.form-outline {
  margin-bottom: 1.5rem;
}

.form-label {
  color: #fff;
  font-weight: 500;
}

.form-control {
  background-color: #2c2c2c;
  border: 1px solid #ccc;
  color: #fff;
  padding: 0.75rem;
  border-radius: 0.5rem;
}

.form-control:focus {
  border-color: #F0A500;
  box-shadow: 0 0 5px #F0A500;
}

.btn {
  border: 1px solid #F0A500;
  color: #F0A500;
  transition: background-color 0.3s, color 0.3s;
}

.btn:hover {
  background-color: #F0A500;
  color: #212529;
}

.social-icon {
  color: #F0A500;
  font-size: 1.5rem;
  transition: color 0.3s;
}

.social-icon:hover {
  color: #fff;
}

.logo-img {
  width: 60px;
  height: auto;
}

.alert {
  border-radius: 0.5rem;
  padding: 1rem;
}

@media (max-width: 768px) {
  .card {
    padding: 1rem;
  }

  .social-icon {
    font-size: 1.25rem;
  }
}
</style>
