<template>
  <div class="principal">
    <v-col>
      <v-row align="center">
        <v-col cols="3">
          <v-card height="378px">
            <h2 style="text-align: center;padding-bottom: 10px">Dados do Agendamento</h2>
            <div>
              <label>Unidade: {{ this.unidadeView.descricao }}</label>
              <label>Endereço: {{
                  `${this.unidadeView.endereco.logradouro} ${this.unidadeView.endereco.numero} ${this.unidadeView.endereco.complemento}`
                }}</label>
            </div>
            <v-divider></v-divider>
            <div>
              <label>Médico: {{ this.medicoView.nome }}</label>
              <label>CRM: {{ this.medicoView.crm }}</label>
              <label>Especialização: {{ this.medicoView.especializacao.descricao }}</label>
            </div>
            <v-divider></v-divider>
            <div>
              <label>Horário de Agendamento: {{ this.horarioAtendimentoView.horaInicio }}</label>
            </div>
          </v-card>
        </v-col>
        <v-col cols="3">
          <v-card class="containerCombobox" height="378px">
            <v-combobox
                v-model="unidadeInput"
                class="padraoCombobox"
                clearable
                label="Unidades"
                variant="outlined"
                :items="this.listaDeUnidades"
                @change="gravarUnidade()"
            ></v-combobox>

            <v-combobox
                v-model="especializacaoInput"
                class="padraoCombobox"
                clearable
                label="Especialidades"
                variant="outlined"
                :items="this.listaDeEspecialidade"
                @change="gravarEspecialidade()"
            ></v-combobox>

            <v-combobox
                v-model="medicoInput"
                class="padraoCombobox"
                clearable
                label="Medicos"
                variant="outlined"
                :items="this.listaDeMedicos"
                @change="gravarMedicos()"
            ></v-combobox>

            <v-alert class="alerta-total"
                     v-if="alertaLigado"
                     dismissible
                     elevation="24"
                     :type="tipoAlerta"
            >{{ this.msgAlerta }}
            </v-alert>
          </v-card>
        </v-col>

        <v-col cols="auto" v-if="this.componenteDatasAtiva">
          <div class="div-datepicker">
            <v-date-picker header-color="#7ececa" v-model="dataPesquisada" class="menuHorarios"
                           @change="buscarDatas()"></v-date-picker>
          </div>

        </v-col>
        <v-col cols="2" v-if="this.componenteDatasAtiva">
          <v-card height="378px">
            <h2 class="cabecalhoHorarios">Horários Disponíveis</h2>
            <div class="menuHorarios" v-for="(horario, index) in horariosDisponiveis" :key="index">
              <v-card class="horariosDisponiveis" @click="selecionarHorarios(horario)">{{ horario.horaInicio }}</v-card>
            </div>
          </v-card>
        </v-col>

      </v-row>
      <v-row>
        <v-btn color="#1693a5" width="20vw" height="7vh" class="btnRealizarAgendamento"
               :disabled="this.isBotaoRelizarAgendamentoHabilitado"
               @click="realizarAgendamento()">Realizar agendamento
        </v-btn>
      </v-row>
    </v-col>
  </div>
</template>

<script>
import {defineComponent} from "vue";
import axios from "axios";
import router from "@/router";

