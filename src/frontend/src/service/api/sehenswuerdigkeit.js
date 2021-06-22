import axios from 'axios'

const sehenswuerdigkeitUrl = '/SHTravel/sehenswuerdigkeit'

export async function fetchSehenswuerdigkeiten () {
  const response = await axios.get(sehenswuerdigkeitUrl)
  return response.data
}

export async function fetchSehenswuerdigkeit (id) {
  const url = sehenswuerdigkeitUrl + '/' + id
  const response = await axios.get(url)
  return response.data
}

export async function createSehenswuerdigkeit ({ name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder }) {
  if (formCheck({ name, laengengrad, breitengrad, nutzerEmail })) {
    const response = await axios.post(sehenswuerdigkeitUrl, {
      name,
      laengengrad,
      breitengrad,
      nutzerEmail,
      beschreibung,
      bilder
    })
    return response
  }
}

export async function updateSehenswuerdigkeit (id, { name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder }) {
  const url = sehenswuerdigkeitUrl + '/' + id
  if (formCheck({ name, laengengrad, breitengrad, nutzerEmail })) {
    const response = await axios.post(url, {
      name,
      laengengrad,
      breitengrad,
      nutzerEmail,
      beschreibung,
      bilder
    })
    return response
  }
}

export async function deleteSehenswuerdigkeit (id) {
  const url = sehenswuerdigkeitUrl + '/' + id
  const response = await axios.delete(url)
  return response
}

/* PRIVATE FUNCTIONS */

function formCheck ({ name, laengengrad, breitengrad, nutzerEmail }) {
  if (name === undefined || name === '') {
    return false
  } else if (laengengrad === undefined || laengengrad !== Number) {
    return false
  } else if (breitengrad === undefined || breitengrad !== Number) {
    return false
  } else if (nutzerEmail === undefined || nutzerEmail === '') {
    return false
  }
  return true
}
