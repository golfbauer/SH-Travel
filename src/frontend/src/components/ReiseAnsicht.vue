<template>
  <div id="reiseansichtcontainer" @mouseover="mouseOver" @mouseleave="mouseLeave">
    <h2 id="reisetitel">{{ reisename }}</h2>
    <button class="reiseoptionbuttons" id="btnabbrechen" @click="onCancelClick">Abbrechen</button>
    <button class="reiseoptionbuttons" id="btnspeichern" @click="onSaveClick">Speichern</button>

    <div class="reiseansicht" id="reiseansicht">
      <ul id="reiseliste">
        <li class="reiseitem" v-for="markeritem in markeritems" v-bind:key="markeritem">
          {{ markeritem }}
          <button class="reiseitembutton" @click="onDeleteClick">-</button>
        </li>
      </ul>
    </div>

  </div>
</template>

<script>
import { toggleScrolling, toggleDragging } from '@/lib/mapWrapper'

export default {
  name: 'ReiseAnsicht',
  data () {
    return {
      isHovered: false,
      reisename: 'Unbekannte Reise',
      markeritems: ['Oma Renata', 'Dienststelle No.2039', 'Burgerhouse', 'Eine wunderschöne Statue', '2000 Jahre alter Baum', 'See mit Spielplatz', 'Feinkostladen', 'Schokoladenfabrik', 'Reginas Wohnung', 'Hotel Kiel', 'Japan Garten', 'Nationalpark', 'Schönes Cafe', 'Kleiner Park', 'Autovermietung']
    }
  },
  methods: {
    mouseOver () {
      toggleScrolling(false)
      toggleDragging(false)
    },
    mouseLeave () {
      toggleScrolling(true)
      toggleDragging(true)
    },
    onSaveClick () {
      console.log('Reise wurde gespeichert.')
    },
    onCancelClick () {
      console.log('Änderungen nicht übernommen.')
    },
    onDeleteClick () {
      console.log('Marker aus Reiseliste entfernen')
    },
    addMarkerToReiseAnsicht (newMarker) {
      this.markeritems.push(newMarker)
    }
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
}

#reiseansicht {
  width: 300px;
  height: 600px;
  position: fixed;
  border-bottom: 20px solid #000000AA;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
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
  background: #000000AA;
  padding: 10px 20px;
  font-size: 20px;
  font-weight: 700;
  color: white;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

#reiseliste {
  position: relative;
  background: #000000AA;
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
