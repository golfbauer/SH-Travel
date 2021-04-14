import L from 'leaflet'
import { getReisepunkte, Reisepunkt } from '@/lib/Reisepunkt'

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

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
    {
      attribution: 'Map data (c) <a href="https://www.openstreetmap.org/copyright">OpenStreetMap contributors</a>'
    }).addTo(map)

  // Eventlistener einbauen
  map.on('dblclick', mouseDoubleclick)
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
  console.log('clicked')
}
