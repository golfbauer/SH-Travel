<template>
  <div>
    <div :id="item.component">
      <div v-if="item.component==='parent'">
        <b-nav-item @click="toggle()" class="bor-bot">
          <div class="dropdown-toggle">
            {{ item.name }}
          </div>
        </b-nav-item>
        <div v-if="showChild">
          <sidebar-menu-item v-on:click.native="handleClick(subitem.content)" v-for="(subitem, index) in childItems"
                             :key="index" :item="subitem" v-b-toggle.sidebar-no-header
                             v-on:openReiseBearbeiten="openReiseBearbeiten($event)"/>
        </div>
      </div>
      <div v-if="item.component === 'child'" class="flexbox">
          {{ item.name }}
          <b-button class="edit-btn" v-if="isReise(item.id)" @click="openReiseBearbeiten(item.id)" size="sm">Bearbeiten</b-button>
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
      chosenChild: undefined,
      childItems: []
    }
  },
  props: {
    item: {
      type: Object,
      required: true,
      // optional properties
      id: 0,
      name: '',
      component: undefined,
      category: undefined,
      content: undefined
    }
  },
  methods: {
    init () {
      const item = this.item
      if (item.component === 'parent' && item.content !== undefined) {
        for (let i = 0; i < item.content.length; i++) {
          const child = {
            name: item.content[i].name,
            component: 'child',
            content: item.content[i],
            id: item.content[i].id
          }
          this.childItems.push(child)
        }
      }
    },
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
    console.log('Component', this.item.component)
    console.log('Content', this.item.content)

    this.init()
  }
}
</script>

<style lang="scss" scoped>

@import "./src/assets/style.scss";

.bor-bot {
  border-bottom: solid 1px #1A776A;
}

#item {
  background-color: $primary-color;

  color: #fff;

  &:hover {
    color: #000;
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

  position: relative;
  box-sizing: border-box;

  height: 2.5em;
  border-bottom: 0.1em solid #1A776A;

  text-align: center;
  vertical-align: middle;
  line-height: 2.5em;

  &:hover {
    background-color: #39f68e;
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
