import axios from 'axios'
import store from '@/store'

const attraktionUrl = '/SHTravel/attraktion'

export async function fetchAttraktionen () {
  if (store.getters.isAuthenticated) {
    const authUrl = attraktionUrl + '/nutzerOrOeffentlich'
    const response = await axios({
      method: 'GET',
      url: authUrl,
      headers: {
        Authorization: 'Bearer ' + store.getters.getToken
      }
    })
    return response.data
  } else {
    const pubUrl = attraktionUrl + '/oeffentlich'
    const response = await axios.get(pubUrl)
    return response.data
  }
}

// export async function fetchAttraktion (id) {
//   const url = attraktionUrl + '/' + id
//   const response = await axios.get(url)
//   return response.data
// }

export async function createAttraktion ({ name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder, attraktionOeffnungszeiten }) {
  if (store.getters.isAuthenticated) {
    if (formCheck({ name, laengengrad, breitengrad, nutzerEmail, attraktionOeffnungszeiten })) {
      const response = await axios({
        method: 'POST',
        url: attraktionUrl,
        data: { name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder, attraktionOeffnungszeiten },
        headers: {
          Authorization: 'Bearer ' + store.getters.getToken
        }
      })
      return response
    }
  }
}

export async function updateAttraktion (id, { name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder, attraktionOeffnungszeiten, oeffentlich }) {
  if (store.getters.isAuthenticated) {
    const url = attraktionUrl + '/' + id
    if (formCheck({ name, laengengrad, breitengrad, nutzerEmail, attraktionOeffnungszeiten })) {
      const response = await axios({
        method: 'PUT',
        url: url,
        data: { name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder, attraktionOeffnungszeiten, oeffentlich },
        headers: {
          Authorization: 'Bearer ' + store.getters.getToken
        }
      })
      return response
    }
  }
}

export async function deleteAttraktion (id) {
  if (store.getters.isAuthenticated) {
    const url = attraktionUrl + '/' + id
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
