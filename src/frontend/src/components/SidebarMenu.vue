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
        <b-img left id="profile-img" v-bind="profileImgProps" :src="profile.img" rounded="circle"
               alt="Circle image"></b-img>
        <div id="profile-name">{{ profile.name }}</div>
        <div id="profile-typ">{{ profile.typ }}</div>
      </div>
      <div class="menu-header" v-else>
         <b-button @click="loadRegister" type="button" class="btn btn-orange">Registrieren</b-button>
         <b-button @click="loadLogin" type="button" class="btn btn-gray">Anmelden</b-button>
      </div>
      <!-- SidebarMenuItems -->
      <nav class="mb-3">
        <b-nav vertical>
          <sidebar-menu-item v-on:openReiseBearbeiten="openReiseBearbeiten($event)" v-for="(item, index) in menu" :key="index" :item="item"/>
        </b-nav>
      </nav>
      <!-- End SidebarMenuItems -->
      <b-button v-b-toggle.sidebar-no-header id="btn-close" type="button" title="Menu">
        &lt;&lt;
      </b-button>
    </b-sidebar>
    <!-- End Sidebar -->

    <ReiseBearbeiten v-if="showReiseBearbeiten" :reiseId="reiseId" v-on:updateShow="closeReiseBearbeiten($event)"/>
  </div>
</template>

<script>
import SidebarMenuItem from '@/components/SidebarMenuItem'
import { mapGetters } from 'vuex'
import ReiseBearbeiten from '@/components/ReiseBearbeiten'
import router from '@/router'

export default {
  name: 'Menu',
  components: {
    SidebarMenuItem,
    ReiseBearbeiten
  },
  data () {
    return {
      profileImgProps: { width: 100, height: 100 },
      profile: {
        signedIn: false,
        name: 'Max Mustermann',
        img: 'https://api-magazin.single.de/fileman/uploads/Neuer%20Ordner/gutes_profilbild_beispiel_4.jpg',
        typ: 'ExampleNutzer'
      },
      menu: [],
      showReiseBearbeiten: false,
      reiseId: 0
    }
  },
  computed: {
    ...mapGetters(['getReisen'])
  },
  methods: {
    loadMenueItems () {
      console.log('Loading Menu Items')
      this.menu = [
        {
          name: 'Mein Reisen',
          component: 'parent',
          category: 'reisen',
          content: this.getReisen
        }
      ]
    },
    closeReiseBearbeiten (status) {
      this.showReiseBearbeiten = !status
    },
    openReiseBearbeiten (reiseId) {
      this.showReiseBearbeiten = true
      this.reiseId = parseInt(reiseId)
    },
    async loadRegister () {
      await router.push('/register')
    },
    async loadLogin () {
      await router.push('/login')
    }
  },
  async mounted () {
    this.$store.dispatch('fetchReisen').then(() => {
      this.loadMenueItems()
    })
  }
}
</script>

<style lang="scss" scoped>

@import "./src/assets/style";

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
  color: #fff;

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
}

.grid-container {
  display: grid;
  column-gap: 0.5rem;
  padding: 0.5rem 1.5rem 0.5rem 0.5rem;
}

.btn {
  margin: 0.5rem;

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
  border-radius: 3px;
  background-color: $background-color-1;
  pointer-events: all;
  transition: transform .6s cubic-bezier(.165, .84, .44, 1);
}

.burger-bar {
  background-color: #fff;
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

<style lang="scss">
$background-color-1: rgb(0, 124, 83);

.b-sidebar-body{
  background-color: $background-color-1;
}
</style>
