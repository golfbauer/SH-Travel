<template>
  <Map/>
  <ReisepunktErstellen/>
</template>

<script>
import Map from './components/Map.vue'
import ReisepunktErstellen from "@/components/ReisepunktErstellen";

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

}
</style>
