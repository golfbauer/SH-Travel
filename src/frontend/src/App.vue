<template>
  <div>
    <Map/>
  </div>
</template>

<script>
import Map from './components/Map.vue'
import {LMap, LMarker, LTileLayer} from "vue2-leaflet";
//import {LMarker, LMap, LTileLayer} from "vue2-leaflet";

export default {
  name: 'App',
  components: {
    Map,
  }
}
</script>

<style>
#app {
  height: 100%;
  width: 100%;
}
</style>
