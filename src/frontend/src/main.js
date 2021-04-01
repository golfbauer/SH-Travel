import L from 'leaflet'
import 'leaflet/dist/leaflet.css'
import {createApp} from 'vue'
import App from './App.vue'

delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
    iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
    iconUrl: require('leaflet/dist/images/marker-icon.png'),
    shadowUrl: require('leaflet/dist/images/marker-shadow.png')
});

createApp(App).mount('#app')