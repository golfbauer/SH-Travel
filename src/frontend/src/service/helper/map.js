import L from 'leaflet'
import * as Icons from '@/assets/external/leaflet-color-markers/js/leaflet-color-markers'
import 'leaflet-routing-machine'
import { fetchReisepunkte } from '@/service/api/reisepunkt'
import { deletePunkt } from '@/service/api/punkt'
import { deleteSehenswuerdigkeit } from '@/service/api/sehenswuerdigkeit'
import { deleteAttraktion } from '@/service/api/attraktion'

/* DATA */
let mapComponent
let lMap
let markers
let route

/* PUBLIC FUNCTIONS */

// A function which instantiates a leaflet map.
export function createMap (component /* reference to the vue component implementing the map */) {
  // setting the component reference
  mapComponent = component

  // creating the map
  lMap = L.map('map_pane' /* DOM Element representing the map */, {
    minZoom: 8,
    maxZoom: 18,
    maxBounds: [[57.0, 7.0], [52.0, 13.0]],
    zoomControl: false
  })

  // setting the starting position of the camera and its zoom
  lMap.setView([54.1667907, 9.8528554], 9)
  // disabling zoom on doubleclick
  lMap.doubleClickZoom.disable()

  // adding tiles to the map
  const tileUrl = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
  const attribution = 'Map data (c) ' +
    '<a href="https://constwww.openstreetmap.org/copyright">OpenStreetMap contributors</a>'
  L.tileLayer(tileUrl, attribution)
    .addTo(lMap)

  /*
  * adding an eventlistener to handle certain events such as doubleclicking he map to create
  * point of interest
  */
  const event = 'dblclick'
  lMap.on(event, function (e) {
    toggleMapIO(false)
    const latlng = lMap.mouseEventToLatLng(e.originalEvent)
    const lat = latlng.lat
    const lng = latlng.lng
    console.log(latlng)
    mapComponent.setClickedCoords(lat, lng)
  })

  // initializing marker group
  markers = L.layerGroup().addTo(lMap)
}

// adding a route to the map to display
export function addRoute (routeIn) {
  console.log(routeIn)
  removeRoute()

  // creating waypoints based on given routes existing points
  const points = []
  routeIn.reisepunkte.forEach((point) => {
    const coords = L.latLng(point.breitengrad, point.laengengrad)
    points.push(coords)
  })
  // const osrmUrl = 'http://picoaio.de:5000/route/v1'

  // check if the route contains more than one waypoint
  if (points.length > 1) {
    console.log('lade route')
    route = L.Routing.control({
      waypoints: points,
      draggableWaypoints: false,
      lineOptions: {
        addWaypoints: false
      }
      // serviceUrl: osrmUrl
    }).addTo(lMap)
    route.hide()
    return true
  }
  return false
}

export function removeRoute () {
  if (route !== undefined) {
    route.remove()
  }
}

// loading travel_points and places them as map marker
export async function loadMarker () {
  try {
    const reisepunkte = await fetchReisepunkte()
    const length = reisepunkte.length
    markers.remove()
    markers = L.layerGroup().addTo(lMap)
    for (let i = 0; i < length; i++) {
      setMarker(reisepunkte[i])
    }
  } catch (e) {
    console.log('Es trat ein Fehler beim Laden der Reisepunkte auf')
  }
}

export function setMarker (point) {
  if (point.breitengrad === null || point.laengengrad === null) {
    return
  }

  switch (point.typ) {
    case 'punkt':
      var marker = L.marker([point.breitengrad, point.laengengrad], { icon: Icons.blueIcon })
        .addTo(markers)
      var popup = createPunktPopup(point)
      marker.bindPopup(popup)
      break
    case 'sehenswuerdigkeit':
      marker = L.marker([point.breitengrad, point.laengengrad], { icon: Icons.greenIcon })
        .addTo(markers)
      popup = createSehenswuerdigkeitPopup(point)
      marker.bindPopup(popup)
      break
    case 'attraktion':
      marker = L.marker([point.breitengrad, point.laengengrad], { icon: Icons.goldIcon })
        .addTo(markers)
      popup = createAttraktionPopup(point)
      marker.bindPopup(popup)
      break
    case undefined:
      console.error('Could not set marker, recieved a Reisepunkt without type')
      break
  }
}

