<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
import { useStore } from "vuex";
import Swal from "sweetalert2";

const user = ref({});
const store = useStore();

const editProfile = () => {
  Swal.fire({
    icon: "info",
    title: "Edit Profile",
    text: "This feature is not yet implemented.",
  });
};

onMounted(async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/current-user?id=${store.getters.userId}`,
      { withCredentials: true }
    );
    user.value = response.data;
    console.log(user.value);
  } catch (error) {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "Failed to fetch user data!",
      footer: "Please try again later",
    });
    window.location.href = "/home";
  }
});
</script>

<template>
  <div class="profile-container">
    <div class="profile-overlay">
      <div class="profile-card">
        <img
          src="/assets/images/bg_images/user.jpg"
          alt="Profile Picture"
          class="profile-picture"
        />
        <h2 class="profile-name">{{ user.username }}</h2>
        <p class="profile-email">{{ user.email }}</p>
        <p class="profile-info">First Name: {{ user.firstName }}</p>
        <p class="profile-info">Last Name: {{ user.lastName }}</p>
        <p class="profile-info">City: {{ user.city }}</p>
        <button class="edit-button" @click="editProfile">Edit Profile</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.profile-container {
  background: linear-gradient(135deg, #1b263b, #3a506b);
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
}

.profile-overlay {
  background-color: rgba(27, 38, 59, 0.95);
  padding: 50px;
  border-radius: 20px;
  color: white;
  width: 100%;
  max-width: 600px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5);
  text-align: center;
}

.profile-card {
  background-color: rgba(255, 255, 255, 0.1);
  padding: 40px;
  border-radius: 15px;
  text-align: center;
}

.profile-picture {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  margin-bottom: 20px;
  object-fit: cover;
  border: 3px solid #f0a500;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.profile-name {
  font-size: 1.8rem;
  color: #f0a500;
  margin-bottom: 15px;
}

.profile-email {
  font-size: 1.2rem;
  color: #e0e0e0;
  margin-bottom: 20px;
}

.profile-info {
  font-size: 1.1rem;
  color: #ffffff;
  margin-bottom: 15px;
}

.edit-button {
  background-color: #f0a500;
  color: #1b263b;
  border: none;
  padding: 12px 30px;
  border-radius: 8px;
  cursor: pointer;
  font-size: 1rem;
  font-weight: bold;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.edit-button:hover {
  background-color: #d18f00;
  transform: translateY(-2px);
}

.edit-button:active {
  transform: translateY(1px);
}
</style>

