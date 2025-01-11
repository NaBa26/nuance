<script setup>
import { ref, computed, onMounted } from "vue";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { useStore } from "vuex";

// Define reactive state for books and error message
const books = ref([]);
const errorMessage = ref(null);

// Computed property for calculating the total price
const totalPrice = computed(() => {
  return books.value.reduce((total, book) => total + book.price * book.quantity, 0);
});

// Increase quantity of a book
const increaseQuantity = async (bookId) => {
  try {
    const response = await axios.put(`http://localhost:8080/api/cart/increase/${bookId}?id=${store.getters.userId}`);
    if (response.status === 200) {
      const book = books.value.find(b => b.id === bookId);
      if (book) book.quantity++;
      alert("Quantity increased successfully.");
    }
  } catch (error) {
    alert("Failed to perform operation. Please try again later.");
  }
};

// Decrease quantity of a book
const decreaseQuantity = async (bookId) => {
  try {
    const response = await axios.put(`http://localhost:8080/api/cart/decrease/${bookId}?id=${store.getters.userId}`);
    if (response.status === 200) {
      const book = books.value.find(b => b.id === bookId);
      if (book) book.quantity--;
      alert("Quantity decreased successfully.");
    }
  } catch (error) {
    alert("Failed to perform operation. Please try again later.");
  }
};

// Remove a book from the cart
const removeItem = async (bookId) => {
  try {
    const response = await axios.delete(`http://localhost:8080/api/cart/deleteBook/${bookId}?id=${store.getters.userId}`);
    if (response.status === 200) {
      books.value = books.value.filter(book => book.id !== bookId);
      alert("Book removed from cart successfully.");
    }
  } catch (error) {
    alert("Failed to remove book from cart. Please try again later.");
  }
};

const checkout = () => {
  alert("Proceeding to checkout...");
  window.location.href = "/checkout";
};

onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/cart/${store.getters.userId}`);
    books.value = response.data;
  } catch (error) {
    errorMessage.value = "Failed to fetch bag. Please try again later.";
  }
});

const store = useStore();
</script>

<template>
  <div class="bag-container">
    <h2 class="bag-title">Your Bag</h2>

    <!-- Show cart items if there are any -->
    <div v-if="books.length > 0" class="bag-items">
      <div v-for="book in books" :key="book.id" class="bag-item">
        <img :src="book.image" alt="Product Image" class="item-image" />
        <div class="item-details">
          <h3 class="item-name">{{ book.name }}</h3>
          <p class="item-price">$ {{ book.price }}</p>
          <div class="item-quantity">
            <button
              @click="decreaseQuantity(book.id)"
              class="quantity-button"
              :disabled="book.quantity <= 1"
            >
              -
            </button>
            <span class="quantity-display">{{ book.quantity }}</span>
            <button @click="increaseQuantity(book.id)" class="quantity-button">+</button>
          </div>
        </div>
        <button class="remove-button" @click="removeItem(book.id)">
          <FontAwesomeIcon :icon="faTrash" size="md" />
        </button>
      </div>

      <div class="bag-summary">
        <p class="summary-text">Total: $ {{ totalPrice }}</p>
        <button class="checkout-button" @click="checkout">Proceed to Checkout</button>
      </div>
    </div>

    <div v-else class="empty-bag">
      <p class="empty-text">{{ errorMessage || "Your bag is empty!" }}</p>
      <router-link to="/books" class="back-to-shop">Continue Shopping</router-link>
    </div>
  </div>
</template>

<style scoped>
.bag-container {
  max-width: 800px;
  margin: 150px auto;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.bag-title {
  font-size: 2rem;
  color: #1b263b;
  text-align: center;
  margin-bottom: 30px;
  font-weight: 600;
}

.bag-items {
  border-top: 2px solid #f1f5f9;
  padding-top: 20px;
}

.bag-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 20px;
  padding: 10px 0;
  border-bottom: 1px solid #e2e8f0;
}

.item-image {
  width: 70px;
  height: 70px;
  border-radius: 8px;
  object-fit: cover;
  border: 2px solid #f0a500;
}

.item-details {
  flex: 1;
  margin-left: 20px;
}

.item-name {
  font-size: 1.2rem;
  color: #1b263b;
  margin: 0;
}

.item-price {
  font-size: 1rem;
  color: #f0a500;
  font-weight: 500;
  margin-top: 5px;
}

.item-quantity {
  display: flex;
  align-items: center;
  margin-top: 10px;
}

.quantity-button {
  background-color: #f0a500;
  border: none;
  color: #ffffff;
  font-size: 1.2rem;
  padding: 8px 12px;
  border-radius: 5px;
  cursor: pointer;
}

.remove-button {
  background-color: #ff4d4d;
  border: none;
  color: #ffffff;
  font-size: 1rem;
  padding: 8px 12px;
  border-radius: 5px;
  cursor: pointer;
}

.bag-summary {
  margin-top: 30px;
  text-align: center;
}

.summary-text {
  font-size: 1.5rem;
  color: #1b263b;
  margin-bottom: 20px;
}

.checkout-button {
  background-color: #1b263b;
  color: #ffffff;
  border: none;
  padding: 12px 25px;
  border-radius: 8px;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
}

.empty-bag {
  text-align: center;
  padding: 50px 0;
  color: #1b263b;
}

.empty-text {
  font-size: 1.2rem;
  margin-bottom: 20px;
}

.back-to-shop {
  color: #f0a500;
  text-decoration: none;
  font-size: 1rem;
  font-weight: bold;
  border-bottom: 2px solid transparent;
  transition: color 0.2s, border-bottom 0.2s;
}
</style>
