import axios from 'axios'

const state = {
  authenticated: false,
  sessionToken: undefined
}

const getters = {
  getToken: (state) => state.sessionToken,
  isAuthenticated: (state) => state.authenticated
}

const actions = {
  async login ({ commit }, username, password) {
    // implement login url
    const response = await axios.post()
    const token = response.data

    commit('setLoggedIn', token)
  },
  logout ({ commit }) {
    commit('setLoggedOut')
  }
}

const mutations = {
  setLoggedIn (state, token) {
    this.state.sessionToken = token
    this.state.authenticated = true
  },
  setLoggedOut (state) {
    this.state.sessionToken = undefined
    this.state.authenticated = false
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
