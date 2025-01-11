import { createRouter, createWebHistory } from 'vue-router';
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import Books from './components/Books.vue';
import Home from './components/Home.vue';
import Book from './components/Book.vue';
import LogIn from './components/index/LogIn.vue';
import SignUp from './components/index/SignUp.vue';
import NotFound from './components/NotFound.vue';
import Bag from './components/Bag.vue';
import Profile from './components/Profile.vue';
import ForgotPassword from './components/index/ForgotPassword.vue';
import ContactUs from './components/ContactUs.vue';
import OAuthRedirectHandler from './components/index/OAuthRedirectHandler.vue';
import Checkout from './components/Checkout.vue';

const routes = [
  { path: '/', redirect: '/home' },
  {
    path: '/home',
    components: {
      home: Home,
      header: Header,
      footer: Footer,
    },
  },
  {
    path: '/books',
    name: 'Books',
    components: {
      books: Books,
      header: Header,
      footer: Footer,
    },
    props: { default: true },
  },
  {
    path: '/books/:bookName',
    name: 'Book',
    components: {
      book: Book,
      header: Header,
      footer: Footer,
    },
    props: { default: true },
  },
  {
    path: '/login',
    name: 'LogIn',
    components: {
      login: LogIn,
    },
  },
  {
    path: '/signup',
    name: 'SignUp',
    components: {
      signup: SignUp,
    },
  },
  {
    path: '/login/oauth2/code/google',
    name: 'OAuthRedirectHandler',
    components: {
      oAuthRedirectHandler: OAuthRedirectHandler,
    },
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    components: {
      notfound: NotFound,
    },
  },
  {
    path: '/forgot_password',
    name: 'ForgotPassword',
    components: {
      forgotPassword: ForgotPassword,
    },
  },
  {
    path: '/profile',
    name: 'Profile',
    components: {
      header: Header,
      profile: Profile,
      footer: Footer,
    },
    props: route => ({ userId: route.params.id }),
  },
  {
   path: '/bag',
   name: Bag,
    components: {
      header: Header,
      footer: Footer,
      bag: Bag,
    },
    props: route => ({ userId: route.params.id }),
  },
  {
    path: '/checkout',
    name: Checkout,
     components: {
       header: Header,
       footer: Footer,
       checkout: Checkout,
     }
   },
   {
    path: '/contact_us',
    name: 'ContactUs',
    components: {
      header: Header,
      footer: Footer,
      contactUs: ContactUs,
    },
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
