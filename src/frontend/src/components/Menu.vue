<template>
  <div id="menu">
  <sidebar-menu :menu="menu"/>
  </div>
</template>

<script>

import {SidebarMenu} from 'vue-sidebar-menu'
export default {
  name: "Menu",
components:{
    SidebarMenu
},
  data() {
     return {
      menu: [
        {
          header: true,
          title: 'Main Navigation',
          hiddenOnCollapse: true
        },
        {
          href: '/',
          title: 'Dashboard',
          icon: 'fa fa-user'
        },
        {
          href: '/charts',
          title: 'Charts',
          icon: 'fa fa-chart-area',
          child: [
            {
              href: '/charts/sublink',
              title: 'Sub Link'
            }
          ]
        }
      ]
    }
  }
}
</script>

<style scoped>
</style>

<!-- https://github.com/yaminncco/vue-sidebar-menu/tree/next -->
<!-- 
https://vuejsexamples.com/an-off-canvas-sidebar-vue-component/
https://vuejsexamples.com/a-vue-js-sidebar-menu-component/
-->