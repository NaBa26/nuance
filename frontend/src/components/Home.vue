<template>
    <main class="container">

      <div class="searchBar-container d-none d-lg-flex justify-content-center align-items-center">
          <input placeholder="Search for authors, titles and genres" class="desktop-searchBar" v-model="searchQuery">
          <span @click="searchValue" class="search-icon">
            <FontAwesomeIcon :icon="faSearch" size="lg" style="color: #fff;" />
          </span>
        </div>

      <!-- Carousel Section -->
      <div id="carouselExampleAutoplaying" class="carousel slide banner-carousel justify-content-center" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <div class="banner-image-wrapper">
              <img src="/public/assets/images/bg_images/31286.jpg" class="d-block w-100 banner-image" alt="Custom">
            </div>
          </div>
          <div class="carousel-item">
            <div class="banner-image-wrapper">
              <img src="/public/assets/images/bg_images/gift_packages.jpg" class="d-block w-100 banner-image" alt="George RR Martin">
            </div>
          </div>
          <div class="carousel-item">
            <div class="banner-image-wrapper">
              <img src="/public/assets/images/bg_images/well_known.jpg" class="d-block w-100 banner-image" alt="Best Seller">
            </div>
          </div>
        </div>
      </div>
  
      <!-- Extra Content Below the Carousel -->
      <div class="extra-content">
        <div class="content-wrapper">
          <h2>Discover Our Latest Collection</h2>
          <p>Explore the best of our curated selection of books and products. From thrilling bestsellers to exclusive gift packages, find something for everyone.</p>
          <button class="cta-button">Shop Now</button>
        </div>
      </div>
  
      <!-- Random Books Section -->
      <div class="books-section justify-content-center">
        <h2>Featured Books</h2>
        <div v-if="loading" class="loading-message">Loading books...</div>
        <div v-if="error" class="error-message">Oops! Something went wrong while fetching the books.</div>
        <div v-if="books.length > 0" class="book-cards-container">
          <div v-for="book in books" :key="book.id" class="book-card">
            <img :src="`/assets/images/${book.image}`" alt="Book Image" class="book-image" />
            <div class="book-details">
              <h3 class="book-title">{{ book.name }}</h3>
              <p class="book-author">{{ book.author }}</p>
              <p class="book-price">${{ book.price }}</p>
            </div>
          </div>
        </div>
        <div v-if="books.length === 0" class="no-books">No books available at the moment.</div>
      </div>
    </main>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        books: [],
        loading: false,
        error: false,
      };
    },
    mounted() {
      this.fetchRandomBooks();
    },
    methods: {
      async fetchRandomBooks() {
        this.loading = true;
        try {
          const response = await axios.get('http://localhost:8080/api/books');
          const books = response.data;
          const randomBooks = this.getRandomBooks(books, 5);
          this.books = randomBooks;
          this.loading = false;
        } catch (error) {
          console.error("There was an error fetching the books:", error);
          this.error = true;
          this.loading = false;
        }
      },

      getRandomBooks(books, numBooks) {
        const randomBooks = [];
        const booksCopy = [...books];
        for (let i = 0; i < numBooks; i++) {
          const randomIndex = Math.floor(Math.random() * booksCopy.length);
          randomBooks.push(booksCopy.splice(randomIndex, 1)[0]);
        }
        return randomBooks;
      },
      addToCart(book) {
        console.log(`Added ${book.title} to the cart.`);
      }
    }
  };
  </script>
  
  <style scoped>
  .banner-carousel {
    height: 800px;
    overflow: hidden;
    padding: 2%;
  }
  
  .banner-image-wrapper {
    position: relative;
    height: 100%;
    width: 100%;
  }
  
  .banner-image {
    object-fit: cover;
    height: 100%;
    width: 100%;
  }
  
  .banner-image-wrapper::after {
    content: "";
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 45%;
    background: linear-gradient(to top, rgba(255, 255, 255, 100), rgba(255, 255, 255, 0));
  }
  
  .carousel-inner {
    height: 100%;
  }
  
  /* Extra Content Styles */
  .extra-content {
    padding-top: 20px;
    padding-bottom: 40px;
    text-align: center;
  }
  
  .content-wrapper {
    max-width: 1200px;
    margin: 0 auto;
    text-align: center;
  }
  
  .content-wrapper h2 {
    font-size: 2rem;
    color: #1B263B;
    margin-bottom: 15px;
  }
  
  .content-wrapper p {
    font-size: 1.1rem;
    color: #555;
    margin-bottom: 20px;
    line-height: 1.6;
  }
  
  .cta-button {
    background-color: #F0A500;
    color: #1B263B;
    border: none;
    padding: 12px 24px;
    font-size: 1.2rem;
    cursor: pointer;
    border-radius: 5px;
    text-transform: uppercase;
  }
  
  .cta-button:hover {
    background-color: #d78800;
    transition: background-color 0.3s;
  }
  
  /* Random Books Section Styles */
  .books-section {
    padding: 40px 0;
    background-color: #f9f9f9;
  }
  
  .books-section h2 {
    font-size: 2rem;
    color: #1B263B;
    text-align: center;
    margin-bottom: 30px;
  }
  
  .loading-message,
  .error-message,
  .no-books {
    text-align: center;
    font-size: 1.2rem;
    color: #f0a500;
    margin-bottom: 20px;
  }
  
  .book-cards-container {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 20px;
    padding: 0 10px;
  }
  
  .book-card {
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    text-align: center;
    transition: transform 0.3s ease;
  }
  
  .book-card:hover {
    transform: translateY(-10px);
  }
  
  .book-image {
    width: 100%;
    height: 250px;
    object-fit: cover;
    border-bottom: 2px solid #f0a500;
  }
  
  .book-details {
    padding: 20px;
  }
  
  .book-title {
    font-size: 1.2rem;
    color: #333;
    font-weight: bold;
    margin-bottom: 10px;
  }
  
  .book-author {
    font-size: 1rem;
    color: #777;
    margin-bottom: 10px;
  }
  
  .book-price {
    font-size: 1.2rem;
    color: #f0a500;
    margin-bottom: 15px;
  }
  
  .add-to-cart-btn {
    background-color: #f0a500;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1rem;
    transition: background-color 0.3s ease;
  }
  
  .add-to-cart-btn:hover {
    background-color: #d78800;
  }

  .searchBar-container {
  display: flex;
  align-items: center;
  width: 100%;
  margin: 0 20px;
}

.searchBar-container input {
  width: 90%;
  height: 50px;
  border-radius: 5px 0 0 5px;
  padding: 0 10px;
  border: 1px solid #ccc;
  outline: none;
  transition: border-color 0.3s ease;
}

.searchBar-container input:focus {
  border-color: #F0A500;
}

.searchBar-container span {
  background-color: #F0A500;
  width: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  border-radius: 0 10px 10px 0;
  transition: background-color 0.3s ease;
}

.searchBar-container span:hover {
  background-color: #1B263B;
}
  </style>
  