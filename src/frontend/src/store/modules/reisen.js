const state = {
  reisen: []
}

const getters = {
  getReisen: (state) => state.reisen
}

const actions = {
  // might need to change the url
  async fetchReisen ({ commit }) {
    console.log('Fetching Reisen')
    // const response = await axios.get('/SHTravel/reise')
    // console.log(response.data)
    // commit('setReisen', response.data)

    // constructed TestData needs to be removed for deployment
    // resembling response.data
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
      },
      {
        name: 'Einfache Test Reise',
        punkte: [
          {
            index: 0,
            reisepunkt: {
              id: 1,
              name: 'Test Punkt 1',
              breitengrad: 54.0259,
              laengengrad: 10.7554
            }
          },
          {
            index: 1,
            reisepunkt: {
              id: 2,
              name: 'Test Punkt 2',
              breitengrad: 54.3447,
              laengengrad: 10.4559
            }
          },
          {
            index: 2,
            reisepunkt: {
              id: 34,
              name: 'Test Punkt 3',
              breitengrad: 54.3908,
              laengengrad: 10.3766
            }
          }
        ]
      }
    ]
    console.log(reisen)
    commit('setReisen', reisen)
  }
}

const mutations = {
  setReisen: function (state, reisen) {
    console.log(reisen)
    state.reisen = reisen
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
