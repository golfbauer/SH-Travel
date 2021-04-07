<template>
  <div id="menu" :class="{ active: isExpanded }" @click.prevent="toggle">
    <div class="grid-container menu-header" v-if="profile.signedIn">
      <img :src="profile.img" id="profile-img" :alt="profile.name"/>
      <div id="profile-name">{{ profile.name }}</div>
      <div id="profile-typ">{{ profile.typ }}</div>
    </div>
    <div class="menu-header" v-else>
      <button class="btn btn-orange">Registrieren</button>
      <button class="btn btn-gray">Anmelden</button>
    </div>
    <div id="menu-items">
      <sidebar-menu-item
          v-for="(item, index) in menu"
          :key="index"
          :item="item"
      />
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
      profile: {
        signedIn: true,
        name: "Max Mustermann",
        img: "https://api-magazin.single.de/fileman/uploads/Neuer%20Ordner/gutes_profilbild_beispiel_4.jpg",
        typ: "ExampleNutzer",
      },

      isExpanded: false,
    }
  }
  ,
  methods: {
    toggle() {
      this.isExpanded = !this.isExpanded;
    }
    ,
    get: function () {
      this.$http.get("");
    }
    ,
  }
  ,
}
;
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

.menu-header {
  background-color: rgb(0, 124, 83);
}

.grid-container {
  display: grid;
  column-gap: 0.5rem;
  padding: 0.5rem;
}

#profile-img {
  height: 5rem;
  width: 5rem;
  grid-row: 1;
  grid-row-end: 3;
  border-radius: 50%;
}

#profile-name {
  grid-row: 1;
  grid-row-end: 2;
  font-size: 1.4rem;
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