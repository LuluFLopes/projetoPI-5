<template>
  <div class="div-principal">
    <h1 class="titulo-principal">Cadastro de Especialização</h1>
    <v-card class="cartao-principal">
      <div class="div-secundaria">
        <v-text-field class="campos-padrao" label="Descrição" v-model="descricao" :disabled="camposDesabilitados">
        </v-text-field>
      </div>

      <div class="quarta-div" v-if="botoesDeConfirmacaoVisivel">
        <div class="div-botoes">
          <v-btn class="texto-botoes" color="#7ececa" @click="voltarTelaHomeAdm">
            Voltar
          </v-btn>

          <v-btn class="texto-botoes" color="#7ececa" @click="salvarEspecializacao">
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
  name: "AlterarEspecializacao",
  data() {
    return {
      id: 0,
      descricao: '',
      urlSalvarEspecializacao: 'http://localhost:8081/especializacao/alterar',
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
      'dadosEspecializacaoAlterada'
    ]),
  },
  methods: {
    ...mapMutations([
      'selecionarItemMenuLateral'
    ]),
    async salvarEspecializacao() {
      try {
        await axios.post(this.urlSalvarEspecializacao, {
          id: this.id,
          descricao: this.descricao,
        });
        this.voltarTelaHomeAdm();
      } catch (ex) {
        this.gerarAlerta('error', 'Erro ao cadastrar', 3);
      }
    },
    liberarBotoes() {
      this.botoesDeAlterarVisivel = false;
      this.camposDesabilitados = false;
      this.botoesDeConfirmacaoVisivel = true;
    },
    preencherInformacoes() {
      this.id = this.dadosEspecializacaoAlterada.id;
      this.descricao = this.dadosEspecializacaoAlterada.descricao;
    },
    voltarTelaHomeAdm() {
      this.selecionarItemMenuLateral(2);
      router.push('gerenciarEspecializacao');
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