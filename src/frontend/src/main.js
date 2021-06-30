import 'leaflet/dist/leaflet.css'
import { Icon } from 'leaflet'

import '@babel/polyfill'
import 'mutationobserver-shim'
import './plugins/bootstrap-vue'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faTimes, faPlus } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import Vue from 'vue'
import Toasted from 'vue-toasted'
import App from './App.vue'
import router from './router'
import store from './store'

delete Icon.Default.prototype._getIconUrl
Icon.Default.mergeOptions({
  iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
  iconUrl: require('leaflet/dist/images/marker-icon.png'),
  shadowUrl: require('leaflet/dist/images/marker-shadow.png')
})

library.add(faTimes, faPlus)

Vue.use(Toasted)
Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
