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
    // const response = await axios.get('/SHTravel/reise')
    // console.log(response.data)
    // commit('setReisen', response.data)

    // constructed TestData needs to be removed for deployment
    const reisen = [
      {
        name: 'Timmendorf nach Travemünde',
        punkte: [
          {
            index: 0,
            reisepunkt: {
              name: 'Timmendorfer Strand',
              breitengrad: 54.0004,
              laengengrad: 10.7824
            }
          },
          {
            index: 1,
            reisepunkt: {
              name: 'Travemünder Strand',
              breitengrad: 53.9568,
              laengengrad: 10.8946
            }
          }
        ]
      }
    ]
    console.log(reisen)
    commit('setReisen', reisen)
  },
  chooseReise ({ commit }, reise) {
    commit('setReise', reise)
  }
}

const mutations = {
  setReisen: function (state, reisen) {
    console.log(reisen)
    state.reisen = reisen
  },
  setReise: (state, reise) => (state.chosenReise = reise)
}

export default {
  state,
  getters,
  actions,
  mutations
}
