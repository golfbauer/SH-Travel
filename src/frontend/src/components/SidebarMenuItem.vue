<template>
  <div>
    <div :id="item.component">
      <div v-if="item.component==='parent'">
        <b-nav-item :href='item.href'>
          <div class="dropdown-toggle">
            {{ item.title }}
          </div>
        </b-nav-item>
        <sidebar-menu-item v-for="(subitem, index) in item.child" :key="index" :item="subitem"/>
      </div>
      <b-nav-item :href='item.href' v-else>
        {{ item.title }}
      </b-nav-item>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SidebarMenuItem',
  props: {
    item: {
      type: Object,
      required: true
    }
  }
}
</script>

<style lang="scss" scoped>
$primary-color: #1b998b;
$primary-color-2: #3dd4b4;

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
