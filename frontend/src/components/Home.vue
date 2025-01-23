<template>
  <main class="container" style="padding-top: 10%;">

    <div id="carouselExampleAutoplaying" class="carousel slide banner-carousel justify-content-center" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <div class="banner-image-wrapper">
            <img src="/assets/images/bg_images/31286.jpg" class="d-block w-100 banner-image" alt="Image1">
          </div>
        </div>
        <div class="carousel-item">
          <div class="banner-image-wrapper">
            <img src="/assets/images/bg_images/gift_packages.jpg" class="d-block w-100 banner-image" alt="Image2">
          </div>
        </div>
        <div class="carousel-item">
          <div class="banner-image-wrapper">
            <img src="/assets/images/bg_images/first_time_buyers.jpg" class="d-block w-100 banner-image" alt="Image2">
          </div>
        </div>
        <div class="carousel-item">
          <div class="banner-image-wrapper">
            <img src="/assets/images/bg_images/best_seller.jpg" class="d-block w-100 banner-image" alt="Image2">
          </div>
        </div>
        <div class="carousel-item">
          <div class="banner-image-wrapper">
            <img src="/assets/images/bg_images/well_known.jpg" class="d-block w-100 banner-image" alt="Image3">
          </div>
        </div>
      </div>
    </div>

    <div class="extra-content">
      <div class="content-wrapper">
        <h2>Discover Our Latest Collection</h2>
        <p>Explore the best of our curated selection of books and products. From thrilling bestsellers to exclusive gift packages, find something for everyone.</p>
        <button class="cta-button">Shop Now</button>
      </div>
    </div>

    <div class="books-section justify-content-center">
      <h2>Featured Books</h2>
      <div v-if="loading" class="loading-message">Loading books...</div>
      <div v-if="error" class="error-message">Oops! Something went wrong while fetching the books.</div>
      <div v-if="books.length > 0" class="book-cards-container">
        <div v-for="book in books" :key="book.id" class="book-card">
          <router-link :to="`/books/${encodeURIComponent(book.name.toLowerCase())}?id=${encryptData(book.id)}`" class="card-link">
          <img :src="`/assets/images/${book.image}`" alt="Book Image" class="book-image" />
          <div class="book-details">
            <h3 class="book-title">{{ book.name }}</h3>
            <p class="book-author">{{ book.author }}</p>
            <p class="book-price">${{ book.price }}</p>
          </div>
        </router-link>
        </div>
      </div>
      <div v-if="books.length === 0" class="no-books">No books available at the moment.</div>
    </div>
    
    <div class="testimonial-section">
      <h2>What Our Customers Say</h2>
      <div class="testimonial">
        <p>"A wonderful place to discover new books. The recommendations are fantastic!"</p>
        <p class="author">- Sarah M.</p>
      </div>
      <div class="testimonial">
        <p>"Fast shipping and great customer service. Highly recommend!"</p>
        <p class="author">- John D.</p>
      </div>
    </div>
  </main>
</template>

<script>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import CryptoJS from 'crypto-js'; // Ensure CryptoJS is imported

export default {
  setup() {
    // Declare reactive variables
    const books = ref([]);
    const loading = ref(false);
    const error = ref(false);

    // Fetch books data when component is mounted
    onMounted(fetchRandomBooks);

    // Function to fetch random books
    async function fetchRandomBooks() {
      loading.value = true;
      try {
        const response = await axios.get('http://localhost:8080/api/books');
        const booksData = response.data;
        const randomBooks = getRandomBooks(booksData, 4);
        books.value = randomBooks;
        loading.value = false;
      } catch (err) {
        console.error("There was an error fetching the books:", err);
        error.value = true;
        loading.value = false;
      }
    }

    // Function to get random books
    function getRandomBooks(books, numBooks) {
      const randomBooks = [];
      const booksCopy = [...books];
      for (let i = 0; i < numBooks; i++) {
        const randomIndex = Math.floor(Math.random() * booksCopy.length);
        randomBooks.push(booksCopy.splice(randomIndex, 1)[0]);
      }
      return randomBooks;
    }

    // Function to encrypt data
    function encryptData(data) {
      const secretKey = import.meta.env.VITE_BOOK_ID_ENCRYPTION_KEY;
      return CryptoJS.AES.encrypt(JSON.stringify(data), secretKey).toString();
    }

    // Return all the necessary methods and refs if you want them to be accessible in the template
    return {
      books,
      loading,
      error,
      fetchRandomBooks,
      getRandomBooks,
      encryptData
    };
  }
};
</script>



