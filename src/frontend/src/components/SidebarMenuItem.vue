<template>
  <div>
    <div :id="item.component">
      <div v-if="item.component==='parent'">
        <b-nav-item @click="toggle()" class="bor-bot"> <!--:href='item.href'-->
          <div class="dropdown-toggle">
            {{ item.name }}
          </div>
        </b-nav-item>
        <div v-if="showChild">
          <sidebar-menu-item v-on:click.native="handleClick(subitem)" v-for="(subitem, index) in this.item.content" :key="index" :item="subitem"/>
        </div>
      </div>
      <div v-if="item.component !== 'parent'">
        {{ item.name }}
      </div>
    </div>
  </div>
</template>

<script>
import * as mapService from '@/service/helper/map'

export default {
  name: 'SidebarMenuItem',
  data () {
    return {
      showChild: true,
      chosenChild: undefined
    }
  },
  props: {
    item: {
      type: Object,
      required: true,
      // optional properties
      component: undefined,
      category: undefined,
      content: undefined
    }
  },
  methods: {
    handleClick (subitem) {
      switch (this.item.category) {
        case 'reisen':
          if (subitem === this.chosenChild) {
            this.chosenChild = undefined
            mapService.removeRoute()
          } else {
            this.chosenChild = subitem
            mapService.addRoute(subitem)
          }
          break
        case undefined:
          break
      }
    },
    toggle () {
      if (this.item.component === 'parent' && this.showChild === false) {
        this.showChild = true
      } else {
        this.showChild = false
      }
    }
  },
  created () {
    console.log('Content', this.item.content)
  }
}
</script>

<style lang="scss" scoped>

@import "./src/assets/style.scss";

.bor-bot{
  border-bottom: #000 solid 1px;
}

#item {
  background-color: $primary-color;

  a {
    color: #fff;

    &:hover {
      color: #000;
    }
  }
}

#parent {
  background-color: $primary-color;

  a {
    color: #fff;

    &:hover {
      color: #000;
    }
  }
}

.dropdown-toggle:after {
  box-sizing: border-box;
  display: inline-block;
  margin-left: .255em;
  vertical-align: .255em;
  content: "";
  border-top: .3em solid;
  border-right: .3em solid transparent;
  border-bottom: 0;
  border-left: .3em solid transparent;
}

#child {
  background-color: $primary-color-2;

  a {
    color: #fff;

    &:hover {
      color: #000;
    }
  }
}
</style>