// toggling dragging and scrolling depending on input
export function toggleMapIO (isEnabled) {
  if (isEnabled) {
    console.log('enabeling map io')
    toggleDragging(isEnabled)
    toggleScrolling(isEnabled)
  } else {
    console.log('disabeling map io')
    toggleDragging(isEnabled)
    toggleScrolling(isEnabled)
  }
}

export function toggleDragging (isEnabled) {
  if (isEnabled) {
    console.log('enabeling dragging')
    lMap.dragging.enable()
  } else {
    console.log('disabeling dragging')
    lMap.dragging.disable()
  }
}

export function toggleScrolling (isEnabled) {
  if (isEnabled) {
    console.log('enabeling zoom')
    lMap.scrollWheelZoom.enable()
  } else {
    console.log('disabeling zoom')
    lMap.scrollWheelZoom.disable()
  }
}

/* PRIVATE FUNCTIONS */

function createPunktPopup (point) {
  // create popup container
  const container = L.DomUtil.create('div', 'leaflet-popup-container')
  container.style = 'align-content: center; width: 210px;'

  // create popup title
  const title = L.DomUtil.create('h4', 'leaflet-popup-title')
  title.style = 'width: 200px; -moz-hyphens: auto; -o-hyphens: auto; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto;'
  title.textContent = point.name

  // create the addButton (adding Reisepunkt to a Reise)
  const addButton = L.DomUtil.create('button', 'leaflet-popup-add-button')
  addButton.textContent = 'Zu Reise hinzufügen'
  addButton.style = 'width: 200px; height: 30px; align-content: center; background-color: #1B998B; color: white; font-weight: bold; border-color: #1B998B;'

  // create the editButton (editing selected Reisepunkt)
  const editButton = L.DomUtil.create('button', 'popupEditButton')
  editButton.textContent = 'Bearbeiten'
  editButton.style = 'width: 200px; height: 30px; align-content: center; background-color: #FF9B71; color: white; font-weight: bold; border-color: #FF9B71;'

  // create the deleteButton (deleting selected Reisepunkt)
  const deleteButton = L.DomUtil.create('button', 'popupDeleteButton')
  deleteButton.textContent = 'Löschen'
  deleteButton.style = 'width: 200px; height: 30px; align-content: center; background-color: #4A5655; color: white; font-weight: bold; border-color: #4A5655;'

  // add event listener to the addButton
  L.DomEvent.addListener(addButton, 'click', () => {
    mapComponent.openReiseAuswahl(point)
  })

  // add event listener to the editButton
  L.DomEvent.addListener(editButton, 'click', () => {
    mapComponent.openReisepunktBearbeiten(point)
    console.log('Bearbeiten Fenster öffnen und Daten laden')
  })

  // add event listener to the deleteButton
  L.DomEvent.addListener(deleteButton, 'click', () => {
    deletePunkt(point.id) // ToDo: Überprüfen obs funktioniert
    console.log('Entferne Reisepunkt ' + point.id + ' ' + point.name + ' vom Typen ' + point.typ)
  })

  container.appendChild(title)
  container.appendChild(addButton)
  container.appendChild(editButton)
  container.appendChild(deleteButton)

  // Popup erstellen
  const popup = L.popup()
    .setContent(container)

  return popup
}

