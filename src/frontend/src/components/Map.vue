<template>
  <div class="map" id="map">
    <l-map :zoom="zoom" :center="center">
      <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
      <l-marker :lat-lng="marker"></l-marker>
    </l-map>
  </div>
</template>

<script>
// import { L } from 'vue2-leaflet'
import { LMap, LTileLayer, LMarker } from 'vue2-leaflet'

export default {
  name: 'Map',
  data () {
    return {
      zoom: 13,
      center: L.latLng(47.413220, -1.219482),
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', // http://{s}.tile.osm.org/{z}/{x}/{y}.png
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors', // &copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors
      marker: L.latLng(47.413220, -1.219482)
    }
  },
  components: {
    LMap,
    LTileLayer,
    LMarker
  }
}

</script>

<style scoped>
  .map {
    height: 800px;
    width: 800px;
    margin: 0;
  }
</style>
