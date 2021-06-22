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
    console.log(response)
  }
}

export async function updatePunkt (id, { name, laengengrad, breitengrad, nutzerEmail }) {
  const url = punktUrl + '/' + id
  if (formCheck({ name, laengengrad, breitengrad, nutzerEmail })) {
    const response = await axios.put(url, { name, laengengrad, breitengrad, nutzerEmail })
    console.log(response)
  }
}

export async function deletePunkt (id) {
  const url = punktUrl + '/' + id
  const response = await axios.delete(url)
  console.log(response)
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
