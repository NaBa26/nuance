import { createStore } from 'vuex';

export default createStore({
    state: {
        isAuthenticated: false,
    },
    mutations: {
        setAuthenticated(state, isAuthenticated) {
            state.isAuthenticated = isAuthenticated;
        },
    },
    actions: {
        login({ commit }, response) {
            if (response.data.active) {
                commit('setAuthenticated', true);
            }
        },
        logout({ commit }) {
            commit('setAuthenticated', false);
        },
    },
    getters: {
        isAuthenticated: (state) => state.isAuthenticated,
    },
});
