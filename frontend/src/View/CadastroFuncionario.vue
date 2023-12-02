<template>
  <div>
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
          v-model="funcionario.cpf"
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
              v-model="funcionario.dataNascimento"
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
            v-model="funcionario.dataNascimento"
            header-color="#7ececa"
            @change="save"
        ></v-date-picker>
      </v-menu>
      <v-text-field
          v-model="funcionario.endereco.logradouro"
          label="Logradouro"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="funcionario.endereco.numero"
          label="Numero"
          required
          color="teal darken-3"
          outlined
          type="number"
      ></v-text-field>
      <v-text-field
          v-model="funcionario.endereco.complemento"
          label="Complemento"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="funcionario.endereco.cep"
          label="CEP"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="funcionario.endereco.bairro"
          label="Bairro"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="funcionario.endereco.cidade"
          label="cidade"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-select
          v-model="funcionario.endereco.uf"
          :items="ufs"
          label="Uf"
          required
          color="teal darken-3"
          outlined
      ></v-select>
      <v-text-field
          v-model="funcionario.contato.email"
          label="E-mail"
          required
          color="teal darken-3"
          outlined
          type="email"
      ></v-text-field>
      <v-text-field
          v-model="funcionario.contato.telefone"
          label="Telefone"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="funcionario.login.usuario"
          label="Login Usuario"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-text-field
          v-model="funcionario.login.senha"
          label="Login Senha"
          required
          color="teal darken-3"
          outlined
      ></v-text-field>
      <v-select
          v-model="funcionario.status"
          :items="statuses"
          label="Status"
          required
          color="teal darken-3"
          outlined
      ></v-select>
      <v-select
          v-model="funcionario.genero"
          :items="generos"
          label="Genero"
          required
          color="teal darken-3"
          outlined
      ></v-select>
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
          v-model="funcionario.crm"
          label="CRM"
          color="teal darken-3"
          outlined
          v-if="funcionario.tipoFuncionario === 'MEDICO'"
      ></v-text-field>
      <v-select
          v-model="funcionario.especializacaoId"
          :items="opcoesDoSelect"
          item-text="descricao"
          item-value= "id"
          label="Especializacao"
          color="teal darken-3"
          outlined
          type="number"
          v-if="funcionario.tipoFuncionario === 'MEDICO'"
      >
        <template v-slot:item="{ item } ">
          {{ item.id }}
          {{ item.descricao }}
          {{ item.status }}
        </template>
      </v-select>
      <v-select
          v-model="funcionario.unidadeId"
          :items="opcoesDaUnidade"
          item-text="descricao"
          item-value="id"
          label="Unidade"
          color="teal darken-3"
          outlined
          type="number"
          v-if="funcionario.tipoFuncionario === 'MEDICO'"
      >
        <template v-slot:item1="{ item1 } ">
          {{ item1.id }}
          {{ item1.descricao }}
          {{ item1.status }}
        </template>
      </v-select>
      <v-select
          v-model="funcionario.cargo"
          :items="cargos"
          label="Cargo"
          color="teal darken-3"
          outlined
          type="number"
          v-if="funcionario.tipoFuncionario === 'FUNCIONARIO'"
      ></v-select>

      <v-row>
        <v-btn type="submit" color="teal darken-2">Cadastrar</v-btn>
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
      funcionario: {
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
      },
      tipos: [
        {
          text:'Médico',
          value:'MEDICO'
        },
        {
          text:'Funcionário',
          value:'FUNCIONARIO'
        }
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
      ],
      cargos: [
        'ADMINISTRADOR',
        'ATENDENTE'
      ],
      opcoesDoSelect: [],
      opcoesDaUnidade: [],
      alertaLigado: false,
      tipoAlerta: '',
      msgAlerta: '',
    };
  },
  methods: {
    async cadastrarCliente() {
      if (this.funcionario.tipoFuncionario === 'MEDICO') {
        try {
          this.gerarAlerta('info', "sucesso em criar cadastro de medico", 3);
          alert(this.funcionario.especializacao.id);
          await axios.post('http://localhost:8081/medicos/cadastrar', this.funcionario);

          //this.limparFormulario();
        } catch (ex) {
          this.gerarAlerta('error', 'Não foi possível realizar o cadastro do médico!', 3);
          console.log(ex.message);
        }
      } else if (this.funcionario.tipoFuncionario === 'FUNCIONARIO'){
        try {
          this.gerarAlerta('info', "sucesso em criar cadastro de funcionario", 3);
          await axios.post('http://localhost:8081/funcionarios/cadastrar', this.funcionario);

          //this.limparFormulario();
        } catch (ex) {
          this.gerarAlerta('error', 'Não foi possível realizar o cadastro do funcionário!', 3);
          console.log(ex.message);
        }
      }

    },
    verificarTipo() {
      if (this.funcionario.tipoFuncionario === 'MEDICO') {
        this.funcionario.crm = '';
        this.funcionario.especializacao = {
          id: this.funcionario.especializacaoId
      };
        this.funcionario.unidade = {
          id: this.funcionario.unidadeId
        };
      }
      if (this.funcionario.tipoFuncionario === 'FUNCIONARIO'){
        this.funcionario.cargo = '';
      }
    },
    limparFormulario(){
      this.funcionario.nome = '';
      this.funcionario.cpf = '';
      this.funcionario.dataNascimento = '';
      this.funcionario.endereco = '';
      this.funcionario.contato = '';
      this.funcionario.login.usuario = '';
      this.funcionario.login.senha = '';
      this.funcionario.status = '';
      this.funcionario.genero = '';

    },
    save (date) {
      this.$refs.menu.save(date)
    },
    async fetchData() {
      try {
        const response = await axios.get('http://localhost:8081/especializacao/listar');
        this.opcoesDoSelect = response.data;
      } catch (error) {
        this.gerarAlerta('error', 'Erro ao recuperar os dados das especializacoes', 3);
      }
    },
    async pegarUnidade(){
      try{
        const response2 = await axios.get('http://localhost:8081/unidades/listar');
        this.opcoesDaUnidade = response2.data;
      } catch(error) {
        this.gerarAlerta('error', 'Erro ao recuperar os dados das unidades', 3);
      }
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
  mounted(){
    this.fetchData();
    this.pegarUnidade();
  }


};
</script>

<style scoped>
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

.alerta-total {
  position: absolute;
  top: 1vh;
  left: 3vw;
}
</style>