<template>
  <div class="home">
    <SidebarMenu/>
    <Map/>
    <ReiseBearbeiten/>
  </div>
</template>

<script>
// @ is an alias to /src
import SidebarMenu from '@/components/SidebarMenu'
import Map from '@/components/Map'
import ReiseBearbeiten from '@/components/ReiseBearbeiten'

export default {
  name: 'Home',
  components: {
    SidebarMenu,
    Map,
    ReiseBearbeiten
  }
}
</script>
