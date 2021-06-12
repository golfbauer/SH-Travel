/**
 * A vuex data store containing the picked coordinates as state, resembling the clicked coordinates when creating a new
 * "Reisepunkt" via double clicking the map.
 */

/*
 * The actual data/state we want to work with on different components.
 * Data is a 'pickedCoords' object with fields 'laengengrad' and 'breitengrad'.
 */
const state = {
  clickedCoords: {
    laengengrad: 0,
    breitengrad: 0
  }
}

// The getters used to retrieve data/state from the store.
const getters = {
  getCoords: (state) => state.clickedCoords
}

// Actions are called to use mutations. (they can be asynchronous)
const actions = {
  chooseCoords ({ commit }, { lng, lat }) {
    // console.log(lng, lat)
    commit('setCoords', { lng, lat })
  }
}

// Mutations are called to change a state.
const mutations = {
  setCoords (state, { lng, lat }) {
    // console.log('entering setLaengengrad')
    state.clickedCoords.laengengrad = lng
    state.clickedCoords.breitengrad = lat
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
