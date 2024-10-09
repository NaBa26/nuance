<template>
    <div class="not-found-container">
      <h1 class="not-found-title">404</h1>
      <p class="not-found-message">Oops! The page you're looking for can't be found.</p>
      <router-link to="/home" class="home-link">Back to Home</router-link>
    </div>
  </template>
  
  <style scoped>
  .not-found-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
    background-color: #1B263B; /* Dark blue */
    color: white;
    text-align: center;
    padding: 20px;
  }
  
  .not-found-title {
    font-size: 8rem;
    margin: 0;
    color: #F0A500; /* Gold */
  }
  
  .not-found-message {
    font-size: 1.5rem;
    margin: 20px 0;
  }
  
  .home-link {
    font-size: 1.2rem;
    color: #F0A500; /* Gold */
    text-decoration: none;
    padding: 10px 20px;
    border: 2px solid #F0A500; /* Gold border */
    border-radius: 5px;
    transition: all 0.3s ease;
  }
  
  .home-link:hover {
    background-color: #F0A500; /* Gold background on hover */
    color: #1B263B; /* Dark blue text on hover */
  }
  </style>
  