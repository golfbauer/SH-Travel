<template>
  <div class="map" id="map">
    <l-map :zoom="zoom" :center="center" :maxZoom="maxZoom" :minZoom="minZoom">
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
      maxZoom: 18,
      minZoom: 7,
      center: L.latLng(54.3227085, 10.1355550),
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
      // marker: L.latLng(47.413220, -1.219482)
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
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
  }
</style>
