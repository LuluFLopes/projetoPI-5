<template>
  <div>
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="5">
        <v-text-field label="Nome" v-model="nome">
        </v-text-field>
      </v-col>
      <v-col cols="5">
        <v-text-field label="CPF" v-model="cpf">
        </v-text-field>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="5">
        <v-date-picker v-model="dataNascimento">
        </v-date-picker>
      </v-col>
      <v-col cols="5">
        <v-text-field label="Logradouro" v-model="logradouro">
        </v-text-field>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="5">
        <v-text-field label="Numero" type="number" v-model="numero">
        </v-text-field>
      </v-col>
      <v-col cols="5">
        <v-text-field label="Complemento" v-model="complemento">
        </v-text-field>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="5">
        <v-text-field label="Cep" v-model="cep">
        </v-text-field>
      </v-col>
      <v-col cols="5">
        <v-text-field label="Bairro" v-model="bairro">
        </v-text-field>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="5">
        <v-text-field label="Cidade" v-model="cidade">
        </v-text-field>
      </v-col>
      <v-col cols="5">
        <v-select label="UF" :items="uf">
        </v-select>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="5">
        <v-text-field label="Email" v-model="email">
        </v-text-field>
      </v-col>
      <v-col cols="5">
        <v-text-field label="Telefone" v-model="telefone">
        </v-text-field>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="5">
        <v-select label="Genero" v-model="genero">
        </v-select>
      </v-col>
      <v-col cols="5">
        <v-select label="Tipo de Cadastro" :items="tipoCadastro">
        </v-select>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
  </div>
</template>

<script>
import {defineComponent} from "vue";

export default defineComponent({
  name: "IncluirUsuario",
  data() {
    return {
      nome: '',
      cpf: '',
      dataNascimento: '',
      logradouro: '',
      numero: 0,
      complemento: '',
      cep: '',
      bairro: '',
      cidade: '',
      uf: '',
      email: '',
      telefone: '',
      genero: '',
      tipoCadastro: '',
    }
  }
})
</script>

<style scoped>

</style>