function createSehenswuerdigkeitPopup (point) {
  // create popup container
  const container = L.DomUtil.create('div', 'leaflet-popup-container')
  container.style = 'align-content: center; width: 210px;'

  // create popup title
  const title = L.DomUtil.create('h4', 'leaflet-popup-title')
  title.style = 'width: 200px; -moz-hyphens: auto; -o-hyphens: auto; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto;'
  title.textContent = point.name

  // create popup description
  const description = L.DomUtil.create('p', 'leaflet-popup-description')
  description.textContent = point.beschreibung
  description.style = 'width: 200px;'

  // creating picture display

  // create the addButton (adding Reisepunkt to a Reise)
  const addButton = L.DomUtil.create('button', 'leaflet-popup-add-button')
  addButton.textContent = 'Zu Reise hinzufügen'
  addButton.style = 'width: 200px; height: 30px; align-content: center; background-color: #1B998B; color: white; font-weight: bold; border-color: #1B998B;'

  // create the editButton (editing selected Reisepunkt)
  const editButton = L.DomUtil.create('button', 'popupEditButton')
  editButton.textContent = 'Bearbeiten'
  editButton.style = 'width: 200px; height: 30px; align-content: center; background-color: #FF9B71; color: white; font-weight: bold; border-color: #FF9B71;'

  // create the deleteButton (deleting selected Reisepunkt)
  const deleteButton = L.DomUtil.create('button', 'popupDeleteButton')
  deleteButton.textContent = 'Löschen'
  deleteButton.style = 'width: 200px; height: 30px; align-content: center; background-color: #4A5655; color: white; font-weight: bold; border-color: #4A5655;'

  // add event listener to the addButton
  L.DomEvent.addListener(addButton, 'click', () => {
    mapComponent.openReiseAuswahl(point)
  })

  // add event listener to the editButton
  L.DomEvent.addListener(editButton, 'click', () => {
    mapComponent.openReisepunktBearbeiten(point)
    console.log('Bearbeiten Fenster öffnen und Daten laden')
  })

  // add event listener to the deleteButton
  L.DomEvent.addListener(deleteButton, 'click', () => {
    deleteSehenswuerdigkeit(point.id) // ToDo: Überprüfen obs funktioniert
    console.log('Entferne Reisepunkt ' + point.id + ' ' + point.name + ' vom Typen ' + point.typ)
  })

  container.appendChild(title)
  container.appendChild(description)
  container.appendChild(addButton)
  container.appendChild(editButton)
  container.appendChild(deleteButton)

  // Popup erstellen
  const popup = L.popup()
    .setContent(container)

  return popup
}

function createAttraktionPopup (point) {
  // create popup container
  const container = L.DomUtil.create('div', 'leaflet-popup-container')
  container.style = 'align-content: center; width: 210px;'

  // create popup title
  const title = L.DomUtil.create('h4', 'leaflet-popup-title')
  title.style = 'width: 200px; -moz-hyphens: auto; -o-hyphens: auto; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto;'
  title.textContent = point.name

  // create popup description
  const description = L.DomUtil.create('p', 'leaflet-popup-description')
  description.textContent = point.beschreibung
  description.style = 'width: 200px;'

  // creating picture display

  // creating 'Oeffnungszeiten' display

  // create the addButton (adding Reisepunkt to a Reise)
  const addButton = L.DomUtil.create('button', 'leaflet-popup-add-button')
  addButton.textContent = 'Zu Reise hinzufügen'
  addButton.style = 'width: 200px; height: 30px; align-content: center; background-color: #1B998B; color: white; font-weight: bold; border-color: #1B998B;'

  // create the editButton (editing selected Reisepunkt)
  const editButton = L.DomUtil.create('button', 'popupEditButton')
  editButton.textContent = 'Bearbeiten'
  editButton.style = 'width: 200px; height: 30px; align-content: center; background-color: #FF9B71; color: white; font-weight: bold; border-color: #FF9B71;'

  // create the deleteButton (deleting selected Reisepunkt)
  const deleteButton = L.DomUtil.create('button', 'popupDeleteButton')
  deleteButton.textContent = 'Löschen'
  deleteButton.style = 'width: 200px; height: 30px; align-content: center; background-color: #4A5655; color: white; font-weight: bold; border-color: #4A5655;'

  // add event listener to the addButton
  L.DomEvent.addListener(addButton, 'click', () => {
    mapComponent.openReiseAuswahl(point)
  })

  // add event listener to the editButton
  L.DomEvent.addListener(editButton, 'click', () => {
    mapComponent.openReisepunktBearbeiten(point)
    console.log('Bearbeiten Fenster öffnen und Daten laden')
  })

  // add event listener to the deleteButton
  L.DomEvent.addListener(deleteButton, 'click', () => {
    deleteAttraktion(point.id) // ToDo: Überprüfen obs funktioniert
    console.log('Entferne Reisepunkt ' + point.id + ' ' + point.name + ' vom Typen ' + point.typ)
  })

  container.appendChild(title)
  container.appendChild(description)
  container.appendChild(addButton)
  container.appendChild(editButton)
  container.appendChild(deleteButton)

  // Popup erstellen
  const popup = L.popup()
    .setContent(container)

  return popup
}
