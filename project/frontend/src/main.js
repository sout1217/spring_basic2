import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'

Vue.config.productionTip = false

axios.defaults.baseURL = '/api/v1'
axios.defaults.headers.post['Content-Type'] = 'application/json'
axios.interceptors.response.use(
  response => response,
  error => {
    return Promise.reject(error)
  },
)

// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
