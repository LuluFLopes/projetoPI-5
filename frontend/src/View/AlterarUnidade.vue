<template>
  <div class="div-principal">
    <h1 class="titulo-principal">Cadastro de Especialização</h1>
    <v-card class="cartao-principal">
      <div class="div-secundaria">
        <v-text-field class="campos-padrao" label="Descrição" v-model="descricao" :disabled="camposDesabilitados">
        </v-text-field>

        <v-text-field class="campos-padrao" label="Logradouro" v-model="logradouro" :disabled="camposDesabilitados">
        </v-text-field>

        <v-text-field class="campos-padrao" label="Numero" v-model="numero" :disabled="camposDesabilitados">
        </v-text-field>

        <v-text-field class="campos-padrao" label="Complemento" v-model="complemento" :disabled="camposDesabilitados">
        </v-text-field>
      </div>

      <div class="div-secundaria">
        <v-text-field class="campos-padrao" label="Bairro" v-model="bairro" :disabled="camposDesabilitados">
        </v-text-field>

        <v-text-field class="campos-padrao" label="Cidade" v-model="cidade" :disabled="camposDesabilitados">
        </v-text-field>

        <v-select class="campos-padrao" label="UF" v-model="uf" :items="listaUf" :disabled="camposDesabilitados">
        </v-select>

        <v-text-field class="campos-padrao" label="Cep" v-model="cep" :disabled="camposDesabilitados">
        </v-text-field>
      </div>

      <div class="quarta-div" v-if="botoesDeConfirmacaoVisivel">
        <div class="div-botoes">
          <v-btn class="texto-botoes" color="#7ececa" @click="voltarTelaHomeAdm">
            Voltar
          </v-btn>

          <v-btn class="texto-botoes" color="#7ececa" @click="salvarUnidade">
            Confirmar
          </v-btn>
        </div>
      </div>

      <div class="quarta-div" v-if="botoesDeAlterarVisivel">
        <div class="div-botoes">
          <v-btn class="texto-botoes" color="#7ececa" @click="voltarTelaHomeAdm">
            Voltar
          </v-btn>

          <v-btn class="texto-botoes" color="#7ececa" @click="liberarBotoes">
            Alterar
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
import {mapMutations, mapState} from "vuex";
import router from "@/router";
import axios from "axios";

export default defineComponent({
  name: "AlterarUnidade",
  data() {
    return {
      descricao: '',
      logradouro: '',
      numero: '',
      complemento: '',
      cep: '',
      bairro: '',
      cidade: '',
      uf: '',
      listaUf: [],
      urlListaUf: 'http://localhost:8081/listas/uf',
      urlSalvarUnidade: 'http://localhost:8081/unidades/cadastrar',
      alertaLigado: false,
      tipoAlerta: '',
      msgAlerta: '',
      camposDesabilitados: true,
      botoesDeConfirmacaoVisivel: false,
      botoesDeAlterarVisivel: true,
    }
  },
  computed: {
    ...mapState([
        'dadosUnidadeAlterado'
    ])
  },
  methods: {
    ...mapMutations([
      'selecionarItemMenuLateral',
    ]),
    async salvarUnidade() {
      try {
        await axios.post(this.urlSalvarUnidade, {
          descricao: this.descricao,
          endereco: {
            logradouro: this.logradouro,
            numero: this.numero,
            complemento: this.complemento,
            cep: this.cep,
            bairro: this.bairro,
            cidade: this.cidade,
            uf: this.uf,
          }
        });
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
    liberarBotoes() {
      this.botoesDeAlterarVisivel = false;
      this.camposDesabilitados = false;
      this.botoesDeConfirmacaoVisivel = true;
    },
    preencherInformacoes() {
      this.descricao = this.dadosUnidadeAlterado.descricao;
      this.logradouro = this.dadosUnidadeAlterado.endereco.logradouro;
      this.numero = this.dadosUnidadeAlterado.endereco.numero;
      this.complemento = this.dadosUnidadeAlterado.endereco.complemento;
      this.cep = this.dadosUnidadeAlterado.endereco.cep;
      this.bairro = this.dadosUnidadeAlterado.endereco.bairro;
      this.cidade = this.dadosUnidadeAlterado.endereco.cidade;
      this.uf = this.dadosUnidadeAlterado.endereco.uf;
    },
    voltarTelaHomeAdm() {
      this.selecionarItemMenuLateral(1);
      router.push('gerenciarUnidades');
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
  beforeMount() {
    this.buscarUf();
    this.preencherInformacoes();
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