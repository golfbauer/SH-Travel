import axios from 'axios'

const punktUrl = '/SHTravel/punkt'

/* PUBLIC FUNCTIONS */

export async function fetchPunkte () {
  const response = await axios.get(punktUrl)
  return response.data
}

export async function fetchPunkt (id) {
  const url = punktUrl + '/' + id
  const response = await axios.get(url)
  return response.data
}

export async function createPunkt ({ name, laengengrad, breitengrad, nutzerEmail }) {
  if (formCheck({ name, laengengrad, breitengrad, nutzerEmail })) {
    const response = await axios.post(punktUrl, { name, laengengrad, breitengrad, nutzerEmail })
    return response
  }
}

export async function updatePunkt (id, { name, laengengrad, breitengrad, nutzerEmail }) {
  const url = punktUrl + '/' + id
  if (formCheck({ name, laengengrad, breitengrad, nutzerEmail })) {
    const response = await axios.put(url, { name, laengengrad, breitengrad, nutzerEmail })
    return response
  }
}

export async function deletePunkt (id) {
  const url = punktUrl + '/' + id
  const response = await axios.delete(url)
  return response
}

/* PRIVATE FUNCTIONS */

function formCheck ({ name, laengengrad, breitengrad, nutzerEmail }) {
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
  }
  return true
}
