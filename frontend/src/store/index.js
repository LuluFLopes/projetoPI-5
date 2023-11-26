import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    itemSelecionadoMenuLateral: -1,
    dadosUsuarioAlterado: {},
  },
  getters: {},
  mutations: {
    selecionarItemMenuLateral(state, payload) {
      state.itemSelecionadoMenuLateral = payload;
    },
    preencherDadosUsuarioAlterado(state, payload) {
      state.dadosUsuarioAlterado = payload;
    },
  },
  actions: {},
  modules: {}
})
