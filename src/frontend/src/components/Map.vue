<template>
  <div id="map_pane">
    <ReisepunktErstellen v-if="showReisepunktErstellen" v-on:updateShow="closeReisepunktErstellen($event)"
                         v-on:makeToast="makeToast($event)"/>
    <ReiseAuswahl v-if="showReiseAuswahl" v-on:selected="openReiseAnsicht($event)"
                  v-on:cancel="closeReiseAuswahl($event)"/>
    <ReiseAnsicht v-if="showReiseAnsicht" v-on:cancel="closeReiseAnsicht" v-on:makeToast="makeToast($event)"/>
    <ReisepunktBearbeiten v-if="showReisepunktBearbeiten" v-on:cancel="closeReisepunktBearbeiten"></ReisepunktBearbeiten>
  </div>
</template>

<script>
// import { createMap, loadMarker, L, map } from '@/service/helper/map'
import * as mapService from '@/service/helper/map'

import ReisepunktErstellen from '@/components/ReisepunktErstellen'
import ReiseAnsicht from '@/components/ReiseAnsicht'
import ReiseAuswahl from '@/components/ReiseAuswahl'
import ReisepunktBearbeiten from '@/components/ReisepunktBearbeiten'

export default {
  name: 'Map',
  data () {
    return {
      showReisepunktErstellen: false,
      showReiseAuswahl: false,
      showReiseAnsicht: false,
      showReisepunktBearbeiten: false
    }
  },
  components: {
    ReisepunktErstellen,
    ReiseAnsicht,
    ReiseAuswahl,
    ReisepunktBearbeiten
  },
  mounted () {
    mapService.createMap(this)
    mapService.loadMarker()
  },
  updated () {
    mapService.loadMarker()
  },
  methods: {
    openReisepunktErstellen: function () {
      this.showReisepunktErstellen = true
    },
    closeReisepunktErstellen: function () {
      this.showReisepunktErstellen = false
    },
    openReiseAuswahl: function (reisepunkt) {
      this.$store.dispatch('selectReisepunkt', reisepunkt)
      this.showReiseAuswahl = true
    },
    closeReiseAuswahl: function () {
      this.showReiseAuswahl = false
    },
    openReiseAnsicht: function () {
      this.closeReiseAuswahl()
      this.showReiseAnsicht = true
    },
    closeReiseAnsicht: function () {
      this.showReiseAnsicht = false
    },
    openReisepunktBearbeiten: function (point) {
      this.closeReisepunktBearbeiten()
      this.showReisepunktBearbeiten = true
      ReisepunktBearbeiten.loadMarkerData(point)
    },
    closeReisepunktBearbeiten: function () {
      this.showReisepunktBearbeiten = false
    },
    setClickedCoords: function (lat, lng) {
      this.$store.dispatch('chooseCoords', {
        lng: lng,
        lat: lat
      })
      this.openReisepunktErstellen()
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
#map_pane {
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
