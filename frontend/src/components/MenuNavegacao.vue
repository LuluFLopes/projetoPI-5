<template>
  <div class="text-center">
    <v-menu offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-icon
            size="x-large"
            v-bind="attrs"
            v-on="on"
        >mdi-account
        </v-icon>
      </template>
      <v-list>
        <v-list-item
            class="elemento-lista"
            v-for="(item, index) in items"
            :key="index"
            :style="{ backgroundColor: (hoveredItem === index) ? 'lightblue' : 'inherit', cursor: 'pointer'}"
            @mouseenter="hoveredItem = index"
            @mouseleave="hoveredItem = null"
            @click="redirecionaParaTelas(item)"
        >
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
</template>

<script>
import {defineComponent} from "vue";
import router from "@/router";

export default defineComponent({
  name: "MenuNavegacao",
  data() {
    return {
      isLogado: false,
      hoveredItem: null,
      items: [
        {
          title: 'Agendar',
          id: 1
        },
        {
          title: 'Meus Dados',
          id: 2
        },
        {
          title: 'Histórico',
          id: 3
        },
        {
          title: 'Sair',
          id: 4
        },
      ],
    }
  },
  methods: {
    redirecionaParaTelas(item) {
      switch (item.id) {
        case 1:
          router.push('agendamento');
          break;
        case 2:
          //TODO criar endereço -> router.push('alterarCadastro');
          break;
        case 3:
          router.push('historicoConsultas');
          break;
        case 4:
          this.deslogar();
          router.push('home');
          window.location.reload();
          break;
      }
    },
    deslogar(){
      sessionStorage.clear();
    }
  },
  beforeMount() {
    let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    if (dadosLogin.isLogado) {
      this.isLogado = true;
    }
  }
});
</script>

<style scoped>
</style>