import Vue from 'vue';
import VueRouter from 'vue-router';
import LoginCliente from "@/View/LoginCliente";
import Home from "@/View/Home";
<<<<<<< Updated upstream
import CadastroPaciente from "@/View/CadastroPaciente.vue";
import CadastroFuncionario from "@/View/CadastroFuncionario.vue";
=======
import TelaAgendamento from "@/View/TelaAgendamento";
>>>>>>> Stashed changes

Vue.use(VueRouter)

const routes = [
  {
    path: '/loginCliente',
    name: 'loginCliente',
    component: LoginCliente
  },
  {
    path: '/Home',
    name: 'Home',
    component: Home
  },
  {
<<<<<<< Updated upstream
    path: '/CadastroPaciente',
    name: 'CadastroPaciente',
    component: CadastroPaciente
  },
  {
    path: '/CadastroFuncionario',
    name: 'CadastroFuncionario',
    component: CadastroFuncionario
  },
=======
    path: '/Agendamento',
    name: 'Agendamento',
    component: TelaAgendamento
  }
>>>>>>> Stashed changes
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router

