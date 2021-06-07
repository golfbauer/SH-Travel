import axios from 'axios'

const state = {
  reisen: []
}

const getters = {
  getReisen: (state) => state.reisen
}

const actions = {
  // might need to change the url
  async fetchReisen ({ commit }) {
    const response = await axios.get('/SHTravel/reisen')
    console.log(response.data)
    commit('setReisen', response.data)
  }
}

const mutations = {
  setReisen: (state, reisen) => (this.state.reisen = reisen)
}

export default {
  state,
  getters,
  actions,
  mutations
}
