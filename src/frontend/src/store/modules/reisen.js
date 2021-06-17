import axios from 'axios'

const state = {
  reisen: []
}

const getters = {
  getReisen: (state) => state.reisen
}

const actions = {
  async fetchReisen ({ commit }) {
    const response = await axios.get('/SHTravel/reise')
    commit('setReisen', response.data)
  }
}

const mutations = {
  setReisen: function (state, reisen) {
    state.reisen = reisen
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
