<template>
  <div class="home">
    <SidebarMenu :key="$store.state.reisen" :updateMenu="updateMenuBool"  v-on:resetUpM="resetUpM()"/>
    <Map v-on:updateMenu="updateMenu()"/>
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
  },
  data () {
    return {
      updateMenuBool: false
    }
  },
  methods: {
    updateMenu: function () {
      this.updateMenuBool = true
    },
    resetUpM: function () {
      this.updateMenuBool = false
    }
  }
}
</script>
