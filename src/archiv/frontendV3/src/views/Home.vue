<template>
  <Menu/>
  <Map/>
</template>

<script>
import Menu from '@/components/Menu.vue';
import Map from '@/components/Map.vue';

export default {
  name: 'Home',
  components: {
    Menu,
    Map,
  },
};
</script>
