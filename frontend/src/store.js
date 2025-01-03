import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';

export default createStore({
  state: {
    isAuthenticated: false,
  },
  mutations: {
    setAuthenticated(state, isAuthenticated) {
      console.log('Mutation called:', isAuthenticated);
      state.isAuthenticated = isAuthenticated;
    },
  },
  actions: {
    async login({ commit }, response) {
      if (response.status === 200 && response.data.id) {
        commit('setAuthenticated', true);
      }
    },
    logout({ commit }, response) {
    if (response.status === 200) {
        commit('setAuthenticated', false);
    }
    },
  },
  getters: {
    isAuthenticated: (state) => state.isAuthenticated,
  },
  plugins: [createPersistedState()],
});