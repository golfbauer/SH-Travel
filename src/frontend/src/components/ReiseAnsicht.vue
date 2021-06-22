<template>
  <div id="reiseansichtcontainer" @mouseover="mouseOver" @mouseleave="mouseLeave">
    <header>
      <input type="text" id="reisetitel" placeholder="Name eingeben">
      <div>
        <button class="reiseoptionbuttons" id="btnabbrechen" @click="onCancelClick">Abbrechen</button>
        <button class="reiseoptionbuttons" id="btnspeichern" @click="onSaveClick">Speichern</button>
      </div>
    </header>

    <div class="reiseansicht" id="reiseansicht">
      <ul id="reiseliste">
        <li class="reiseitem" v-for="(item, index) in reisepunkte" :key="index">
          {{ item.name }}
          <button class="reiseitembutton" @click="onDeleteClick">-</button>
        </li>
      </ul>
    </div>

  </div>
</template>

<script>
import * as journeyService from '@/service/api/reise'
import * as mapService from '@/service/helper/map'
import { mapGetters } from 'vuex'

export default {
  name: 'ReiseAnsicht',
  data () {
    return {
      reiseName: undefined,
      reisepunkte: []
    }
  },
  methods: {
    init () {
      const reisepunkt = this.getReisepunkt
      this.reisepunkte.push(reisepunkt)
    },
    makeToast (variant = null, title = null, body = null) {
      this.$emit('makeToast', [variant, title, body])
    },
    mouseOver () {
      mapService.toggleMapIO(false)
    },
    mouseLeave () {
      mapService.toggleMapIO(true)
    },
    onSaveClick () {
      this.reiseName = document.getElementById('reisetitel').value
      const reise = {
        name: this.reiseName,
        termin: undefined,
        oeffentlich: true,
        reisepunkte: this.reisepunkte,
        reisekatalog: undefined
      }
      if (journeyService.createReise(reise)) {
        console.log('posted reise')
        this.makeToast('success', 'Reise ', this.reiseName + ' wurde erfolgreich angelegt')
        this.$emit('cancel')
        mapService.toggleMapIO(true)
      } else {
        this.makeToast('danger', 'Fehler beim erstellen von ' + this.reiseName)
      }
    },
    onCancelClick () {
      mapService.toggleMapIO(true)
      this.$emit('cancel')
      console.log('Änderungen nicht übernommen.')
    },
    onDeleteClick () {
      console.log('Marker aus Reiseliste entfernen')
    }
  },
  computed: {
    ...mapGetters(['getReise', 'getReisepunkt'])
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

#reiseansichtcontainer {
  position: absolute;
  right: 2%;
  top: 5vh;
  background: #000000AA;
  border: 1px solid #000000AA;
  border-radius: 10px;
  padding-bottom: 20px;
}

#reiseansicht {
  width: 300px;
  height: 600px;
  overflow: hidden;
  overflow-y: scroll;
}

.reiseoptionbuttons {
  position: relative;
  bottom: 0;
  width: 50%;
  height: 20%;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

#btnabbrechen {
  background-color: #4A5655;
}

#btnspeichern {
  background-color: #FF9B71;
}

#btnabbrechen:active {
  background-color: #6b7776;
}

#btnspeichern:active {
  background-color: #ffba71;
}

.reiseoptionbuttons:hover {
  z-index: 100;
  background-color: #ffffff;
  color: #fff;
  opacity: 1;
  font-weight: bold;
}

#reisetitel {
  width: 300px;
  padding: 10px 20px;
  font-size: 20px;
  font-weight: 700;
  background-color: transparent;
  color: #ffffff;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

#reiseliste {
  position: relative;
}

.reiseitem {
  list-style: none;
  padding: 10px;
  width: 100%;
  background: #00000011;
  box-shadow: 0 5px 25px rgba(0,0,0,.1);
  transition: transform .5s;
  color: white;
  text-align: left;
  font-size: 16px;
}

#reiseliste:hover reiseitem {
  opacity: 0.2;
}

.reiseitem:hover {
  transform: scale(1.1);
  z-index: 100;
  background: #FFFFFF04;
  box-shadow: 0 5px 25px rgba(0,0,0,.2);
  color: #fff;
  opacity: 1;
  font-weight: bold;
}

.reiseitembutton {
  width: 20px;
  height: 20px;
  text-align: center;
  line-height: 20px;
  background: #FF9B71;
  color: #fff;
  display: inline-block;
  border-radius: 50%;
  margin-right: 10px;
  font-size: 22px;
  font-weight: 600;
  transform: translateY(-2px);
  float: right;
  border: none;
  text-decoration: none;
  display: inline-block;
}

.reiseitembutton:hover {
  transform: scale(1.1);
  background: #fff;
  color: #ff9b71;
}
</style>
