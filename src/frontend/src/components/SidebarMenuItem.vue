<template>
  <div id="sidebar-menu-item">
    {{ item.title }}
    {{ item.component}}
  </div>
</template>

<script>
export default {
  name: "SidebarMenuItem",
  props: {
    item: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
#sidebar-menu-item{
  padding: 0.5rem;
  color: #fff;
}
</style>