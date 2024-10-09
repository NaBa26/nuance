<script setup>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faXTwitter, faGoodreads, faGoogle } from '@fortawesome/free-brands-svg-icons';
</script>

<template>
  <section class="d-flex align-items-center"
    style="min-height: 100vh; max-height: 100vh; background-color: #1B263B; overflow: hidden;">
    <div class="container py-4" style="max-height: 100%; overflow: auto;">
      <div class="row g-0 align-items-center" style="max-height: 100%; overflow: auto;">
        <div class="col-lg-5 mb-5 mb-lg-0">
          <div class="card cascading-right bg-body-tertiary"
            style="backdrop-filter: blur(30px); max-height: 100%; overflow: auto;">
            <div class="card-body p-4 shadow-5 text-center" style="background-color: #212529;">
              <h2 class="fw-bold mb-4" style="color: #F0A500;">Sign up now!</h2>
              <form @submit="handleSubmit">
                <div class="row">
                  <div class="col-md-6 mb-3">
                    <div class="form-outline">
                      <label class="form-label" for="first_name">First name</label>
                      <input type="text" id="firstName" class="form-control" required />
                    </div>
                  </div>
                  <div class="col-md-6 mb-3">
                    <div class="form-outline">
                      <label class="form-label" for="last_name">Last name</label>
                      <input type="text" id="lastName" class="form-control" required />
                    </div>
                  </div>
                </div>

                <div class="form-outline mb-3">
                  <label class="form-label" for="email">Email address</label>
                  <input type="email" id="email" class="form-control" required />
                </div>

                <div class="form-outline mb-3">
                  <label class="form-label" for="username">Username</label>
                  <input type="text" id="username" class="form-control" required />
                </div>

                <div class="form-outline mb-3">
                  <label class="form-label" for="password">Password</label>
                  <input type="password" id="password" class="form-control" required />
                </div>

                <div class="form-outline mb-3">
                  <label class="form-label" for="confirm_password">Confirm Password</label>
                  <input type="password" id="confirm_password" class="form-control" required />
                </div>

                <div class="form-outline mb-4">
                  <label class="form-label" for="city">City</label>
                  <input type="text" id="city" class="form-control" required />
                </div>

                <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-4"
                  type="submit">SignUp</button>

                <div class="d-flex justify-content-center mt-3">
                  <div class="auth-footer">
                    <h6>Or login with</h6>
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

<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const formData = ref({
      first_name: '',
      last_name: '',
      email: '',
      username: '',
      password: '',
      city: '',
    });

    const handleSubmit = async () => {
      try {
        const response = await axios.post('http://localhost:8080/api/process-signup', formData.value);
        console.log('User signed up successfully:', response.data);
        console.log(formData);
      } catch (error) {
        console.error('Error signing up:', error);
      }
    };

    return {
      formData,
      handleSubmit,
    };
  },
};
</script>

<style lang="css" scoped>

.cascading-right {
  margin-right: -40px;
}

@media (max-width: 991.98px) {
  .cascading-right {
    margin-right: 0;
  }
}

form {
  padding: 0.75rem;
  border-radius: 0.5rem;
  color: #fff;
}

form input {
  background-color: #2c2c2c;
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

.auth-footer a:hover{
  color: white;
}
</style>
