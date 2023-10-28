import Vue from 'vue';
import VueRouter from 'vue-router';
import LoginCliente from "@/View/LoginCliente";

Vue.use(VueRouter)

const routes = [
  {
    path: '/loginCliente',
    name: 'loginCliente',
    component: LoginCliente
  }
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router

