<template>
  <v-row
      align="center"
      justify="center"
  >
    <v-col cols="12">
      <v-hover
          v-slot="{ hover }"
      >
        <v-card
            :elevation="hover ? 12 : 2"
            class="mx-auto"
            height="200"
            max-width="1500"
            color="#45b5c4"
        >
          <br>
          <p class="texto-cartao">Especialização: {{ dadosAgendamento.medico.especializacao.descricao }}</p>
          <p class="texto-cartao">Profissional: {{ dadosAgendamento.medico.nome }}</p>
          <p class="texto-cartao">Data e Hora: {{ dataConvertida }} as {{ horarioConvertido }}</p>
          <v-btn color="#1693a5" class="btn-detalhes" @click="exibirDetalhes()">Detalhes</v-btn>
        </v-card>
      </v-hover>
    </v-col>
  </v-row>
</template>

<script>
import {defineComponent} from "vue";

export default defineComponent({
  name: "HoverHistorico",
  props: {
    dadosAgendamento: Object,
  },
  data() {
    return {}
  },
  computed: {
    dataConvertida(){
      let data = this.dadosAgendamento.dataAgendamento;
      let dataEmString = new Date(`${data[0]}-${data[1]}-${data[2]}`);
      return dataEmString.toLocaleDateString('pt-BR');
    },
    horarioConvertido(){
      let hora = this.dadosAgendamento.horaInicio;
      let horarioConvertido = new Date();
      horarioConvertido.setHours(hora[0]);
      horarioConvertido.setMinutes(hora[1]);
      horarioConvertido.setSeconds(0);
      return horarioConvertido.toLocaleTimeString('pr-BR');
    }
  },
  emits: ['ao-clicar-botao-detalhes'],
  methods: {
    exibirDetalhes() {
      this.$emit('ao-clicar-botao-detalhes');
    }
  }
})
</script>

<style scoped>
.texto-cartao {
  font-weight: bold;
  margin-left: 2vw;
}

.btn-detalhes {
  margin-left: 90%;
}
</style>