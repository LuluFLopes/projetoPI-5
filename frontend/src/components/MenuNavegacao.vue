<template>
  <div>
  </div>
</template>

<script>
import {defineComponent} from "vue";

export default defineComponent ({
  name: "MenuNavegacao",
  data() {
    return {
      isLogado: false,
      isDropdownAtivo: false,
    }
  },
  methods: {
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