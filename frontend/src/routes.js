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
    path: '/home/:userId',
    components: {
      home: Home,
      header: Header,
      footer: Footer,
    },
    props:{default:true},
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
  // {
  //   path: '/books/:userId',
  //   components: {
  //     home: Home,
  //     header: Header,
  //     footer: Footer,
  //   },
  // },
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
    path: '/bag',
    name: 'Bag',
    components: {
      header: Header,
      bag: Bag,
      footer: Footer,
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
    path: '/contact_us',
    name: 'ContactUs',
    components: {
      header: Header,
      footer: Footer,
      contactUs: ContactUs,
    },
  },
  {
    path: '/contact_us/:userId',
    components: {
      home: Home,
      header: Header,
      footer: Footer,
    },
    props:{default:true},
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
