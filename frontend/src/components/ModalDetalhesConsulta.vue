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
                <p class="padrao-linha padrao-esquerda">Nome Completo: {{ detalhesConsulta.paciente.nome }}</p>
                <p class="padrao-linha">CPF: {{ detalhesConsulta.paciente.cpf }}</p>
                <p class="padrao-linha padrao-direita">Genero: {{ detalhesConsulta.paciente.genero }}</p>
              </div>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Data Nascimento: {{  }}</p>
              </div>
            </v-card>
            <v-card class="elementos" elevation="10">
              <h2 class="sub-titulos">Informações do Médico</h2>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Nome Completo: {{ }}</p>
                <p class="padrao-linha">CPF: {{ }}</p>
                <p class="padrao-linha padrao-direita">Sexo: {{ }}</p>
              </div>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Nome Completo: {{ }}</p>
                <p class="padrao-linha">CPF: {{ }}</p>
                <p class="padrao-linha padrao-direita">Sexo: {{ }}</p>
              </div>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Nome Completo: {{ }}</p>
                <p class="padrao-linha">CPF: {{ }}</p>
                <p class="padrao-linha padrao-direita">Sexo: {{ }}</p>
              </div>
            </v-card>
            <v-card class="elementos" elevation="10">
              <h2 class="sub-titulos">Informações do Agendamento</h2>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Nome Completo: {{ }}</p>
                <p class="padrao-linha">CPF: {{ }}</p>
                <p class="padrao-linha padrao-direita">Sexo: {{ }}</p>
              </div>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Nome Completo: {{ }}</p>
                <p class="padrao-linha">CPF: {{ }}</p>
                <p class="padrao-linha padrao-direita">Sexo: {{ }}</p>
              </div>
              <div class="padrao-div">
                <p class="padrao-linha padrao-esquerda">Nome Completo: {{ }}</p>
                <p class="padrao-linha">CPF: {{ }}</p>
                <p class="padrao-linha padrao-direita">Sexo: {{ }}</p>
              </div>
            </v-card>
          </div>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
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

export default defineComponent({
  name: "ModalDetalhesConsulta",
  emits: ['ao-clicar-botao-fechar-modal'],
  props: {
    isAberto: Boolean,
    detalhesConsulta: Object,
  },
  methods: {
    desativarModal() {
      this.$emit('ao-clicar-botao-fechar-modal');
    }
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
  margin-top: 1vh;
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