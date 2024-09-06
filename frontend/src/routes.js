import { createRouter, createWebHistory } from 'vue-router';
import Product from './components/Product.vue';
import Home from './components/Home.vue';

const routes = [
  { path: '/', component: Home },
  { path: '/products', component: Product }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;