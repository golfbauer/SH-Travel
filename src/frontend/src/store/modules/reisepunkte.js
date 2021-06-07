import axios from 'axios'
/** !!!UNDER CONSTRUCTION!!! (WORK IN PROGRESS)
 * A vuex data store containing all 'Reisepunkte'.
 */

/*
 * The actual data/state we want to work with on different components.
 * Data is an array of 'Reisepunkt' objects.
 */
const state = {
  reisepunkte: {}
}

// The getters used to retrieve data/state from the store.
const getters = {
  getReisepunkte: (state) => state.reisepunkte
}

// Actions are called to use mutations. (they can be asynchronous)
const actions = {
  fetchReisepunkte ({ commit }) {
    const response = axios.get('/SHTravel/reisepunkt')
  }

}

// Mutations are called to change a state.
const mutations = {
  setReisepunkte (state, payload) {

  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
