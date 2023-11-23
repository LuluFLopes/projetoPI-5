<template>
  <div>
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
  name: "Login-Funcionario",
  data() {
    return {
      urlFuncionario: 'http://localhost:8081/funcionarios/login',
      urlMedico: 'http://localhost:8081/medicos/login',
      dadosLogin: {
        usuario: '',
        senha: ''
      }
    }
  },
  methods: {
    async fazerLogin() {
      try {
        const requestFuncionario = await axios.put(this.urlFuncionario, this.dadosLogin);
        const requestMedico = await axios.put(this.urlMedico, this.dadosLogin);

        if (requestFuncionario !== undefined) {
          sessionStorage.setItem('usuarioLogado', JSON.stringify({
            id: requestFuncionario.data.id,
            usuario: requestFuncionario.data.login.usuario,
            isLogado: true
          }));
          router.push('/');
        } else {
          sessionStorage.setItem('usuarioLogado', JSON.stringify({
            id: requestMedico.data.id,
            usuario: requestMedico.data.login.usuario,
            isLogado: true
          }));
          router.push('/');
        }

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