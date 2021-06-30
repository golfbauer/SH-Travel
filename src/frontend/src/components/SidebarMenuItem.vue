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
          <sidebar-menu-item v-b-toggle.sidebar-no-header v-on:openReiseBearbeiten="openReiseBearbeiten($event)" v-on:click.native="handleClick(subitem)" v-for="(subitem, index) in item.content" :key="index" :item="subitem"/>
        </div>
      </div>
      <b-nav-item :href='item.href' v-else class="bor-bot">
        <div class="flexbox">
          {{ item.name }}
          <b-button class="edit-btn" v-if="isReise(item.id)" @click="openReiseBearbeiten(item.id)" size="sm">Bearbeiten</b-button>
        </div>
      </b-nav-item>
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
    },
    isReise (reiseId) {
      if (reiseId !== undefined && reiseId >= 0) {
        return true
      }
      return false
    },
    openReiseBearbeiten (reiseId) {
      console.log('button pressed' + reiseId)
      this.$emit('openReiseBearbeiten', reiseId)
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

.flexbox {
  display: flex;
  justify-content: space-between;
  text-align: left;
}

.edit-btn {
  z-index: 9999;
  margin: 0;
  height: min-content;
  padding: 0.1em 0.4em;
  align-self: center
}
</style>
