<template>
    <div @dblclick="openPopup()" class="map" id="map" ref="mapContainer">
      <ReisepunktErstellen
        v-if="showR"
        v-bind:laengengrad="laengengrad"
        v-bind:breitengrad="breitengrad"
        v-on:updateShow="closePopup($event)"
        v-on:makeToast="makeToast($event)"
      />
    </div>
</template>

<script>
import Vue from 'vue'
import { createMap, loadMarker, L, map } from '@/lib/Map'
import ReisepunktErstellen from '@/components/ReisepunktErstellen'

export default {
  name: 'Map',
  data () {
    return {
      laengengrad: 0,
      breitengrad: 0,
      ReisepunktErstellenShow: null,
      showR: false
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
    openPopup: function () {
      this.showR = true
    },
    closePopup: function (showProp) {
      this.showR = false
    },
    handCoords: function (lat, lng) {
      console.log(lat + ' ' + lng)
      this.laengengrad = lng
      this.breitengrad = lat
      console.log(this.laengengrad + ' ' + this.breitengrad)
      ReisepunktErstellen.$forceUpdate()
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
