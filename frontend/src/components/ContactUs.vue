<template>
    <section class="contact-us-section py-5" style="margin-top: 5%;">
      <div class="container text-center">
        <h1 class="mb-4">Get in Touch</h1>
        <p class="mb-5">We’re here to assist you! Reach out through any of the following ways:</p>
  
        <div class="row g-4">
          <!-- Customer Care -->
          <div class="col-md-4">
            <div class="contact-card p-4 shadow">
              <FontAwesomeIcon :icon="faPencil" size="2x" style="color: #F0A500; cursor: pointer;" />
              <h5>Customer Care</h5>
              <p>For inquiries about orders, returns, and more.</p>
              <a href="tel:+18001234567" class="btn btn-primary mt-2">Call Us</a>
            </div>
          </div>
  
          <!-- Support -->
          <div class="col-md-4">
            <div class="contact-card p-4 shadow">
              <FontAwesomeIcon :icon="faGear" size="2x" style="color: #F0A500; cursor: pointer;" />
              <h5>Technical Support</h5>
              <p>Need help with our website or services?</p>
              <a href="mailto:support@nuance.com" class="btn btn-primary mt-2">Email Support</a>
            </div>
          </div>
  
          <!-- General Inquiries -->
          <div class="col-md-4">
            <div class="contact-card p-4 shadow">
              <FontAwesomeIcon :icon="faQuestion" size="2x" style="color: #F0A500; cursor: pointer;" />
              <h5>General Inquiries</h5>
              <p>Have a question? We’d love to hear from you.</p>
              <a href="mailto:info@nuance.com" class="btn btn-primary mt-2">Email Us</a>
            </div>
          </div>
        </div>
  
        <!-- Social Media -->
        <div class="mt-5">
          <h4>Follow Us</h4>
          <div class="social-icons mt-3">
            <a href="#" class="text-decoration-none me-3">
              <FontAwesomeIcon :icon="faFacebook" size="2x" style="color: #3b5998;" />
            </a>
            <a href="#" class="text-decoration-none me-3">
              <FontAwesomeIcon :icon="faInstagram" size="2x" style="color: #E1306C;" />
            </a>
            <a href="#" class="text-decoration-none me-3">
              <FontAwesomeIcon :icon="faXTwitter" size="2x" style="color: #1DA1F2;" />
            </a>
            <a href="#" class="text-decoration-none">
              <FontAwesomeIcon :icon="faLinkedin" size="2x" style="color: #0077B5;" />
            </a>
          </div>
        </div>
      </div>
    </section>
  </template>
  
  <script setup>
  import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
  import { faFacebook, faInstagram, faXTwitter, faLinkedin } from '@fortawesome/free-brands-svg-icons';
  import { faGear, faPencil, faQuestion } from '@fortawesome/free-solid-svg-icons';
  </script>
  
  <style scoped>
  .contact-us-section {
    color: #1B263B;
    font-family: 'Poppins', sans-serif;
  }
  
  .contact-us-section h1 {
    font-size: 2.5rem;
  }
  
  .contact-us-section p {
    font-size: 1.2rem;
  }
  
  .contact-card {
    background: #f0f0f0;
    border-radius: 10px;
    text-align: center;
    color: #333;
  }
  
  .contact-card h5 {
    font-size: 1.5rem;
    margin-bottom: 10px;
  }
  
  .contact-card img.contact-icon {
    width: 60px;
    height: 60px;
  }
  
  .contact-card a.btn {
    background-color: #F0A500;
    color: #fff;
    text-transform: uppercase;
    font-weight: bold;
    transition: transform 0.3s ease, background-color 0.3s ease;
  }
  
  .contact-card a.btn:hover {
    background-color: #1B263B;
    transform: scale(1.1);
  }
  
  .social-icons a {
    transition: transform 0.3s ease;
  }
  
  .social-icons a:hover {
    transform: scale(1.2);
  }
  </style>
  