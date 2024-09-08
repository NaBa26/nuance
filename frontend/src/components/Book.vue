<template>
  <div>
    <h2>Search Results</h2>
    <div>
    <span style="display: flex; flex-direction: row; flex-wrap: wrap; align-content: flex-start; width: 100%; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
      Returned first <b>10</b> entried from a total of <b style="color: #1b261b; font-size:20px"> {{ books.length }} </b> entries.
    </span>
    </div>
    <div v-for="book in books" :key="book.id" class="book-container">
      <!-- databinding is done via the vue-bind attribute. The `${expression}` is used to bind data with the string with variables -->
      <img :src="`/assets/images/${ book.image }`" :alt="book.name" class="book-image">
      <div class="book-details">
        <div class="book-name"><b>{{ book.name }}</b></div>
        <div class="book-information">
          <div class="book-author"><span>by</span> <b> {{ book.author }} </b> </div><br>
          <span>|</span>
          <div class="book-published-date"> {{ book.date_published }}</div>
          <span>|</span>
          <div class="book-genre">{{ book.genre }}</div>
        </div>
        <div class="book-price">$ {{ book.price }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const books = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/books');
    books.value = response.data;
  } catch (error) {
    console.error('Error fetching books:', error);
  }
});
</script>

<style scoped>
.book-container {
  cursor:default;
  display: flex;
  align-items: center;
  border: 1px solid #f0a500;
  padding: 16px;
  margin: 16px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.book-image {
  cursor: pointer;
  padding: 16px;
  width: 150px;
  height: 200px;
  margin-right: 16px;
  background-color: whitesmoke;
}

.book-details {
  flex: 1;
}

.book-information {
  display: flex;
  flex: 1;
}
.book-author, .book-published-date {
  font-size: 0.8em;
  color: black;
}
.book-author b, .book-genre{
  cursor: pointer;
}
.book-name {
  cursor: pointer;
  color: #1B263B;
  font-weight: bold;
  font-size: 1.5em;
  margin-bottom: 8px;
}

.book-price {
  color: black;
  font-size: 1em;
}
</style>