import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    itemSelecionadoMenuLateral: -1,
  },
  getters: {},
  mutations: {
    selecionarItemMenuLateral(state, payload) {
      state.itemSelecionadoMenuLateral = payload;
    }
  },
  actions: {},
  modules: {}
})
