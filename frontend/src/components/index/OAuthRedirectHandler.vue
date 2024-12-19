<template>
    <div class="oauth-redirect-container">
      <div class="message-box">
        <FontAwesomeIcon :icon="faSpinner" v-if="loading" spin size="3x" class="loading-icon" />
        <p v-if="loading" class="loading-text">Logging you in, please wait...</p>
        <p v-if="error" class="error-text">{{ error }}</p>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useRouter } from 'vue-router';
  import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
  import { faSpinner } from '@fortawesome/free-solid-svg-icons';
  
  const loading = ref(true);
  const error = ref(null);
  const router = useRouter();
  
  const checkUserAuthentication = async () => {
    try {
      const response = await fetch('/api/check-auth'); 
      const data = await response.json();
      return data.authenticated;
    } catch {
      throw new Error('Failed to check authentication.');
    }
  };
  
  onMounted(async () => {
    try {
      const isAuthenticated = await checkUserAuthentication();
      if (isAuthenticated) {
        router.push('/home'); 
      } else {
        throw new Error('Authentication failed. Please try logging in again.');
      }
    } catch (err) {
      error.value = err.message;
      loading.value = false;
    }
  });
  </script>
  
  <style scoped>
  .oauth-redirect-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #1B263B;
  }
  
  .message-box {
    text-align: center;
    padding: 20px;
    border: 1px solid #F0A500;
    border-radius: 10px;
    background-color: #FFFFFF;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }
  
  .loading-icon {
    color: #F0A500;
    margin-bottom: 20px;
  }
  
  .loading-text {
    font-size: 1.5em;
    color: #1B263B;
  }
  
  .error-text {
    font-size: 1.5em;
    color: red;
  }
  </style>
  