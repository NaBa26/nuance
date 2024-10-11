<template>
  <aside class="sidebar">
    <section class="filter-section">
      <h4>Format(s)</h4>
      <ul class="filter-list">
        <li><input type="checkbox" id="paperback"><label for="paperback">Paperback</label></li>
        <li><input type="checkbox" id="hardcover"><label for="hardcover">Hardcover</label></li>
        <li><input type="checkbox" id="ebook"><label for="ebook">E-book</label></li>
        <li><input type="checkbox" id="pocket"><label for="pocket">Pocket editions</label></li>
      </ul>
    </section>

    <section class="filter-section">
      <h4>Author(s)</h4>
      <ul class="filter-list">
        <li><input type="checkbox" id="frank"><label for="frank">Frank Herbert</label></li>
        <li><input type="checkbox" id="jane"><label for="jane">Jane Austen</label></li>
        <li><input type="checkbox" id="agatha"><label for="agatha">Agatha Christie</label></li>
        <li><input type="checkbox" id="fyodor"><label for="fyodor">Fyodor Dostoevsky</label></li>
      </ul>
    </section>

    <section class="filter-section">
      <h4>Genre</h4>
      <ul class="filter-list">
        <li><input type="checkbox" id="fiction"><label for="fiction">Fiction</label></li>
        <li><input type="checkbox" id="non-fiction"><label for="non-fiction">Non-Fiction</label></li>
        <li><input type="checkbox" id="fantasy"><label for="fantasy">Fantasy</label></li>
        <li><input type="checkbox" id="mystery"><label for="mystery">Mystery</label></li>
      </ul>
    </section>

    <section class="filter-section">
      <h4>Language</h4>
      <ul class="filter-list">
        <li><input type="checkbox" id="english"><label for="english">English</label></li>
        <li><input type="checkbox" id="spanish"><label for="spanish">Spanish</label></li>
        <li><input type="checkbox" id="french"><label for="french">French</label></li>
        <li><input type="checkbox" id="german"><label for="german">German</label></li>
      </ul>
    </section>

    <section class="filter-section">
      <h4>Customer Rating</h4>
      <ul class="filter-list">
        <li>
          <input type="checkbox" id="4-star">
          <label for="4-star">
            <span class="stars">★★★★☆</span> & Up
          </label>
        </li>
        <li>
          <input type="checkbox" id="3-star">
          <label for="3-star">
            <span class="stars">★★★☆☆</span> & Up
          </label>
        </li>
        <li>
          <input type="checkbox" id="2-star">
          <label for="2-star">
            <span class="stars">★★☆☆☆</span> & Up
          </label>
        </li>
        <li>
          <input type="checkbox" id="1-star">
          <label for="1-star">
            <span class="stars">★☆☆☆☆</span> & Up
          </label>
        </li>
      </ul>
    </section>
  </aside>
</template>

<style lang="css" scoped>
.sidebar {
  width: 250px;
  padding: 1rem;
  border-right: 1px solid #ddd;
  background-color: #f9f9f9;
  height: 100vh;
  overflow-y: auto;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* Subtle shadow */
}

.filter-section {
  margin-bottom: 1.5rem;
}

.filter-section h4 {
  margin-bottom: 0.5rem;
  color: #1B263B; /* Darker color for headings */
  font-weight: 600;
  border-bottom: 2px solid #F0A500; /* Underline for section headers */
  padding-bottom: 5px;
}

.filter-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.filter-list li {
  display: flex;
  align-items: center;
  margin-bottom: 0.75rem;
}

.filter-list input[type="checkbox"] {
  margin-right: 0.5rem;
  accent-color: #F0A500; /* Checkbox accent color */
}

.filter-list label {
  color: #333; /* Darker label text */
  cursor: pointer;
  transition: color 0.3s; /* Smooth color transition */
}

.filter-list label:hover {
  color: #F0A500; /* Highlight on hover */
}

.stars {
  color: #F0A500; /* Star color */
  font-weight: bold;
}

@media (max-width: 768px) {
  .sidebar {
    width: 100%;
    border-right: none;
    border-bottom: 1px solid #ddd;
  }
}
</style>
