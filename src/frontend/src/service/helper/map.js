import L from 'leaflet'
import 'leaflet-routing-machine'
import { fetchTravelPoints } from '@/service/api/travelPoints'

/* DATA */
let mapComponent
let lMap
let route

/* FUNCTIONS */

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
  /* DUMMY */
  try {
    const travelPoints = await fetchTravelPoints()
    const { length } = travelPoints
    console.log(length)
    console.log(travelPoints)
    for (let i = 0; i < length; i++) {
      console.log('setting marker')
      setMarker(travelPoints[i], mapComponent)
    }
  } catch (e) {
    console.log('Konnte Reisepunkte nicht laden ERROR')
  }
}

export function setMarker (point) {
  /* DUMMY */
  if (point.breitengrad === null || point.laengengrad === null) {
    return
  }

  // Div erstellen
  const content = L.DomUtil.create('div', 'popupContainer')
  content.style = 'align-content: center;'

  // Childs erstellen
  const title = L.DomUtil.create('h4', 'popupTitle')
  title.style = 'width: 200px;'
  title.textContent = point.name

  const lngText = L.DomUtil.create('p')
  lngText.innerHTML = `Längengrad: <i>${point.laengengrad}</i>`
  lngText.style = 'overflow-wrap: break-word; width: 200px;'

  const latText = L.DomUtil.create('p')
  latText.innerHTML = `Breitengrad: <i>${point.breitengrad}</i>`
  latText.style = 'overflow-wrap: break-word; width: 200px;'

  const beschreibungText = L.DomUtil.create('p')
  beschreibungText.style = 'width: 200px;'
  beschreibungText.textContent = 'Beschreibung hier einfügen...'

  const addButton = L.DomUtil.create('button', 'popupAddButton')
  addButton.textContent = 'Hinzufügen'
  addButton.style = 'width: 200px; height: 30px; align-content: center;'

  const editButton = L.DomUtil.create('button', 'popupEditButton')
  editButton.textContent = 'Bearbeiten'
  editButton.style = 'width: 200px; height: 30px; align-content: center;'

  // Zusammenfügen
  content.appendChild(title)
  content.appendChild(lngText)
  content.appendChild(latText)
  content.appendChild(beschreibungText)
  content.appendChild(addButton)
  content.appendChild(editButton)

  // Event hinzufügen
  L.DomEvent.addListener(addButton, 'click', () => {
    // ToDo: Marker in die Reise hinzufügen
    mapComponent.openReiseAuswahl(point)
  })

  L.DomEvent.addListener(editButton, 'click', () => {
    mapComponent.openReisepunktBearbeiten(point)
    console.log('Bearbeiten Fenster öffnen')
  })

  // Popup erstellen
  const popup = L.popup()
    .setContent(content)
  const markerTest = L.marker([point.breitengrad, point.laengengrad])
    .addTo(lMap)

  markerTest.bindPopup(popup)
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
