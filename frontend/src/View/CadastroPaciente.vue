<template>
  <div class="div-principal">
    <h1 class="titulo-principal">Cadastre-se</h1>
  <v-card class="cartao-principal">
    <v-form @submit="cadastrarCliente">
      <div class="div-secundaria">
      <v-text-field
          class="campos-padrao"
          v-model="cliente.nome"
          label="Nome"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          class="campos-padrao"
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
              class="campos-padrao"
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
          class="campos-padrao"
          v-model="cliente.endereco.logradouro"
          label="Logradouro"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      </div>
      <div class="div-secundaria">
      <v-text-field
          class="campos-padrao"
          v-model="cliente.endereco.numero"
          label="Numero"
          required
          color="teal darken-3"
          outlined
          type="number"
      ></v-text-field>
      <v-text-field
          class="campos-padrao"
          v-model="cliente.endereco.complemento"
          label="Complemento"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          class="campos-padrao"
          v-model="cliente.endereco.cep"
          label="CEP"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          class="campos-padrao"
          v-model="cliente.endereco.bairro"
          label="Bairro"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      </div>
      <div class="div-secundaria">
      <v-text-field
          class="campos-padrao"
          v-model="cliente.endereco.cidade"
          label="cidade"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>

      <v-select
          class="campos-padrao"
          v-model="cliente.endereco.uf"
          :items="ufs"
          label="Uf"
          required
          color="teal darken-3"
          outlined
      ></v-select>
      <v-text-field
          class="campos-padrao"
          v-model="cliente.contato.email"
          label="E-mail"
          required
          color="teal darken-3"
          outlined
          type="email"
      ></v-text-field>
      <v-text-field
          class="campos-padrao"
          v-model="cliente.contato.telefone"
          label="Telefone"
          required
          color="teal darken-3"
          outlined
          type="phone"
          placeholder="(##) #####-####"
      ></v-text-field>
      </div>
      <div class="div-ternaria">
      <v-text-field
          class="campos-padrao"
          v-model="cliente.login.usuario"
          label="Login Usuario"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          class="campos-padrao"
          v-model="cliente.login.senha"
          label="Login Senha"
          required
          color="teal darken-3"
          outlined
          type="password"
      ></v-text-field>
      <v-select
          class="campos-padrao campo-final"
          v-model="cliente.genero"
          :items="generos"
          label="Genero"
          required
          color="teal darken-3"
          outlined
      ></v-select>
      </div>
      <div class="quarta-div">
        <div class="div-botoes">
          <v-btn class="texto-botoes" color="#7ececa" @click="voltarTelaHome">
            Voltar
          </v-btn>

          <v-btn class="texto-botoes" color="#7ececa" type="submit">
            Confirmar
          </v-btn>
        </div>
      </div>
    </v-form>
  </v-card>
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
import router from "@/router";

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
        status: 'ATIVO',
        genero: '',
      },
      generos: [
        'MASCULINO',
        'FEMININO',
        'PREFIRO_NAO_INFORMAR'
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
        this.gerarAlerta('error', 'Não foi possível realizar o cadastro!', 3);
      }
    },
    limparFormulario(){
      this.cliente.nome = '';
      this.cliente.cpf = '';
      this.cliente.dataNascimento = '';
      this.cliente.endereco = '';
      this.cliente.contato = '';
      this.cliente.login = '';
      this.cliente.genero = '';
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
    voltarTelaHome() {
      router.push('home');
    },
  },
};
</script>

<style scoped>

.div-principal {
  margin-top: 3vh;
  text-align: center;
}

.titulo-principal {
  margin-bottom: 5vh;
}

.cartao-principal {
  width: 60vw;
  max-width: 60vw;
  margin: 0 auto;
  padding: 5vh;
}

.div-secundaria {
  display: flex;
  justify-content: space-between;
  width: 55vw;
  padding: 10px;
}

.div-ternaria {
  display: flex;
  width: 55vw;
  margin: 0 auto;
  padding: 10px;
  justify-content: space-between;
}

.campos-padrao {
  width: 12vw;
  max-width: 12vw;
}

.campo-final {
  margin-left: 2vw;
}

.quarta-div {
  display: flex;
  justify-content: center;
  margin-top: 2vh;
  height: 7vh;
}

.div-botoes {
  width: 25vw;
  display: flex;
  justify-content: space-between;
}

.texto-botoes {
  color: #FFFFFF !important;
}

.alerta-total {
  position: absolute;
  top: 1vh;
  left: 3vw;
}

</style>