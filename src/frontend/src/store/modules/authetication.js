import axios from 'axios'

const state = {
  authenticated: false,
  sessionToken: undefined,
  userRole: undefined
}

const getters = {
  getToken: (state) => state.sessionToken,
  isAuthenticated: (state) => state.authenticated,
  getRole: (state) => state.userRole
}

const actions = {
  async login ({ commit }, payload) {
    // implement login url
    // const response = await axios.post('/SHTravel/login', { username, password })
    // const token = response.headers
    // const role = response.data
    //
    // commit('setLoggedIn', { token, role })
    console.log(payload.username, payload.password)
  },
  logout ({ commit }) {
    commit('setLoggedOut')
  }
}

const mutations = {
  setLoggedIn (state, { token, role }) {
    state.authenticated = true
    state.sessionToken = token
    state.userRole = role
  },
  setLoggedOut (state) {
    state.authenticated = false
    state.sessionToken = undefined
    state.userRole = undefined
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
