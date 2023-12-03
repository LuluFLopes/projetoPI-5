<template>
  <div>
    <menu-lateral/>
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
    }
  },
  methods: {
    ...mapMutations([
      'preencherDadosUsuarioAlterado'
    ]),
    async buscarUsuario() {
      try {
        const response = await axios.get(`${this.urlBuscarUsuario}/${this.idUsuarioLogado}`);
        await this.preencherDadosUsuarioAlterado(response.data);
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao carregar usuario', 3);
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
  },
  beforeMount() {
    this.buscarIdUsuarioLogado();
    this.preencherUrl();
    this.buscarUsuario();
  }
})
</script>

<style scoped>
</style>