/**
 * A vuex script containing the picked coordinates as state, resembling the picked coordinates when creating a new
 * "Reisepunkt" via double clicking the map.
 * It also contains all necessary getter functions, as well as actions and mutations.
 */

// the actual data we want to work with on different components
const state = {
  pickedCoords: {
    laengengrad: 50.0,
    breitengrad: 10.0
  }
}

// the getters used to retrieve data/state
const getters = {
  getCoords: (state) => state.pickedCoords
}

const actions = {}

const mutations = {
  setCoords: (state, laengengrad, breitengrad) => (state.pickedCoords.laengengrad = laengengrad,
    state.pickedCoords.breitengrad = breitengrad)
}

export default {
  state,
  getters,
  actions,
  mutations
}
