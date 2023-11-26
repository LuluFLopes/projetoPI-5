<template>
  <div>
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
          v-model="cliente.cpf"
          label="CPF"
          required
          color="teal darken-3"
          outlined
          placeholder="Ex: 000.000.000-00"
      ></v-text-field>
      <v-menu
          ref="menu"
          v-model="menu"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          min-width="auto"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
              v-model="cliente.dataNascimento"
              label="Data de nascimento"
              required
              color="teal darken-3"
              outlined
              prepend-icon="mdi-calendar"
              readonly
              v-bind="attrs"
              v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker
            v-model="cliente.dataNascimento"
            header-color="#7ececa"
            @change="save"
        ></v-date-picker>
      </v-menu>
      <v-text-field
          v-model="cliente.endereco.logradouro"
          label="Logradouro"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="cliente.endereco.numero"
          label="Numero"
          required
          color="teal darken-3"
          outlined
          type="number"
      ></v-text-field>
      <v-text-field
          v-model="cliente.endereco.complemento"
          label="Complemento"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="cliente.endereco.cep"
          label="CEP"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="cliente.endereco.bairro"
          label="Bairro"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="cliente.endereco.cidade"
          label="cidade"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-select
          v-model="cliente.endereco.uf"
          :items="ufs"
          label="Uf"
          required
          color="teal darken-3"
          outlined
      ></v-select>
      <v-text-field
          v-model="cliente.contato.email"
          label="E-mail"
          required
          color="teal darken-3"
          outlined
          type="email"
      ></v-text-field>
      <v-text-field
          v-model="cliente.contato.telefone"
          label="Telefone"
          required
          color="teal darken-3"
          outlined
          type="phone"
          placeholder="(##) #####-####"
      ></v-text-field>
      <v-text-field
          v-model="cliente.login.usuario"
          label="Login Usuario"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="cliente.login.senha"
          label="Login Senha"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-select
          v-model="cliente.status"
          :items="statuses"
          label="Status"
          required
          color="teal darken-3"
          outlined
      ></v-select>
      <v-select
          v-model="cliente.genero"
          :items="generos"
          label="Genero"
          required
          color="teal darken-3"
          outlined
      ></v-select>


      <v-row>
      <v-btn type="submit" color="teal darken-2">Cadastrar Cliente</v-btn>
        <v-spacer></v-spacer>
      <v-btn @click="limparFormulario" color="error">Limpar os campos</v-btn>
      </v-row>
    </v-form>
  </v-container>
  <v-alert class="alerta-total"
           v-if="alertaLigado"
           dismissible
           elevation="24"
           :type="tipoAlerta"
  >{{ this.msgAlerta }}
  </v-alert>
  </div>
</template>


<script>
import axios from "axios";

export default {
  data() {
    return {
      url: 'http://localhost:8081/pacientes/cadastrar',
      cliente: {
        nome: '',
        cpf: '',
        dataNascimento: '',
        endereco: {
          logradouro: '',
          numero: '',
          complemento: '',
          cep: '',
          bairro: '',
          cidade: '',
          uf: ''
        },
        contato: {
          email: '',
          telefone: ''
        },
        login: {
          usuario: '',
          senha: ''
        },
        status: '',
        genero: '',
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
      ],
      generos: [
        'MASCULINO',
        'FEMININO',
        'PREFIRO_NAO_INFORMAR'
      ],
      statuses: [
        'ATIVO',
        'INATIVO'
      ],
      ufs: [
        'AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG',
        'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'
      ]
    };
  },
  methods: {
    async cadastrarCliente() {
      try {
        this.gerarAlerta('info', 'Deu certo o cadastro do paciente', 3);
        await axios.post(this.url, this.cliente);
        this.limparFormulario();
      } catch (ex) {
        this.gerarAlerta('error', 'Não foi possível realizar o cadastro', 3);
      }
    },
    verificarOutroPlano() {
      if (this.cliente.plano !== 'Outro') {
        this.cliente.outroPlano = '';
      }
    },
    limparFormulario(){
      this.cliente.nome = '';
      this.cliente.cpf = '';
      this.cliente.dataNascimento = '';
      this.cliente.endereco = '';
      this.cliente.contato = '';
      this.cliente.login = '';
      this.cliente.status = '';
      this.cliente.genero = '';
      this.cliente.plano = '';
      this.cliente.cartaoPlano = '';
    },
    save (date) {
      this.$refs.menu.save(date)
    },
    gerarAlerta(tipoDeAlerta, mensagem, segundosParaFechar) {
      this.tipoAlerta = tipoDeAlerta;
      this.msgAlerta = mensagem;
      this.alertaLigado = true;

      setTimeout(() => {
        this.alertaLigado = false;
      }, segundosParaFechar * 1000);
    },
  },


};
</script>

<style>
v-container {
  background-color: #c7ede8;
  display: flex;
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

main {
  margin-bottom: 70px;
}

.alerta-total {
  position: absolute;
  top: 1vh;
  left: 3vw;
}

</style>