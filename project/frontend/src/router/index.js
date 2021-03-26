import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from "../views/LoginPage";
import App from "../App";

Vue.use(VueRouter)

const routes = [
  {
   path: '/',
   name: 'app',
   component: App
  },
  {
    path: '/login',
    name: 'LoginPage',
    component: LoginPage
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
