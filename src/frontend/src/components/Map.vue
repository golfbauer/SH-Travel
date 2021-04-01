<template>
  <div id="Map" name="Map" class="Map">
    <l-map :zoom="zoom" :center="center">
      <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
      <l-marker :lat-lng="marker"></l-marker>
    </l-map>
  </div>
</template>

<script>
import L from 'leaflet'
import {LMap, LTileLayer, LMarker} from 'vue2-leaflet';

export default {
  name: 'Map',
  data() {
    return {
      zoom:13,
      center: L.latLng(47.413220, -1.219482),
      url:'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
      attribution:'&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      marker: L.latLng(47.413220, -1.219482),
      //map: L.map('Map').setView([47.413220, -1.219482], 15)
    }
  },
  components: {
    LMap,
    LTileLayer,
    LMarker
  }, methods: {
    latLng: function (lat, lng) {
      return L.latLng(lat, lng);
    },
    centerUpdate: function(center) {
      this.currentCenter = center;
    },
    zoomUpdate: function(zoom) {
      this.currentZoom = zoom;
    },
  }
};

/*map = L.map('map').setView([54.3227085, 10.1355550], 15);

L.tileLayer('https://api.maptiler.com/maps/streets/{z}/{x}/{y}.png?key=W4vKV7wGqEKyorE0V0tZ', {
  attribution: '<a href="https://www.maptiler.com/copyright/" target="_blank">&copy; MapTiler</a> <a href="https://www.openstreetmap.org/copyright" target="_blank">&copy; OpenStreetMap contributors</a>',
}).addTo(map);*/

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.Map {
  height: 100%;
  margin: 0;
}
</style>
