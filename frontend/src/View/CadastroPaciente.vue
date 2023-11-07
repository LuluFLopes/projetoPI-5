<template>
  <v-container>
    <v-form @submit="cadastrarCliente">
      <v-text-field
          v-model="cliente.nome"
          label="Nome"
          required
          color="teal darken-3"
          outlined

      ></v-text-field>
      <v-text-field
          v-model="cliente.email"
          label="E-mail"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="cliente.telefone"
          label="Telefone"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="cliente.cpf"
          label="CPF"
          required
          color="teal darken-3"
          outlined
          placeholder="Ex: 000.000.000-00"
      ></v-text-field>
      <v-select
          v-model="cliente.plano"
          :items="planosSaude"
          label="Plano de Saúde"
          required
          color="teal darken-3"
          outlined
          @change="verificarOutroPlano"
      ></v-select>
      <v-text-field
          v-model="cliente.outroPlano"
          label="Especificar Outro Plano"
          color="teal darken-3"
          outlined
          class="custom-input"
          v-if="cliente.plano === 'Outro'"
      ></v-text-field>
      <v-text-field
          v-model="cliente.cartaoPlano"
          label="Número do Cartão do Plano de Saúde"
          required
          color="teal darken-3"
          outlined
          placeholder="Ex: 00000-0000-00000"
      ></v-text-field>

      <v-row>
      <v-btn type="submit" color="teal darken-2">Cadastrar Cliente</v-btn>
        <v-spacer></v-spacer>
      <v-btn @click="limparFormulario" color="error">Limpar os campos</v-btn>
      </v-row>
    </v-form>
  </v-container>
</template>


<script>
import axios from "axios";

export default {
  data() {
    return {
      url: 'http://localhost:8081/pacientes/cadastrar',
      cliente: {
        nome: '',
        email: '',
        telefone: '',
        cpf: '',
        plano: null,
        cartaoPlano: ''
      },
      planosSaude: [
        'NotreDame Intermédica',
        'Hapvida Assistência Médica',
        'Bradesco Saúde',
        'Amil Assistência Médica',
        'Central Nacional Unimed',
        'Outro',
      ]
    };
  },
  methods: {
    async cadastrarCliente() {
      try {
        alert("Deu certo o cadastro do paciente");
        await axios.post(this.url, this.cliente);
        this.limparFormulario();
      } catch (ex) {
        alert("Não foi possível fazer o cadastro!");
        console.log(ex.message);
      }
    },
    verificarOutroPlano() {
      if (this.cliente.plano !== 'Outro') {
        this.cliente.outroPlano = '';
      }
    },
    limparFormulario(){
      this.cliente.nome = '';
      this.cliente.email = '';
      this.cliente.telefone = '';
      this.cliente.cpf = '';
      this.cliente.plano = '';
      this.cliente.cartaoPlano = '';
    }
  },
};
</script>

<style>
v-container {
  background-color: #c7ede8;
}

label {
  color: #1693a5;
}

v-icon {
  color: #7ececa;
}

v-btn {
  background-color: #45b5c4;
  color: white;
}
</style>