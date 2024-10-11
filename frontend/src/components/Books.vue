<script>
export function getOriginalPrice(currentPrice) {
  const randomPercentage = Math.random() * (10 - 5) + 5;
  return (currentPrice * (1 + randomPercentage / 100)).toFixed(2);
}

export function calculateDiscount(currentPrice, originalPrice) {
  const discount = ((originalPrice - currentPrice) / originalPrice) * 100;
  return discount.toFixed(2);
}
</script>

<script setup>
import { onMounted, ref, watch } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';

const books = ref([]);
const route = useRoute();

const trimBookName = (name) => {
  return encodeURIComponent(name.trim());
};

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/books');
    books.value = response.data;
  } catch (error) {
    console.error('Error fetching books:', error);
  }
});

watch(
  () => route.params,
  async (newParams) => {
    const bookName = trimBookName(decodeURIComponent(newParams.bookName));
    const bookId = newParams.bookId;

    if (bookId && bookName) {
      try {
        const url = `http://localhost:8080/api/books/${bookName}?id=${bookId}`;
        const response = await axios.get(url);
        books.value = response.data;
      } catch (error) {
        console.error('Error fetching book details:', error);
      }
    }
  },
  { immediate: true }
);
</script>

<template>
  <div>
    <h3 style="padding:1%">Search Results</h3>
    <hr>
    <div v-if="books.length>0" class="cards-container">
      <div v-for="book in books" :key="book.id" class="book-card">
        <router-link 
          class="book-image-container" 
          :to="`/books/${encodeURIComponent(book.name.toLowerCase())}?id=${book.id}`">
          <img :src="`/assets/images/${book.image}`" :alt="book.name" class="book-image">
        </router-link>
        <div class="book-description">
          <router-link 
            class="book-title" 
            :to="`/books/${encodeURIComponent(book.name.toLowerCase())}?id=${book.id}`">
            {{ book.name }}
          </router-link>
          <div class="book-author">
            <span>by </span><b>{{ book.author }}</b>
          </div>
          <div class="book-price">
            $ {{ book.price }}
          </div>
          <div class="book-original-price">
            $ {{ getOriginalPrice(book.price) }}
          </div>
          <div class="book-discount">
            ({{ calculateDiscount(book.price, getOriginalPrice(book.price)) }}% off)
          </div>
          <button class="add-to-cart-btn">Add to Bag</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.cards-container {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  justify-content: space-evenly;
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
  justify-content: space-between;
  align-items: center;
  text-align: center;
}

.book-description {
  flex-grow: 1;
  margin-top: 10px;
  display: flex;
  flex-direction: column;
}

.add-to-cart-btn {
  background-color: #1B263B;
  border: none;
  padding: 10px 20px;
  color: white;
  cursor: pointer;
  border-radius: 4px;
  font-size: 1em;
  align-self: center;
  margin-top: auto;
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
.book-price,
.book-original-price,
.book-discount {
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
