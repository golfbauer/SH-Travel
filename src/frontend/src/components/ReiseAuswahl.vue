<template>
  <div id="auswahl_base">
    <div id="auswahl_container" @mouseover="mouseOver" @mouseleave="mouseLeave">
      <div id="auswahl_header">
        <h5>Speichern in...</h5>
        <font-awesome-icon icon="times" @click="cancelThis"/>
      </div>
      <hr>
      <ul id="auswahl_body">
        <li v-for="(reise, index) in reisen" :key="index" v-on:click="addReisepunkt(reise)">
          <h5>{{ reise.name }}</h5>
        </li>
      </ul>
      <hr>
      <div id="auswahl_footer" @click="selectReise()">
        <h5>Neue Reise erstellen</h5>
        <font-awesome-icon icon="plus"/>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import * as mapService from '@/service/helper/map'
import * as reiseService from '@/service/api/reise'

export default {
  name: 'ReiseAuswahl',
  data () {
    return {
      reisen: []
    }
  },
  methods: {
    async init () {
      await this.$store.dispatch('fetchReisen').then(() => {
        this.reisen = this.getReisen
      })
    },
    mouseOver () {
      mapService.toggleMapIO(false)
    },
    mouseLeave () {
      mapService.toggleMapIO(true)
    },
    selectReise (reise) {
      this.$store.dispatch('selectReise', reise)
      this.proceedThis()
    },
    addReisepunkt (reise) {
      const reisepunkt = this.getReisepunkt
      reiseService.addReisepunkt(reise, reisepunkt)
      this.$store.dispatch('fetchReisen')
      this.$emit('updateMenu', true)
      this.cancelThis()
    },
    proceedThis () {
      mapService.toggleMapIO(true)
      this.$emit('selected')
    },
    cancelThis () {
      mapService.toggleMapIO(true)
      this.$emit('cancel')
    }
  },
  computed: {
    ...mapGetters(['getReisen', 'getReisepunkt'])
  },
  created () {
    this.init()
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  z-index: 990;
}

#auswahl_base {
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
}

#auswahl_container {
  position: relative;
  background-color: #FFFFFF;
  margin: auto;
  top: 30%;
  width: 300px;
  height: 35%;
}

#auswahl_header {
  display: flex;
  justify-content: space-between;
  padding: 3%;
  height: 15%;
  align-items: center;
}

#auswahl_header h4 {
  display: inline;
}

#auswahl_header svg {
  width: 20px;
  height: 20px;
}

#auswahl_body {
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  height: 70%;
  overflow: hidden;
  overflow-y: scroll;
}

#auswahl_body li {
  margin-top: 5%;
  margin-bottom: 5%;
  padding-left: 3%;
  padding-right: 3%;
}

#auswahl_body li h5 {
  text-align: left;
}

#auswahl_footer {
  display: flex;
  justify-content: space-between;
  padding: 3%;
  height: 15%;
  align-items: center;
}

#auswahl_footer svg {
  width: 20px;
  height: 20px;
}
</style>
