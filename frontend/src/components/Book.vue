<template>
    <div class="main-content">

        <!-- Left Column (Product Images) -->
        <div class="left-column">
            <img :src="`/assets/images/${books.image}`" :alt="books.name" class="book-image">
            <div class="thumbnail-gallery">
                <img src="https://via.placeholder.com/60x80" alt="Thumb1">
                <img src="https://via.placeholder.com/60x80" alt="Thumb2">
                <img src="https://via.placeholder.com/60x80" alt="Thumb3">
            </div>
        </div>

        <!-- Middle Column (Product Info) -->
        <div class="middle-column">
            <h1>Book Title: Example Book</h1>
            <p class="author">by Author Name</p>

            <div class="ratings">
                <img src="https://via.placeholder.com/100x20" alt="Ratings">
                <span>(150 customer reviews)</span>
            </div>

            <p class="price">$19.99</p>

            <ul class="features">
                <li>Format: Hardcover</li>
                <li>Language: English</li>
                <li>Publisher: ABC Publishing</li>
                <li>Page Count: 320 pages</li>
            </ul>
        </div>

        <!-- Right Column (Buy Section) -->
        <div class="right-column">
            <p class="price">$19.99</p>
            <button>Add to Bag</button>
            <button class="buy-now">Buy Now</button>
            <div class="delivery-info">
                <p>Delivery: Free shipping on orders over $25</p>
                <p>In Stock. Ships from and sold by BookStore.</p>
            </div>
        </div>

    </div>

    <!-- Product Details and Customer Reviews -->
    <div class="product-details">
        <div class="product-description">
            <h2>Product Description</h2>
            <p>This is a detailed description of the book. It provides information about the content, storyline, and
                other relevant details.</p>
        </div>

        <div class="customer-reviews">
            <h3>Customer Reviews</h3>
            <p>This is where customer reviews and ratings would be displayed, with options to filter by rating or most recent.</p>
        </div>
    </div>
</template>

<script setup>
import { onMounted, ref, watch, defineProps } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';

// Define props
const props = defineProps(['bookName', 'bookId']);
const books = ref([]);
const route = useRoute();

const trimBookName = (bookName) => {
  return bookName.trim().replace(/\s+/g, '-').toLowerCase();
};

// Fetch book details on mounted
onMounted(async () => {
  const bookName = trimBookName(decodeURIComponent(props.bookName));
  const bookId = props.bookId;

  if (bookId && bookName) {
    try {
      const url = `api/books/${bookName}/?id=${bookId}`;
      const response = await axios.get(url);
      books.value = response.data;
      console.log(books.value);
    } catch (error) {
      console.error('Error fetching book details:', error);
    }
  }
});

// Watch for route changes
watch(
  () => route.params,
  async (newParams) => {
    const bookName = trimBookName(decodeURIComponent(newParams.bookName));
    const bookId = newParams.bookId;

    if (bookId && bookName) {
      try {
        const url = `api/books/${bookName}/?id=${bookId}`;
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


<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}

/* Header Styling */
.header {
    background-color: #232F3E;
    padding: 10px 20px;
    color: white;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.header .logo {
    font-size: 24px;
    font-weight: bold;
}

.header .search-bar input {
    padding: 8px;
    width: 400px;
}

.header .nav-links {
    display: flex;
    gap: 20px;
}

.header .nav-links a {
    color: white;
    text-decoration: none;
    padding: 5px 10px;
}

/* Main Content Styling */
.main-content {
    display: flex;
    padding: 20px;
    gap: 40px;
}

.main-content .left-column img {
    width: 400px;
    height: auto;
    border: 1px solid #ccc;
}

.main-content .thumbnail-gallery img {
    width: 60px;
    height: auto;
    margin-top: 10px;
    cursor: pointer;
}

.middle-column {
    flex-grow: 2;
}

.middle-column h1 {
    font-size: 24px;
}

.middle-column .author {
    color: #007185;
    margin-top: -10px;
}

.middle-column .price {
    font-size: 24px;
    color: #B12704;
    margin: 15px 0;
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
}

.middle-column .features li {
    margin-bottom: 10px;
}

.right-column {
    max-width: 300px;
    border: 1px solid #ddd;
    padding: 20px;
    background-color: #f3f3f3;
}

.right-column .price {
    font-size: 22px;
    color: #B12704;
    margin-bottom: 15px;
}

.right-column button {
    width: 100%;
    padding: 12px;
    background-color: #FF9900;
    color: white;
    border: none;
    margin-bottom: 10px;
    cursor: pointer;
}

.right-column button.buy-now {
    background-color: #f0c14b;
}

.right-column .delivery-info {
    font-size: 14px;
    margin-top: 10px;
}

/* Product Details and Reviews */
.product-details {
    margin-top: 40px;
    padding: 0 20px;
}

.product-description {
    padding: 10px;
    background-color: #f9f9f9;
    margin-bottom: 20px;
}

.customer-reviews {
    padding: 10px;
    background-color: #f9f9f9;
}

.customer-reviews h3 {
    font-size: 18px;
}
</style>