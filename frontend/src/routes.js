import { createRouter, createWebHistory } from 'vue-router';
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import Books from './components/Books.vue';
import Home from './components/Home.vue';
import Sidebar from './components/Sidebar.vue';
import Book from './components/Book.vue';
import LogIn from './components/index/LogIn.vue';
import SignUp from './components/index/SignUp.vue';


const routes = [
  { path: '/',
    home:Home, 
    components: 
    {
      home: Home,
      header: Header,
      footer:Footer
    } 
  },
  { path: '/books',
    name: 'Books',
    components: {
      books: Books,
      sidebar: Sidebar,
      header: Header,
      footer:Footer
  }
  }, 
  {
    path: '/books/:bookName',
    name: 'Book',
    components: 
    {
      book:Book,
      sidebar:Sidebar,
      header: Header,
      footer:Footer
    },
    props: route=>({bookId:route.query.id})
  },
  {
    path: '/login',
    name: 'LogIn',
    components: {
      login: LogIn
  }
  },
  {
    path: '/signup',
    name: 'SignUp',
    components: {
      signup: SignUp
    }
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});


export default router;