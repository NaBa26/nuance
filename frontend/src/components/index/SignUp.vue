<script setup>
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faGoogle } from "@fortawesome/free-brands-svg-icons";
import { ref } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { computed } from "vue";
import Swal from "sweetalert2";

const formData = ref({
  firstName: "",
  lastName: "",
  email: "",
  username: "",
  password: "",
  confirmPassword: "",
  city: "",
});

const errorMessage = ref("");
const successMessage = ref("");
const isLoading = ref(false);
const router = useRouter();
const store = useStore();

const isAuthenticated = computed(() => store.getters.isAuthenticated);

const handleSubmit = async () => {
  if (isAuthenticated.value) {
    Swal.fire({
      icon: "error",
      title: "Another user is already logged in.",
    });
    return;
  }
  errorMessage.value = "";
  successMessage.value = "";
  isLoading.value = true;

  try {
    const response = await axios.post(
      "http://localhost:8080/api/process-signup",
      formData.value
    );
    if (response.status === 201) {
      store.dispatch("login", response);
      Swal.fire({
        icon: "success",
        title: "User signed up successfully!",
        showConfirmButton: false,
        timer: 2000,
      });
      router.push("/home");
    }
  } catch (error) {
    if (error.response) {
      if (error.response.status === 400) {
        errorMessage.value =
          "Bad Request: " + (error.response.data || "Invalid data provided.");
      } else {
        errorMessage.value =
          "Error signing up: " +
          (error.response.data || "An unknown error occurred.");
      }
    } else {
      errorMessage.value = "Error signing up: " + error.message;
    }
  } finally {
    isLoading.value = false;
  }
};
</script>

<script></script>

<template>
  <section class="fullscreen">
    <div class="container centered-content">
      <div
        class="row justify-content-center align-items-center"
        style="width: 100%"
      >
        <div class="col-lg-6 col-md-8 col-sm-10">
          <div class="card bg-dark text-white shadow-lg">
            <div class="card-body p-5">
              <div class="text-center">
                <img
                  src="/apple-touch-icon.png"
                  alt="logo"
                  class="logo-img mb-4"
                />
                <h2 class="fw-bold text-uppercase mb-4" style="color: #f0a500">
                  Sign up now!
                </h2>
              </div>

              <!-- Sign-Up Form -->
              <form @submit.prevent="onSubmit">
                <div class="row">
                  <div class="col-md-6 mb-2">
                    <label for="firstName" class="form-label"
                      >First Name <span class="text-danger">*</span></label
                    >
                    <input
                      type="text"
                      id="firstName"
                      v-model="firstName"
                      class="form-control form-control-lg"
                    />
                  </div>
                  <div class="col-md-6 mb-3">
                    <label for="lastName" class="form-label"
                      >Last Name <span class="text-danger">*</span></label
                    >
                    <input
                      type="text"
                      id="lastName"
                      v-model="lastName"
                      class="form-control form-control-lg"
                    />
                  </div>
                </div>

                <div class="mb-3">
                  <label for="email" class="form-label"
                    >Email <span class="text-danger">*</span></label
                  >
                  <input
                    type="email"
                    id="email"
                    v-model="email"
                    class="form-control form-control-lg"
                  />
                </div>

                <div class="mb-3">
                  <label for="username" class="form-label"
                    >Username <span class="text-danger">*</span></label
                  >
                  <input
                    type="text"
                    id="username"
                    v-model="username"
                    class="form-control form-control-lg"
                  />
                </div>

                <div class="mb-3">
                  <label for="password" class="form-label"
                    >Password <span class="text-danger">*</span></label
                  >
                  <input
                    type="password"
                    id="password"
                    v-model="password"
                    class="form-control form-control-lg"
                  />
                </div>

                <div class="mb-3">
                  <label for="confirmPassword" class="form-label"
                    >Confirm Password <span class="text-danger">*</span></label
                  >
                  <input
                    type="password"
                    id="confirmPassword"
                    v-model="confirmPassword"
                    class="form-control form-control-lg"
                  />
                </div>

                <div class="mb-3">
                  <label for="city" class="form-label"
                    >City <span class="text-danger">*</span></label
                  >
                  <input
                    type="text"
                    id="city"
                    v-model="city"
                    class="form-control form-control-lg"
                  />
                </div>

                <button
                  type="submit"
                  class="btn btn-outline-light btn-lg w-100 mb-3"
                  :disabled="isSubmitting"
                >
                  Sign Up
                </button>
              </form>
              <div v-if="isLoading" class="loader-overlay">
                <div class="loader"></div>
              </div>

              <!-- Social Sign-Up -->
              <div class="text-center mt-3">
                <div class="oauth-card">
                  <p class="text-white-50">Or login with</p>
                  <div class="d-flex justify-content-center gap-3">
                    <a
                      href="http://localhost:8080/oauth2/authorization/google"
                      class="social-icon"
                    >
                      <FontAwesomeIcon :icon="faGoogle" size="lg" />
                      <span>Google</span>
                    </a>
                  </div>
                </div>
              </div>

              <div class="mt-4 text-center">
                <p class="text-white-50">
                  Already Registered?
                  <router-link to="/login" class="text-white fw-bold"
                    >Log In</router-link
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

<style scoped>
.fullscreen {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #1b263b;
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
  border-color: #f0a500;
  box-shadow: 0 0 5px #f0a500;
}

.btn {
  border: 1px solid #f0a500;
  color: #f0a500;
  transition: background-color 0.3s, color 0.3s;
}

.btn:hover {
  background-color: #f0a500;
  color: #212529;
}

.oauth-card {
  padding: 1rem;
  border-radius: 0.5rem;
  max-width: 200px;
  margin: 0 auto;
}

.social-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f0a500;
  color: #fff;
  font-size: 1.5rem;
  border-radius: 0.375rem;
  padding: 0.75rem 1rem;
  text-decoration: none;
  transition: all 0.3s ease;
}

.social-icon:hover {
  background-color: #fff;
  color: #f0a500;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.social-icon span {
  margin-left: 0.5rem;
  font-size: 1rem;
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

/* Loader Styles */
.loader-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.loader {
  border: 8px solid #f3f3f3;
  border-top: 8px solid #f0a500;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
