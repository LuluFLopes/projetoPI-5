<template>
  <div class="div-principal">
    <h1 class="titulo-principal">Cadastro de Usuarios</h1>
    <v-card class="cartao-principal">
      <div class="div-secundaria">
        <v-text-field class="campos-padrao" label="Nome" v-model="nome">
        </v-text-field>

        <v-text-field class="campos-padrao" label="CPF" v-model="cpf">
        </v-text-field>

        <v-menu
            ref="modal"
            v-model="modal"
            :close-on-content-click="false"
            transition="scale-transition"
            offset-y
            max-width="290px"
            min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
                class="campos-padrao"
                :value="dataFormatada"
                @input="parseDate(dataFormatada)"
                label="Data Nascimento"
                persistent-hint
                prepend-icon="mdi-calendar"
                v-bind="attrs"
                v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
              v-model="dataNascimento"
              no-title
              @input="modal = false"
          ></v-date-picker>
        </v-menu>

        <v-text-field class="campos-padrao" label="Logradouro" v-model="logradouro">
        </v-text-field>
      </div>

      <div class="div-secundaria">
        <v-text-field class="campos-padrao" label="Numero" v-model="numero">
        </v-text-field>

        <v-text-field class="campos-padrao" label="Complemento" v-model="complemento">
        </v-text-field>

        <v-text-field class="campos-padrao" label="Cep" v-model="cep">
        </v-text-field>

        <v-text-field class="campos-padrao" label="Bairro" v-model="bairro">
        </v-text-field>
      </div>


      <div class="div-secundaria">
        <v-text-field class="campos-padrao" label="Cidade" v-model="cidade">
        </v-text-field>

        <v-select class="campos-padrao" label="UF" v-model="uf" :items="listaUf">
        </v-select>

        <v-text-field class="campos-padrao" label="Email" v-model="email">
        </v-text-field>

        <v-text-field class="campos-padrao" label="Telefone" v-model="telefone">
        </v-text-field>
      </div>

      <div class="div-ternaria">
        <v-select class="campos-padrao" label="Genero" v-model="genero" :items="listaGeneros">
        </v-select>

        <v-select class="campos-padrao campo-final" label="Tipo de Cadastro" v-model="tipoCadastro"
                  :items="listaTiposCadastros" @change="ativarTipoDeCadastro">
        </v-select>
      </div>

      <div class="div-secundaria" v-if="camposMedicoAtivo">
        <v-text-field class="campos-padrao" label="Crm" v-model="crm">
        </v-text-field>

        <v-select class="campos-padrao" label="Especialização" v-model="especializacao" :items="listaEspecializacoes">
        </v-select>

        <v-select class="campos-padrao" label="Unidades" v-model="unidade" :items="listaUnidades">
        </v-select>
      </div>

      <div class="div-secundaria" v-if="camposFuncionarioAtivo">
        <v-select class="campos-padrao" label="Cargos" v-model="cargo" :items="listaCargos">
        </v-select>
      </div>

      <div class="quarta-div">
        <div class="div-botoes">
          <v-btn class="texto-botoes" color="#7ececa" @click="voltarTelaHomeAdm">
            Voltar
          </v-btn>

          <v-btn class="texto-botoes" color="#7ececa">
            Confirmar
          </v-btn>
        </div>
      </div>
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
import {defineComponent} from "vue";
import axios from "axios";
import router from "@/router";
import {mapMutations} from "vuex";

