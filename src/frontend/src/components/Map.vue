<template>
  <div>
    <div class="map" id="map" ref="mapContainer">
    </div>
    <component
        v-bind:is="ReisepunktErstellenShow"
        v-bind:laengengrad="laengengrad"
        v-bind:breitengrad="breitengrad"
        v-on:updateShow="updateReisepunktErstellenShow($event)"
        v-on:makeToast="makeToast($event)"
      >
      </component>
  </div>
</template>

<script>
import { createMap, loadMarker, L, map } from '@/lib/Map'
import ReisepunktErstellen from '@/components/ReisepunktErstellen'

export default {
  name: 'Map',
  data () {
    return {
      laengengrad: 10.3,
      breitengrad: 54.4,
      ReisepunktErstellenShow: null
    }
  },
  components: {
    ReisepunktErstellen
  },
  mounted () {
    createMap()
    loadMarker()
  },
  updated () {
    loadMarker()
  },
  methods: {
    openPopup: function (e) {
      var popup = L.popup()
        .setLatLng([e.latlng.lat, e.latlng.lng])
        .setContent('<button @click="updateReisepunktErstellenShow(true)">Reisepunkt erstellen</button>')
        .openOn(map)

      this.laengengrad = e.latlng.lng
      this.breitengrad = e.latlng.lat
    },
    updateReisepunktErstellenShow: function (showProp) {
      alert(showProp)
      if (showProp === true) {
        this.ReisepunktErstellenShow = ReisepunktErstellen
        alert('Reisepunkterstellen ist nun sichtbar')
      } else {
        this.ReisepunktErstellenShow = null
        alert('Reisepunkterstellen ist nun nicht mehr sichtbar')
      }
    },
    makeToast (array) {
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
