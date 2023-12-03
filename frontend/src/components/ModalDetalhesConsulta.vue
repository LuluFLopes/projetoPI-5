<template>
  <v-row justify="center">
    <v-dialog
        class="dialog"
        :value="isAberto"
        persistent
        width="1000"
    >
      <v-card class="dialog">
        <h1 class="titulo-modal">Dados da Consulta</h1>
        <v-card-text class="container-conteudo">
          <div class="container-primeira-linha">
            <v-card class="elementos" elevation="10">
              <h2 class="sub-titulos">Informações do Paciente</h2>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Nome Completo: {{ nomePaciente }}</p>
                <p class="padrao-linha">CPF: {{ cpfPaciente }}</p>
                <p class="padrao-linha padrao-direita">Genero: {{ generoPaciente }}</p>
              </div>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Data Nascimento: {{ dataNascimento }}</p>
              </div>
            </v-card>
            <v-card class="elementos" elevation="10">
              <h2 class="sub-titulos">Informações do Médico</h2>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Nome Completo: {{ nomeMedico }}</p>
                <p class="padrao-linha">CRM: {{ crm }}</p>
                <p class="padrao-linha padrao-direita">Genero: {{ generoMedico }}</p>
              </div>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Especialização:
                  {{ especializacao }}</p>
              </div>
            </v-card>
            <v-card class="elementos" elevation="10">
              <h2 class="sub-titulos">Informações do Agendamento</h2>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Unidade: {{ unidade }}</p>
                <p class="padrao-linha">Data do Agendamento: {{ detalhesConsulta.dataAgendamento }}</p>
                <p class="padrao-linha padrao-direita">Hora do Agendamento: {{ horaAgendamento }}</p>
              </div>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Endereço: {{ endereco }}</p>
                <p class="padrao-linha padrao-direita">Status do Agendamento: {{ statusDoAgendamento }}</p>
              </div>
            </v-card>
          </div>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              v-if="botaoAtivo"
              color="red darken-1"
              text
              @click="cancelarAgendamento"
          >
            Cancelar Consulta
          </v-btn>
          <v-btn
              color="red darken-1"
              text
              @click="desativarModal()"
          >
            Fechar
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import {defineComponent} from "vue";
import axios from "axios";

export default defineComponent({
  name: "ModalDetalhesConsulta",
  data() {
    return {
      urlCancelarAgendamento: 'http://localhost:8081/agendamentos/cancelar',
      tipoCadastro: '',
    }
  },
  emits: ['ao-clicar-botao-fechar-modal'],
  props: {
    isAberto: Boolean,
    detalhesConsulta: Object,
  },
  computed: {
    nomePaciente() {
      return this.detalhesConsulta.paciente.nome;
    },
    cpfPaciente() {
      return this.detalhesConsulta.paciente.cpf;
    },
    generoPaciente() {
      return this.detalhesConsulta.paciente.genero;
    },
    dataNascimento() {
      let dataNascimento = this.detalhesConsulta.paciente.dataNascimento;
      let dataFormatada = new Date(`${dataNascimento[0]}-${dataNascimento[1]}-${dataNascimento[2]}`);
      return dataFormatada.toLocaleDateString('pt-BR');
    },
    nomeMedico() {
      return this.detalhesConsulta.medico.nome;
    },
    crm() {
      return this.detalhesConsulta.medico.crm;
    },
    generoMedico() {
      return this.detalhesConsulta.medico.genero;
    },
    especializacao() {
      return this.detalhesConsulta.medico.especializacao.descricao;
    },
    unidade() {
      return this.detalhesConsulta.unidade.descricao;
    },
    horaAgendamento() {
      let hora = this.detalhesConsulta.horaInicio;
      let horarioConvertido = new Date();
      horarioConvertido.setHours(hora[0]);
      horarioConvertido.setMinutes(hora[1]);
      horarioConvertido.setSeconds(0);
      return horarioConvertido.toLocaleTimeString('pr-BR');
    },
    endereco() {
      let endereco = this.detalhesConsulta.unidade.endereco;
      return `${endereco.logradouro}, ${endereco.numero} - ${endereco.complemento} - ${endereco.bairro} - ${endereco.cep}`
    },
    statusDoAgendamento() {
      return this.detalhesConsulta.andamento;
    },
    botaoAtivo() {
      return this.detalhesConsulta.andamento !== 'CANCELADO' && this.tipoCadastro === 'PACIENTE';
    }
  },
  methods: {
    async cancelarAgendamento() {
      try {
        await axios.put(`${this.urlCancelarAgendamento}/${this.detalhesConsulta.id}`);
        window.location.reload();
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao cancelar agendamento', 3);
      }
    },
    desativarModal() {
      this.$emit('ao-clicar-botao-fechar-modal');
    },
    buscarTipoDeLogin() {
      let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
      if (dadosLogin !== undefined && dadosLogin !== null) {
        if (dadosLogin.isLogado) {
          this.tipoCadastro = dadosLogin.tipoCadastro;
        }
      }
    },
  },
  beforeMount() {
    this.buscarTipoDeLogin();
  }
})
</script>

<style scoped>

.dialog {
  background: #a0ded6 !important;
}

.container-conteudo {
  height: 220vh !important;
  max-height: 70vh;
}

.sub-titulos {
  margin-top: 2vw;
  text-align: center;
  background-color: #45b5c4;
}

.elementos {
  display: block;
  background: #7ececa !important;
  height: 20vh;
  width: 100%;
  margin-bottom: 2vh;

}

.titulo-modal {
  text-align: center;
  margin-bottom: 5vh;
}

.padrao-div {
  display: flex;
  justify-content: space-between;
  margin-top: 3vh;
  font-weight: bold;
}

.padrao-linha {
  display: inline-block;
}

.padrao-esquerda {
  margin-left: 1.5vw;
}

.padrao-direita {
  margin-right: 1.5vw;
}

</style>