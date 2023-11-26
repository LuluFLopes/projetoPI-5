<template>
  <div class="div-central">
    <v-row>
      <v-col cols="4" align="left">
        <router-link to="home">
          <img src="../assets/mednote.png" alt=""/>
        </router-link>
      </v-col>
      <v-col cols="3">
        <div class="div-link" v-if="this.isLogado !== true">
          <router-link class="router-link" to="login">Entrar</router-link>
          <p class="router-link">&nbsp;/&nbsp;</p>
          <router-link class="router-link" to="CadastroPaciente">Registrar</router-link>
        </div>
      </v-col>
      <v-col cols="2">
        <div class="texto-boas-vindas" v-if="this.isLogado">
          <p>Olá {{ this.usuario.nome }}!</p>
        </div>
      </v-col>
      <v-col cols="2">
        <div v-if="verificarLoginPaciente" class="menu-navegacao">
          <menu-navegacao/>
        </div>
      </v-col>
      <v-col cols="1">
        <div class="btn-sair" v-if="verificarLoginOutros">
          <p @click="deslogar()">Sair</p>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import {defineComponent} from "vue";
import MenuNavegacao from "@/components/MenuNavegacao";
import router from "@/router";

export default defineComponent({
  name: "Header-Vue",
  components: {MenuNavegacao},
  data() {
    return {
      isLogado: false,
      usuario: {
        nome: '',
        tipoCadastro: '',
      }
    }
  },
  methods: {
    deslogar() {
      sessionStorage.clear();
      router.push('/');
      window.location.reload();
    },
  },
  computed: {
    verificarLoginPaciente() {
      return this.isLogado && this.usuario.tipoCadastro === 'PACIENTE';
    },
    verificarLoginOutros() {
      return this.isLogado && this.usuario.tipoCadastro !== 'PACIENTE';
    },
  },
  beforeMount() {
    let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    if (dadosLogin !== undefined && dadosLogin !== null) {
      if (dadosLogin.isLogado) {
        this.isLogado = true;
        this.usuario.nome = dadosLogin.nome;
        this.usuario.tipoCadastro = dadosLogin.tipoCadastro;
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

.btn-sair {
  color: grey;
  padding-top: 10vh;
  text-align: center;
}

.btn-sair:hover {
  color: white;
  font-weight: bold;
  cursor: pointer;
}
</style>