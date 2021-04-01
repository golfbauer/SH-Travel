<template>
  <Map/>
  <ReisepunktErstellen/>
</template>

<script>
import Map from './components/Map.vue'
import ReisepunktErstellen from "@/components/ReisepunktErstellen";
import {LMap, LMarker, LTileLayer} from "vue2-leaflet";
//import {LMarker, LMap, LTileLayer} from "vue2-leaflet";

export default {
  name: 'App',
  components: {
    Map,
    ReisepunktErstellen
  }
}
</script>

<style>
#app {
  height: 100%;
  width: 100%;
}
</style>
