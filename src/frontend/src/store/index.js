import Vue from 'vue'
import Vuex from 'vuex'
import Reisepunkte from './modules/reisepunkte'
import ClickedCoords from './modules/clickedCoords'

/**
 * This script creates and exports a Vuex (data-)store.
 * It also registers store modules which represent different data stores.
 */

// Telling Vue to load and use Vuex.
Vue.use(Vuex)

// Create data store and register modules which are to contain.
export const store = new Vuex.Store({
  modules: {
    Reisepunkte,
    ClickedCoords
  }
})
