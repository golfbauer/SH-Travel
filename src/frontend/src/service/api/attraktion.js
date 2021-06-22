import axios from 'axios'

const attraktionUrl = '/SHTravel/attraktion'

export async function fetchAttraktionen () {
  const response = await axios.get(attraktionUrl)
  return response.data
}

export async function fetchAttraktion (id) {
  const url = attraktionUrl + '/' + id
  const response = await axios.get(url)
  return response.data
}

export async function createAttraktion ({ name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder, attraktionOeffnungszeiten }) {
  if (formCheck({ name, laengengrad, breitengrad, nutzerEmail, attraktionOeffnungszeiten })) {
    const response = await axios.post(attraktionUrl, {
      name,
      laengengrad,
      breitengrad,
      nutzerEmail,
      beschreibung,
      bilder,
      attraktionOeffnungszeiten
    })
    return response
  }
}

export async function updateAttraktion (id, { name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder, attraktionOeffnungszeiten }) {
  const url = attraktionUrl + '/' + id
  if (formCheck({ name, laengengrad, breitengrad, nutzerEmail, attraktionOeffnungszeiten })) {
    const response = await axios.post(url, {
      name,
      laengengrad,
      breitengrad,
      nutzerEmail,
      beschreibung,
      bilder,
      attraktionOeffnungszeiten
    })
    return response
  }
}

export async function deleteAttraktion (id) {
  const url = attraktionUrl + '/' + id
  const response = await axios.delete(url)
  return response
}

/* PRIVATE FUNCTIONS */

function formCheck ({ name, laengengrad, breitengrad, nutzerEmail, attraktionOeffnungszeiten }) {
  if (name === undefined || name === '') {
    console.log('Formcheck: name')
    console.log(name)
    return false
  } else if (laengengrad === undefined) {
    console.log('Formcheck: laengengrad')
    console.log(laengengrad)
    return false
  } else if (breitengrad === undefined) {
    console.log('Formcheck: breitengrad')
    console.log(breitengrad)
    return false
  } else if (nutzerEmail === undefined /* || nutzerEmail === '' */) {
    console.log('Formcheck: nutzeremail')
    console.log(nutzerEmail)
    return false
  } else if (attraktionOeffnungszeiten === undefined) {
    console.log('Formcheck: oeffnungszeiten')
    console.log(attraktionOeffnungszeiten)
    return false
  }
  return true
}
