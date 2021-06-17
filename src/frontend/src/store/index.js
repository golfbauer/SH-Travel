import Vue from 'vue'
import Vuex from 'vuex'
import ClickedCoords from './modules/clickedCoords'
import Reisen from './modules/reisen'
import CreateReisen from './modules/createReisen'
import Authentication from './modules/authetication'

/**
 * This script creates and exports a Vuex (data-)store.
 * It also registers store modules which represent different data stores.
 */

// Telling Vue to load and use Vuex.
Vue.use(Vuex)

// Create data store and register modules which are to contain.
export const store = new Vuex.Store({
  modules: {
    ClickedCoords,
    Reisen,
    CreateReisen,
    Authentication
  }
})
