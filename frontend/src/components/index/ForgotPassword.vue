<template>
  <section class="fullscreen" style="background-color: #1b263b">
    <div class="container centered-content">
      <div
        class="row justify-content-center align-items-center"
        style="width: 60vw"
      >
        <div class="col-12 col-sm-10 col-md-8 col-lg-6">
          <div class="card bg-dark text-white" style="border-radius: 1rem">
            <div class="card-body p-5 text-center">
              <img src="/apple-touch-icon.png" alt="logo" class="logo-img" />
              <hr />
              <h2 class="fw-bold mb-4 text-uppercase" style="color: #f0a500">
                Forgot Password
              </h2>
              <p class="text-white-50 mb-5">
                Enter your registered email address, and we will send you a
                password reset link.
              </p>

              <form @submit.prevent="forgotPassword">
                <div class="form-outline form-white mb-4">
                  <label class="form-label" for="email">Email Address</label>
                  <input
                    v-model="formData.email"
                    type="email"
                    id="email"
                    name="email"
                    class="form-control form-control-lg"
                    required
                  />
                </div>

                <button class="btn btn-outline-light btn-lg px-5" type="submit">
                  Submit Request
                </button>
              </form>
              <hr />

              <div
                class="d-flex justify-content-center align-items-center mt-4 back-to-login"
              >
                <router-link to="/login" class="back-to-login-link">
                  <FontAwesomeIcon :icon="faArrowAltCircleLeft" size="lg" />
                  <span class="ms-2">Back to Login</span>
                </router-link>
              </div>

              <div class="mt-3">
                <p class="mb-3">
                  Don't have an account?
                  <router-link to="/signup" class="text-white-50 fw-bold"
                    >Sign Up</router-link
                  >
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faArrowAltCircleLeft } from "@fortawesome/free-regular-svg-icons";

const formData = ref({
  email: "",
});

const forgotPassword = async () => {
  try {
    console.log(formData.value);
    await axios.post(
      "http://localhost:8080/api/email/forgot-password",
      formData.value
    );
    alert("Password reset instructions have been sent to your email.");
  } catch (error) {
    alert(
      "An error occurred while sending the reset instructions. Please try again."
    );
  }
};
</script>

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
  width: fit-content;
}

.btn:hover {
  background-color: #f0a500;
}

.d-flex a {
  color: #f0a500;
}
</style>
