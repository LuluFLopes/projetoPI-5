<template>
  <div class="principal">
    <v-row>
      <v-col cols="2">
          <v-date-picker v-model="dataPesquisada" class="menuHorarios" @change="buscarDatas()"></v-date-picker>
      </v-col>
      <v-col cols="2">
        <v-card height="378px">
          <div class="menuHorarios" v-for="(horario, index) in horariosDisponiveis" :key="index">
            <v-card class="horariosDisponiveis" @click="this.selecionarHorarios(horario)">{{ horario }}</v-card>
          </div>
        </v-card>
      </v-col>
    </v-row>
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
        medico: {},
        horarioAgendamento: '',
        unidade: {},
        cliente: {},
      },
      dataSelecionada: {},
      urlBuscarHorarios: 'http://localhost:8081/agendamentos/listarHorariosDisponiveisPorData',
      dadosBuscaHorarios: {
        medico: {
          id: 0
        },
        dataAgendamento: ''
      },
      dataPesquisada: ''
    }
  },
  methods: {
    selecionarHorarios(index) {
      this.agendamento.horarioAgendamento = this.horariosDisponiveis[index];
    },
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
    }
  }
})
</script>

<style scoped>

.principal {
  max-height: 350px !important;
}

.menuHorarios {
  display: inline-block;
}

.horariosDisponiveis {
  background: #45b5c4;
  padding: 5px;
  margin-left: 5px;
  margin-top: 5px;
  font-weight: bold;
  color: #FFF;
}

</style>