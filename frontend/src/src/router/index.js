import Vue from 'vue';

Vue.use(VueRouter)

const routes = [
  {
    path: '/loginCliente',
    name: 'loginCliente',
    component: loginCliente
  },
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router

