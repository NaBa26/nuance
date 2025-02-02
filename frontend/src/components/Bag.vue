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
  return books.value?.reduce(
    (total, book) => total + (Number(book.book.price) || 0) * (Number(book.quantity) || 0),
    0
  ) || 0;
});


const store = useStore();
const isAuthenticated = computed(() => store.getters.isAuthenticated);

const increaseQuantity = async (bookId) => {
  try {
    const response = await axios.put(
      `http://localhost:8080/api/cart/increase/${bookId}?id=${store.getters.userId}`
    );
    if (response.status === 200) {
      const book = books.value.find((b) => b.id === bookId);
      if (book && book.quantity<book.book.quantity) book.quantity++;
      window.location.reload();
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
      if (book && book.quantity>0) book.quantity--;
      window.location.reload();
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
        totalAmount: totalPrice.value.toString(),
        }
        const response = await axios.post(
          `http://localhost:8080/api/orders/create-order`,
          formData
        );
        if (response.status === 201) {
          Swal.fire({
            icon: "success",
            title: "Order placed successfully!",
            footer: "Total cost: $" + totalPrice.value,
          });
          window.location.reload();
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
      window.location.reload();
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

onMounted(async () => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/cart?id=${store.getters.userId}`,
      { withCredentials: true }
    );
    books.value = response.data;
  } catch (error) {
    if (error.response === 404) {
      errorMessage.value = "Your bag is empty!";
    } else {
      errorMessage.value = "Failed to fetch bag. Please try again later.";
    }
  }
});
</script>

<template>
  <div class="bag-container">
    <h2 class="bag-title">Your Bag</h2>

    <div v-if="books.length > 0" class="bag-items">
      <div v-for="book in books" :key="book.book.id" class="bag-item">
        <img
          :src="`/assets/images/${book.book.image}`"
          alt="Product Image"
          class="item-image"
        />
        <div class="item-details">
          <h3 class="item-name">{{ book.book.name }}</h3>
          <p class="item-price">$ {{ book.book.price }}</p>
          <div class="item-quantity">
            <button
              @click="decreaseQuantity(book.book.id)"
              class="quantity-button"
              :disabled="book.quantity <= 1"
            >
              -
            </button>
            <span class="quantity-display"
              ><b>{{ book.quantity }}</b></span
            >
            <button @click="increaseQuantity(book.book.id)" class="quantity-button">
              +
            </button>
          </div>
        </div>
        <button class="remove-button" @click="removeItem(book.book.id)">
          <FontAwesomeIcon :icon="faTrash" size="md" />
        </button>
      </div>

      <div class="bag-summary">
        <p class="summary-text">Total: $ {{ totalPrice }}</p>
        <button class="checkout-button" @click="order">Place Order</button>
      </div>
    </div>
    <div v-else class="empty-bag">
      <p class="empty-text">Your bag is empty. Click on the link below to fill it up!</p>
    </div>
    
    <br />
    <div class="text-center mb-3 mt-3">
      <router-link to="/books" class="back-to-shop">
        <FontAwesomeIcon :icon="faArrowLeft" size="md" />
        <span class="back-to-shop-text">Continue Shopping</span>
      </router-link>
    </div>
  </div>
</template>

<style scoped>
/* General Styles for the Page */
.bag-container {
  max-width: 900px;
  margin: 100px auto;
  padding: 40px;
  background-color: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  font-family: "Arial", sans-serif;
}

/* Title Styles */
.bag-title {
  font-size: 2.5rem;
  color: #1b263b;
  text-align: center;
  margin-bottom: 30px;
  font-weight: 700;
}

/* Bag Item Styles */
.bag-items {
  border-top: 2px solid #f1f5f9;
  padding-top: 20px;
}

.bag-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 25px;
  padding: 15px 0;
  border-bottom: 1px solid #e2e8f0;
}

.item-image {
  max-width: 20%;
  height: auto;
  border-radius: 8px;
  object-fit: cover;
  border: 2px solid #f0a500;
  display: block;
}

.item-details {
  flex: 1;
  margin-left: 20px;
}

.item-name {
  font-size: 1.4rem;
  color: #1b263b;
  margin: 0;
  font-weight: 600;
}

.item-price {
  font-size: 1.2rem;
  color: #f0a500;
  font-weight: 500;
  margin-top: 5px;
}

.item-quantity {
  display: flex;
  align-items: center;
}

.quantity-button {
  background-color: #f0a500;
  border: none;
  color: #ffffff;
  font-size: 1.3rem;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 40px; /* Ensures the buttons have a consistent size */
  height: 40px; /* Ensures the buttons have a consistent size */
  display: flex;
  justify-content: center;
  align-items: center;
}

.quantity-button:disabled {
  background-color: #e2e8f0;
  cursor: not-allowed;
}

.quantity-button:hover {
  background-color: #f5a623;
}

.quantity-display {
  font-size: 1.3rem;
  color: #1b263b;
  font-weight: 500;
  width: 40px;
  text-align: center;
}

.remove-button {
  background-color: #ff4d4d;
  border: none;
  color: #ffffff;
  font-size: 1.2rem;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.remove-button:hover {
  background-color: #d83b3b;
}

/* Bag Summary Section */
.bag-summary {
  margin-top: 40px;
  text-align: center;
}

.summary-text {
  font-size: 1.6rem;
  color: #1b263b;
  margin-bottom: 25px;
  font-weight: 500;
}

.checkout-button {
  background-color: #1b263b;
  color: #ffffff;
  border: none;
  padding: 15px 30px;
  border-radius: 8px;
  font-size: 1.2rem;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.checkout-button:hover {
  background-color: #3c4f75;
}

/* Empty Bag Message */
.empty-bag {
  text-align: center;
  padding: 60px 0;
  color: #1b263b;
}

.empty-text {
  font-size: 1.4rem;
  margin-bottom: 20px;
  color: #1b263b;
  font-weight: 400;
}

.empty-text span {
  font-weight: 600;
  color: #f0a500;
}

/* Back to Shop Button */
.back-to-shop {
  padding: 12px 20px;
  color: #1b263b;
  background-color: #f0a500;
  border-radius: 25px;
  text-decoration: none;
  font-size: 1.2rem;
  font-weight: bold;
  border-bottom: 2px solid transparent;
  transition: transform 0.3s ease, color 0.3s ease;
}

.back-to-shop:hover {
  background-color: #1b263b;
  color: #f0a500;
  transform: scale(1.05);
}

@media (max-width: 768px) {
  .bag-container {
    padding: 25px;
  }

  .bag-title {
    font-size: 2rem;
  }

  .bag-item {
    flex-direction: column;
    align-items: flex-start;
    margin-bottom: 15px;
  }

  .item-image {
    width: 80px;
    height: 80px;
  }

  .item-details {
    margin-left: 0;
  }

  .quantity-button {
    font-size: 1.1rem;
  }

  .remove-button {
    font-size: 1.1rem;
  }

  .summary-text {
    font-size: 1.4rem;
  }

  .checkout-button {
    font-size: 1.1rem;
    padding: 12px 25px;
  }

  .back-to-shop {
    font-size: 1.1rem;
    padding: 10px 18px;
  }
}
</style>
