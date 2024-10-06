<script setup>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faGoogle } from '@fortawesome/free-brands-svg-icons';
import { faMeta } from '@fortawesome/free-brands-svg-icons';
</script>

<template>
<section class="vh-100" style="background-color: #1B263B;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card bg-dark text-white" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
            <h2 class="fw-bold mb-4 text-uppercase">Login</h2>
            <p class="text-white-50 mb-5">Please enter your login and password!</p>

            <form>
            <div class="form-outline form-white mb-4">
              <label class="form-label" for="typeEmailX">Username</label>
              <input type="text" id="typeEmailX" class="form-control form-control-lg" required />
            </div>

            <div class="form-outline form-white mb-4">
              <label class="form-label" for="typePasswordX">Password</label>
              <input type="password" id="typePasswordX" class="form-control form-control-lg" required />
            </div>
            
            <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>

            <div class="d-flex justify-content-center mt-4">
                <div class="auth-footer">
                    <h6>Or login with</h6>
                        <a href="#" class="text-decoration-none me-2"><FontAwesomeIcon :icon="faGoogle" size="xl" /></a>
                        <a href="#" class="text-decoration-none me-2"><FontAwesomeIcon :icon="faMeta" size="xl" /></a>
                </div>
            </div>

        </form>

            <div class="mt-3">
              <p class="mb-5">Don't have an account? <router-link to="/signup" class="text-white-50 fw-bold">Sign Up</router-link></p>
            </div>


            <p class="small mb-0"><a class="text-white-50" href="#!">Forgot password?</a></p>

          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</template>

<style scoped>
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
</style>
