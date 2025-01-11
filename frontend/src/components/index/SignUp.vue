<script setup>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faGoogle, faXTwitter, faGoodreads } from '@fortawesome/free-brands-svg-icons';
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { computed } from 'vue';

const formData = ref({
  firstName: '',
  lastName: '',
  email: '',
  username: '',
  password: '',
  confirmPassword: '',
  city: '',
});

const errorMessage = ref('');
const successMessage = ref('');
const router = useRouter();
const store = useStore();

const isAuthenticated = computed(() => store.getters.isAuthenticated);

const handleSubmit = async () => {
  if (isAuthenticated.value) {
    alert('Another user is already logged in');
    return;
  }
  errorMessage.value = '';
  successMessage.value = '';

  try {
    const response = await axios.post('http://localhost:8080/api/process-signup', formData.value);
    if (response.status === 201) {
      store.dispatch('login', response);
      successMessage.value = 'User signed up successfully!';
      setTimeout(() => {
        router.push('/home');
      }, 1000);
    }
  } catch (error) {
    if (error.response) {
      if (error.response.status === 400) {
        errorMessage.value = 'Bad Request: ' + (error.response.data || 'Invalid data provided.');
      } else {
        errorMessage.value = 'Error signing up: ' + (error.response.data || 'An unknown error occurred.');
      }
    } else {
      errorMessage.value = 'Error signing up: ' + error.message;
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
                <h2 class="fw-bold text-uppercase mb-4" style="color: #F0A500;">Sign up now!</h2>
              </div>

              <!-- Success/Error Messages -->
              <div v-if="errorMessage" class="alert alert-danger mb-3" role="alert">
                {{ errorMessage }}
              </div>
              <div v-if="successMessage" class="alert alert-success mb-3" role="alert">
                {{ successMessage }}
              </div>

              <!-- Sign-Up Form -->
              <form @submit.prevent="handleSubmit">
                <div class="row">
                  <div class="col-md-6 mb-2">
                    <label class="form-label" for="firstName">First Name <span class="text-danger">*</span></label>
                    <input v-model="formData.firstName" type="text" id="firstName" class="form-control form-control-lg" required />
                  </div>
                  <div class="col-md-6 mb-3">
                    <label class="form-label" for="lastName">Last Name <span class="text-danger">*</span></label>
                    <input v-model="formData.lastName" type="text" id="lastName" class="form-control form-control-lg" required />
                  </div>
                </div>

                <div class="mb-3">
                  <label class="form-label" for="email">Email <span class="text-danger">*</span></label>
                  <input v-model="formData.email" type="email" id="email" class="form-control form-control-lg" required />
                </div>

                <div class="mb-3">
                  <label class="form-label" for="username">Username <span class="text-danger">*</span></label>
                  <input v-model="formData.username" type="text" id="username" class="form-control form-control-lg" required />
                </div>

                <div class="mb-3">
                  <label class="form-label" for="password">Password <span class="text-danger">*</span></label>
                  <input v-model="formData.password" type="password" id="password" class="form-control form-control-lg" required />
                </div>

                <div class="mb-3">
                  <label class="form-label" for="confirmPassword">Confirm Password <span class="text-danger">*</span></label>
                  <input v-model="formData.confirmPassword" type="password" id="confirmPassword" class="form-control form-control-lg" required />
                </div>

                <div class="mb-3">
                  <label class="form-label" for="city">City <span class="text-danger">*</span></label>
                  <input v-model="formData.city" type="text" id="city" class="form-control form-control-lg" required />
                </div>

                <button
                  type="submit"
                  class="btn btn-outline-light btn-lg w-100 mb-3"
                >
                  SignUp
                </button>
              </form>

              <!-- Social Sign-Up -->
              <div class="text-center mt-3">
                <p class="text-white-50">Or sign up with</p>
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

              <div v-if="errorMessage" class="alert alert-danger mt-4" role="alert">
                {{ errorMessage }}
              </div>

              <div v-if="successMessage" class="alert alert-success mt-4" role="alert">
                {{ successMessage }}
              </div>

              <!-- Footer Links -->
              <div class="mt-4 text-center">
                <p class="text-white-50">
                  Already Registered?
                  <router-link to="/login" class="text-white fw-bold">Log In</router-link>
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
  max-width: 800px;
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
