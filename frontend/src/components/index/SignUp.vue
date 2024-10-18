<script setup>
import { ref } from 'vue';
import axios from 'axios';

const formData = ref({
  first_name: '',
  last_name: '',
  email: '',
  username: '',
  password: '',
  confirm_password: ''
});

const errorMessage = ref('');
const successMessage = ref('');

const handleSubmit = async () => {
  errorMessage.value = '';
  successMessage.value = '';

  /*if (formData.value.password !== formData.value.confirm_password) {
    errorMessage.value = "Passwords do not match.";
    return;
  }*/

  try {
    const response = await axios.post('http://localhost:8080/api/process-signup', formData.value);
    if (response.status === 201) {
      successMessage.value = 'User signed up successfully!';
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
    console.error('Error signing up:', error);
  }
};
</script>


<template>
  <section class="d-flex align-items-center"
    style="background-color: #1B263B; overflow: hidden;">
    <div class="container py-4" style="max-height: 100%; overflow: auto;">
      <div class="row g-0 align-items-center" style="max-height: 100%; overflow: auto;">
        <div class="col-lg-5 mb-5 mb-lg-0">
          <div class="card cascading-right bg-body-tertiary"
            style="backdrop-filter: blur(30px); max-height: 100%; overflow: auto;">
            <div class="card-body p-4 shadow-5 text-center" style="background-color: #212529;">
              <h2 class="fw-bold mb-4" style="color: #F0A500;">Sign up now!</h2>
              <form @submit.prevent="handleSubmit" method="post">
                <div class="row">
                  <div class="col-md-6 mb-3">
                    <div class="form-outline form-white mb-4">
                      <label class="form-label" for="first_name">First Name</label>
                      <input v-model="formData.first_name" type="text" id="first_name" class="form-control form-control-lg" required />
                    </div>
                  </div>
                  <div class="col-md-6 mb-3">
                    <div class="form-outline form-white mb-4">
                      <label class="form-label" for="last_name">Last Name</label>
                      <input v-model="formData.last_name" type="text" id="last_name" class="form-control form-control-lg" required />
                    </div>
                  </div>
                </div>

                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="email">Email</label>
                  <input v-model="formData.email" type="email" id="email" class="form-control form-control-lg" required />
                </div>

                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="username">Username</label>
                  <input v-model="formData.username" type="text" id="username" class="form-control form-control-lg" required />
                </div>

                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="password">Password</label>
                  <input v-model="formData.password" type="password" id="password" class="form-control form-control-lg" required />
                </div>

                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="confirm_password">Confirm Password</label>
                  <input v-model="formData.confirm_password" type="password" id="confirm_password" class="form-control form-control-lg" required />
                </div>

                <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-4" type="submit">SignUp</button>

                <div class="d-flex justify-content-center mt-3">
                  <div class="auth-footer">
                    <h6 style="color: white;">Or sign up with</h6>
                    <a href="#" class="text-decoration-none me-2">
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

              <div class="mt-3">
                <p class="mb-5" style="color: white;">Already Registered? <router-link to="/login" class="text-white-50 fw-bold">Log In</router-link></p>
              </div>
            </div>
          </div>
        </div>

        <div class="col-lg-6 mb-5 mb-lg-0">
          <img src="/public/assets/images/bg_images/sign_up.jpg" class="w-100 rounded-4 shadow-4" alt="" />
        </div>
      </div>
    </div>
  </section>
</template>

<style lang="css" scoped>
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
</style>
