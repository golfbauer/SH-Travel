<template>
    <div class="map" id="map" ref="mapContainer">
      <ReisepunktErstellen
        v-if="showR"
        v-on:updateShow="closePopup($event)"
        v-on:makeToast="makeToast($event)"
      />
<!--      <ReiseAnsicht-->
<!--      />-->
    </div>
</template>

<script>
import { createMap, loadMarker, L, map } from '@/lib/mapWrapper'
import ReisepunktErstellen from '@/components/ReisepunktErstellen'
import ReiseAnsicht from '@/components/ReiseAnsicht'

export default {
  name: 'Map',
  data () {
    return {
      showR: false
    }
  },
  components: {
    ReisepunktErstellen// ,
    // ReiseAnsicht
  },
  mounted () {
    createMap(this)
    loadMarker()
  },
  updated () {
    loadMarker()
  },
  methods: {
    openPopup: function () {
      this.showR = true
    },
    closePopup: function (showProp) {
      this.showR = false
    },
    setClickedCoords: function (lat, lng) {
      this.$store.dispatch('chooseCoords', { lng: lng, lat: lat })
      this.openPopup()
    },
    makeToast: function (array) {
      this.$bvToast.toast(array[2], {
        title: array[1],
        variant: array[0],
        solid: true
      })
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
<style>
.leaflet-control-container .leaflet-routing-container-hide {
  display: none;
}
</style>
