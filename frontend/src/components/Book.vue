<script setup>
import { onMounted, ref, watch, computed } from "vue";
import axios from "axios";
import { defineProps } from "vue";
import { useStore } from "vuex";
import Swal from "sweetalert2";

const props = defineProps(["encryptedId", "bookName"]);

const book = ref();
const loader = ref(true);

const store = useStore();
const isAuthenticated = computed(() => store.getters.isAuthenticated);

const addToCart = async (bookId) => {
  try {
    let response;

    try {
      response = await axios.get(
        `http://localhost:8080/api/cart/${bookId}?id=${store.getters.userId}`
      );
    } catch (getError) {
      if (getError.response && getError.response.status === 404) {
        response = null;
      } else {
        throw getError;
      }
    }

    if (response && response.data) {
      const cartItem = response.data;

      if (cartItem.deletedAt) {
        const restoreResponse = await axios.put(
          `http://localhost:8080/api/cart/restore/${bookId}?id=${store.getters.userId}`
        );
        if (restoreResponse.status === 200) {
          Swal.fire({
            icon: "success",
            title: "Book added back to the cart!",
          });
        }
      } else {
        const updateResponse = await axios.put(
          `http://localhost:8080/api/cart/increase/${bookId}?id=${store.getters.userId}`
        );
        if (updateResponse.status === 200) {
          Swal.fire({
            icon: "success",
            title: "Quantity increased!",
          });
        }
      }
    } else {
      const createResponse = await axios.post(
        `http://localhost:8080/api/cart/add/${bookId}?id=${store.getters.userId}`
      );
      if (createResponse.status === 200) {
        Swal.fire({
          icon: "success",
          title: "Book added to the cart!",
        });
      }
    }
  } catch (error) {
    console.error("Error adding to cart:", error);
    Swal.fire({
      icon: "error",
      title: "Oops...",
      text: "Failed to perform operation!",
      footer: "Please try again later",
    });
  }
};


