<template>
  <div class="div-principal">
    <h1 class="titulo-principal">Seus Dados</h1>
    <v-card class="cartao-principal">
      <v-form @submit="alterarPaciente">
        <div class="div-secundaria">
          <v-text-field
              class="campos-padrao"
              v-model="paciente.nome"
              label="Nome"
              required
              color="teal darken-3"
              outlined
              :disabled="!modoEdicao"
          ></v-text-field>
          <v-text-field
              class="campos-padrao"
              v-model="paciente.cpf"
              label="CPF"
              required
              color="teal darken-3"
              outlined
              placeholder="Ex: 000.000.000-00"
              :disabled="!modoEdicao"
          ></v-text-field>
          <v-dialog
              ref="dialog"
              v-model="modal"
              :return-value.sync="paciente.dataNascimento"
              persistent
              width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                  class="campos-padrao"
                  v-model="paciente.dataNascimento"
                  label="Data Nascimento"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                  :disabled="!modoEdicao"
              ></v-text-field>
            </template>
            <v-date-picker
                v-model="paciente.dataNascimento"
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
                  @click="$refs.dialog.save(paciente.dataNascimento)"
              >OK
              </v-btn>
            </v-date-picker>
          </v-dialog>

          <v-text-field
              class="campos-padrao"
              v-model="paciente.endereco.logradouro"
              label="Logradouro"
              required
              color="teal darken-3"
              outlined
              :disabled="!modoEdicao"
          ></v-text-field>
        </div>
        <div class="div-secundaria">
          <v-text-field
              class="campos-padrao"
              v-model="paciente.endereco.numero"
              label="Numero"
              required
              color="teal darken-3"
              outlined
              type="number"
              :disabled="!modoEdicao"
          ></v-text-field>
          <v-text-field
              class="campos-padrao"
              v-model="paciente.endereco.complemento"
              label="Complemento"
              required
              color="teal darken-3"
              outlined
              :disabled="!modoEdicao"
          ></v-text-field>
          <v-text-field
              class="campos-padrao"
              v-model="paciente.endereco.cep"
              label="CEP"
              required
              color="teal darken-3"
              outlined
              :disabled="!modoEdicao"
          ></v-text-field>
          <v-text-field
              class="campos-padrao"
              v-model="paciente.endereco.bairro"
              label="Bairro"
              required
              color="teal darken-3"
              outlined
              :disabled="!modoEdicao"
          ></v-text-field>
        </div>
        <div class="div-secundaria">
          <v-text-field
              class="campos-padrao"
              v-model="paciente.endereco.cidade"
              label="cidade"
              required
              color="teal darken-3"
              outlined
              :disabled="!modoEdicao"
          ></v-text-field>

          <v-select
              class="campos-padrao"
              v-model="paciente.endereco.uf"
              :items="ufs"
              label="Uf"
              required
              color="teal darken-3"
              outlined
              :disabled="!modoEdicao"
          ></v-select>
          <v-text-field
              class="campos-padrao"
              v-model="paciente.contato.email"
              label="E-mail"
              required
              color="teal darken-3"
              outlined
              type="email"
              :disabled="!modoEdicao"
          ></v-text-field>
          <v-text-field
              class="campos-padrao"
              v-model="paciente.contato.telefone"
              label="Telefone"
              required
              color="teal darken-3"
              outlined
              type="phone"
              placeholder="(##) #####-####"
              :disabled="!modoEdicao"
          ></v-text-field>
        </div>
        <div class="div-ternaria">
          <v-text-field
              class="campos-padrao"
              v-model="paciente.login.usuario"
              label="Login Usuario"
              required
              color="teal darken-3"
              outlined
              :disabled="!modoEdicao"
          ></v-text-field>
          <v-text-field
              class="campos-padrao"
              v-model="paciente.login.senha"
              label="Login Senha"
              required
              color="teal darken-3"
              outlined
              :disabled="!modoEdicao"
              type="password"
          ></v-text-field>
          <v-select
              class="campos-padrao"
              v-model="paciente.status"
              :items="statuses"
              label="Status"
              required
              color="teal darken-3"
              outlined
              :disabled="true"
          ></v-select>
          <v-select
              class="campos-padrao campo-final"
              v-model="paciente.genero"
              :items="generos"
              label="Genero"
              required
              color="teal darken-3"
              outlined
              :disabled="!modoEdicao"
          ></v-select>
        </div>
        <div class="quarta-div">
          <div class="div-botoes">
            <v-btn class="texto-botoes" color="#7ececa" @click="voltarTelaHome">
              Voltar
            </v-btn>

            <v-btn class="texto-botoes" color="#7ececa" type="submit" :disabled="!modoEdicao">
              Confirmar
            </v-btn>

            <v-btn class="texto-botoes" color="#7ececa" :disabled="modoEdicao" @click="ativarModoEdicao">
              Alterar os meus dados
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
      url: 'http://localhost:8081/pacientes/alterar',
      paciente: '',
      modal: false,
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
      modoEdicao: false
    };
  },
  methods: {
    async alterarPaciente() {
      try {
        this.gerarAlerta('info', 'Paciente Alterado', 3);
        await axios.post(this.url, this.paciente);
      } catch (ex) {
        this.gerarAlerta('error', 'Não foi possível realizar a alteração', 3);
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
    voltarTelaHome() {
      router.push('home');
    },
    fetchUsuario(usuarioId) {
      // Substitua a URL pelo endpoint real do seu backend
      const apiUrl = `http://localhost:8081/pacientes/buscar/${usuarioId}`;

      // Faz uma solicitação HTTP usando axios (ou outro método de sua escolha)
      axios.get(apiUrl)
          .then(response => {
            // Atualiza o estado da aplicação com os dados do usuário
            this.paciente = response.data;
            this.paciente.dataNascimento = this.coversorDeData();
          })
          .catch(error => {
            this.gerarAlerta('error', error, 3);
          });
    },
    ativarModoEdicao() {
      this.modoEdicao = true;
    },
    coversorDeData() {
      let data = this.paciente.dataNascimento;
      let dataConvertida = new Date(data[0], data[1] - 1, data[2]);
      return (new Date(dataConvertida.getTime() - dataConvertida.getTimezoneOffset() * 60000)).toISOString().substr(0, 10)
    },
  },
  mounted() {
    const usuarioData = JSON.parse(sessionStorage.getItem('usuarioLogado'));

    if(usuarioData){
      const id = usuarioData.id;
      this.fetchUsuario(id);
    } else {
      this.gerarAlerta('error', 'Erro ao resgatar id!', 3);
    }
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