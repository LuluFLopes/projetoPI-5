<template>
  <div class="principal">
    <v-col>
      <v-row>
        <v-col cols="3">
          <v-card height="378px">
          </v-card>
        </v-col>
        <v-col cols="3">
          <v-card class="container-combobox" height="378px">
            <v-combobox
                v-model="unidadeInput"
                class="padrao-combobox"
                clearable
                label="Unidades"
                variant="outlined"
                :items="this.listaDeUnidades"
                @change="gravarUnidade()"
            ></v-combobox>

            <v-combobox
                v-model="especializacaoInput"
                class="padrao-combobox"
                clearable
                label="Especialidades"
                variant="outlined"
                :items="this.listaDeEspecialidade"
                @change="gravarEspecialidade()"
            ></v-combobox>

            <v-combobox
                v-model="medicoInput"
                class="padrao-combobox"
                clearable
                label="Medicos"
                variant="outlined"
                :items="this.listaDeMedicos"
                @change="gravarMedicos()"
            ></v-combobox>
          </v-card>
        </v-col>

        <v-col cols="2" v-if="this.componenteDatasAtiva">
          <v-date-picker v-model="dataPesquisada" class="menuHorarios" @change="buscarDatas()"></v-date-picker>
        </v-col>
        <v-col cols="2" v-if="this.componenteDatasAtiva">
          <v-card height="378px">
            <h2 class="cabecalho-horarios">Horários Disponíveis</h2>
            <div class="menuHorarios" v-for="(horario, index) in horariosDisponiveis" :key="index">
              <v-card class="horariosDisponiveis" @click="selecionarHorarios(horario)">{{ horario }}</v-card>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-col>
  </div>
</template>

<script>
import {defineComponent} from "vue";
import axios from "axios";

export default defineComponent({
  name: "TelaAgendamento",
  data() {
    return {
      horariosDisponiveis: [],
      agendamento: {
        horarioAgendamento: '',
        medico: {
          id: 0
        },
        unidade: {
          id: 0
        },
        cliente: {
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
      medicoInput: ''
    }
  },
  methods: {
    async buscarDatas() {
      this.limparLista();
      this.preencherRequisicaoBuscarDatas();
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
        console.log(ex.message);
      }
    },
    selecionarHorarios(index) {
      this.agendamento.horarioAgendamento = this.horariosDisponiveis[index];
    },
    preencherRequisicaoBuscarDatas() {
      this.dadosBuscaHorarios = {
        medico: {
          id: 1
        },
        dataAgendamento: this.dataPesquisada
      }
    },
    preencherHorariosDisponiveis(listaDeHorariosDisponiveis) {
      listaDeHorariosDisponiveis.forEach(horario => {
        this.horariosDisponiveis.push(horario.horaInicio);
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
      if(this.agendamento.medico.id > 0) {
        this.componenteDatasAtiva = true;
      }

    },
    filtrarMedicosPorNome() {
      let medicoUtilizado = {};
      this.listaDeMedicosCompleta.forEach(medico => {
        if (medico.nome === this.medicoInput) {
          medicoUtilizado = medico;
        }
      });
      return medicoUtilizado ? medicoUtilizado.id : 0;
    }
  },
  mounted() {
    this.buscarListaDeUnidades();
    this.buscarEspecialidades();
  }
})
</script>

<style scoped>

.principal {
  max-height: 350px !important;
}

.container-combobox {
  text-align: center;
}

.padrao-combobox {
  width: 80%;
  padding-top: 5vh;
  margin: 0 auto;
}

.menuHorarios {
  display: inline-block;
}

.cabecalho-horarios {
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
</style>