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
    return false
  } else if (laengengrad === undefined || laengengrad !== Number) {
    return false
  } else if (breitengrad === undefined || breitengrad !== Number) {
    return false
  } else if (nutzerEmail === undefined || nutzerEmail === '') {
    return false
  } else if (attraktionOeffnungszeiten === undefined) {
    return false
  }
  return true
}
