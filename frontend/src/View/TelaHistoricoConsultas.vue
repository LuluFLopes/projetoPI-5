<template>
  <div>
    <v-card class="cartao-historico">
      <h1>Histórico de Consultas</h1>
      <div v-if="listaDeAgendamentos.length > 0">
        <v-container class="container-historico" v-for="(elemento, index) in listaDeAgendamentos"
                     :key="index">
          <hover @ao-clicar-botao-detalhes="testarEvento" :dadosAgendamento="elemento" />
        </v-container>
      </div>
      <p class="texto-agendamento" v-if="listaDeAgendamentos.length <= 0">Não há agendamentos registrados</p>
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
import Hover from "@/components/Hover";
import router from "@/router";
import axios from "axios";

export default defineComponent({
  name: "TelaHistoricoConsultas",
  components: {Hover},
  data() {
    return {
      listaDeAgendamentos: [],
      idPaciente: 0,
      alertaLigado: false,
      tipoAlerta: '',
      msgAlerta: '',
      url: 'http://localhost:8081/agendamentos/listarPorPaciente',
    }
  },
  methods: {
    async buscarAgendamentos() {
      if (this.idPaciente > 0) {
        try {
          const response = await axios.get(`${this.url}/${this.idPaciente}`);
          response.data.forEach(agendamento => {
            this.listaDeAgendamentos.push(agendamento);
          });
        } catch (ex) {
          this.gerarAlerta('error', 'Erro ao buscar lista', 3);
        }
      } else {
        this.gerarAlerta('error', 'Não foi possivel localizar o cliente', 3);
      }
    },
    testarEvento(){
      this.gerarAlerta('success', 'Deu bom', 1);
    },
    verificaLogin() {
      let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
      if (dadosLogin !== undefined && dadosLogin !== null) {
        if (dadosLogin.isLogado) {
          this.idPaciente = dadosLogin.id;
        } else {
          router.push('loginCliente');
        }
      }
    },
    gerarAlerta(tipoDeAlerta, mensagem, segundosParaFechar) {
      this.tipoAlerta = tipoDeAlerta;
      this.msgAlerta = mensagem;
      this.alertaLigado = true;

      setTimeout(() => {
        this.alertaLigado = false;
      }, segundosParaFechar * 1000);
    }
  },
  beforeMount() {
    this.verificaLogin();
    this.buscarAgendamentos();
  }
})
</script>

<style scoped>
h1 {
  text-align: center;
  padding-bottom: 5vh;
}

.cartao-historico {
  max-width: 1700px;
  height: 60vh;
  margin: 8vh auto 0;
  overflow-x: hidden;
  background-color: #a0ded6 !important;
}

.texto-agendamento {
  font-weight: bold;
  text-align: center;
  padding-top: 15vh;
}

.alerta-total {
  position: absolute;
  top: 1vh;
  left: 3vw;
}
</style>