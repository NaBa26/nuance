<template>
    <div class="profile-container">
      <div class="profile-overlay">
        <div class="profile-card">
          <img class="profile-picture" :src="user.profileImage" alt="Profile Picture">
          <h2 class="profile-name">{{ user.name }}</h2>
          <p class="profile-email">{{ user.email }}</p>
          <button class="edit-button" @click="editProfile">Edit Profile</button>
        </div>
        
        <div class="profile-details">
          <h3 class="section-title">Order History</h3>
          <div v-if="orders.length > 0" class="order-list">
            <div v-for="order in orders" :key="order.id" class="order-item">
              <p><strong>Order #:</strong> {{ order.id }}</p>
              <p><strong>Date:</strong> {{ order.date }}</p>
              <p><strong>Total:</strong> ${{ order.total }}</p>
              <button class="details-button">View Details</button>
            </div>
          </div>
          <div v-else class="no-orders">
            <p>You haven't placed any orders yet.</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        user: {
          name: "John Doe",
          email: "john.doe@example.com",
          profileImage: "assets/images/profile_pic.jpeg"
        },
        orders: [
          { id: '12345', date: '2024-09-10', total: '150.00' },
          { id: '67890', date: '2024-10-05', total: '230.00' }
        ]
      };
    },
    methods: {
      editProfile() {
      }
    }
  };
  </script>
  
  <style scoped>
  .profile-container {

    background-size: cover;
    background-position: center;
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .profile-overlay {
    background-color: rgba(27, 38, 59, 0.8);
    padding: 30px;
    border-radius: 12px;
    color: white;
    width: 100%;
    max-width: 900px;
  }
  
  .profile-card {
    background-color: rgba(255, 255, 255, 0.1);
    padding: 20px;
    border-radius: 8px;
    text-align: center;
    margin-bottom: 20px;
  }
  
  .profile-picture {
    border-radius: 50%;
    width: 150px;
    height: 150px;
    object-fit: cover;
    margin-bottom: 10px;
  }
  
  .profile-name {
    font-size: 1.8rem;
    color: #F0A500;
  }
  
  .profile-email {
    font-size: 1.2rem;
    margin-bottom: 20px;
  }
  
  .edit-button {
    background-color: #F0A500;
    color: #1B263B;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1rem;
  }
  
  .profile-details {
    margin-top: 30px;
  }
  
  .section-title {
    font-size: 1.5rem;
    margin-bottom: 15px;
  }
  
  .order-list {
    background-color: rgba(255, 255, 255, 0.1);
    padding: 15px;
    border-radius: 8px;
    color: white;
  }
  
  .order-item {
    margin-bottom: 10px;
    padding: 10px;
    border-bottom: 1px solid #F0A500;
  }
  
  .details-button {
    background-color: #F0A500;
    color: #1B263B;
    border: none;
    padding: 5px 10px;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .no-orders {
    background-color: rgba(240, 165, 0, 0.8);
    padding: 20px;
    border-radius: 8px;
    text-align: center;
  }
  </style>
  