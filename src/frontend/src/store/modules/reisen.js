import axios from 'axios'

const state = {
  reisen: [],
  chosenReise: ''
}

const getters = {
  getReisen: (state) => state.reisen,
  getChosenReise: (state) => state.chosenReise
}

const actions = {
  // might need to change the url
  async fetchReisen ({ commit }) {
    console.log('Fetching Reisen')
    const response = await axios.get('/SHTravel/reise')
    console.log(response.data)
    commit('setReisen', response.data)
  },
  chooseReise ({ commit }, reise) {
    commit('setReise', reise)
  }
}

const mutations = {
  setReisen: (state, reisen) => (state.reisen = reisen),
  setReise: (state, reise) => (state.chosenReise = reise)
}

export default {
  state,
  getters,
  actions,
  mutations
}
