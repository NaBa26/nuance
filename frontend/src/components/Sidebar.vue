<template>
  <aside class="sidebar">
    <section class="filter-section">
      <h4>Format(s)</h4>
      <ul class="filter-list">
        <li><input type="checkbox" id="electronics"><label for="electronics">Paperback</label></li>
        <li><input type="checkbox" id="clothing"><label for="clothing">Hardcover</label></li>
        <li><input type="checkbox" id="home"><label for="home">E-book</label></li>
        <li><input type="checkbox" id="home"><label for="home">Pocket editions</label></li>
      </ul>
    </section>

    <section class="filter-section">
      <h4>Author(s)</h4>
      <ul class="filter-list">
        <li><input type="checkbox" id="electronics"><label for="electronics">Frank Herbert</label></li>
        <li><input type="checkbox" id="clothing"><label for="clothing">Jane Austen</label></li>
        <li><input type="checkbox" id="home"><label for="home">Agatha Christie</label></li>
        <li><input type="checkbox" id="home"><label for="home">Fyodor Dostoevsky</label></li>
      </ul>
    </section>

    <section class="filter-section">
      <h4>Price Range</h4>
      <div class="a-section a-spacing-mini sf-range-slider-row">
        <div id="p_36/range-slider_slider-item" class="a-section a-spacing-base a-spacing-top-base s-slider-container">
          <div class="a-section s-range-input-container s-lower-bound aok-relative">
            <input type="range" class="s-range-input" id="p_36/range-slider_slider-item_lower-bound-slider" value="0"
              min="0" max="187" step="1" aria-valuetext="₹66">
          </div>
          <div class="a-section s-range-input-container s-upper-bound">
            <input type="range" class="s-range-input" id="p_36/range-slider_slider-item_upper-bound-slider" value="187"
              min="0" max="187" step="1" aria-valuetext="₹36,800+"
              style="z-index: 0; background: linear-gradient(to right, rgb(187, 191, 191) 0%, rgb(187, 191, 191) 0%, rgb(0, 113, 133) 0%, rgb(0, 113, 133) 100%, rgb(187, 191, 191) 100%, rgb(187, 191, 191) 100%);">
          </div>
        </div>
        <div class="a-section sf-submit-range-button">
          <span class="a-button a-button-base" id="a-autoid-61"><span class="a-button-inner"><input
                aria-label="Go - Submit price range" class="a-button-input" type="submit"><span class="a-button-text"
                aria-hidden="true" id="a-autoid-61-announce">
                <span class="a-size-base a-color-base">Go</span>
              </span></span></span>
        </div>
      </div>
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
}

.filter-section {
  margin-bottom: 1.5rem;
}

.filter-section h4 {
  margin-bottom: 0.5rem;
  color: #333;
  font-weight: 600;
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
}

.filter-list label {
  color: #555;
  cursor: pointer;
}

.stars {
  color: #f0c14b;
  font-weight: bold;
}

@media (max-width: 768px) {
  .sidebar {
    width: 100%;
    border-right: none;
    border-bottom: 1px solid #ddd;
  }
}

.sf-range-slider-row {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 10px;
}

.s-range-input-container {
  position: relative;
}

.s-range-input {
  width: 100%;
  -webkit-appearance: none;
  appearance: none;
  background: linear-gradient(to right, rgb(187, 191, 191) 0%, rgb(0, 113, 133) 50%, rgb(187, 191, 191) 100%);
  height: 6px;
  border-radius: 5px;
  cursor: pointer;
}

.s-range-input::-webkit-slider-thumb {
  -webkit-appearance: none;
  width: 16px;
  height: 16px;
  background-color: #007185;
  border-radius: 50%;
  cursor: pointer;
}

.s-range-input::-moz-range-thumb {
  width: 16px;
  height: 16px;
  background-color: #007185;
  border-radius: 50%;
  cursor: pointer;
}

.price-label {
  font-size: 0.9rem;
  font-weight: 600;
  margin-top: 5px;
  display: block;
}

.sf-submit-range-button {
  margin-top: 10px;
}

.a-button {
  display: inline-block;
  border-radius: 4px;
  background-color: #f0c14b;
  padding: 0.5rem 1rem;
  cursor: pointer;
}

.a-button-inner {
  display: flex;
  justify-content: center;
  align-items: center;
}

.a-button-input {
  display: none;
}

.a-button-text {
  font-size: 1rem;
  color: #111;
}
</style>