<style scoped>
html, body {
  height: 100%;
  margin: 0;
  overflow-x: hidden;
}

.container {
  display: flex;
  flex-direction: column;
  min-height: 100%;
}

.banner-carousel {
  height: 600px;
  overflow: hidden;
}

.card-link {
  text-decoration: none;
  color: inherit;
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

.card-link:focus,
.card-link:hover {
  text-decoration: none;
}

.banner-image-wrapper {
  position: relative;
  height: 100%;
  width: 100%;
  overflow: hidden;
}

.banner-image {
  object-fit: cover;
  height: 100%;
  width: 100%;
  transition: transform 0.5s ease; 
}

.carousel-item {
  position: relative;
  height: 100%;
}

.banner-image-wrapper {
  position: relative;
  height: 100%;
  width: 100%;
  overflow: hidden;
}

.banner-image {
  object-fit: cover;
  height: 100%;
  width: 100%;
}

.carousel-item::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 150px;
  background: linear-gradient(to bottom, rgba(0, 0, 0, 0) 0%, #f7f7f7 100%);
  pointer-events: none;
}


.carousel-inner {
  height: 100%;
}

.extra-content {
  padding-top: 20px;
  padding-bottom: 40px;
  text-align: center;
}

.content-wrapper {
  max-width: 1200px;
  margin: 0 auto;
  text-align: center;
}

.content-wrapper h2 {
  font-size: 2rem;
  color: #1B263B;
  margin-bottom: 15px;
}

.content-wrapper p {
  font-size: 1.1rem;
  color: #555;
  margin-bottom: 20px;
  line-height: 1.6;
}

.cta-button {
  background-color: #F0A500;
  color: #fff;
  border: none;
  padding: 12px 24px;
  font-size: 1.2rem;
  cursor: pointer;
  border-radius: 5px;
  text-transform: uppercase;
  transition: background-color 0.3s;
}

.cta-button:hover {
  background-color: #1B263B;
}

.books-section {
  padding: 40px 0;
  background-color: #f9f9f9;
}

.books-section h2 {
  font-size: 2rem;
  color: #1B263B;
  text-align: center;
  margin-bottom: 30px;
}

.loading-message,
.error-message,
.no-books {
  text-align: center;
  font-size: 1.2rem;
  color: #f0a500;
  margin-bottom: 20px;
}

.book-cards-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  padding: 0 10px;
}

.book-card {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  text-align: center;
  transition: transform 0.3s ease;
}

.book-card:hover {
  transform: translateY(-10px);
}

.book-image {
  width: 100%;
  height: max-content;
  object-fit: cover;
  border-bottom: 2px solid #f0a500;
}

.book-details {
  padding: 20px;
}

.book-title {
  font-size: 1.2rem;
  color: #333;
  font-weight: bold;
  margin-bottom: 10px;
}

.book-author {
  font-size: 1rem;
  color: #777;
  margin-bottom: 10px;
}

.book-price {
  font-size: 1.2rem;
  color: #f0a500;
  margin-bottom: 15px;
}

.testimonial-section {
  padding: 40px 20px;
  background-color: #f7f7f7;
  text-align: center;
}

.testimonial-section h2 {
  font-size: 2rem;
  color: #1B263B;
  margin-bottom: 30px;
}

.testimonial {
  margin-bottom: 20px;
}

.testimonial p {
  font-size: 1.2rem;
  color: #555;
}

.author {
  font-size: 1rem;
  color: #1B263B;
  font-weight: bold;
}

.searchBar-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  margin: 0 20px;
}

.searchBar-container input {
  width: 90%;
  height: 50px;
  border-radius: 5px 0 0 5px;
  padding: 0 10px;
  border: 1px solid #ccc;
  outline: none;
  transition: border-color 0.3s ease;
}

.searchBar-container input:focus {
  border-color: #F0A500;
}

.searchBar-container span {
  background-color: #F0A500;
  width: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  border-radius: 0 10px 10px 0;
  transition: background-color 0.3s ease;
}

.searchBar-container span:hover {
  background-color: #1B263B;
}
</style>
