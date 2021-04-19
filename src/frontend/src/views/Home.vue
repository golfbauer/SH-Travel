<template>
  <div class="home">
    <SidebarMenu/>
    <Map/>
    <ReisepunktErstellen/>
  </div>
</template>

<script>
// @ is an alias to /src
import SidebarMenu from '@/components/SidebarMenu'
import Map from '@/components/Map'
import ReisepunktErstellen from '@/components/ReisepunktErstellen'

export default {
  name: 'Home',
  components: {
    SidebarMenu,
    Map,
    ReisepunktErstellen
  }
}
</script>
