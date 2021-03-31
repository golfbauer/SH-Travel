<template>
  <div id="menu" :class="{ active: isExpanded }" @click.prevent="toggle">
    <div id="menu-header" v-if="profile.signedIn">
      <img :src="img" :alt="name" />
    </div>
    <div id="menu-header" v-else>
      <button class="btn btn-orange">Registrieren</button>
      <button class="btn btn-gray">Anmelden</button>
    </div>
    <div id="menu-items">
      <sidebar-menu-item v-for="(item, index) in menu" :key="index" :item="item"/>
    </div>
  </div>
</template>

<script>
import SidebarMenuItem from './SidebarMenuItem.vue';
export default {
  name: "Menu",
  components: {
    SidebarMenuItem,
  },
  props: {
    menu: {
      type: Array,
      required: true
    },
  },
  data() {
    return {
      isExpanded: false,
      profile: {
        signedIn: false,
        name: "",
        img: "",
        typ: "",
      },
    };
  },
  methods: {
    toggle() {
      this.isBurgerActive = !this.isBurgerActive;
    },
    get: function () {
      this.$http.get("");
    },
  },
};
</script>

<style scoped>
#menu {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  width: 16rem;
  max-width: 100%;
  margin: 0;
  background-color: rgb(0, 172, 114);
}
#menu-header {
  background-color: rgb(0, 124, 83);
}
.btn {
  box-sizing: border-box;
  margin: 0.5rem;
  padding: 0.5rem 0;
  width: 7rem;
  border-radius: 0.3rem;
  border: 0;
}
.btn-gray {
  background-color: gray;
  color: #fff;
}
.btn-orange {
  background-color: orange;
  color: #fff;
}
</style>