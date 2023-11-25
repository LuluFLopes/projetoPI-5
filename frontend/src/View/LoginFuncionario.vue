<template>
  <div>
    <div class="div-imagem">
      <img class="imagem-fundo" src="../assets/equipe-medica.png" alt=""/>
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
    <v-alert class="alerta-total"
             v-if="alertaLigado"
             dismissible
             elevation="24"
             :type="tipoAlerta"
    >{{ this.msgAlerta }}
    </v-alert>
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
      url: 'http://localhost:8081/funcionarios/login',
      dadosLogin: {
        usuario: '',
        senha: ''
      },
      alertaLigado: false,
      tipoAlerta: '',
      msgAlerta: '',
    }
  },
  methods: {
    async fazerLogin() {
      try {
        const request = await axios.put(this.url, this.dadosLogin);
        sessionStorage.setItem('usuarioLogado', JSON.stringify({
          id: request.data.id,
          usuario: request.data.login.usuario,
          nome: request.data.nome,
          isLogado: true,
          tipoUsuario: 'FUNCIONARIO',
        }));
        router.push('/home');
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao logar', 3);
        console.log(ex.message);
      }
    },
    gerarAlerta(tipoDeAlerta, mensagem, segundosParaFechar) {
      this.tipoAlerta = tipoDeAlerta;
      this.msgAlerta = mensagem;
      this.alertaLigado = true;

      setTimeout(() => {
        this.alertaLigado = false;
      }, segundosParaFechar * 1000);
    },
  },
  beforeMount() {
    let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
    if (dadosLogin !== undefined && dadosLogin !== null) {
      if (dadosLogin.isLogado) {
        router.push('/');
      }
    }
  }
});
</script>

<style scoped>
.div-imagem {
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

.alerta-total {
  position: absolute;
  top: 1vh;
  left: 3vw;
}

</style>