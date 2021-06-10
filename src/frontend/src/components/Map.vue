<template>
    <div class="map" id="map" ref="mapContainer">
      <ReisepunktErstellen v-if="showReisepunktErstellen" v-on:updateShow="closeReisepunktErstellen($event)"
                           v-on:makeToast="makeToast($event)"/>
      <ReiseAuswahl v-if="showReiseAuswahl" v-on:selected="closeReiseAuswahl($event)"/>
      <ReiseAnsicht v-if="showReiseAnsicht"/>
    </div>
</template>

<script>
import { createMap, loadMarker, L, map } from '@/lib/mapWrapper'
import ReisepunktErstellen from '@/components/ReisepunktErstellen'
import ReiseAnsicht from '@/components/ReiseAnsicht'
import ReiseAuswahl from '@/components/ReiseAuswahl'

export default {
  name: 'Map',
  data () {
    return {
      showReisepunktErstellen: false,
      showReiseAuswahl: true,
      showReiseAnsicht: true
    }
  },
  components: {
    ReisepunktErstellen,
    ReiseAnsicht,
    ReiseAuswahl
  },
  mounted () {
    createMap(this)
    loadMarker()
  },
  updated () {
    loadMarker()
  },
  methods: {
    openReisepunktErstellen: function () {
      this.showReisepunktErstellen = true
    },
    closeReisepunktErstellen: function (showProp) {
      this.showReisepunktErstellen = false
    },
    openReiseAuswahl: function (reisepunkt) {
      this.$store.dispatch('selectReisepunkt', reisepunkt)
      this.showReiseAuswahl = true
    },
    closeReiseAuswahl: function (showProp) {
      this.showReiseAuswahl = false
    },
    setClickedCoords: function (lat, lng) {
      this.$store.dispatch('chooseCoords', { lng: lng, lat: lat })
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
.map {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}
</style>
