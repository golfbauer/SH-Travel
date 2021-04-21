<template>
  <div>
    <div class="map" id="map" ref="mapContainer">
    </div>
    <component
        v-bind:is="ReisepunktErstellenShow"
        v-bind:laengengrad="lat"
        v-bind:breitengrad="long"
        v-on:updateShow="updateReisepunktErstellenShow($event)"
      >
      </component>
  </div>
</template>

<script>
import { createMap, loadMarker } from '@/lib/Map'
import ReisepunktErstellen from '@/components/ReisepunktErstellen'

export default {
  name: 'Map',
  data () {
    return {
      lat: 53,
      long: 5,
      ReisepunktErstellenShow: ReisepunktErstellen
    }
  },
  components: {
    ReisepunktErstellen
  },
  mounted () {
    createMap()
    loadMarker()
  },
  methods: {
    updateReisepunktErstellenShow: function (showProp) {
      if (showProp === true) {
        this.ReisepunktErstellenShow = ReisepunktErstellen
      } else {
        this.ReisepunktErstellenShow = null
      }
    }
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
