<template>
  <div>
    <div class="div-imagem">
      <img class="imagem-fundo" src="../assets/equipe-medica.png"  alt=""/>
    </div>
    <v-card class="cartao-principal">
      <v-text-field v-model="dadosLogin.usuario" label="Login: "></v-text-field>
      <v-text-field v-model="dadosLogin.senha" label="Senha: " type="password"></v-text-field>
      <v-row/>
      <v-btn class="btnEnviar" @click="fazerLogin()">Entrar</v-btn>
      <v-row/>
      <div class="texto-cadastro">
        <p>Ainda não tem cadastro ?</p>
        <p><a href="">Clique Aqui</a></p>
      </div>
    </v-card>
  </div>
</template>

<script>
import {defineComponent} from "vue";
import axios from "axios";
import router from "@/router";

export default defineComponent({
  name: "Login-Cliente",
  data() {
    return {
      url: 'http://localhost:8081/pacientes/login',
      dadosLogin: {
        usuario: '',
        senha: ''
      }
    }
  },
  methods: {
    async fazerLogin() {
      try {
        const request = await axios.put(this.url, this.dadosLogin);
        sessionStorage.setItem('usuarioLogado', JSON.stringify({
          id: request.data.id,
          usuario: request.data.login.usuario,
          isLogado: true
        }));
        router.push('/');
      } catch (ex) {
        alert("Não foi possível fazer o login, verifique seus dados!");
        console.log(ex.message);
      }
    },
  },
  beforeMount() {
    let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    if (dadosLogin.isLogado) {
      router.push('/');
    }
  }
});
</script>

<style scoped>
.div-imagem{
  display: flex;
  justify-content: center;
}

.imagem-fundo {
  top: 0;
  position: absolute;
  width: 40vw;
  opacity: 35%;
}

div {
  margin: 0 auto;
}

.cartao-principal {
  width: 30%;
  padding: 5vh;
  margin-top: 16vh;
  text-align: center;
}

.texto-cadastro {
  margin-top: 3vh;
}

</style>