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
}

function setMarker (reisepunkt) {
  // console.log(reisepunkt)
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

async function loadMarker () {
  var reisepunkte = await getReisepunkte()
  // console.log(reisepunkte)
  var length = reisepunkte.length
  // console.log(length)

  for (let i = 0; i < length; i++) {
    // console.log('setting marker')
    setMarker(reisepunkte[i])
  }
}

// function setCoordinates (event) {
//   lat = event.latlng.lat
//   lng = event.latlng.lng
//   console.log(lat + ' : ' + lng)
// }
