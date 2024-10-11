<script setup>
import { onMounted, ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';

const route = useRoute();
const book = ref([]);

const trimBookName = (name) => encodeURIComponent(name.trim());

const fetchBookDetails = async (name, id) => {
  if (id && name) {
    try {
      const url = `http://localhost:8080/api/books/${trimBookName(name)}?id=${id}`;
      const response = await axios.get(url);
      book.value = response.data;
      console.log(book.value);
    } catch (error) {
      console.error('Error fetching book details:', error);
    }
  }
};

onMounted(() => {
  const bookName = decodeURIComponent(route.params.bookName);
  const bookId = route.query.id;
  fetchBookDetails(bookName, bookId);
});

watch(
  () => route.params,
  (newParams) => {
    const bookName = decodeURIComponent(newParams.bookName);
    const bookId = route.query.id;
    fetchBookDetails(bookName, bookId);
  },
  { immediate: true }
);

</script>

<template>
    <div class="main-content">
      <div class="left-column">
        <img :src="`/assets/images/${book.image}`" :alt="book.name" class="book-image">
      </div>
      <div class="middle-column">
        <h1>{{ book.name }}</h1>
        <p class="author">{{ book.author }}</p>
        <div class="ratings">
          <img src="https://via.placeholder.com/100x20" alt="Ratings">
          <span>(150 customer reviews)</span>
        </div>
        <p class="price">${{ book.price }}</p>
        <ul class="features">
          <li>Format: Hardcover</li>
          <li>Language: English</li>
          <li>Published Date: {{ book.publishedDate }}</li>
          <li>Genre: {{ book.genre }}</li>
        </ul>
      </div>
      <div class="right-column">
        <p class="price">${{ book.price }}</p>
        <button>Add to Bag</button>
        <button class="buy-now">Buy Now</button>
        <div class="delivery-info">
          <p>Delivery: Free shipping on orders over $25</p>
          <p>In Stock. Ships from and sold by BookStore.</p>
        </div>
      </div>
    </div>
    <div class="product-details">
      <div class="product-description">
        <h2>Product Description</h2>
        <p>This is a detailed description of the book. It provides information about the content, storyline, and other relevant details.</p>
      </div>
      <div class="customer-reviews">
        <h3>Customer Reviews</h3>
        <p>This is where customer reviews and ratings would be displayed, with options to filter by rating or most recent.</p>
      </div>
    </div>
  </template>
  

  <style scoped>
  body {
    font-family: 'Arial', sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4; /* Soft background color */
  }
  
  .main-content {
    display: flex;
    padding: 20px;
    gap: 40px;
    background-color: #ffffff; /* White background for main content */
    border-radius: 8px; /* Rounded corners */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
  }
  
  .main-content .left-column img {
    width: 400px;
    height: auto;
    border: 1px solid #ccc; /* Light border */
    border-radius: 8px; /* Rounded corners for images */
  }
  
  .middle-column {
    flex-grow: 2;
  }
  
  .middle-column h1 {
    font-size: 30px; /* Larger font for title */
    color: #1B263B; /* Primary dark color */
    margin-bottom: 10px; /* Spacing below title */
  }
  
  .middle-column .author {
    color: #F0A500; /* Primary accent color */
    margin-top: -5px; /* Minor adjustment for positioning */
    font-style: italic; /* Italic for author name */
  }
  
  .middle-column .price {
    font-size: 28px; /* Larger font for price */
    color: #B12704; /* Original price color */
    margin: 10px 0;
  }
  
  .middle-column .ratings {
    display: flex;
    align-items: center;
    gap: 10px;
  }
  
  .middle-column .ratings span {
    font-size: 14px;
    color: #555;
  }
  
  .middle-column .features {
    list-style: none;
    padding: 0;
    margin: 10px 0; /* Margin for spacing */
  }
  
  .middle-column .features li {
    margin-bottom: 8px; /* Spacing between features */
  }
  
  .right-column {
    max-width: 300px;
    border: 1px solid #ddd;
    padding: 20px;
    background-color: #F0A500; /* Primary accent color */
    border-radius: 8px; /* Rounded corners */
    color: #1B263B; /* Primary dark color */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
  }
  
  .right-column .price {
    font-size: 22px; /* Price font size */
    margin-bottom: 15px;
  }
  
  .right-column button {
    width: 100%;
    padding: 12px;
    background-color: #1B263B; /* Dark button background */
    color: white;
    border: none;
    margin-bottom: 10px;
    cursor: pointer;
    border-radius: 8px; /* Rounded corners */
    transition: background-color 0.3s; /* Smooth transition */
  }
  
  .right-column button:hover {
    background-color: #0f1e28; /* Darker shade on hover */
  }
  
  .right-column button.buy-now {
    background-color: #f0c14b; /* Keep original for buy now */
  }
  
  .product-details {
    margin-top: 40px;
    padding: 0 20px;
  }
  
  .product-description,
  .customer-reviews {
    padding: 15px;
    background-color: #ffffff; /* White background for details */
    margin-bottom: 20px;
    border-radius: 8px; /* Rounded corners */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
  }
  
  .product-description h2,
  .customer-reviews h3 {
    color: #1B263B; /* Primary dark color */
  }
  
  .customer-reviews h3 {
    margin-bottom: 10px; /* Space below heading */
  }
  </style>
  