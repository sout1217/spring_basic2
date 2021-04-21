import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'HomePage',
    component: () => import('../views/home/HomePage'),
  },
  {
    path: '/login',
    name: 'LoginPage',
    component: () => import('../views/login/LoginPage'),
  },
  {
    path: '/register',
    name: 'RegisterPage',
    component: () => import('../views/register/RegisterPage'),
  },
  {
    path: '/board/:id',
    name: 'BoardPage',
    component: () => import('@/views/board/BoardPage'),
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

export default router
