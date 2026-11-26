import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: { apiStatus: '확인 전', apiMessage: '' },
  mutations: {
    setApiStatus(state, { status, message }) {
      state.apiStatus = status
      state.apiMessage = message
    }
  }
})
