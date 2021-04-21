<template>
  <div class="map" id="map" ref="mapContainer">
    <ReisepunktErstellen v-show="popUpShow"/>
  </div>
</template>

<script>
import { createMap, loadMarker } from '@/lib/Map'
import ReisepunktErstellen from '@/components/ReisepunktErstellen'

export default {
  name: 'Map',
  data () {
    return {
      popUpShow: false
    }
  },
  components: {
    ReisepunktErstellen
  },
  mounted () {
    createMap()
    loadMarker()
  }
}
</script>

<style scoped>
.map {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}
</style>
