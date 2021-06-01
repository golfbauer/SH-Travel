import Vue from 'vue'
import Vuex from 'vuex'
import map from './modules/map'
import reisepunkterstellen from './modules/reisepunkterstellen'

// load Vuex
Vue.use(Vuex)

// create data store
export default new Vuex.Store({
  modules: {
    map,
    reisepunkterstellen
  }
})
