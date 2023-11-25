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
          <p class="texto-cartao">Especialização: {{  }}</p>
          <p class="texto-cartao">Profissional: {{  }}</p>
          <p class="texto-cartao">Data e Hora: {{  }} as {{  }}</p>
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
  emits: ['ao-clicar-botao-detalhes'],
  methods: {
    exibirDetalhes(){
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