<template>
  <div>
    <div :id="component" >
      <div v-if="component==='parent'">
        <b-nav-item :href='link'>
          <div class="dropdown-toggle">
            {{title}}
          </div>
        </b-nav-item>
        <sidebar-menu-item title="Child" link="#" component="child"/>
      </div>
      <b-nav-item :href='link' v-else>
        {{title}}
      </b-nav-item>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SidebarMenuItem',
  props: {
    title: {
      type: String,
      required: true
    },
    link: {
      type: String,
      required: true
    },
    component: {
      type: String,
      required: true
    },
    childs: {
      type: Object
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
