import axios from 'axios'
import store from '@/store'

const reiseUrl = '/SHTravel/reise'

/* PUBLIC FUNCTIONS */

export async function fetchReisen () {
  if (store.getters.isAuthenticated) {
    const authUrl = reiseUrl + '/nutzerOrOeffentlich'
    const response = await axios({
      method: 'GET',
      url: authUrl,
      headers: {
        Authorization: 'Bearer ' + store.getters.getToken
      }
    })
    console.log(response.data)
    return response.data
  } else {
    const pubUrl = reiseUrl + '/oeffentlich'
    const response = await axios.get(pubUrl)
    console.log(response.data)
    return response.data
  }
}

//
// export async function fetchReise (id) {
//   const url = reiseUrl + '/' + id
//   const response = await axios.get(url)
//   return response.data
// }

export async function createReise ({ name, termin, oeffentlich, reisepunkte, reisekatalog }) {
  if (store.getters.isAuthenticated) {
    if (formCheck(name, oeffentlich)) {
      const response = await axios({
        method: 'POST',
        url: reiseUrl,
        data: { name, termin, oeffentlich, reisepunkte, reisekatalog },
        headers: {
          Authorization: 'Bearer ' + store.getters.getToken
        }
      })
      console.log(response)
      return true
    }
  }
  return false
}

export async function updateReise (id, { name, termin, oeffentlich, reisepunkte, reisekatalog }) {
  if (store.getters.isAuthenticated) {
    const url = reiseUrl + '/' + id
    if (formCheck(name, oeffentlich)) {
      const response = await axios({
        method: 'PUT',
        url: url,
        data: { name, termin, oeffentlich, reisepunkte, reisekatalog },
        headers: {
          Authorization: 'Bearer ' + store.getters.getToken
        }
      })
      console.log(response)
      return true
    } else {
      return false
    }
  }
}

export async function deleteReise (id) {
  if (store.getters.isAuthenticated) {
    const url = reiseUrl + '/' + id
    const response = await axios({
      method: 'DELETE',
      url: url,
      headers: {
        Authorization: 'Bearer ' + store.getters.getToken
      }
    })
    console.log(response)
  }
}
// rework needed?
export async function addReisepunkt (reise, reisepunkt) {
  if (store.getters.isAuthenticated) {
    const reiseId = reise.id
    const reisepunktId = reisepunkt.id

    const url = reiseUrl + '/reisepunkt/' + reiseId + '?idReisepunkt=' + reisepunktId
    const response = await axios({
      method: 'PUT',
      url: url,
      headers: {
        Authorization: 'Bearer ' + store.getters.getToken
      }
    })
    return response
  }
}

// // needed or unnecessary due to put method?
// export async function changePrivacy (id, oeffentlich) {
//   const url = reiseUrl + '/reisepunkt/' + id + '?oeffentlich=' + oeffentlich
//   const response = await axios.put(url)
//   console.log(response)
// }

/* PRIVATE FUNCTIONS */

function formCheck (name, oeffentlich) {
  if (name === undefined || name === '') {
    return false
  } else if (oeffentlich === undefined) {
    return false
  }
  return true
}
