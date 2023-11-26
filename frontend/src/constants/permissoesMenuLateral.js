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
    rota: '',
    grupo: [
      permissoes.administrador,
    ]
  },
  {
    titulo: 'Gerenciar Especializações',
    icone: 'mdi-medical-bag',
    rota: '',
    grupo: [
      permissoes.administrador,
    ]
  },
  {
    titulo: 'Alterar Meus Dados',
    icone: 'mdi-account',
    rota: '',
    grupo: [
      permissoes.medico,
      permissoes.funcionario,
    ]
  }
]