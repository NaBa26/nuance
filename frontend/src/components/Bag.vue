<script setup>
import { ref, computed, onMounted } from "vue";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faTrash, faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { useStore } from "vuex";
import Swal from "sweetalert2";

const books = ref([]);
const errorMessage = ref(null);

const totalPrice = computed(() => {
  return books.value.reduce(
    (total, book) => total + book.price * book.quantity,
    0
  );
});

const increaseQuantity = async (bookId) => {
  try {
    const response = await axios.put(
      `http://localhost:8080/api/cart/increase/${bookId}?id=${store.getters.userId}`
    );
    if (response.status === 200) {
      const book = books.value.find((b) => b.id === bookId);
      if (book) book.quantity++;
    }
  } catch (error) {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "Failed to perform operation!",
      footer: "Please try again later",
    });
  }
};

const decreaseQuantity = async (bookId) => {
  try {
    const response = await axios.put(
      `http://localhost:8080/api/cart/decrease/${bookId}?id=${store.getters.userId}`
    );
    if (response.status === 200) {
      const book = books.value.find((b) => b.id === bookId);
      if (book) book.quantity--;
    }
  } catch (error) {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "Failed to perform operation!",
      footer: "Please try again later",
    });
  }
};

const removeItem = async (bookId) => {
  try {
    const response = await axios.delete(
      `http://localhost:8080/api/cart/deleteBook/${bookId}?id=${store.getters.userId}`
    );
    if (response.status === 200) {
      books.value = books.value.filter((book) => book.id !== bookId);
      Swal.fire({
        icon: "success",
        title: "Book removed from the bag succesfully!",
      });
    }
  } catch (error) {
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "Failed to perform operation!",
      footer: "Please try again later",
    });
  }
};

const checkout = () => {
  setTimeout(() => {
    Swal.fire({
      icon: "info",
      title: "Proceeding to checkout!",
    });
  }, 1000);
  window.location.href = "/checkout";
};

onMounted(async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/cart?id=${store.getters.userId}`, { withCredentials: true }
    );
    console.log(response);
    books.value = response.data;
  } catch (error) {
    if (error.response === 404) {
      errorMessage.value = "Your bag is empty!";
    } else {
      errorMessage.value = "Failed to fetch bag. Please try again later.";
    }
  }
});

const store = useStore();
</script>

<template>
  <div class="bag-container">
    <h2 class="bag-title">Your Bag</h2>

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
            <button @click="increaseQuantity(book.id)" class="quantity-button">
              +
            </button>
          </div>
        </div>
        <button class="remove-button" @click="removeItem(book.id)">
          <FontAwesomeIcon :icon="faTrash" size="md" />
        </button>
      </div>

      <div class="bag-summary">
        <p class="summary-text">Total: $ {{ totalPrice }}</p>
        <button class="checkout-button" @click="checkout">
          Proceed to Checkout
        </button>
      </div>
    </div>

    <div v-else class="empty-bag">
      <p class="empty-text">{{ errorMessage }}</p>
    </div>
    <br>
    <div class="text-center mb-3 mt-3">
    <router-link to="/books" class="back-to-shop">
      <FontAwesomeIcon :icon="faArrowLeft" size="md" />
      <span class="back-to-shop-text">Continue Shopping</span>
    </router-link>
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
  color: #1b263b;
  font-weight: 400;
}

.back-to-shop {
  padding: 20px;
  color: #1b263b;
  background-color: #f0a500;
  border-radius: 15px;
  text-decoration: none;
  font-size: 1rem;
  font-weight: bold;
  border-bottom: 2px solid transparent;
  transition: transform 0.3s ease, color 0.3s ease;
}

.back-to-shop:hover {
  background-color: #1b263b;
  color: #f0a500;
}
</style>
