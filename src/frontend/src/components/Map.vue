<template>
  <div class="map" id="map" ref="mapContainer">
    <ReisepunktErstellen v-show="showPopUp" v-bind:laengengrad="lat" v-bind:breitengrad="long" v-on:updateShow="updateReisepunktErstellenShow($event)"/>
    <b-button @click="popUpShow = !popUpShow"></b-button>
    {{popUpShow}}
  </div>
</template>

<script>
import { createMap, loadMarker } from '@/lib/Map'
import ReisepunktErstellen from '@/components/ReisepunktErstellen'

export default {
  name: 'Map',
  data () {
    return {
      popUpShow: true,
      PopUp: true,
      lat: 53,
      long: 5
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
      this.popUpShow = showProp
      alert('Show: ' + this.popUpShow)
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
