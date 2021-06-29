import store from '@/store'

import axios from 'axios'

const punktUrl = '/SHTravel/punkt'

/* PUBLIC FUNCTIONS */

export async function fetchPunkte () {
  if (store.getters.isAuthenticated) {
    const authUrl = punktUrl + '/nutzerOrOeffentlich'
    const response = await axios({
      method: 'GET',
      url: authUrl,
      headers: {
        Authorization: 'Bearer ' + store.getters.getToken
      }
    })
    return response.data
  } else {
    const pubUrl = punktUrl + '/oeffentlich'
    const response = await axios.get(pubUrl)
    return response.data
  }
}

// export async function fetchPunkt (id) {
//   const url = punktUrl + '/' + id
//   const response = await axios.get(url)
//   return response.data
// }

export async function createPunkt ({ name, laengengrad, breitengrad, nutzerEmail }) {
  if (store.getters.isAuthenticated) {
    if (formCheck({ name, laengengrad, breitengrad, nutzerEmail })) {
      const response = await axios({
        method: 'POST',
        url: punktUrl,
        data: { name, laengengrad, breitengrad, nutzerEmail },
        headers: {
          Authorization: 'Bearer ' + store.getters.getToken
        }
      })
      return response
    }
  }
}

export async function updatePunkt (id, { name, laengengrad, breitengrad, nutzerEmail }) {
  if (store.getters.isAuthenticated) {
    const url = punktUrl + '/' + id
    if (formCheck({ name, laengengrad, breitengrad, nutzerEmail })) {
      const response = await axios({
        method: 'PUT',
        url: url,
        data: { name, laengengrad, breitengrad, nutzerEmail },
        headers: {
          Authorization: 'Bearer ' + store.getters.getToken
        }
      })
      return response
    }
  }
}

export async function deletePunkt (id) {
  if (store.getters.isAuthenticated) {
    const url = punktUrl + '/' + id
    const response = await axios({
      method: 'DELETE',
      url: url,
      headers: {
        Authorization: 'Bearer ' + store.getters.getToken
      }
    })
    return response
  }
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
