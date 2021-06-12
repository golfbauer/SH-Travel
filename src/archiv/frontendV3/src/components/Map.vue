<template>
  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.1.0/dist/leaflet.css"/>
  <div id="map_container"/>
</template>

<script>
import * as mapService from '@/service/helper/map';

export default {
  name: 'Map',
  data() {
    return {
      showCreateTravelPoint: false,
      showTravelChoice: false,
      showTravelList: false,
    };
  },
  setup() {
  },
  async mounted() {
    mapService.createMap(this);
    await mapService.loadMarker();
  },
  async updated() {
    await mapService.loadMarker();
  },
};
</script>

<style scoped>
#map_container {
  position: fixed;
  width: 100%;
  height: 100%;
}
</style>
