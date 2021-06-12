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
        <!-- b-button type="button" class="btn btn-orange">Registrieren</b-button -->
        <!-- b-button type="button" class="btn btn-gray">Anmelden</b-button -->
      </div>
      <!-- SidebarMenuItems -->
      <nav class="mb-3">
        <b-nav vertical>
          <MenuItem v-for="(item, index) in menu" :key="index" :item="item"/>
        </b-nav>
      </nav>
      <!-- End SidebarMenuItems -->
      <b-button v-b-toggle.sidebar-no-header id="btn-close" type="button" title="Menu">
        &lt;&lt;
      </b-button>
    </b-sidebar>
    <!-- End Sidebar -->
  </div>
</template>

<script>
import MenuItem from '@/components/MenuItem.vue';

export default {
  name: 'Menu',
  components: {
    MenuItem,
  },
};
</script>

<style scoped>

</style>
