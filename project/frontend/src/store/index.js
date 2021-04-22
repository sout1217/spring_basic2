import Vue from 'vue'
import Vuex, { createLogger } from 'vuex'
import * as getters from './getters'
import * as actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      name: null,
    },
    teams: [],
    boards: [],
  },
  getters,
  mutations,
  actions,
  plugins: process.env.NODE_ENV !== 'production' ? [createLogger()] : [],
})
