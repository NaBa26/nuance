<script setup>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faXTwitter, faGoodreads, faGoogle } from '@fortawesome/free-brands-svg-icons';
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router'; // For routing to home

const formData = ref({
  firstName: '',
  lastName: '',
  email: '',
  username: '',
  password: '',
  confirmPassword: '',
  city: ''
});

const errorMessage = ref('');
const successMessage = ref('');
const loading = ref(false); // Loading state
const router = useRouter(); // To handle redirection

const handleSubmit = async () => {
  loading.value = true; // Show loading state
  errorMessage.value = ''; // Reset error message
  successMessage.value = ''; // Reset success message
  
  try {
    const response = await axios.post('http://localhost:8080/api/process-signup', formData.value);
    if (response.status === 201) {
      successMessage.value = 'User signed up successfully!';
      setTimeout(() => {
        router.push('/home');
      }, 1000); // Delay to show success message
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
  } finally {
    loading.value = false; // Hide loading state
  }
};
</script>

<template>
  <section class="fullscreen" style="background-color: #1B263B;">
    <div class="container h-100">
      <div class="row g-0 align-items-center h-100">
        <div class="col-lg-6">
          <div class="card cascading-right bg-body-tertiary" style="backdrop-filter: blur(30px); max-height: 100%; overflow: auto;">
            <div class="card-body p-4 shadow-5 text-center" style="background-color: #212529;">
              <h2 class="fw-bold mb-4" style="color: #F0A500;">Sign up now!</h2>
              <form @submit.prevent="handleSubmit" method="post">
                <div class="row">
                  <div class="col-md-6 mb-3">
                    <div class="form-outline form-white mb-4">
                      <label class="form-label" for="firstName">
                        First Name <span class="text-danger">*</span>
                      </label>
                      <input v-model="formData.firstName" type="text" id="firstName" class="form-control form-control-lg" required :disabled="loading.value"/>
                    </div>
                  </div>
                  <div class="col-md-6 mb-3">
                    <div class="form-outline form-white mb-4">
                      <label class="form-label" for="lastName">
                        Last Name <span class="text-danger">*</span>
                      </label>
                      <input v-model="formData.lastName" type="text" id="lastName" class="form-control form-control-lg" required :disabled="loading.value"/>
                    </div>
                  </div>
                </div>

                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="email">
                    Email <span class="text-danger">*</span>
                  </label>
                  <input v-model="formData.email" type="email" id="email" class="form-control form-control-lg" required :disabled="loading.value"/>
                </div>

                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="username">
                    Username <span class="text-danger">*</span>
                  </label>
                  <input v-model="formData.username" type="text" id="username" class="form-control form-control-lg" required :disabled="loading.value"/>
                </div>

                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="password">
                    Password <span class="text-danger">*</span>
                  </label>
                  <input v-model="formData.password" type="password" id="password" class="form-control form-control-lg" required :disabled="loading.value"/>
                </div>

                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="confirmPassword">
                    Confirm Password <span class="text-danger">*</span>
                  </label>
                  <input v-model="formData.confirmPassword" type="password" id="confirmPassword" class="form-control form-control-lg" required :disabled="loading.value"/>
                </div>

                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="city">
                    City <span class="text-danger">*</span>
                  </label>
                  <input v-model="formData.city" type="text" id="city" class="form-control form-control-lg" required :disabled="loading.value"/>
                </div>

                <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-4" type="submit" :disabled="loading.value">
                  <span v-if="loading.value">Loading...</span>
                  <span v-else>Sign Up</span>
                </button>

                <div v-if="loading.value" class="spinner-border text-light mt-3" role="status">
                  <span class="visually-hidden">Loading...</span>
                </div>

                <div class="d-flex justify-content-center mt-3">
                  <div class="auth-footer">
                    <h6 style="color: white;">Or sign up with</h6>
                    <a href="http://localhost:8080/oauth2/authorization/google" class="text-decoration-none me-2">
                      <FontAwesomeIcon :icon="faGoogle" size="xl" />
                    </a>
                    <a href="#" class="text-decoration-none me-2">
                      <FontAwesomeIcon :icon="faXTwitter" size="xl" />
                    </a>
                    <a href="#" class="text-decoration-none me-2">
                      <FontAwesomeIcon :icon="faGoodreads" size="xl" />
                    </a>
                  </div>
                </div>
              </form>

              <!-- Success/Error Messages -->
              <div v-if="errorMessage" class="mt-3 alert alert-danger" role="alert">
                {{ errorMessage }}
              </div>
              <div v-if="successMessage" class="mt-3 alert alert-success" role="alert">
                {{ successMessage }}
              </div>

              <div class="mt-3">
                <p class="mb-5" style="color: white;">Already Registered? <router-link to="/login" class="text-white-50 fw-bold">Log In</router-link></p>
              </div>
            </div>
          </div>
        </div>

        <div class="col-lg-6 d-none d-lg-block">
          <img src="/public/assets/images/bg_images/sign_up.jpg" class="w-100 rounded-4 shadow-4" alt="" />
        </div>
      </div>
    </div>
  </section>
</template>

<style lang="css" scoped>
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
.cascading-right {
  margin-right: -40px;
}

@media (max-width: 991.98px) {
  .cascading-right {
    margin-right: 0;
  }
}

.btn {
  width: fit-content;
}

.btn:hover {
  background-color: #F0A500;
}

.d-flex a {
  color: #F0A500;
}

img {
  max-height: 100%;
  object-fit: cover;
}

.auth-footer a:hover {
  color: white;
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

.spinner-border {
  width: 2rem;
  height: 2rem;
  border-width: 0.25rem;
}
</style>