export default defineComponent({
  name: "IncluirUsuario",
  data() {
    return {
      nome: '',
      cpf: '',
      dataNascimento: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      logradouro: '',
      numero: '',
      complemento: '',
      cep: '',
      bairro: '',
      cidade: '',
      uf: '',
      email: '',
      telefone: '',
      genero: '',
      tipoCadastro: '',
      crm: '',
      especializacao: {},
      unidade: {},
      cargo: {},
      listaUf: [],
      listaTiposCadastros: [],
      listaGeneros: [],
      listaEspecializacoes: [],
      listaUnidades: [],
      listaCargos: [],
      urlListaUf: 'http://localhost:8081/listas/uf',
      urlListaTiposCadastros: 'http://localhost:8081/listas/tiposCadastros',
      urlListaGeneros: 'http://localhost:8081/listas/generos',
      urlListaEspecializacoes: 'http://localhost:8081/especializacao/listar',
      urlListaUnidades: 'http://localhost:8081/unidades/listar',
      urlListaCargos: 'http://localhost:8081/listas/cargos',
      alertaLigado: false,
      tipoAlerta: '',
      msgAlerta: '',
      dataFormatada: this.formatDate((new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)),
      modal: false,
      camposMedicoAtivo: false,
      camposFuncionarioAtivo: false,
    }
  },
  methods: {
    ...mapMutations([
      "selecionarItemMenuLateral"
    ]),
    async buscarUf() {
      try {
        const response = await axios.get(this.urlListaUf);
        response.data.forEach((uf) => {
          this.listaUf.push(uf);
        });
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao carregar uf', 3);
      }
    },
    async buscarTiposCadastros() {
      try {
        const response = await axios.get(this.urlListaTiposCadastros);
        response.data.forEach((tipoCadastro) => {
          if (tipoCadastro.nome !== 'ADMINISTRADOR') {
            this.listaTiposCadastros.push(
                {
                  text: tipoCadastro.descricao,
                  value: tipoCadastro.nome,
                }
            );
          }
        });
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao carregar tipos de cadastros', 3);
      }
    },
    async buscarGeneros() {
      try {
        const response = await axios.get(this.urlListaGeneros);
        response.data.forEach((genero) => {
          this.listaGeneros.push(
              {
                text: genero.descricao,
                value: genero.nome,
              }
          );
        });
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao carregar generos', 3);
      }
    },
    async buscarEspecializacao() {
      try {
        const response = await axios.get(this.urlListaEspecializacoes);
        response.data.forEach((especializacao) => {
          this.listaEspecializacoes.push(
              {
                text: especializacao.descricao,
                value: especializacao.id,
              }
          );
        });
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao carregar especializações', 3);
      }
    },
    async buscarUnidades() {
      try {
        const response = await axios.get(this.urlListaUnidades);
        response.data.forEach((unidades) => {
          this.listaUnidades.push(
              {
                text: unidades.descricao,
                value: unidades.id,
              }
          );
        });
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao carregar unidades', 3);
      }
    },
    async buscarCargos() {
      try {
        const response = await axios.get(this.urlListaCargos);
        response.data.forEach((cargo) => {
          this.listaCargos.push(
              {
                text: cargo.descricao,
                value: cargo.nome,
              }
          );
        });
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao carregar cargos', 3);
      }
    },
    formatDate(date) {
      if (!date) return null

      const [year, month, day] = date.split('-')
      return `${day}/${month}/${year}`
    },
    parseDate(date) {
      if (!date) return null

      const [month, day, year] = date.split('/')
      return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
    },
    voltarTelaHomeAdm() {
      this.selecionarItemMenuLateral(0);
      router.push('gerenciarCadastros');
    },
    ativarTipoDeCadastro() {
      switch (this.tipoCadastro) {
        case 'MEDICO':
          this.desabilitarBotoes();
          this.camposMedicoAtivo = true;
          break;
        case 'FUNCIONARIO':
          this.desabilitarBotoes();
          this.camposFuncionarioAtivo = true;
          break;
        case 'PACIENTE':
          this.desabilitarBotoes();
          break;
      }
    },
    desabilitarBotoes() {
      this.camposMedicoAtivo = false;
      this.camposFuncionarioAtivo = false;
    },
    gerarAlerta(tipoDeAlerta, mensagem, segundosParaFechar) {
      this.tipoAlerta = tipoDeAlerta;
      this.msgAlerta = mensagem;
      this.alertaLigado = true;

      setTimeout(() => {
        this.alertaLigado = false;
      }, segundosParaFechar * 1000);
    },
    inicializarDados() {
      this.buscarUf();
      this.buscarTiposCadastros();
      this.buscarGeneros();
      this.buscarEspecializacao();
      this.buscarUnidades();
      this.buscarCargos();
    },
  },
  watch: {
    dataNascimento() {
      this.dataFormatada = this.formatDate(this.dataNascimento)
    },
  },
  beforeMount() {
    this.inicializarDados();
  }
})
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