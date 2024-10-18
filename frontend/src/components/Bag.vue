<template>
    <div class="bag-container">
      <h2 class="bag-title">Your bag</h2>
      
      <div v-if="bagItems.length > 0" class="bag-items">
        <div v-for="item in bagItems" :key="item.id" class="bag-item">
          <img :src="item.image" alt="Product Image" class="item-image" />
          <div class="item-details">
            <h3 class="item-name">{{ item.name }}</h3>
            <p class="item-price">$ {{ item.price }}</p>
            <div class="item-quantity">
              <button @click="decreaseQuantity(item)" class="quantity-button">-</button>
              <span>{{ item.quantity }}</span>
              <button @click="increaseQuantity(item)" class="quantity-button">+</button>
            </div>
          </div>
          <button class="remove-button" @click="removeItem(item)">Remove</button>
        </div>
        
        <div class="bag-summary">
          <p class="summary-text">Total: $ {{ totalPrice }}</p>
          <button class="checkout-button" @click="checkout">Proceed to Checkout</button>
        </div>
      </div>
      
      <div v-else class="empty-bag">
        <p>Your bag is empty!</p>
        <router-link to="/shop" class="back-to-shop">Continue Shopping</router-link>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        bagItems: [
          // Example items
          { id: 1, name: 'Product 1', price: 100, quantity: 1, image: 'product1.jpg' },
          { id: 2, name: 'Product 2', price: 50, quantity: 2, image: 'product2.jpg' }
        ]
      };
    },
    computed: {
      totalPrice() {
        return this.bagItems.reduce((total, item) => total + item.price * item.quantity, 0);
      }
    },
    methods: {
      increaseQuantity(item) {
        item.quantity++;
      },
      decreaseQuantity(item) {
        if (item.quantity > 1) item.quantity--;
      },
      removeItem(item) {
        this.bagItems = this.bagItems.filter(bagItem => bagItem.id !== item.id);
      },
      checkout() {
        alert('Proceeding to checkout...');
        // Checkout logic
      }
    }
  };
  </script>
  
  <style scoped>
  .bag-container {
    max-width: 900px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f8f9fa;
  }
  
  .bag-title {
    color: #1B263B;
    font-size: 2rem;
    margin-bottom: 20px;
  }
  
  .bag-items {
    background-color: #ffffff;
    border-radius: 8px;
    padding: 20px;
  }
  
  .bag-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    border-bottom: 1px solid #e9ecef;
    padding-bottom: 10px;
  }
  
  .item-image {
    width: 80px;
    height: 80px;
    border-radius: 8px;
    object-fit: cover;
  }
  
  .item-details {
    flex-grow: 1;
    margin-left: 20px;
  }
  
  .item-name {
    font-size: 1.2rem;
    color: #1B263B;
  }
  
  .item-price {
    font-size: 1rem;
    color: #F0A500;
    margin-top: 5px;
  }
  
  .item-quantity {
    display: flex;
    align-items: center;
    margin-top: 10px;
  }
  
  .quantity-button {
    background-color: #F0A500;
    border: none;
    color: white;
    font-size: 1.2rem;
    padding: 5px 10px;
    cursor: pointer;
  }
  
  .remove-button {
    background-color: transparent;
    border: none;
    color: #1B263B;
    cursor: pointer;
    font-size: 0.9rem;
  }
  
  .bag-summary {
    text-align: right;
    margin-top: 20px;
  }
  
  .summary-text {
    font-size: 1.5rem;
    color: #1B263B;
  }
  
  .checkout-button {
    background-color: #F0A500;
    color: #ffffff;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    border-radius: 8px;
    font-size: 1rem;
  }
  
  .empty-bag {
    text-align: center;
    padding: 40px 0;
    color: #1B263B;
  }
  
  .back-to-shop {
    color: #F0A500;
    text-decoration: none;
    font-weight: bold;
    margin-top: 20px;
    display: inline-block;
  }
  </style>
  