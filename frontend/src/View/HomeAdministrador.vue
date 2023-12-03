<template>
  <div>
    <menu-lateral/>
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
import MenuLateral from "@/components/MenuLateral";
import {mapMutations} from "vuex";
import axios from "axios";

export default defineComponent({
  name: "HomeAdministrador",
  components: {MenuLateral},
  data() {
    return {
      urlBuscarUsuario: '',
      alertaLigado: false,
      tipoAlerta: '',
      msgAlerta: '',
    }
  },
  methods: {
    ...mapMutations([
      'preencherDadosUsuarioAlterado'
    ]),
    async buscarUsuario() {
      if (this.tipoUsuario !== 'ADMINISTRADOR'){
        try {
          const response = await axios.get(`${this.urlBuscarUsuario}/${this.idUsuarioLogado}`);
          await this.preencherDadosUsuarioAlterado(response.data);
        } catch (ex) {
          this.gerarAlerta('error', 'Erro ao carregar usuario', 3);
        }
      }
    },
    preencherUrl() {
      switch (this.tipoUsuario) {
        case 'MEDICO':
          this.urlBuscarUsuario = 'http://localhost:8081/medicos/buscar';
          break;
        case 'FUNCIONARIO':
          this.urlBuscarUsuario = 'http://localhost:8081/funcionarios/buscar';
          break;
        case 'PACIENTE':
          this.urlBuscarUsuario = 'http://localhost:8081/pacientes/buscar';
          break;
      }
    },
    buscarIdUsuarioLogado() {
      let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
      if (dadosLogin !== undefined && dadosLogin !== null) {
        if (dadosLogin.isLogado) {
          this.idUsuarioLogado = dadosLogin.id;
          this.tipoUsuario = dadosLogin.tipoCadastro;
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
    },
  },
  beforeMount() {
    this.buscarIdUsuarioLogado();
    this.preencherUrl();
    this.buscarUsuario();
  }
})
</script>

<style scoped>
.alerta-total {
  position: absolute;
  top: 1vh;
  left: 3vw;
}
</style>