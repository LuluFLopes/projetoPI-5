import Vue from 'vue';
import LoginCliente from "@/View/LoginCliente";
import Home from "@/View/Home";

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
  }
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router

