import L from 'leaflet'
import { getReisepunkte } from '@/lib/Reisepunkt'
/**
 * This script contains functions to create a leaflet map, as well as loading 'Reisepunkte' as mapmarkers and placing
 * them on the map.
 */

// Declaring leaflet map variable.
var map = ''

/*
 * This function instantiates a leaflet map and adds an event listener to handle doubleklicks.
 */
function createMap (mapComponent) {
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

  // eventlistener for double clicking the map
  map.on('dblclick', function (e) {
    var latlng = map.mouseEventToLatLng(e.originalEvent)
    var lat = latlng.lat
    var lng = latlng.lng
    console.log()
    mapComponent.setClickedCoords(lat, lng)
  })
}

/*
 * This function is used to place mapmarker.
 */
function setMarker (reisepunkt) {
  if (reisepunkt.breitengrad === null || reisepunkt.laengengrad === null) {
    return
  }
  var marker = L.marker([reisepunkt.breitengrad, reisepunkt.laengengrad]).addTo(map)
  marker.bindPopup(
    reisepunkt.name +
    ' <br> Längengrad: ' +
    reisepunkt.laengengrad +
    ' <br>Breitengrad: ' +
    reisepunkt.breitengrad)
}

/*
 * This function loads new 'Reisepunkt' objects rom the backend api and places them as mapmarkers on the map.
 */
async function loadMarker () {
  var reisepunkte = await getReisepunkte()
  var length = reisepunkte.length

  for (let i = 0; i < length; i++) {
    setMarker(reisepunkte[i])
  }
}

export {
  createMap,
  loadMarker
}
