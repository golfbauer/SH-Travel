<template>
  <div>
    <b-nav-item :id="component" :href='link'>
      <div v-if="component==='parent'">
        <div class="dropdown-toggle">
        {{title}}
        </div>
        <sidebar-menu-item/>
      </div>
      <div v-else>
        {{title}}
      </div>
    </b-nav-item>
  </div>
</template>

<script>
export default {
  name: 'SidebarMenuItem',
  data () {
    return {
      link: '#',
      title: 'SidebarMenuItem',
      component: 'parent'
    }
  }
}
</script>

<style lang="scss" scoped>
$primary-color: #1b998b;
$primary-color-2: #3dd4b4;

#item{
  background-color: $primary-color;

  a{
  color: #fff;

    &:hover{
      color: #000;
    }
  }
}

#parent{
  background-color: $primary-color;

  a{
  color: #fff;

    &:hover{
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

#child{
  background-color: $primary-color-2;

  a{
  color: #fff;

    &:hover{
      color: #000;
    }
  }
}
</style>
