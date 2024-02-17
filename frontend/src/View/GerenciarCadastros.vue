<template>
  <div class="div-principal">
    <menu-lateral/>
    <div class="div-tabela">
      <div>
        <v-card-title>
          Cadastros
          <v-spacer></v-spacer>
          <v-text-field
              v-model="buscar"
              append-icon="mdi-magnify"
              label="Buscar"
              single-line
              hide-details
          ></v-text-field>
        </v-card-title>
        <v-data-table
            :headers="campos"
            :items="lista"
            :search="buscar"
            class="elevation-1 tabela-principal"
        >
          <template v-slot:item="{ item }">
            <tr>
              <td>{{ item.nome }}</td>
              <td>{{ item.cpf }}</td>
              <td>{{ item.tipoCadastro }}</td>
              <td>{{ item.status }}</td>
              <td><img :src="require(`../assets/imagens-usuarios/${item.nomeFoto}`)" class="img-medico"></td>
              <td>
                <v-checkbox v-model="item.checkboxAtiva" @click="aoSelecionarUmElementoTabela(item)"></v-checkbox>
              </td>
            </tr>
          </template>
        </v-data-table>

      </div>
      <div class="div-botoes">
        <v-btn class="botoes-laterais" :disabled="false" @click="incluirCadastro">Incluir</v-btn>
        <v-btn class="botoes-laterais" :disabled="botaoAlterarDesativado" @click="alterarCadastro">Alterar</v-btn>
        <v-btn class="botoes-laterais" :disabled="botaoArquivarDesativado" @click="arquivarCadastro">Arquivar</v-btn>
        <v-btn class="botoes-laterais" :disabled="botaoAtivarDesativado" @click="ativarCadastro">Ativar</v-btn>
      </div>
    </div>
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
import MenuLateral from "@/components/MenuLateral";
import {mapMutations, mapState} from "vuex";
import axios from "axios";
import router from "@/router";

