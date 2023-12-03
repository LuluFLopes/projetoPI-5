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
import GerenciarUnidades from "@/View/GerenciarUnidades";
import GerenciarEspecializacao from "@/View/GerenciarEspecializacao";
import IncluirUsuario from "@/View/IncluirUsuario";
import AlterarPaciente from "@/View/AlterarPaciente.vue"
import IncluirEspecializacao from "@/View/IncluirEspecializacao";
import IncluirUnidade from "@/View/IncluirUnidade";
import AlterarUsuario from "@/View/AlterarUsuario";
import AlterarUnidade from "@/View/AlterarUnidade";
import AlterarEspecializacao from "@/View/AlterarEspecializacao";
import AlterarCadastro from "@/View/AlterarCadastro";
import GerenciaHistoricoMedicos from "@/View/GerenciaHistoricoMedicos";


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
  {
    path: '/gerenciarUnidades',
    name: 'gerenciarUnidades',
    component: GerenciarUnidades
  },
  {
    path: '/gerenciarEspecializacao',
    name: 'gerenciarEspecializacao',
    component: GerenciarEspecializacao
  },
  {
    path: '/incluirUsuarios',
    name: 'incluirUsuarios',
    component: IncluirUsuario
  },
  {
    path: '/alterarPaciente',
    name: 'alterarPaciente',
    component: AlterarPaciente
  },
  {
    path: '/incluirEspecializacao',
    name: 'incluirEspecializacao',
    component: IncluirEspecializacao
  },
  {
    path: '/incluirUnidade',
    name: 'incluirUnidade',
    component: IncluirUnidade
  },
  {
    path: '/alterarUsuario',
    name: 'alterarUsuario',
    component: AlterarUsuario
  },
  {
    path: '/alterarUnidade',
    name: 'alterarUnidade',
    component: AlterarUnidade
  },
  {
    path: '/alterarEspecializacao',
    name: 'alterarEspecializacao',
    component: AlterarEspecializacao
  },
  {
    path: '/alterarCadastro',
    name: 'alterarCadastro',
    component: AlterarCadastro
  },
  {
    path: '/historicoMedicos',
    name: 'historicoMedicos',
    component: GerenciaHistoricoMedicos
  },
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router

