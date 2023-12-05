<template>
  <div class="div-principal">
    <menu-lateral/>
    <div class="div-tabela">
      <div>
        <v-card-title>
          Unidades
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
              <td>{{ item.descricao }}</td>
              <td>{{ item.status }}</td>
              <td>
                <v-checkbox v-model="item.checkboxAtiva" @click="aoSelecionarUmElementoTabela(item)"></v-checkbox>
              </td>
            </tr>
          </template>
        </v-data-table>

      </div>
      <div class="div-botoes">
        <v-btn class="botoes-laterais" :disabled="false" @click="incluirUnidade">Incluir</v-btn>
        <v-btn class="botoes-laterais" :disabled="botaoAlterarDesativado" @click="alterarUnidade">Alterar</v-btn>
        <v-btn class="botoes-laterais" :disabled="botaoArquivarDesativado" @click="arquivarUnidade">Arquivar</v-btn>
        <v-btn class="botoes-laterais" :disabled="botaoAtivarDesativado" @click="ativarUnidade">Ativar</v-btn>
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
  name: "GerenciarUnidades",
  components: {MenuLateral},
  data() {
    return {
      ...mapState([
        'itemSelecionadoMenuLateral'
      ]),
      itemSelecionado: [],
      buscar: '',
      campos: [
        {
          text: 'Descrição',
          align: 'start',
          sortable: false,
          value: 'descricao',
        },
        {
          text: 'Status da Unidade',
          align: 'start',
          sortable: false,
          value: 'status',
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
      urlListarUnidade: 'http://localhost:8081/unidades/listar',
      urlArquivarUnidade: 'http://localhost:8081/unidades/inativar',
      urlAtivarUnidade: 'http://localhost:8081/unidades/ativar',
      alertaLigado: false,
      tipoAlerta: '',
      msgAlerta: '',
      unidadeParaAlterar: {},
    }
  },
  methods: {
    ...mapMutations([
      'preencherDadosUnidadeAlterada'
    ]),
    async buscarUnidades() {
      try {
        let response = await axios.get(this.urlListarUnidade);
        await this.preencheItensLista(response.data);
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao buscar unidades', 3);
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
        elemento.checkboxAtiva = false;
        this.lista.push(elemento);
      });
    },
    buscarDadosLista() {
      this.limparLista();
      this.buscarUnidades();
    },
    aoSelecionarUmElementoTabela(item) {
      this.deselecionarOutros(item);
      this.mudarEstadoBotoes(item);
      this.unidadeParaAlterar = item;
    },
    deselecionarOutros(item) {
      this.lista.forEach(usuario => {
        if (usuario.id !== item.id) {
          usuario.checkboxAtiva = false;
        }
      })
    },
    incluirUnidade() {
      router.push('incluirUnidade');
    },
    alterarUnidade() {
      this.preencherDadosUnidadeAlterada(this.unidadeParaAlterar);
      router.push('alterarUnidade');
    },
    arquivarUnidade() {
      this.arquivar(this.unidadeParaAlterar.id, this.urlArquivarUnidade);
    },
    ativarUnidade() {
      this.ativar(this.unidadeParaAlterar.id, this.urlAtivarUnidade);
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
</style>