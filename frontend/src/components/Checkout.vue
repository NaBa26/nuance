<script setup>
import { ref } from "vue";
import axios from "axios";

const checkoutDetails = ref({
  name: "",
  email: "",
  address: "",
  city: "",
  state: "",
  zip: "",
  paymentMethod: "",
});

const isSubmitting = ref(false);
const successMessage = ref("");
const errorMessage = ref("");

const handleCheckout = async () => {
  isSubmitting.value = true;
  successMessage.value = "";
  errorMessage.value = "";

  try {
    const response = await axios.post("http://localhost:8080/api/checkout", checkoutDetails.value);
    if (response.status === 200) {
      successMessage.value = "Checkout successful! Thank you for your purchase.";
      checkoutDetails.value = { name: "", email: "", address: "", city: "", state: "", zip: "", paymentMethod: "" };
    }
  } catch (error) {
    errorMessage.value = "Failed to process checkout. Please try again later.";
    console.error("Checkout Error:", error);
  } finally {
    isSubmitting.value = false;
  }
};
</script>

<template>
  <div class="checkout-container">
    <h2 class="checkout-title">Checkout</h2>

    <div v-if="successMessage" class="alert alert-success">{{ successMessage }}</div>
    <div v-if="errorMessage" class="alert alert-danger">{{ errorMessage }}</div>

    <form @submit.prevent="handleCheckout" class="checkout-form">
      <div class="form-group">
        <label for="name">Name</label>
        <input
          v-model="checkoutDetails.name"
          type="text"
          id="name"
          class="form-control"
          required
        />
      </div>

      <div class="form-group">
        <label for="email">Email</label>
        <input
          v-model="checkoutDetails.email"
          type="email"
          id="email"
          class="form-control"
          required
        />
      </div>

      <div class="form-group">
        <label for="address">Address</label>
        <input
          v-model="checkoutDetails.address"
          type="text"
          id="address"
          class="form-control"
          required
        />
      </div>

      <div class="form-group">
        <label for="city">City</label>
        <input
          v-model="checkoutDetails.city"
          type="text"
          id="city"
          class="form-control"
          required
        />
      </div>

      <div class="form-row">
        <div class="form-group">
          <label for="state">State</label>
          <input
            v-model="checkoutDetails.state"
            type="text"
            id="state"
            class="form-control"
            required
          />
        </div>

        <div class="form-group">
          <label for="zip">Zip Code</label>
          <input
            v-model="checkoutDetails.zip"
            type="text"
            id="zip"
            class="form-control"
            required
          />
        </div>
      </div>

      <div class="form-group">
        <label for="paymentMethod">Payment Method</label>
        <select
          v-model="checkoutDetails.paymentMethod"
          id="paymentMethod"
          class="form-control"
          required
        >
          <option disabled value="">Select a payment method</option>
          <option value="creditCard">Credit Card</option>
          <option value="paypal">PayPal</option>
          <option value="cod">Cash on Delivery</option>
        </select>
      </div>

      <button
        type="submit"
        class="checkout-button"
        :disabled="isSubmitting"
      >
        {{ isSubmitting ? "Processing..." : "Complete Purchase" }}
      </button>
    </form>
  </div>
</template>

<style scoped>
.checkout-container {
  max-width: 800px;
  margin: 150px auto;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.checkout-title {
  font-size: 2rem;
  color: #1b263b;
  text-align: center;
  margin-bottom: 30px;
  font-weight: 600;
}

.alert {
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 5px;
}

.alert-success {
  background-color: #d4edda;
  color: #155724;
  border: 1px solid #c3e6cb;
}

.alert-danger {
  background-color: #f8d7da;
  color: #721c24;
  border: 1px solid #f5c6cb;
}

.checkout-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group label {
  font-size: 1rem;
  color: #1b263b;
  margin-bottom: 5px;
}

.form-control {
  padding: 10px;
  border: 1px solid #e2e8f0;
  border-radius: 5px;
  font-size: 1rem;
}

.form-control:focus {
  border-color: #f0a500;
  box-shadow: 0 0 5px #f0a500;
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
  transition: transform 0.2s, background-color 0.2s;
}

.checkout-button:hover {
  background-color: #f0a500;
  color: #1b263b;
  transform: scale(1.05);
}
</style>
