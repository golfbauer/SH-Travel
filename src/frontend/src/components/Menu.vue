<template>
  <div id="menu">
    <sidebar-menu :menu="menu" :collapsed="collapsed" :width="width" :widthCollapsed="widthCollapsed"
                  :showOneChild="showOneChild" :showChild="showChild"/>
  </div>
</template>

<script>
import {SidebarMenu} from 'vue-sidebar-menu'
import 'vue-sidebar-menu/dist/vue-sidebar-menu.css'

export default {
  name: "Menu",
  components: {
    SidebarMenu
  },
  data() {
    return {
      menu: [
        {
          header: 'Main Navigation',
          hiddenOnCollapse: true
        },
        {
          href: '/',
          title: 'Home',
          icon: 'fa fa-user'
        },
        {
          href: '/reisepunkterstellen',
          title: 'Reisepunkt erstellen',
          icon: 'fa fa-chart-area',
        },/*
        {
          href: '/reisepunkterstellen',
          title: 'neu Reisepunkt',
          icon: 'fa fa-chart-area',
          child: [
            {
              href: '/charts/sublink',
              title: 'Sub Link'
            }
          ]
        }*/
      ],
      collapsed: false,
      width: '300px',
      widthCollapsed: '65px',
      showOneChild: false,
      showChild: false,
    }
  }
}
</script>

<style lang="scss" scoped>
@import "../../node_modules/vue-sidebar-menu/src/scss/vue-sidebar-menu.scss";
</style>
<!-- npm i vue-router@next -->
<!-- npm install --save-dev sass-loader@10.1.1 node-sass style-resources-loader -->
<!-- npm i style-resources-loader -D -->
<!-- https://github.com/yaminncco/vue-sidebar-menu/tree/next -->
<!-- 
https://vuejsexamples.com/an-off-canvas-sidebar-vue-component/
https://vuejsexamples.com/a-vue-js-sidebar-menu-component/
-->