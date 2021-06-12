<template>
  <div class="home">
    <SidebarMenu/>
    <Map/>
  </div>
</template>

<script>
// @ is an alias to /src
import SidebarMenu from '@/components/SidebarMenu'
import Map from '@/components/Map'

export default {
  name: 'Home',
  components: {
    SidebarMenu,
    Map
  }
}
</script>
