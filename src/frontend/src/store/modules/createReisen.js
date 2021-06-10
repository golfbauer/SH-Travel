const state = {
  reise: undefined,
  reisepunkt: undefined
}

const getters = {
  getReise: (state) => state.reise,
  getReisepunkt: (state) => state.reisepunkt
}

const actions = {
  selectReise ({ commit }, reise) {
    commit('setReise', reise)
  },
  selectReisepunkt ({ commit }, reisepunkt) {
    commit('setReisepunkt', reisepunkt)
  }
}

const mutations = {
  setReise: function (state, reise) {
    state.reise = reise
  },
  setReisepunkt: function (state, reisepunkt) {
    state.reisepunkt = reisepunkt
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