export default defineComponent({
  name: "GerenciaCadastros",
  components: {MenuLateral},
  data() {
    return {
      itemSelecionado: [],
      buscar: '',
      campos: [
        {
          text: 'Nome',
          align: 'start',
          sortable: false,
          value: 'nome',
        },
        {
          text: 'CPF',
          align: 'start',
          sortable: false,
          value: 'cpf',
        },
        {
          text: 'Tipo de Cadastro',
          align: 'start',
          sortable: false,
          value: 'tipoCadastro',
        },
        {
          text: 'Status do Cadastro',
          align: 'start',
          sortable: false,
          value: 'status',
        },
        {
          text: '',
          align: 'start',
          sortable: false,
          value: 'caminhoFoto',
        },
        {
          text: '',
          align: 'start',
          sortable: false,
          value: 'status',
        },
      ],
      lista: [],
      botaoAlterarDesativado: true,
      botaoArquivarDesativado: true,
      botaoAtivarDesativado: true,
      urlListarMedico: 'http://localhost:8081/medicos/listar',
      urlListarFuncionario: 'http://localhost:8081/funcionarios/listar',
      urlListarPaciente: 'http://localhost:8081/pacientes/listar',
      urlArquivarMedico: 'http://localhost:8081/medicos/inativar',
      urlArquivarFuncionario: 'http://localhost:8081/funcionarios/inativar',
      urlArquivarPaciente: 'http://localhost:8081/pacientes/inativar',
      urlAtivarMedico: 'http://localhost:8081/medicos/ativar',
      urlAtivarFuncionario: 'http://localhost:8081/funcionarios/ativar',
      urlAtivarPaciente: 'http://localhost:8081/pacientes/ativar',
      alertaLigado: false,
      tipoAlerta: '',
      msgAlerta: '',
      usuarioParaAlterar: {},
    }
  },
  computed: {
    ...mapState([
      'itemSelecionadoMenuLateral'
    ]),
  },
  methods: {
    ...mapMutations([
      'preencherDadosUsuarioAlterado'
    ]),
    async buscarMedicos() {
      try {
        let response = await axios.get(this.urlListarMedico);
        this.preencheItensLista(response.data);
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao buscar medicos', 3);
      }
    },
    async buscarFuncionarios() {
      try {
        let response = await axios.get(this.urlListarFuncionario);
        this.preencheItensLista(response.data);
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao buscar funcionarios', 3);
      }
    },
    async buscarPacientes() {
      try {
        let response = await axios.get(this.urlListarPaciente);
        this.preencheItensLista(response.data);
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao buscar pacientes', 3);
      }
    },
    async arquivar(id, url) {
      try {
        await axios.put(`${url}/${id}`);
        await this.buscarDadosLista();
        await this.deselecionarTodos();
        await this.desativarBotoes();
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao arquivar', 3);
      }
    },
    async ativar(id, url) {
      try {
        await axios.put(`${url}/${id}`);
        await this.buscarDadosLista()
        await this.deselecionarTodos();
        await this.desativarBotoes();
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao ativar', 3);
      }
    },
    preencheItensLista(lista) {
      lista.forEach(elemento => {
        if (elemento.tipoCadastro !== 'ADMINISTRADOR') {
          elemento.checkboxAtiva = false;
          this.lista.push(elemento);
        }
      });
    },
    buscarDadosLista() {
      this.limparLista();
      this.buscarMedicos();
      this.buscarFuncionarios();
      this.buscarPacientes();
    },
    aoSelecionarUmElementoTabela(item) {
      this.deselecionarOutros(item);
      this.mudarEstadoBotoes(item);
      this.usuarioParaAlterar = item;
    },
    deselecionarOutros(item) {
      this.lista.forEach(usuario => {
        if (usuario.id !== item.id) {
          usuario.checkboxAtiva = false;
        }
      })
    },
    incluirCadastro() {
      router.push('incluirUsuarios');
    },
    alterarCadastro() {
      this.preencherDadosUsuarioAlterado(this.usuarioParaAlterar);
      router.push('alterarUsuario');
    },
    arquivarCadastro() {
      switch (this.usuarioParaAlterar.tipoCadastro) {
        case 'MEDICO':
          this.arquivar(this.usuarioParaAlterar.id, this.urlArquivarMedico);
          break;
        case 'FUNCIONARIO':
          this.arquivar(this.usuarioParaAlterar.id, this.urlArquivarFuncionario);
          break;
        case 'PACIENTE':
          this.arquivar(this.usuarioParaAlterar.id, this.urlArquivarPaciente);
          break;
      }
    },
    ativarCadastro() {
      switch (this.usuarioParaAlterar.tipoCadastro) {
        case 'MEDICO':
          this.ativar(this.usuarioParaAlterar.id, this.urlAtivarMedico);
          break;
        case 'FUNCIONARIO':
          this.ativar(this.usuarioParaAlterar.id, this.urlAtivarFuncionario);
          break;
        case 'PACIENTE':
          this.ativar(this.usuarioParaAlterar.id, this.urlAtivarPaciente);
          break;
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
    limparLista() {
      this.lista = [];
    },
    mudarEstadoBotoes(item) {
      if (item.checkboxAtiva) {
        this.botaoAlterarDesativado = false;
        if (item.status === 'ATIVO') {
          this.botaoArquivarDesativado = false;
        } else {
          this.botaoAtivarDesativado = false;
        }
      } else {
        this.botaoAlterarDesativado = true;
        if (item.status === 'ATIVO') {
          this.botaoArquivarDesativado = true;
        } else {
          this.botaoAtivarDesativado = true;
        }
      }
    },
    deselecionarTodos() {
      this.lista.forEach(elemento => {
        elemento.checkboxAtiva = false;
      })
    },
    desativarBotoes() {
      this.botaoAlterarDesativado = true;
      this.botaoArquivarDesativado = true;
      this.botaoAtivarDesativado = true;
    },
  },
  beforeMount() {
    this.buscarDadosLista();
  }
})
</script>

<style scoped>
.div-principal {
  display: flex;
  justify-content: space-between;
  height: 70vh;
}

.tabela-principal {
  width: 70vw;
  max-width: 70vw;
}

.div-tabela {
  padding-top: 2vh;
  display: flex;
  justify-content: space-between;
  max-height: 50vh;
  overflow-y: auto;
  overflow-x: hidden;
}

.div-botoes {
  width: 10vw;
  padding-top: 5vh;
}

.botoes-laterais {
  display: block;
  margin-right: auto;
  margin-left: auto;
  margin-bottom: 1vh;
  width: 5vw;
}

.alerta-total {
  position: absolute;
  top: 1vh;
  left: 3vw;
}

.img-medico {
  width: 3vw;
  border-radius: 1vw;
}
</style>