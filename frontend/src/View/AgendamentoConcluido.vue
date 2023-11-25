<template>
  <div class="div-principal">
    <v-card class="card-principal">
      <h1 class="titulo-principal">Agendamento Concluído!</h1>
      <div class="div-secundaria">
        <v-card class="card-secundario">
          <div class="terceira-div">
            <p class="texto-padrao">Com o seu agendamento finalizado, tenho alguns lembretes para você:</p>
            <p class="texto-padrao"> - Chegue ao menos 30 minutos antes do horário agendado</p>
            <p class="texto-padrao"> - Leve um documento com foto</p>
            <p class="texto-padrao"> - Leve a carteirinha do seu convênio</p>
            <p class="texto-padrao">Caso queira verificar em detalhes sobre seu agendamento,
              <router-link to="historicoConsultas">clique aqui</router-link>
              .
            </p>
          </div>
        </v-card>
      </div>
    </v-card>
  </div>
</template>

<script>
import {defineComponent} from "vue";

export default defineComponent({
  name: "AgendamentoConcluido"
})
</script>

<style scoped>

.div-principal {
  display: flex;
  justify-content: center;
}

.card-principal {
  margin-top: 8vh;
  height: 60vh;
  width: 70vw;
  background: #7ececa !important;
}

.titulo-principal {
  text-align: center;
  margin-top: 1.5vh;
}

.div-secundaria {
  margin-top: 3vh;
  display: flex;
  justify-content: center;
}

.card-secundario {
  height: 45vh;
  width: 60vw;
  background: #a0ded6 !important;
  text-align: center;
}

.terceira-div {
  margin-top: 10vh;
}

.texto-padrao {
  font-weight: bold;
}

</style>