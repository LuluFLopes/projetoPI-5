<template>
  <v-container>
    <v-form @submit="cadastrarCliente">
      <v-text-field
          v-model="funcionario.nome"
          label="Nome"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="funcionario.email"
          label="E-mail"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="funcionario.telefone"
          label="Telefone"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="funcionario.cpf"
          label="CPF"
          required
          color="teal darken-3"
          outlined
          placeholder="Ex: 000.000.000-00"
      ></v-text-field>
      <v-select
          v-model="funcionario.tipoFuncionario"
          :items="tipos"
          label="Tipo de Funcionário"
          required
          color="teal darken-3"
          outlined
          @change="verificarTipo"
      ></v-select>
      <v-text-field
          v-model="funcionario.medicoEspecialidade"
          label="Especialidade"
          color="teal darken-3"
          outlined
          v-if="funcionario.tipoFuncionario === 'Médico'"
      ></v-text-field>
      <v-text-field
          v-model="funcionario.medicoDisponibilidade"
          label="Disponibilidade"
          color="teal darken-3"
          outlined
          v-if="funcionario.tipoFuncionario === 'Médico'"
      ></v-text-field>
      <v-text-field
          v-model="funcionario.funcionarioArea"
          label="Área"
          color="teal darken-3"
          outlined
          v-if="funcionario.tipoFuncionario === 'Funcionário'"
      ></v-text-field>
      <v-select
          v-model="funcionario.funcionarioHorario"
          :items="horarios"
          label="Horário"
          color="teal darken-3"
          outlined
          v-if="funcionario.tipoFuncionario === 'Funcionário'"
      ></v-select>

      <v-row>
        <v-btn type="submit" color="teal darken-2">Cadastrar</v-btn>
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
      funcionario: {
        nome: '',
        email: '',
        telefone: '',
        cpf: '',
        tipoFuncionario: null,
      },
      tipos: ['Médico','Funcionário'],
      horarios: ['08:00 - 17:00', '16:00 - 01:00', '24:00 - 09:00'],
    };
  },
  methods: {
    async cadastrarCliente() {
      if (this.funcionario.tipoFuncionario === 'Médico') {
        try {
          alert("Deu certo o cadastro do medico");
          await axios.post('http://localhost:8081/medicos/cadastrar', this.funcionario);

          this.limparFormulario();
        } catch (ex) {
          alert("Não foi possível fazer o cadastro do médico!");
          console.log(ex.message);
        }
      } else if (this.funcionario.tipoFuncionario === 'Funcionário'){
        try {
          alert("Deu certo o cadastro do funcionario");
          await axios.post('http://localhost:8081/funcionarios/cadastrar', this.funcionario);

          this.limparFormulario();
        } catch (ex) {
          alert("Não foi possível fazer o cadastro do funcionário!");
          console.log(ex.message);
        }
      }

    },
    verificarTipo() {
      if (this.funcionario.tipoFuncionario !== 'Médico') {
        this.funcionario.medicoEspecialidade = '';
        this.funcionario.medicoDisponibilidade = '';
      }
      if (this.funcionario.tipoFuncionario !== 'Funcionário'){
        this.funcionario.funcionarioArea = '';
        this.funcionario.funcionarioHorario = null;
      }
    },
    limparFormulario(){
      this.funcionario.nome = '';
      this.funcionario.email = '';
      this.funcionario.telefone = '';
      this.funcionario.cpf = '';
      this.funcionario.plano = '';
      this.funcionario.cartaoPlano = '';
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