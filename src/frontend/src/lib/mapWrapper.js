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

  // Erstellen des Markers 2.0
  // Div erstellen
  var content = L.DomUtil.create('div', 'popupContainer')

  // Childs erstellen
  var title = L.DomUtil.create('h4', 'popupTitle')
  title.textContent = reisepunkt.name

  var lngText = L.DomUtil.create('p')
  lngText.textContent = 'Längengrad: ' + reisepunkt.laengengrad

  var latText = L.DomUtil.create('p')
  latText.textContent = 'Breitengrad: ' + reisepunkt.breitengrad

  var beschreibungText = L.DomUtil.create('p')
  beschreibungText.textContent = 'Ich bin ein Sampletext.'

  var addButton = L.DomUtil.create('button', 'popupAddButton')
  var buttonText = L.DomUtil.create('p')
  buttonText.textContent = 'Hinzufügen'
  addButton.appendChild(buttonText)

  // Zusammenfügen
  content.appendChild(title)
  content.appendChild(lngText)
  content.appendChild(latText)
  content.appendChild(beschreibungText)
  content.appendChild(addButton)

  // Event hinzufügen
  L.DomEvent.addListener(addButton, 'click', function (event) {
    console.log('Hinzugefügt!')
  })

  // Popup erstellen
  var popup = L.popup().setContent(content)
  var markerTest = L.marker([reisepunkt.breitengrad, reisepunkt.laengengrad]).addTo(map)

  markerTest.bindPopup(popup)
}

/*
 * This function loads new 'Reisepunkt' objects rom the backend api and places them as mapmarkers on the map.
 */
async function loadMarker () {
  var reisepunkte = await getReisepunkte()
  var length = reisepunkte.length

  for (let i = 0; i < length; i++) {
    console.log('setting marker')
    setMarker(reisepunkte[i])
  }
}

export {
  createMap,
  loadMarker
}
