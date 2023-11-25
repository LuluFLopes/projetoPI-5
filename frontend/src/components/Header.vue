<template>
  <div class="div-central">
    <v-row>
      <v-col cols="4" align="left">
        <router-link to="home">
          <img src="../assets/mednote.png" alt=""/>
        </router-link>
      </v-col>
      <v-col cols="4">
        <div class="div-link" v-if="this.isLogado !== true">
          <router-link class="router-link" to="loginCliente">Entrar</router-link>
          <p class="router-link">&nbsp;/&nbsp;</p>
          <router-link class="router-link" to="CadastroPaciente">Registrar</router-link>
        </div>
      </v-col>
      <v-col cols="2">
        <div class="texto-boas-vindas" v-if="this.isLogado">
          <p>Olá {{this.usuario.nome}}!</p>
        </div>
      </v-col>
      <v-col cols="2">
        <div v-if="this.isLogado" class="menu-navegacao">
          <menu-navegacao />
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import {defineComponent} from "vue";
import MenuNavegacao from "@/components/MenuNavegacao";

export default defineComponent({
  name: "Header-Vue",
  components: {MenuNavegacao},
  data() {
    return {
      isLogado: false,
      usuario: {
        nome: ''
      }
    }
  },
  beforeMount() {
    let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    if (dadosLogin !== undefined && dadosLogin !== null) {
      if (dadosLogin.isLogado) {
        this.isLogado = true;
        this.usuario.nome = dadosLogin.nome;
      }
    }
  }
})
</script>

<style scoped>
.div-central {
}

div {
  background-color: #7ececa;
}

div img {
  margin: 0;
  display: inline-block;
  width: 15vw;
}

.div-link {
  text-align: right;
  padding-top: 10vh;
}

.router-link {
  display: inline-block;
  font-weight: bold;
  color: grey;
  text-decoration: none;
}

.router-link:hover {
  color: #FFFFFF;
}

.texto-boas-vindas {
  color: grey;
  padding-top: 10vh;
  text-align: right;
}

.menu-navegacao {
  color: grey;
  padding-top: 10vh;
  text-align: center;
}
</style>