import { createRouter, createWebHistory } from 'vue-router';
import Book from './components/Book.vue';
import Home from './components/Home.vue';
import Sidebar from './components/Sidebar.vue';

const routes = [
  { path: '/', component: Home },
  { path: '/books',
    components: {
      book: Book,
      sidebar: Sidebar
    }}
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;