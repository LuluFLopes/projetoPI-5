import Vue from 'vue';
import VueRouter from 'vue-router';
import LoginCliente from "@/View/LoginCliente";
import LoginFuncionario from "@/View/LoginFuncionario";
import Home from "@/View/Home";
import CadastroPaciente from "@/View/CadastroPaciente.vue";
import CadastroFuncionario from "@/View/CadastroFuncionario.vue";
import TelaAgendamento from "@/View/TelaAgendamento";

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
    path: '/Home',
    name: 'Home',
    component: Home
  },
  {
    path: '/CadastroPaciente',
    name: 'CadastroPaciente',
    component: CadastroPaciente
  },
  {
    path: '/CadastroFuncionario',
    name: 'CadastroFuncionario',
    component: CadastroFuncionario
  },
  {
    path: '/Agendamento',
    name: 'Agendamento',
    component: TelaAgendamento
  }
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router

