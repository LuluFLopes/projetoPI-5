<template>
  <v-list class="lista-principal" dense>
    <v-list-item-group
        v-model="selectedItem"
        color="primary"
    >
      <v-list-item
          class="item-lista"
          v-for="(item, i) in buscarListaFiltradaDePermissoes"
          :key="i"
      >
        <v-list-item-icon>
          <v-icon v-text="item.icone"></v-icon>
        </v-list-item-icon>
        <v-list-item-content class="conteudo-item-lista">
          <router-link class="texto-rota" :to="item.rota"><v-list-item-title v-text="item.titulo"></v-list-item-title></router-link>
        </v-list-item-content>
      </v-list-item>
    </v-list-item-group>
  </v-list>
</template>

<script>
import {defineComponent} from "vue";
import {permissoesMenuLateral} from "@/constants/permissoesMenuLateral";

export default defineComponent({
  name: "MenuLateral",
  data() {
    return {
      tipoCadastro: '',
    }
  },
  computed: {
    buscarListaFiltradaDePermissoes() {
      return permissoesMenuLateral.filter((elementoLista) => {
            return elementoLista.grupo.find((permissao) => permissao === this.tipoCadastro)
          }
      )
    }
  },
  methods: {
    verificaTipoCadastro() {
      let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
      if (dadosLogin !== undefined && dadosLogin !== null) {
        if (dadosLogin.isLogado) {
          this.tipoCadastro = dadosLogin.tipoCadastro;
        }
      }
    }
  },
  beforeMount() {
    this.verificaTipoCadastro();
  }
})
</script>

<style scoped>
.lista-principal {
  max-width: 15vw;
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