import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    isAuthenticated: false,
    userId: null,
  },
  mutations: {
    setAuthenticated(state, isAuthenticated) {
      state.isAuthenticated = isAuthenticated;
    },
    setUserId(state, userId) {
      state.userId = userId;
    },
  },
  actions: {
    async login({ commit }, response) {
      if (response.status === 200 && response.data.id) {
        commit("setAuthenticated", true);
        commit("setUserId", response.data.id);
      }
    },
    logout({ commit }) {
      commit("setAuthenticated", false);
      commit("setUserId", null);

      window.sessionStorage.removeItem('vuex');
      document.cookie = "MY_SESSION_COOKIE=; Max-Age=0; path=/; secure; HttpOnly;"
    },
  },
  getters: {
    isAuthenticated: (state) => state.isAuthenticated,
    userId: (state) => state.userId,
  },
  plugins: [createPersistedState({ storage: window.sessionStorage })],
});
