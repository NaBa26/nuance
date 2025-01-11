<script setup>
import { onMounted, ref, watch } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import { computed } from "vue";
import { useStore } from "vuex";
import CryptoJS from 'crypto-js';

function encryptData(data) {
  const secretKey = import.meta.env.VITE_BOOK_ID_ENCRYPTION_KEY;
  return CryptoJS.AES.encrypt(JSON.stringify(data), secretKey).toString();
}


function decryptData(data) {
  const encryptionKey = import.meta.env.VITE_BOOK_ID_ENCRYPTION_KEY;
  const bytes = CryptoJS.AES.decrypt(data, encryptionKey);
  const decryptedData = bytes.toString(CryptoJS.enc.Utf8);
  return JSON.parse(decryptedData);
}

const getOriginalPrice = (currentPrice) => {
  const randomPercentage = Math.random() * (10 - 5) + 5;
  return (currentPrice * (1 + randomPercentage / 100)).toFixed(2);
};

const calculateDiscount = (currentPrice, originalPrice) => {
  const discount = ((originalPrice - currentPrice) / originalPrice) * 100;
  return discount.toFixed(2);
};

const booksWithPriceDetails = computed(() =>
  books.value.map((book) => {
    const originalPrice = parseFloat(getOriginalPrice(book.price));
    const discount = calculateDiscount(book.price, originalPrice);
    return { ...book, originalPrice, discount };
  })
);

const books = ref([]);
const route = useRoute();

const trimBookName = (name) => {
  return encodeURIComponent(name.trim());
};

const addToCart = async (bookId) => {
  try {
    const response = await axios.post(
      `http://localhost:8080/api/cart/add/${bookId}?id=${store.getters.userId}`
    );
    console.log(response.data);
    alert("Book added to cart successfully.");
  } catch (error) {
    alert("Failed to add book to cart. Please try again later.");
  }
};

const errorMessage = ref("");

const store = useStore();
const isAuthenticated = computed(() => store.getters.isAuthenticated);

onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/books`);
    books.value = response.data;
  } catch (error) {
    errorMessage.value = "Failed to fetch books. Please try again later.";
    console.error("Error fetching books:", error);
  }
});

watch(
  () => route.params,
  async (newParams) => {
    if (newParams.bookName && newParams.bookId) {
      const bookName = trimBookName(decodeURIComponent(newParams.bookName));
      const bookId = decryptData(newParams.bookId);
      try {
        const url = `http://localhost:8080/api/books/${bookName}?id=${bookId}`;
        const response = await axios.get(url);
        books.value = response.data;
      } catch (error) {
        errorMessage.value = "Failed to fetch book details.";
        console.error("Error fetching book details:", error);
      }
    }
  },
  { immediate: true }
);
</script>

<template>
  <div style="margin-top: 3em; margin-bottom: 3em">
    <h3 style="padding: 5px">Search Results</h3>
    <div v-if="errorMessage">{{ errorMessage }}</div>
    <hr />
    <div v-if="books.length > 0" class="cards-container">
      <div
        v-for="book in booksWithPriceDetails"
        :key="book.id"
        class="book-card"
      >
        <!-- Router link wrapping the card -->
        <router-link
          :to="`/books/${encodeURIComponent(book.name.toLowerCase())}?id=${
            encryptData(book.id)
          }`"
          class="card-link"
        >
          <div class="book-image-container">
            <img
              :src="`/assets/images/${book.image}`"
              :alt="book.name"
              class="book-image"
            />
          </div>
          <div class="book-description">
            <div class="book-title">{{ book.name }}</div>
            <div class="book-author">
              <span>by </span><b>{{ book.author }}</b>
            </div>
            <div class="book-price">$ {{ book.price }}</div>
            <div class="book-original-price">$ {{ book.originalPrice }}</div>
            <div class="book-discount">({{ book.discount }}% off)</div>
          </div>
        </router-link>
        <!-- Add to Cart button -->
         <button class="add-to-cart-btn" @click= "isAuthenticated ? addToCart(book.id) : routeFunction('login')">Add to Cart</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.cards-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  justify-content: space-evenly;
  font-family: "Merriweather", serif;
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
  justify-content: space-between;
  align-items: center;
  text-align: center;
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

.book-description {
  flex-grow: 1;
  margin-top: 10px;
  display: flex;
  flex-direction: column;
}

.add-to-cart-btn {
  background-color: #1b263b;
  border: none;
  padding: 10px 20px;
  color: white;
  cursor: pointer;
  border-radius: 4px;
  font-size: 1em;
  align-self: center;
  margin-top: auto;
  text-decoration: none;
  text-align: center;
}

.add-to-cart-btn:hover {
  background-color: #f0a500;
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
  color: #f0a500;
}

.book-author,
.book-price,
.book-original-price,
.book-discount {
  font-size: 0.9em;
  margin-bottom: 5px;
}

.book-price {
  color: #f0a500;
  font-weight: bold;
}

.book-original-price {
  text-decoration: line-through;
  color: gray;
}

@media (max-width: 1200px) {
  .book-card {
    width: 180px;
  }
}

@media (max-width: 768px) {
  .book-card {
    width: 45%;
  }
}

@media (max-width: 480px) {
  .book-card {
    width: 100%;
  }
}
</style>
