<template>
    <div class="events-container">
      <div class="header">
        <h1 class="title">Upcoming Events</h1>
        <p class="subtitle">
          Explore events related to books, book signings, and more!
        </p>
      </div>
      <div class="events-grid">
        <div v-for="event in events" :key="event.id" class="event-card">
          <img :src="event.image" alt="Event Image" class="event-image" />
          <div class="event-details">
            <h2 class="event-title">{{ event.title }}</h2>
            <p class="event-date">{{ event.date }}</p>
            <p class="event-location">{{ event.location }}</p>
            <button class="details-button" @click="viewDetails(event.id)">
              View Details
            </button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue";
  
  const events = ref([
    {
      id: 1,
      title: "Book Signing with Author John Doe",
      date: "Feb 15, 2025",
      location: "Central Library, New York",
      image: "/assets/images/bg_images/book_signing.jpg",
    },
    {
      id: 2,
      title: "Comic Con 2025",
      date: "Mar 10-12, 2025",
      location: "LA Convention Center, Los Angeles",
      image: "/assets/images/bg_images/book_club.jpeg",
    },
    {
      id: 3,
      title: "Fantasy Books Meetup",
      date: "Apr 5, 2025",
      location: "Rivertown Coffee, Chicago",
      image: "/assets/images/bg_images/comic.jpg",
    },
  ]);
  
  const viewDetails = (id) => {
    alert(`You clicked on event ID: ${id}`);
  };
  </script>
  
  <style scoped>
  .events-container {
    margin-top: 60px;
    background: linear-gradient(135deg, #1b263b, #3a506b);
    color: white;
    padding: 50px 20px;
    min-height: 100vh;
  }
  
  .header {
    text-align: center;
    margin-bottom: 30px;
  }
  
  .title {
    font-size: 2.5rem;
    color: #f0a500;
  }
  
  .subtitle {
    font-size: 1.2rem;
    color: #e0e0e0;
  }
  
  .events-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 20px;
  }
  
  .event-card {
    background-color: rgba(255, 255, 255, 0.1);
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }
  
  .event-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.4);
  }
  
  .event-image {
    width: 100%;
    height: 200px;
    object-fit: cover;
  }
  
  .event-details {
    padding: 20px;
    text-align: center;
  }
  
  .event-title {
    font-size: 1.5rem;
    color: #f0a500;
    margin-bottom: 10px;
  }
  
  .event-date,
  .event-location {
    font-size: 1rem;
    color: #e0e0e0;
    margin-bottom: 10px;
  }
  
  .details-button {
    background-color: #f0a500;
    color: #1b263b;
    border: none;
    padding: 10px 20px;
    border-radius: 8px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.2s ease;
  }
  
  .details-button:hover {
    background-color: #d18f00;
  }
  
  .details-button:active {
    transform: scale(0.98);
  }
  </style>
  