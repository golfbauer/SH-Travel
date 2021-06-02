import Vue from 'vue'
import Vuex from 'vuex'
import Coords from './modules/Coords'

// load Vuex
Vue.use(Vuex)

// create data store
export default new Vuex.Store({
  modules: {
    Coords
  }
})
