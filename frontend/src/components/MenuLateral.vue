<template>
  <v-list class="lista-principal" dense>
    <v-list-item-group
        v-model="itemListaSelecionado"
    >
      <v-list-item
          class="item-lista"
          v-for="(item, i) in buscarListaFiltradaDePermissoes"
          :key="i"
          @click="selecionarItemMenu(i, item.rota)"
      >
        <v-list-item-icon>
          <v-icon v-text="item.icone"></v-icon>
        </v-list-item-icon>
        <v-list-item-content class="conteudo-item-lista">
          <v-list-item-title class="texto-rota" v-text="item.titulo"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list-item-group>
  </v-list>
</template>

<script>
import {defineComponent} from "vue";
import {permissoesMenuLateral} from "@/constants/permissoesMenuLateral";
import {mapMutations, mapState} from "vuex";
import router from "@/router";

export default defineComponent({
  name: "MenuLateral",
  data() {
    return {
      tipoCadastro: '',
      itemListaSelecionado: -1,
    }
  },
  computed: {
    ...mapState([
      'itemSelecionadoMenuLateral'
    ]),
    buscarListaFiltradaDePermissoes() {
      return permissoesMenuLateral.filter((elementoLista) => {
            return elementoLista.grupo.find((permissao) => permissao === this.tipoCadastro)
          }
      )
    },
  },
  methods: {
    ...mapMutations([
      'selecionarItemMenuLateral'
    ]),
    verificaTipoCadastro() {
      let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
      if (dadosLogin !== undefined && dadosLogin !== null) {
        if (dadosLogin.isLogado) {
          this.tipoCadastro = dadosLogin.tipoCadastro;
        }
      }
    },
    selecionarItemMenu(indice, rota) {
      this.selecionarItemMenuLateral(indice);
      router.push(rota);
    },
    preencheItemSelecionado() {
      return this.itemListaSelecionado = this.itemSelecionadoMenuLateral;
    },
  },
  beforeMount() {
    this.verificaTipoCadastro();
    this.preencheItemSelecionado();
  }
})
</script>

<style scoped>
.lista-principal {
  max-width: 15vw;
  width: 15vw;
  height: 80vh;
  background: #a0ded6 !important;
}

.item-lista {
  margin-top: 2vh;
}

.texto-rota {
  text-decoration: none;
  color: black;
  display: flex;
  justify-content: center;
}

.conteudo-item-lista {
  text-align: center;
}
</style>