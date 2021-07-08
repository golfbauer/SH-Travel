<template>
  <div id="map_pane">
    <ReisepunktErstellen v-if="showReisepunktErstellen" v-on:updateShow="closeReisepunktErstellen($event)"
                         v-on:makeToast="makeToast($event)"/>
    <ReiseAuswahl v-if="showReiseAuswahl" v-on:selected="openReiseAnsicht($event)"
                  v-on:cancel="closeReiseAuswahl($event)" v-on:updateMenu="updateMenu($event)"/>
    <ReiseAnsicht v-if="showReiseAnsicht" v-on:cancel="closeReiseAnsicht" v-on:makeToast="makeToast($event)"/>
    <ReisepunktBearbeiten v-if="showReisepunktBearbeiten" v-on:cancel="closeReisepunktBearbeiten" v-on:close="closeReisepunktBearbeiten" :reisepunkt="passedPoint"/>
  </div>
</template>

<script>
import * as mapService from '@/service/helper/map'

import ReisepunktErstellen from '@/components/ReisepunktErstellen'
import ReiseAnsicht from '@/components/ReiseAnsicht'
import ReiseAuswahl from '@/components/ReiseAuswahl'
import ReisepunktBearbeiten from '@/components/ReisepunktBearbeiten'
import { loadMarker } from '@/service/helper/map'
import { mapGetters } from 'vuex'

export default {
  name: 'Map',
  data () {
    return {
      showReisepunktErstellen: false,
      showReiseAuswahl: false,
      showReiseAnsicht: false,
      showReisepunktBearbeiten: false,
      passedPoint: Object
    }
  },
  components: {
    ReisepunktErstellen,
    ReiseAnsicht,
    ReiseAuswahl,
    ReisepunktBearbeiten
  },
  mounted () {
    mapService.createMap(this)
    mapService.loadMarker()
  },
  updated () {
    mapService.loadMarker()
  },
  computed: {
    ...mapGetters(['isAuthenticated'])
  },
  methods: {
    openReisepunktErstellen: function () {
      this.showReisepunktErstellen = true
    },
    closeReisepunktErstellen: function () {
      this.showReisepunktErstellen = false
    },
    openReiseAuswahl: function (reisepunkt) {
      this.$store.dispatch('selectReisepunkt', reisepunkt)
      this.showReiseAuswahl = true
    },
    closeReiseAuswahl: function () {
      this.showReiseAuswahl = false
    },
    openReiseAnsicht: function () {
      this.closeReiseAuswahl()
      this.showReiseAnsicht = true
    },
    closeReiseAnsicht: function () {
      this.showReiseAnsicht = false
    },
    openReisepunktBearbeiten: function (point) {
      this.closeReisepunktBearbeiten()
      this.showReisepunktBearbeiten = true
      this.passedPoint = point
    },
    closeReisepunktBearbeiten: function () {
      this.showReisepunktBearbeiten = false
      loadMarker()
    },
    setClickedCoords: function (lat, lng) {
      // Prevent opening ReiseErstellen menu while using other menus
      if (this.showReisepunktErstellen === true || this.showReisepunktBearbeiten === true) {
        return false
      }
      if (this.isAuthenticated === false) {
        return false
      }

      this.$store.dispatch('chooseCoords', {
        lng: lng,
        lat: lat
      })
      this.openReisepunktErstellen()
      return true
    },
    makeToast: function (array) {
      this.$toasted.show(array[1], {
        type: array[0],
        duration: 3000
      })
    },
    updateMenu: function (bool) {
      this.$emit('updateMenu')
    }
  }
}
</script>

<style scoped>
#map_pane {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}
</style>

<style>
.leaflet-control-container .leaflet-routing-container-hide {
  display: none;
}
</style>
