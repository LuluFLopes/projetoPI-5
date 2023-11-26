<template>
  <v-list class="lista-principal" dense>
    <v-list-item-group
        :value="buscarItemSelecionado"
        @input="preencheItemSelecionado"
    >
      <v-list-item
          class="item-lista"
          v-for="(item, i) in buscarListaFiltradaDePermissoes"
          :key="i"
          @click="selecionarItemMenu(i)"
      >
        <v-list-item-icon>
          <v-icon v-text="item.icone"></v-icon>
        </v-list-item-icon>
        <v-list-item-content class="conteudo-item-lista">
          <router-link class="texto-rota" :to="item.rota">
            <v-list-item-title v-text="item.titulo"></v-list-item-title>
          </router-link>
        </v-list-item-content>
      </v-list-item>
    </v-list-item-group>
  </v-list>
</template>

<script>
import {defineComponent} from "vue";
import {permissoesMenuLateral} from "@/constants/permissoesMenuLateral";
import {mapMutations, mapState} from "vuex";

export default defineComponent({
  name: "MenuLateral",
  data() {
    return {
      tipoCadastro: '',
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
    buscarItemSelecionado() {
      return this.itemSelecionadoMenuLateral;
    }
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
    selecionarItemMenu(indice) {
      this.selecionarItemMenuLateral(indice);
      this.itemSelecionado = indice;
    },
    preencheItemSelecionado() {
      return this.itemSelecionadoMenuLateral;
    },
  },
  beforeMount() {
    this.verificaTipoCadastro();
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
}

.conteudo-item-lista {
  text-align: center;
}
</style>