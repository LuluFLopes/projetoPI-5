<template>
  <div class="div-principal">
    <menu-lateral/>
    <div class="div-tabela">
      <div>
        <v-card-title>
          Histórico
          <v-spacer></v-spacer>
          <v-text-field
              v-model="buscar"
              append-icon="mdi-magnify"
              label="Buscar"
              single-line
              hide-details
          ></v-text-field>
          <v-spacer></v-spacer>
          <v-dialog
              ref="dialog"
              v-model="modal"
              :return-value.sync="data"
              persistent
              width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                  class="campos-padrao"
                  v-model="data"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker
                v-model="data"
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
                  @click="$refs.dialog.save(data)"
              >OK
              </v-btn>
            </v-date-picker>
          </v-dialog>
        </v-card-title>
        <v-data-table
            :headers="campos"
            :items="lista"
            :search="buscar"
            class="elevation-1 tabela-principal"
        >
          <template v-slot:item="{ item }">
            <tr>
              <td>{{ item.paciente.nome }}</td>
              <td>{{ item.andamento }}</td>
              <td>
                <v-checkbox v-model="item.checkboxAtiva" @click="aoSelecionarItem(item)"></v-checkbox>
              </td>
            </tr>
          </template>
        </v-data-table>

      </div>
      <div class="div-botoes">
        <v-btn class="botoes-laterais" :disabled="false" @click="buscarAgendamentos">Buscar</v-btn>
        <v-btn class="botoes-laterais" :disabled="botaoCancelar" @click="cancelarAgendamento">Cancelar</v-btn>
        <v-btn class="botoes-laterais" :disabled="botaoDetalhes" @click="ativarModal">Detalhes</v-btn>
      </div>
    </div>
    <modal-detalhes-consulta :isAberto="isModalAberto" :detalhesConsulta="detalhesConsulta"
                             @ao-clicar-botao-fechar-modal="fecharModal()"/>
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
import axios from "axios";
import ModalDetalhesConsulta from "@/components/ModalDetalhesConsulta";

export default defineComponent({
  name: "GerenciaHistoricoMedicos",
  components: {MenuLateral, ModalDetalhesConsulta},
  data() {
    return {
      itemSelecionado: [],
      buscar: '',
      campos: [
        {
          text: 'Paciente',
          align: 'start',
          sortable: false,
          value: 'paciente.nome',
        },
        {
          text: 'Status',
          align: 'start',
          sortable: false,
          value: 'andamento',
        },
        {
          text: '',
          align: 'start',
          sortable: false,
          value: '',
        },
      ],
      lista: [],
      alertaLigado: false,
      tipoAlerta: '',
      msgAlerta: '',
      botaoCancelar: true,
      botaoDetalhes: true,
      modal: false,
      data: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      urlBuscarAgendamentos: 'http://localhost:8081/agendamentos/listarPorDataEMedico',
      id: 0,
      detalhesConsulta: {},
      isModalAberto: false,
      urlCancelarAgendamento: 'http://localhost:8081/agendamentos/cancelar',
    }
  },
  methods: {
    async buscarAgendamentos() {
      try {
        const request = await axios.get(`${this.urlBuscarAgendamentos}?data=${this.data}&id=${this.id}`);
        this.limparLista();
        request.data.forEach(agendamento => {
          this.lista.push(agendamento);
        });
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao buscar agendamentos', 3);
        console.log(ex.message);
      }
    },
    async cancelarAgendamento() {
      try {
        await axios.put(`${this.urlCancelarAgendamento}/${this.detalhesConsulta.id}`);
        await this.buscarAgendamentos();
        this.desativarBotoes();
        this.deselecionarTodos();
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao cancelar agendamento', 3);
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
    buscarDadosUsuarioLogado() {
      let dadosLogin = JSON.parse(sessionStorage.getItem('usuarioLogado'));
      if (dadosLogin !== undefined && dadosLogin !== null) {
        if (dadosLogin.isLogado) {
          this.id = dadosLogin.id;
        }
      }
    },
    aoSelecionarItem(item) {
      this.detalhesConsulta = item;
      this.mudarEstadoBotoes(item);
      this.deselecionarOutros(item);
    },
    deselecionarOutros(item) {
      this.lista.forEach(usuario => {
        if (usuario.id !== item.id) {
          usuario.checkboxAtiva = false;
        }
      })
    },
    ativarBotoes() {
      this.botaoCancelar = false;
      this.botaoDetalhes = false;
    },
    desativarBotoes() {
      this.botaoCancelar = true;
      this.botaoDetalhes = true;
    },
    limparLista() {
      this.lista = [];
    },
    mudarEstadoBotoes(item) {
      if (item.checkboxAtiva) {
        this.ativarBotoes();
      } else {
        this.desativarBotoes();
      }
    },
    deselecionarTodos() {
      this.lista.forEach(elemento => {
        elemento.checkboxAtiva = false;
      })
    },
    ativarModal(){
      this.isModalAberto = true;
    },
    fecharModal(){
      this.isModalAberto = false;
    },
  },
  beforeMount() {
    this.buscarDadosUsuarioLogado();
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