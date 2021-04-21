import L from 'leaflet'
import { getReisepunkte, Reisepunkt } from '@/lib/Reisepunkt'
import { setPopUpShow } from '@/components/Map'

export {
  createMap,
  loadMarker
}

var map = 0

function createMap () {
  map = L.map('map', {
    minZoom: 8,
    maxZoom: 18,
    maxBounds: [[57.0, 7.0], [52.0, 13.0]],
    zoomControl: false
  }).setView([54.3227085, 10.1355550], 13)

  map.doubleClickZoom.disable()

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
    {
      attribution: 'Map data (c) <a href="https://www.openstreetmap.org/copyright">OpenStreetMap contributors</a>'
    }).addTo(map)

  // Eventlistener für Doubleclick
  map.on('dblclick', mouseDoubleclick)

  // Eventlistener für MouseMove
  // map.on('mousemove', function (event) {
  //   setCoordinates(event)
  // })
}

function setMarker (reisepunkt) {
  console.log(reisepunkt)
  var marker = L.marker([reisepunkt.breitengrad, reisepunkt.laengengrad]).addTo(map)
  marker.bindPopup(reisepunkt.name)
}

async function loadMarker () {
  var reisepunkte = await getReisepunkte()
  console.log(reisepunkte)
  var length = reisepunkte.length
  console.log(length)

  for (let i = 0; i < length; i++) {
    console.log('setting marker')
    setMarker(reisepunkte[i])
  }
}

function mouseDoubleclick (event) {
  // console.log('Clicked - Menü öffnen zum Erstellen\nKoordinaten lauten: ' + lat + ' : ' + lng)
  var lat, lng
  // lat = map.mouseEventToLatLng(event).lat
  // lng = map.mouseEventToLatLng(event).lng
  // ReisepunktErstellen.methods.showMenu(lat, lng)
  setPopUpShow()
}

// function setCoordinates (event) {
//   lat = event.latlng.lat
//   lng = event.latlng.lng
//   console.log(lat + ' : ' + lng)
// }
