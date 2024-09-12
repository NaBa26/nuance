<template>
  <div>
    <h3 style="padding:1%">Search Results</h3>
    <hr>
    <!-- Container for all book cards -->
    <div class="cards-container">
      <div v-for="book in paginatedBooks" :key="book.id" class="book-card">
        <router-link class="book-image-container" :to="`/books/${encodeURIComponent(book.name.toLowerCase().replace(/\s+/g, '-'))}/?id=${book.id}`">
          <img :src="`/assets/images/${book.image}`" :alt="book.name" class="book-image">
        </router-link>
        <div class="book-description">
          <router-link class="book-title" :to="`/books/${encodeURIComponent(book.name.toLowerCase().replace(/\s+/g, '-'))}/?id=${book.id}`">{{ book.name }}</router-link>
          <div class="book-author"><span>by </span><b>{{ book.author }}</b></div>
          <div class="book-price">$ {{ book.price }}</div>
          <div class="book-original-price">$ {{ getOriginalPrice(book.price) }}</div>
          <div class="book-discount">({{ calculateDiscount(book.price, getOriginalPrice(book.price)) }}% off)</div>
          <button class="add-to-cart-btn">Add to cart</button>
        </div>
      </div>
    </div>

    <!-- Pagination Controls -->
    <div class="pagination">
      <button @click="prevPage" :disabled="currentPage === 1">Previous</button>
      <span>Page {{ currentPage }} of {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages">Next</button>
    </div>
  </div>
</template>


<script setup>
import { ref, computed, onMounted } from 'vue';
import axios from 'axios';

// State to store books and pagination variables
const books = ref([]);
const currentPage = ref(1);
const entriesPerPage = 15;

// Fetch books data
onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/books');
    books.value = response.data;
  } catch (error) {
    console.error('Error fetching books:', error);
  }
});

// Function to calculate a random price between 5% and 15% more than the current price
const getOriginalPrice = (price) => {
  const randomPercentage = Math.random() * (15 - 5) + 5;
  const originalPrice = price + (price * randomPercentage / 100);
  return originalPrice.toFixed(2);
};

// Function to calculate discount percentage
const calculateDiscount = (price, originalPrice) => {
  const discount = ((originalPrice - price) / originalPrice) * 100;
  return discount.toFixed(0);
};

// Computed property to get the books for the current page
const paginatedBooks = computed(() => {
  const start = (currentPage.value - 1) * entriesPerPage;
  const end = start + entriesPerPage;
  return books.value.slice(start, end);
});

// Total number of pages
const totalPages = computed(() => {
  return Math.ceil(books.value.length / entriesPerPage);
});

// Method to navigate to the next page
const nextPage = () => {
  if (currentPage.value < totalPages.value) {
    currentPage.value++;
  }
};

// Method to navigate to the previous page
const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};
</script>

<style scoped>
.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  padding: 7%;
}

.pagination button {
  background-color: #ff9900;
  border: none;
  padding: 8px 12px;
  color: white;
  cursor: pointer;
  border-radius: 4px;
  margin: 0 10px;
}

.pagination button:disabled {
  background-color: #d3d3d3;
  cursor: not-allowed;
}

.cards-container {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  /* Space between cards */
  justify-content: space-evenly;
  /* Distribute cards evenly */
  font-family: 'Merriweather', serif;
}

.book-card {
  background-color: white;
  border: 1px solid #dcdcdc;
  border-radius: 8px;
  padding: 16px;
  width: 200px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);

  display: flex;
  flex-direction: column;
  /* Arrange content vertically */
  justify-content: space-between;
  /* Distribute space between children */
  align-items: center;
  text-align: center;
}

.book-description {
  flex-grow: 1;
  /* Make the description area grow and take up available space */
  margin-top: 10px;
  display: flex;
  flex-direction: column;
}

.add-to-cart-btn {
  background-color: #1B263B;
  ;
  border: none;
  padding: 10px 20px;
  color: white;
  cursor: pointer;
  border-radius: 4px;
  font-size: 1em;

  /* Positioning the button */
  align-self: center;
  /* Center the button horizontally */
  margin-top: auto;
  /* Ensure it's pushed to the bottom */
}

.add-to-cart-btn:hover {
  background-color: #F0A500;
}


.book-image-container {
  cursor: pointer;
  width: 100%;
  height: 250px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.book-image {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
  border-radius: 4px;
  background-color: whitesmoke;
}


.book-title {
  font-size: 1.2em;
  font-weight: bold;
  margin-bottom: 5px;
  color: #333;
}

.book-title:hover {
  color: #F0A500;
}

.book-author,
.book-rating,
.book-reviews,
.book-price,
.book-original-price,
.book-discount,
.book-delivery,
.book-seller {
  font-size: 0.9em;
  margin-bottom: 5px;
}

.book-price {
  color: #F0A500;
  font-weight: bold;
}

.book-original-price {
  text-decoration: line-through;
  color: gray;
}

@media (max-width: 1200px) {
  .book-card {
    width: 180px;
    /* Adjust card size for smaller screens */
  }
}

@media (max-width: 768px) {
  .book-card {
    width: 45%;
    /* Two cards per row on tablet screens */
  }
}

@media (max-width: 480px) {
  .book-card {
    width: 100%;
    /* One card per row on mobile screens */
  }
}
</style>
