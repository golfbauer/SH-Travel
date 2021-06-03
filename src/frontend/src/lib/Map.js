import L from 'leaflet'
import { getReisepunkte, Reisepunkt } from '@/lib/Reisepunkt'
import Map from '@/components/Map'

var map = 0

export {
  createMap,
  loadMarker,
  L,
  map
}

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
  map.on('dblclick', function (e) {
    var latlng = map.mouseEventToLatLng(e.originalEvent)
    var lat = latlng.lat
    var lng = latlng.lng
    Map.methods.handCoords(lat, lng)
  })

  // Eventlistener für MouseMove
  // map.on('mousemove', function (event) {
  //   setCoordinates(event)
  // })
  // Zum testen vom Marker für das Popup
  var lat, lng
  lat = 10.13
  lng = 54.3225

  // Div erstellen
  var content = L.DomUtil.create('div', 'popupContainer')

  // Childs erstellen
  var title = L.DomUtil.create('h4', 'popupTitle')
  title.textContent = 'ICH BIN TITEL'

  var lngText = L.DomUtil.create('p')
  lngText.textContent = 'Längengrad: ' + lng

  var latText = L.DomUtil.create('p')
  latText.textContent = 'Breitengrad: ' + lat

  var beschreibungText = L.DomUtil.create('p')
  beschreibungText.textContent = 'Hier wohnt meine Oma, weil sie hier wohnt.'

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
  var markerTest = L.marker([lng, lat]).addTo(map)

  markerTest.bindPopup(popup)
}

function setMarker (reisepunkt) {
  // console.log(reisepunkt)
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

async function loadMarker () {
  var reisepunkte = await getReisepunkte()
  // console.log(reisepunkte)
  var length = reisepunkte.length
  // console.log(length)

  for (let i = 0; i < length; i++) {
    console.log('setting marker')
    setMarker(reisepunkte[i])
  }
}

// function setCoordinates (event) {
//   lat = event.latlng.lat
//   lng = event.latlng.lng
//   console.log(lat + ' : ' + lng)
// }
