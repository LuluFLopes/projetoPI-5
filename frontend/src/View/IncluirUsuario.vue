<template>
  <div class="div-principal">
    <h1 class="titulo-principal">Cadastro de Usuarios</h1>
    <v-card class="cartao-principal">
      <div class="div-secundaria">
        <v-text-field class="campos-padrao" label="Nome" v-model="nome">
        </v-text-field>

        <v-text-field class="campos-padrao" label="CPF" v-model="cpf">
        </v-text-field>

        <v-dialog
            ref="dialog"
            v-model="modal"
            :return-value.sync="dataNascimento"
            persistent
            width="290px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
                class="campos-padrao"
                v-model="dataNascimento"
                label="Data Nascimento"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
              v-model="dataNascimento"
              scrollable
          >
            <v-spacer></v-spacer>
            <v-btn
                text
                color="primary"
                @click="modal = false"
            >
              Cancel
            </v-btn>
            <v-btn
                text
                color="primary"
                @click="$refs.dialog.save(dataNascimento)"
            >OK
            </v-btn>
          </v-date-picker>
        </v-dialog>

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
      <v-divider/>

      <div class="div-secundaria">
        <v-text-field class="campos-padrao" label="Login" v-model="usuario">
        </v-text-field>

        <v-text-field class="campos-padrao" type="password" label="Senha" v-model="senha">
        </v-text-field>

        <v-text-field class="campos-padrao" type="password" label="Confirmar Senha" v-model="confirmarSenha">
        </v-text-field>
      </div>
      <v-divider/>

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

          <v-btn class="texto-botoes" color="#7ececa" @click="salvarUsuario">
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
      usuario: '',
      senha: '',
      confirmarSenha: '',
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
      urlSalvarMedico: 'http://localhost:8081/medicos/cadastrar',
      urlSalvarFuncionario: 'http://localhost:8081/funcionarios/cadastrar',
      urlSalvarPaciente: 'http://localhost:8081/pacientes/cadastrar',
      alertaLigado: false,
      tipoAlerta: '',
      msgAlerta: '',
      modal: false,
      camposMedicoAtivo: false,
      camposFuncionarioAtivo: false,
      usuarioACadastrar: {},
    }
  },
  methods: {
    ...mapMutations([
      'selecionarItemMenuLateral'
    ]),
    async salvarMedico() {
      try {
        await axios.post(this.urlSalvarMedico, this.usuarioACadastrar);
        this.voltarTelaHomeAdm();
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao cadastrar', 3);
      }
    },
    async salvarFuncionario() {
      try {
        await axios.post(this.urlSalvarFuncionario, this.usuarioACadastrar);
        this.voltarTelaHomeAdm();
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao cadastrar', 3);
      }
    },
    async salvarPaciente() {
      try {
        await axios.post(this.urlSalvarPaciente, this.usuarioACadastrar);
        this.voltarTelaHomeAdm();
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao cadastrar', 3);
      }
    },
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
    salvarUsuario() {
      if (this.tipoCadastro !== '') {
        if (this.validaConfirmacaoDeSenha()) {
          this.preencheUsuarioAntesDoCadastro();
          switch (this.tipoCadastro) {
            case 'MEDICO':
              this.salvarMedico();
              break;
            case 'FUNCIONARIO':
              this.salvarFuncionario();
              break;
            case 'PACIENTE':
              this.salvarPaciente();
              break;
          }
        } else {
          this.gerarAlerta('error', 'As senhas não coincidem', 3);
        }
      } else {
        this.gerarAlerta('error', 'Para prosseguir, você deve selecionar um tipo de cadastro', 3);
      }
    },
    validaConfirmacaoDeSenha() {
      return this.senha === this.confirmarSenha;
    },
    preencheUsuarioAntesDoCadastro() {
      this.usuarioACadastrar = {
        nome: this.nome,
        cpf: this.cpf,
        dataNascimento: this.dataNascimento,
        endereco: {
          logradouro: this.logradouro,
          numero: this.numero,
          complemento: this.complemento,
          cep: this.cep,
          bairro: this.bairro,
          cidade: this.cidade,
          uf: this.uf,
        },
        contato: {
          email: this.email,
          telefone: this.telefone,
        },
        login: {
          usuario: this.usuario,
          senha: this.senha,
        },
        genero: this.genero,
        tipoCadastro: this.tipoCadastro,
      }

      if (this.tipoCadastro === 'MEDICO') {
        this.usuarioACadastrar.crm = this.crm;
        this.usuarioACadastrar.especializacao = {
          id: this.especializacao,
        };
        this.usuarioACadastrar.unidade = {
          id: this.unidade,
        };
      }

      if (this.tipoCadastro === 'FUNCIONARIO') {
        this.usuarioACadastrar.cargo = this.cargo;
      }
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
      this.limparCamposEspecificos();
    },
    limparCamposEspecificos() {
      this.crm = '';
      this.especializacao = {};
      this.unidade = {};
      this.cargo = {};
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
  max-height: 60vh;
  overflow-y: auto;
  overflow-x: hidden;
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