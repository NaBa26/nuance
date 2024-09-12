import { createRouter, createWebHistory } from 'vue-router';
import Books from './components/Books.vue';
import Home from './components/Home.vue';
import Sidebar from './components/Sidebar.vue';
import Book from './components/Book.vue';


const routes = [
  { path: '/',
    home:Home, 
    components: 
    {
      home: Home
    } 
  },
  { path: '/books',
    components: {
      books: Books,
      sidebar: Sidebar
  }
  }, 
  {
    path: '/books/:bookName',
    name: 'Book',
    components: 
    {
      book:Book
    }
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;