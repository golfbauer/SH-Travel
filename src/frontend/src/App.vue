<template>
  <Menu :menu="menu" />
  <div class="grid-container">
    <Map id="map" />
    <ReisepunktErstellen />
  </div>
</template>

<script>
import Map from "./components/Map.vue";
import Menu from "./components/Menu.vue";
import ReisepunktErstellen from "@/components/ReisepunktErstellen";
import {LMap, LMarker, LTileLayer} from "vue2-leaflet";
//import {LMarker, LMap, LTileLayer} from "vue2-leaflet";

export default {
  name: "App",
  components: {
    Map,
    ReisepunktErstellen,
    Menu,
  },
  data() {
    return {
      menu: [
        {
          title: "Reispunkt erstellen",
          component: "Reisepunkterstellen",
        },
        {
          title: "Test",
          component: "Test",
        },
        {
          title: "Logout",
          component: "Logout",
        },
      ],
    };
  },
};
</script>

<style>
html,
body {
  margin: 0;
  padding: 0;
}
#app {
}

.grid-container {
  display: grid;
}

#map {
  height: 100%;
  width: 100%;
}
</style>
