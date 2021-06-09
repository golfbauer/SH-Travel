import L, { latLng } from 'leaflet'
import 'leaflet-routing-machine'
import { getReisepunkte } from '@/lib/Reisepunkt'
/**
 * This script contains functions to create a leaflet map, as well as loading 'Reisepunkte' as mapmarkers and placing
 * them on the map.
 */

// Declaring leaflet map variable.
var map
var routeControl

/**
 * This function instantiates a leaflet map and adds an event listener to handle doubleklicks.
 */
function createMap (mapComponent) {
  map = L.map('map', {
    minZoom: 8,
    maxZoom: 18,
    maxBounds: [[57.0, 7.0], [52.0, 13.0]],
    zoomControl: false
    // dragging: true
  }).setView([54.3227085, 10.1355550], 13)

  map.doubleClickZoom.disable()

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
    {
      attribution: 'Map data (c) <a href="https://www.openstreetmap.org/copyright">OpenStreetMap contributors</a>'
    }).addTo(map)

  // eventlistener for double clicking the map
  map.on('dblclick', function (e) {
    toggleDragging(false)
    var latlng = map.mouseEventToLatLng(e.originalEvent)
    var lat = latlng.lat
    var lng = latlng.lng
    console.log()
    mapComponent.setClickedCoords(lat, lng)
  })
}

/**
 * Adds a router to the UI to display a possible route between multiple points on the map.
 *
 * @param route the route (Reise) to be displayed
 */
function addRoute (route) {
  console.log(route)

  removeRoute()

  var waypoints = []
  route.punkte.forEach((point) => {
    const waypoint = L.latLng(point.reisepunkt.breitengrad, point.reisepunkt.laengengrad)
    waypoints.push(waypoint)
  })
  console.log(waypoints)

  routeControl = L.Routing.control({
    waypoints: waypoints,
    draggableWaypoints: false,
    lineOptions: {
      addWaypoints: false
    }
  }).addTo(map)
  routeControl.hide()
}

function removeRoute () {
  if (routeControl !== undefined) {
    routeControl.remove()
  }
}

/**
 * This function is used to place mapmarker.
 */
function setMarker (reisepunkt) {
  if (reisepunkt.breitengrad === null || reisepunkt.laengengrad === null) {
    return
  }

  // Div erstellen
  var content = L.DomUtil.create('div', 'popupContainer')
  content.style = 'align-content: center;'

  // Childs erstellen
  var title = L.DomUtil.create('h4', 'popupTitle')
  title.style = 'width: 200px;'
  title.textContent = reisepunkt.name

  var lngText = L.DomUtil.create('p')
  lngText.innerHTML = 'Längengrad: ' + '<i>' + reisepunkt.laengengrad + '</i>'
  lngText.style = 'overflow-wrap: break-word; width: 200px;'

  var latText = L.DomUtil.create('p')
  latText.innerHTML = 'Breitengrad: ' + '<i>' + reisepunkt.breitengrad + '</i>'
  latText.style = 'overflow-wrap: break-word; width: 200px;'

  var beschreibungText = L.DomUtil.create('p')
  beschreibungText.style = 'width: 200px;'
  beschreibungText.textContent = 'Beschreibung hier einfügen...'

  var addButton = L.DomUtil.create('button', 'popupAddButton')
  addButton.textContent = 'Hinzufügen'
  addButton.style = 'width: 200px; height: 30px; align-content: center;'

  // Zusammenfügen
  content.appendChild(title)
  content.appendChild(lngText)
  content.appendChild(latText)
  content.appendChild(beschreibungText)
  content.appendChild(addButton)

  // Event hinzufügen
  L.DomEvent.addListener(addButton, 'click', function (event) {
    // ToDo: Marker in die Reise hinzufügen
    console.log('Hinzugefügt!')
  })

  // Popup erstellen
  var popup = L.popup().setContent(content)
  var markerTest = L.marker([reisepunkt.laengengrad, reisepunkt.breitengrad]).addTo(map)

  markerTest.bindPopup(popup)
}

/**
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

// Toggle dragging for the map
function toggleDragging (isEnabled) {
  isEnabled === true ? map.dragging.enable() : map.dragging.disable()
}

export {
  createMap,
  loadMarker,
  addRoute,
  toggleDragging,
  removeRoute
}
