import Vue from 'vue';
import VueRouter from 'vue-router';
import LoginCliente from "@/View/LoginCliente";
import LoginFuncionario from "@/View/LoginFuncionario";
import LoginMedico from "@/View/LoginMedico";
import Home from "@/View/Home";
import CadastroPaciente from "@/View/CadastroPaciente.vue";
import CadastroFuncionario from "@/View/CadastroFuncionario.vue";
import TelaAgendamento from "@/View/TelaAgendamento";
import TelaHistoricoConsultas from "@/View/TelaHistoricoConsultas";
import HomeAdministrador from "@/View/HomeAdministrador";

Vue.use(VueRouter)

const routes = [
  {
    path: '/loginCliente',
    name: 'loginCliente',
    component: LoginCliente
  },
  {
    path: '/loginFuncionario',
    name: 'loginFuncionario',
    component: LoginFuncionario
  },
  {
    path: '/loginMedico',
    name: 'loginMedico',
    component: LoginMedico
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
    path: '/homeAdm',
    name: 'homeAdm',
    component: HomeAdministrador
  },
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router

