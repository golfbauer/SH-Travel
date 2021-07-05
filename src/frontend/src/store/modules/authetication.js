import axios from 'axios'

const state = {
  authenticated: false,
  sessionToken: undefined,
  userRole: undefined,
  name: undefined
}

const getters = {
  getToken: (state) => state.sessionToken,
  isAuthenticated: (state) => state.authenticated,
  getRole: (state) => state.userRole,
  getName: (state) => state.name
}

const actions = {
  async login ({ commit }, payload) {
    // implement login url
    // console.log(payload.username, payload.password)
    const username = payload.username
    const password = payload.password
    const response = await axios.post('/SHTravel/login', { username, password })
    const data = response.data

    const token = data.token
    const role = data.nutzerRolle
    const name = data.vorname + ' ' + data.nachname

    commit('setLoggedIn', { token, role, name })
  },
  logout ({ commit }) {
    commit('setLoggedOut')
  }
}

const mutations = {
  setLoggedIn (state, { token, role, name }) {
    state.authenticated = true
    state.sessionToken = token
    state.userRole = role
    state.name = name
  },
  setLoggedOut (state) {
    state.authenticated = false
    state.sessionToken = undefined
    state.userRole = undefined
    state.name = undefined
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
