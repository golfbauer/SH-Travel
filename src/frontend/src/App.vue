<template>
  <Map/>
  <Menu/>
  <ReisepunktErstellen/>
</template>

<script>
import Map from './components/Map.vue'
import ReisepunktErstellen from './components/ReisepunktErstellen'
import Menu from './components/Menu.vue'

export default {
  name: 'App',
  components: {
    Map,
    ReisepunktErstellen,
    Menu,
  }
}
</script>

<style>
#app {

}
</style>
