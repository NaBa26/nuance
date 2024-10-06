<script setup>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faGoogle } from '@fortawesome/free-brands-svg-icons';
import { faMeta } from '@fortawesome/free-brands-svg-icons';
</script>

<template>
<section class="text-center text-lg-start" style="background-color: #1B263B;">
    <div class="container py-4">
      <div class="row g-0 align-items-center">
        <div class="col-lg-5 mb-5 mb-lg-0">
          <div class="card cascading-right bg-body-tertiary" style="backdrop-filter: blur(30px);">
            <div class="card-body p-5 shadow-5 text-center" style="background-color: #212529;">
              <h2 class="fw-bold mb-5" style="color: #F0A500;">Sign up now</h2>
              <form>
                <div class="row">
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                      <label class="form-label" for="firstName">First name</label>
                      <input type="text" id="firstName" class="form-control" required />
                    </div>
                  </div>
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                      <label class="form-label" for="lastName">Last name</label>
                      <input type="text" id="lastName" class="form-control" required />
                    </div>
                  </div>
                </div>
  
                <!-- Email input -->
                <div class="form-outline mb-4">
                  <label class="form-label" for="email">Email address</label>
                  <input type="email" id="email" class="form-control" required />
                </div>
  
                <!-- Username input -->
                <div class="form-outline mb-4">
                  <label class="form-label" for="username">Username</label>
                  <input type="text" id="username" class="form-control" required />
                </div>
  
                <!-- Password input -->
                <div class="form-outline mb-4">
                  <label class="form-label" for="password">Password</label>
                  <input type="password" id="password" class="form-control" required />
                </div>
  
                <!-- Confirm Password input -->
                <div class="form-outline mb-4">
                  <label class="form-label" for="confirmPassword">Confirm Password</label>
                  <input type="password" id="confirmPassword" class="form-control" required />
                </div>
  
                <!-- City input -->
                <div class="form-outline mb-4">
                  <label class="form-label" for="city">City</label>
                  <input type="text" id="city" class="form-control" required />
                </div>
  
  
                <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-5" type="submit">SignUp</button>

            <div class="d-flex justify-content-center mt-4">
                <div class="auth-footer">
                    <h6>Or login with</h6>
                        <a href="#" class="text-decoration-none me-2"><FontAwesomeIcon :icon="faGoogle" size="xl" /></a>
                        <a href="#" class="text-decoration-none me-2"><FontAwesomeIcon :icon="faMeta" size="xl" /></a>
                </div>
            </div>
              </form>
            </div>
          </div>
        </div>
  
        <div class="col-lg-6 mb-5 mb-lg-0">
          <img src="/public/assets/images/bg_images/sign_up.jpg" class="w-100 rounded-4 shadow-4"
            alt="" />
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>

.cascading-right {
  margin-right: -50px;
}

@media (max-width: 991.98px) {
  .cascading-right {
    margin-right: 0;
  }
}

form {
  padding: 0.5rem 0.75rem;
  border-radius: 0.5rem;
  color: #fff;
}

form input {
    background-color: #2c2c2c;
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
</style>
  