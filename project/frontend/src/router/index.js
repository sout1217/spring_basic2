import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/MainPage')
  },
  {
    path: '/login',
    name: 'LoginPage',
    component: () => import('../views/LoginPage')
  },
  {
    path: '/register',
    name: 'RegisterPage',
    component: () => import('../views/RegisterPage')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