const order = async () => {
  if (isAuthenticated.value) {
    const response = await Swal.fire({
      title: "Place order?",
      icon: "info",
      confirmButtonText: "Confirm",
      cancelButtonText: "Cancel",
      showCancelButton: true,
    });

    if (response.isConfirmed) {
      try {
        const formData = {
          userId: Number(store.getters.userId),
          totalAmount: book.value.price.toString(),
}
        const response = await axios.post(
          `http://localhost:8080/api/orders/create-order`,
          formData
        );
        if (response.status === 201) {
          Swal.fire({
            icon: "success",
            title: "Order placed successfully!",
            footer: "Total cost: $" + book.price,
          });
        } else if (response.status === 404) {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "User not found!",
            footer: "Please try again later",
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
    }
  }
};

const fetchBookDetails = async (id, name) => {
  if (id && name) {
    try {
      const url = `http://localhost:8080/api/books/${encodeURIComponent(
        name.toLowerCase()
      )}?id=${id}`;
      console.log("Fetching book details with URL:", url);
      const response = await axios.get(url);
      book.value = response.data;
      loader.value = false;
    } catch (error) {
      console.error("Error fetching book details:", error);
    }
  }
};

onMounted(() => {
  if (props.encryptedId && props.bookName) {
    fetchBookDetails(props.encryptedId, props.bookName);
  } else {
    console.error("Decryption failed or invalid ID");
  }
});

watch(
  () => props.encryptedId,
  (newEncryptedId) => {
    if (newEncryptedId) {
      fetchBookDetails(newEncryptedId, props.bookName);
    }
  },
  { immediate: true }
);
</script>

<template>
  <div v-if="loader" class="loading"></div>
  <div v-else class="main-content">
    <div class="left-column">
      <img
        :src="`/assets/images/${book.image || 'default-image.png'}`"
        :alt="book.name"
        class="book-image"
      />
    </div>
    <div class="middle-column" style="cursor: pointer">
      <h1>{{ book.name }}</h1>
      <p class="author">{{ book.author }}</p>
      <p class="price">${{ book.price }}</p>
      <ul class="features">
        <li>Format: {{ book.format || "Hardcover" }}</li>
        <li>Language: {{ book.language || "English" }}</li>
        <li>Published Date: {{ book.publishedDate }}</li>
        <li>Genre: {{ book.genre }}</li>
      </ul>
    </div>
    <div class="right-column">
      <p class="price">${{ book.price }}</p>
      <button v-on:click="addToCart(book.id)">Add to Bag</button>
      <button v-on:click="order()" class="buy-now">Buy Now</button>
      <div class="delivery-info">
        <p>Delivery: Free shipping on orders over $25</p>
        <p>In Stock. Ships from and sold by BookStore.</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
body {
  font-family: "Arial", sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f4f4f4; /* Soft background color */
}

.loading {
  display: flex;
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
  height: 100vh; /* Take full viewport height */
  text-align: center;
  padding: 20px;
  font-size: 20px; /* Slightly larger font size */
  color: #ffffff; /* White text for better contrast */
  background: linear-gradient(135deg, #4e6692, #1b263b);
  box-shadow: 0 8px 15px rgba(0, 0, 0, 0.2); /* Deeper shadow for more depth */
  font-family: "Poppins", sans-serif; /* Modern font */
  position: relative;
  overflow: hidden;
  transition: all 0.3s ease;
}

/* Adding a loading spinner */
.loading::before {
  content: "";
  position: absolute;
  top: 50%;
  left: 50%;
  width: 70px;
  height: 70px;
  border: 10px solid #ffffff;
  border-top: 10px solid #f0a500;
  border-radius: 50%;
  animation: spin 1s linear infinite;
  transform: translate(-50%, -50%);
}

/* Spin animation for the spinner */
@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

/* Optional: Adding a fade-in effect when loading starts */
@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

.loading {
  animation: fadeIn 1s ease-in-out; /* Fade-in animation */
}
.main-content {
  margin-top: 100px;
  display: flex;
  justify-content: space-between;
  padding: 20px;
  gap: 30px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  animation: fadeIn 0.5s ease-in-out;
}

.main-content .left-column img {
  width: 300px;
  height: auto;
  border-radius: 8px;
  transition: transform 0.3s ease;
}

.main-content .left-column img:hover {
  transform: scale(1.05);
}

.middle-column {
  flex-grow: 2;
}

.middle-column h1 {
  font-size: 30px;
  color: #1b263b;
  margin-bottom: 10px;
  transition: color 0.3s;
}

.middle-column h1:hover {
  color: #f0a500;
}

.middle-column .author {
  font-size: 18px;
  color: #f0a500;
  margin-top: -5px;
  font-style: italic;
  transition: color 0.3s;
}

.middle-column .author:hover {
  color: #b12704;
}

.middle-column .price {
  font-size: 28px;
  color: #b12704;
  margin: 15px 0;
}

.middle-column .ratings {
  display: flex;
  align-items: center;
  gap: 8px;
}

.middle-column .ratings span {
  font-size: 14px;
  color: #555;
}

.middle-column .features {
  list-style: none;
  padding: 0;
  margin: 15px 0;
}

.middle-column .features li {
  margin-bottom: 8px;
  color: #777;
  font-size: 16px;
  transition: color 0.3s ease;
}

.middle-column .features li:hover {
  color: #f0a500;
}

.right-column {
  max-width: 300px;
  border: 1px solid #ddd;
  padding: 20px;
  background-color: #f0a500;
  border-radius: 10px;
  color: #1b263b;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  animation: slideIn 0.5s ease-out;
}

.right-column .price {
  font-size: 22px;
  margin-bottom: 15px;
  color: white;
}

.right-column button {
  width: 100%;
  padding: 12px;
  background-color: #1b263b;
  color: white;
  border: none;
  margin-bottom: 12px;
  cursor: pointer;
  border-radius: 8px;
  transition: background-color 0.3s;
}

.right-column button:hover {
  background-color: #0f1e28;
}

.right-column button.buy-now {
  background-color: #f0c14b;
  color: #1b263b;
}

.right-column button.buy-now:hover {
  background-color: #e5b940;
}

.delivery-info p {
  font-size: 14px;
  color: #fff;
  margin-top: 10px;
}

/* Fade-in animation */
@keyframes fadeIn {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

/* Slide-in animation */
@keyframes slideIn {
  0% {
    transform: translateX(100%);
    opacity: 0;
  }
  100% {
    transform: translateX(0);
    opacity: 1;
  }
}
</style>
