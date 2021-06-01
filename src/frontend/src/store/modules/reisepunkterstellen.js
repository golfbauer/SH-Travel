// the actual data we want to work with on different components
const state = {
  pickedCoords: [
    {
      laengengrad: 50.0,
      breitengrad: 10.0
    }
  ]
}

const getters = {
  getCoords: (state) => state.pickedCoords
}

const actions = {}

const mutations = {}

export default {
  state,
  getters,
  actions,
  mutations
}
