<template>
  <Menu :menu="menu" />
  <div class="grid-container">
    <Map id="map" />
    <ReisepunktErstellen />
  </div>
</template>

<script>
import Map from "./components/Map.vue";
import ReisepunktErstellen from "./components/ReisepunktErstellen";
import Menu from "./components/Menu.vue";

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
}
</style>