export default defineComponent({
  name: "TelaAgendamento",
  data() {
    return {
      horariosDisponiveis: [],
      agendamento: {
        horaInicio: '',
        horaTermino: '',
        medico: {
          id: 0
        },
        unidade: {
          id: 0
        },
        paciente: {
          id: 0
        },
        especializacao: {
          id: 0
        },
      },
      dataSelecionada: {},
      urlBuscarHorarios: 'http://localhost:8081/agendamentos/listarHorariosDisponiveisPorData',
      urlBuscarUnidades: 'http://localhost:8081/unidades/listar',
      urlBuscarEspecialidades: 'http://localhost:8081/especializacao/listar',
      urlBuscarMedicoPorUnidadeEPorEspecializacao: 'http://localhost:8081/medicos/listarPorUnidadeEPorEspecializacao',
      urlCadastrarAgendamento: 'http://localhost:8081/agendamentos/cadastrar',
      dadosBuscaHorarios: {
        medico: {
          id: 0
        },
        dataAgendamento: ''
      },
      dataPesquisada: '',
      componenteDatasAtiva: false,
      listaDeUnidades: [],
      listaDeUnidadesCompleta: [],
      listaDeEspecialidade: [],
      listaDeEspecialidadeCompleta: [],
      listaDeMedicos: [],
      listaDeMedicosCompleta: [],
      unidadeInput: '',
      especializacaoInput: '',
      medicoInput: '',
      unidadeView: {
        descricao: '',
        endereco: {
          logradouro: '',
          numero: '',
          complemento: '',
        }
      },
      medicoView: {
        nome: '',
        crm: '',
        especializacao: {
          descricao: ''
        }
      },
      horarioAtendimentoView: '',
      isBotaoRelizarAgendamentoHabilitado: true,
      alertaLigado: false,
      tipoAlerta: '',
      msgAlerta: '',
    }
  },
  methods: {
    async buscarDatas() {
      this.limparLista();
      this.preencherRequisicaoBuscarDatas();
      this.isBotaoRelizarAgendamentoHabilitado = true;
      this.horarioAtendimentoView = '';
      this.agendamento.dataAgendamento = this.dataPesquisada;
      try {
        const request = await axios.post(this.urlBuscarHorarios, this.dadosBuscaHorarios);
        this.preencherHorariosDisponiveis(request.data);
      } catch (ex) {
        console.log(ex.message);
      }
    },
    async buscarListaDeUnidades() {
      try {
        const request = await axios.get(this.urlBuscarUnidades);
        for (let i = 0; i < request.data.length; i++) {
          this.listaDeUnidadesCompleta.push(request.data[i]);
        }
        this.preencheListaUnidadeParaComboBox();
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao buscar unidades', 3);
        console.log(ex.message);
      }
    },
    async buscarEspecialidades() {
      try {
        const request = await axios.get(this.urlBuscarEspecialidades);
        for (let i = 0; i < request.data.length; i++) {
          this.listaDeEspecialidadeCompleta.push(request.data[i]);
        }
        this.preencheListaEspecialidadeParaComboBox();
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao buscar especialidades', 3);
        console.log(ex.message);
      }
    },
    async buscarMedicosPorUnidade() {
      try {
        const request = await axios.get(`${this.urlBuscarMedicoPorUnidadeEPorEspecializacao}?idUnidade=${this.agendamento.unidade.id}&idEspecializacao=${this.agendamento.especializacao.id}`);
        for (let i = 0; i < request.data.length; i++) {
          this.listaDeMedicosCompleta.push(request.data[i]);
        }
        this.preencheListaMedicoParaComboBox();
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao buscar medicos', 3);
        console.log(ex.message);
      }
    },
    async realizarAgendamento() {
      try {
        await axios.post(this.urlCadastrarAgendamento, this.agendamento);
        await router.push('agendamentoConcluido');
      } catch (ex) {
        this.gerarAlerta('error', 'Não foi possível realizar o agendamento, entre em contato com o suporte', 3);
        console.log(ex.message);
      }
    },
    selecionarHorarios(horario) {
      this.agendamento.horaInicio = horario.horaInicio;
      this.agendamento.horaTermino = horario.horaTermino;
      this.horarioAtendimentoView = horario;
      this.isBotaoRelizarAgendamentoHabilitado = false;
    },
    preencherRequisicaoBuscarDatas() {
      this.dadosBuscaHorarios = {
        medico: {
          id: this.agendamento.medico.id,
        },
        dataAgendamento: this.dataPesquisada
      }
    },
    preencherHorariosDisponiveis(listaDeHorariosDisponiveis) {
      listaDeHorariosDisponiveis.forEach(horario => {
        this.horariosDisponiveis.push(
            {
              horaInicio: horario.horaInicio,
              horaTermino: horario.horaTermino
            }
        );
      });
    },
    limparLista() {
      this.horariosDisponiveis = [];
    },
    preencheListaUnidadeParaComboBox() {
      this.listaDeUnidadesCompleta.forEach(unidade => {
        this.listaDeUnidades.push(unidade.descricao);
      });
    },
    preencheListaEspecialidadeParaComboBox() {
      this.listaDeEspecialidadeCompleta.forEach(especializacao => {
        this.listaDeEspecialidade.push(especializacao.descricao);
      });
    },
    preencheListaMedicoParaComboBox() {
      this.listaDeMedicosCompleta.forEach(medico => {
        this.listaDeMedicos.push(medico.nome);
      });
    },
    gravarUnidade() {
      this.agendamento.unidade.id = this.filtrarUnidadePorNome();
    },
    filtrarUnidadePorNome() {
      let unidadeUtilizada = {};
      this.listaDeUnidadesCompleta.forEach(unidade => {
        if (unidade.descricao === this.unidadeInput) {
          this.unidadeView = unidade;
          unidadeUtilizada = unidade;
        }
      });
      return unidadeUtilizada ? unidadeUtilizada.id : 0;
    },
    gravarEspecialidade() {
      this.agendamento.especializacao.id = this.filtrarEspecializacaoPorNome();
      this.buscarMedicosPorUnidade();
    },
    filtrarEspecializacaoPorNome() {
      let especializacaoUtilizada = {};
      this.listaDeEspecialidadeCompleta.forEach(especializacao => {
        if (especializacao.descricao === this.especializacaoInput) {
          especializacaoUtilizada = especializacao;
        }
      });
      return especializacaoUtilizada ? especializacaoUtilizada.id : 0;
    },
    gravarMedicos() {
      this.agendamento.medico.id = this.filtrarMedicosPorNome();
      if (this.agendamento.medico.id > 0) {
        this.componenteDatasAtiva = true;
      }
    },
    filtrarMedicosPorNome() {
      let medicoUtilizado = {};
      this.listaDeMedicosCompleta.forEach(medico => {
        if (medico.nome === this.medicoInput) {
          this.medicoView = medico;
          medicoUtilizado = medico;
        }
      });
      return medicoUtilizado ? medicoUtilizado.id : 0;
    },
    preencherPaciente() {
      let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
      this.agendamento.paciente.id = dadosLogin.id;
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
  mounted() {
    this.buscarListaDeUnidades();
    this.buscarEspecialidades();
    this.preencherPaciente();
  }
})
</script>

<style scoped>
.principal {
  max-height: 350px !important;
}

label {
  display: block;
  padding-top: 15px;
  padding-bottom: 15px;
  padding-left: 10px;
}

.containerCombobox {
  text-align: center;
}

.padraoCombobox {
  width: 80%;
  padding-top: 5vh;
  margin: 0 auto;
}

.menuHorarios {
  display: inline-block;
}

.cabecalhoHorarios {
  text-align: center;
  margin-bottom: 50px;
}

.horariosDisponiveis {
  background: #45b5c4 !important;
  padding: 5px;
  margin-left: 5px;
  margin-top: 5px;
  font-weight: bold;
  color: #FFF !important;
}

.btnRealizarAgendamento {
  margin: 2vh auto;
}

.alerta-total {
  position: absolute;
  top: 1vh;
  left: 3vw;
}
</style>