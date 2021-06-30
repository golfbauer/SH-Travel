import * as reiseService from '@/service/api/reise'

const state = {
  reisen: []
}

const getters = {
  getReisen: (state) => state.reisen
}

const actions = {
  async fetchReisen ({ commit }) {
    console.log('Fetche Reisen')
    const reisen = await reiseService.fetchReisen()
    commit('setReisen', reisen)
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
