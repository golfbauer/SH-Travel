class Reisepunkt {
    constructor(input) {
        if(input === undefined) {
            input = {
                id: 'unknown',
                laengengrad: 0,
                breitengrad: 0,
                nutzeremail: 'unknown',
                name: 'unknown'
            }
        }

        const {id, laengengrad, breitengrad, nutzerEmail, name} = input

        this.id = id;
        this.laengengrad = laengengrad;
        this.breitengrad = breitengrad;
        this.nutzeremail = nutzerEmail;
        this.name = name;
    }
}

async function getReisepunkte() {
    let response = await fetch('http://localhost:8080/reisepunkte');
    let myJson = await response.json();

    //console.log(myJson)

    //do something with the json
    let length = myJson.length;
    //console.log(length)
    var result = [];
    for (let i = 0; i < length; i++) {
        var reisepunkt = new Reisepunkt(myJson[i])
        result.push(reisepunkt);
    }
    console.log(result)
    return result;
}



function myfunction() {
    document.getElementById("laengengrad").innerText = reisepunkt.laengengrad;
    document.getElementById("breitengrad").innerText = reisepunkt.breitengrad;
    document.getElementById("nutzeremail").innerText = reisepunkt.nutzeremail;
    document.getElementById("name").innerText = reisepunkt.name;
}

// integrated leaflet scripts

var map = L.map('map', {
    center: [54.3227085, 10.1355550],
    zoom: 15
    });

L.tileLayer('https://api.maptiler.com/maps/streets/{z}/{x}/{y}.png?key=W4vKV7wGqEKyorE0V0tZ', {
    attribution: '<a href="https://www.maptiler.com/copyright/" target="_blank">&copy; MapTiler</a> <a href="https://www.openstreetmap.org/copyright" target="_blank">&copy; OpenStreetMap contributors</a>',
}).addTo(map);

/*var leafletIcon = L.icon({
  iconUrl: 'https://cdn.pixabay.com/photo/2020/04/29/07/54/coronavirus-5107715_1280.png',
  iconSize: [40,40],
  iconAnchor: [20,20],
  popupAnchor: [20,40]
})*/

var LeafletIcon = L.Icon.extend({
    options: {
        shadowUrl: 'https://leafletjs.com/examples/custom-icons/leaf-shadow.png',
        iconSize: [38,95],
        shadowSize: [50,64],
        iconAnchor: [22,94],
        shadowAnchor: [4,62],
        popupAnchor: [-3,-76]
    }
})

var greenIcon = new LeafletIcon({iconUrl:'https://leafletjs.com/examples/custom-icons/leaf-green.png'}),
    redIcon = new LeafletIcon({iconUrl:'https://leafletjs.com/examples/custom-icons/leaf-red.png'}),
    orangeIcon = new LeafletIcon({iconUrl:'https://leafletjs.com/examples/custom-icons/leaf-orange.png'})

// Reisepunkt adding
function setMarker(reisepunkt) {
    console.log(reisepunkt)
    var marker = L.marker([reisepunkt.breitengrad, reisepunkt.laengengrad],{icon:greenIcon}).addTo(map);
}

async function loadMarker() {
    var reisepunkte = await getReisepunkte();
    console.log(reisepunkte)
    var length = reisepunkte.length;
    console.log(length)

    for (let i = 0; i < length; i++) {
        console.log("setting marker")
        setMarker(reisepunkte[i])
    }
}

loadMarker();

// var marker = L.marker([54.3227085, 10.1355550],{icon:greenIcon}).addTo(map);
// var marker = L.marker([54.3237095, 10.1365550],{icon:redIcon}).addTo(map);
// var marker = L.marker([54.3247105, 10.1335550],{icon:orangeIcon}).addTo(map);

var circle = L.circle([54.373, 10.136], {
    color: 'red',
    fillColor: '#f03',
    fillOpacity: 0.5,
    radius: 500
}).addTo(map);

var polygon = L.polygon([
    [54.43, 10.43],
    [54.44, 10.48],
    [54.48, 10.43]
]).addTo(map);

//marker.bindPopup("<b>Kiel - Information...</b><br> Mr.Marker").openPopup();
circle.bindPopup("Hier gibts viel Corona");
polygon.bindPopup("Ich stehe ziemlich weit weg");