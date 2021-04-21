<template>
  <div>
    <b-button @click="updateReisepunktErstellenShow(true)">{{ReisepunktErstellenShow}}</b-button>
    <div class="map" id="map" ref="mapContainer">
    </div>
    <component
        v-bind:is="ReisepunktErstellenShow"
        v-bind:laengengrad="lat"
        v-bind:breitengrad="long"
        v-on:updateShow="updateReisepunktErstellenShow($event)"
        v-on:makeToast="makeToast($event)"
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
      lat: 10.3,
      long: 54.4,
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
  updated () {
    loadMarker()
  },
  methods: {
    updateReisepunktErstellenShow: function (showProp) {
      alert('updateReisepunkterstellenshow')
      if (showProp === true) {
        this.ReisepunktErstellenShow = ReisepunktErstellen
      } else {
        this.ReisepunktErstellenShow = null
      }
      alert(this.ReisepunktErstellenShow)
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
