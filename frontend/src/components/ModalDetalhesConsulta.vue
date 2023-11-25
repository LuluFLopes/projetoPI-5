<template>
  <v-row justify="center">
    <v-dialog

        :value="isAberto"
        persistent
        width="800"
    >
      <v-card>
        <v-card-title class="text-h5">
          Dados da Consulta
        </v-card-title>
        <v-container class="container-conteudo">

        </v-container>
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

export default defineComponent ({
  name: "ModalDetalhesConsulta",
  emits: ['ao-clicar-botao-fechar-modal'],
  props: {
    isAberto: Boolean,
  },
  methods: {
    desativarModal(){
      this.$emit('ao-clicar-botao-fechar-modal');
    }
  }
})
</script>

<style scoped>
.container-conteudo {
  height: 200vh !important;
  max-height: 70vh;
}

</style>