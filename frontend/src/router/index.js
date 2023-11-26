import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from "@/View/Login";
import Home from "@/View/Home";
import CadastroPaciente from "@/View/CadastroPaciente.vue";
import CadastroFuncionario from "@/View/CadastroFuncionario.vue";
import TelaAgendamento from "@/View/Agendamento";
import TelaHistoricoConsultas from "@/View/HistoricoConsultasCliente";
import AgendamentoConcluido from "@/View/AgendamentoConcluido";
import HomeAdministrador from "@/View/HomeAdministrador";
import GerenciarCadastros from "@/View/GerenciarCadastros";

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path: '/cadastroPaciente',
    name: 'cadastroPaciente',
    component: CadastroPaciente
  },
  {
    path: '/cadastroFuncionario',
    name: 'cadastroFuncionario',
    component: CadastroFuncionario
  },
  {
    path: '/agendamento',
    name: 'agendamento',
    component: TelaAgendamento
  },
  {
    path: '/historicoConsultas',
    name: 'historicoConsultas',
    component: TelaHistoricoConsultas
  },
  {
    path: '/agendamentoConcluido',
    name: 'agendamentoConcluido',
    component: AgendamentoConcluido
  },
  {
    path: '/homeAdm',
    name: 'homeAdm',
    component: HomeAdministrador
  },
  {
    path: '/gerenciarCadastros',
    name: 'gerenciarCadastros',
    component: GerenciarCadastros
  },
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router

