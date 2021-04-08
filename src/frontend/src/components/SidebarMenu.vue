<template>
  <div>
    <!-- Burger Button -->
    <div id="burger">
      <b-button v-b-toggle.sidebar-no-header type="button" class="burger-button" title="Menu">
        <span class="burger-bar burger-bar--1"></span>
        <span class="burger-bar burger-bar--2"></span>
        <span class="burger-bar burger-bar--3"></span>
      </b-button>
    </div>
    <!-- Sidebar -->
    <b-sidebar id="sidebar-no-header" aria-labelledby="sidebar-no-header-title" no-header shadow>
      <div class="grid-container menu-header" v-if="profile.signedIn">
        <img :src="profile.img" id="profile-img"/>
        <div id="profile-name">{{ profile.name }}</div>
        <div id="profile-typ">{{ profile.typ }}</div>
      </div>
      <div class="menu-header" v-else>
        <button class="btn btn-orange">Registrieren</button>
        <button class="btn btn-gray">Anmelden</button>
      </div>
      <!-- SidebarMenuItems -->
      <div id="menu-items">
        <!--sidebar-menu-item
            v-for="(item, index) in menu"
            :key="index"
            :item="item"
        /-->
      </div>
      <!-- End SidebarMenuItems -->
      <b-button v-b-toggle.sidebar-no-header id="btn-close" type="button" title="Menu">
        &lt;&lt;
      </b-button>
    </b-sidebar>
    <!-- End Sidebar -->
  </div>
</template>

<script>
// import SidebarMenuItem from '@/components/SidebarMenuItem'

export default {
  name: 'Menu',
  components: {
    // SidebarMenuItem
  },
  data () {
    return {
      profile: {
        signedIn: true,
        name: 'Max Mustermann',
        img: 'https://api-magazin.single.de/fileman/uploads/Neuer%20Ordner/gutes_profilbild_beispiel_4.jpg',
        typ: 'ExampleNutzer'
      },

      isExpanded: false
    }
  },
  methods: {
    get: function () {
      this.$http.get('')
    }
  }
}
</script>

<style lang="scss" scoped>

$background-color-1: rgb(0, 124, 83);
$background-color-2: rgb(0, 172, 114);
$orange: rgb(194, 126, 0);

#menu {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  width: 16rem;
  max-width: 100%;
  margin: 0;
  background-color: $background-color-2;
}

.menu-header {
  background-color: $background-color-1;
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

  &-gray {
    background-color: gray;
    color: #fff;
  }

  &-orange {
    background-color: $orange;
    color: #fff;
  }
}

#btn-close {
  position: absolute;
  display: block;
  width: 2.5rem;
  background-color: $orange;
  border-radius: 50%;
  bottom: 0;
  right: 0;
}

// Burger Icon
button {
  cursor: pointer;
  /* remove blue outline */
  &:focus {
    outline: 0;
  }
}

.burger-button {
  position: absolute;
  top: 7px;
  left: 7px;
  height: 30px;
  width: 32px;
  display: block;
  z-index: 999;
  border: 0;
  border-radius: 0;
  background-color: transparent;
  pointer-events: all;
  transition: transform .6s cubic-bezier(.165, .84, .44, 1);
}

.burger-bar {
  background-color: #130f40;
  position: absolute;
  top: 50%;
  right: 6px;
  left: 6px;
  height: 2px;
  width: auto;
  transition: transform .6s cubic-bezier(.165, .84, .44, 1), opacity .3s cubic-bezier(.165, .84, .44, 1), background-color .6s cubic-bezier(.165, .84, .44, 1);
}

.burger-bar--1 {
  -webkit-transform: translateY(-6px);
  transform: translateY(-6px);
}

.burger-bar--2 {
  transform-origin: 100% 50%;
  transform: scaleX(.8);
}

.burger-button:hover .burger-bar--2 {
  transform: scaleX(1);
}

.no-touchevents .burger-bar--2:hover {
  transform: scaleX(1);
}

.burger-bar--3 {
  transform: translateY(6px);
}
// End Burger Icon
</style>
