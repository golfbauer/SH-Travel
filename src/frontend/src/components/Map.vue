<template>
  <div class="map" id="map" ref="mapContainer"></div>
</template>

<script>
import { createMap, loadMarker } from '../lib/Map'

export default {
  name: 'Map',
  mounted () {
    createMap()
    loadMarker()
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
