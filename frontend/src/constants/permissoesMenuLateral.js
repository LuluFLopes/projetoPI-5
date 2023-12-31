import {permissoes} from "@/constants/permissoes";

export const permissoesMenuLateral = [
  {
    titulo: 'Gerenciar Cadastros',
    icone: 'mdi-account',
    rota: 'gerenciarCadastros',
    grupo: [
      permissoes.administrador,
    ],
  },
  {
    titulo: 'Gerenciar Unidades',
    icone: 'mdi-domain',
    rota: 'gerenciarUnidades',
    grupo: [
      permissoes.administrador,
    ]
  },
  {
    titulo: 'Gerenciar Especializações',
    icone: 'mdi-medical-bag',
    rota: 'gerenciarEspecializacao',
    grupo: [
      permissoes.administrador,
    ]
  },
  {
    titulo: 'Alterar Meus Dados',
    icone: 'mdi-account',
    rota: 'alterarCadastro',
    grupo: [
      permissoes.medico,
      permissoes.funcionario,
    ]
  },
  {
    titulo: 'Agendamentos',
    icone: 'mdi-clipboard-text-clock',
    rota: 'historicoMedicos',
    grupo: [
      permissoes.medico,
    ]
  },
  {
    titulo: 'Agendamentos',
    icone: 'mdi-clipboard-text-clock',
    rota: 'historicoFuncionarios',
    grupo: [
      permissoes.funcionario,
    ]
  }
]