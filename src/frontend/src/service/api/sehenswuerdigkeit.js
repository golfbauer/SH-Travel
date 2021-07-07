import axios from 'axios'
import store from '@/store'

const sehenswuerdigkeitUrl = '/SHTravel/sehenswuerdigkeit'

export async function fetchSehenswuerdigkeiten () {
  if (store.getters.isAuthenticated) {
    const authUrl = sehenswuerdigkeitUrl + '/nutzerOrOeffentlich'
    const response = await axios({
      method: 'GET',
      url: authUrl,
      headers: {
        Authorization: 'Bearer ' + store.getters.getToken
      }
    })
    return response.data
  } else {
    const pubUrl = sehenswuerdigkeitUrl + '/oeffentlich'
    const response = await axios.get(pubUrl)
    return response.data
  }
}

// export async function fetchSehenswuerdigkeit (id) {
//   const url = sehenswuerdigkeitUrl + '/' + id
//   const response = await axios.get(url)
//   return response.data
// }

export async function createSehenswuerdigkeit ({ name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder, oeffentlich }) {
  if (store.getters.isAuthenticated) {
    if (formCheck({ name, laengengrad, breitengrad, nutzerEmail })) {
      const response = await axios({
        method: 'POST',
        url: sehenswuerdigkeitUrl,
        data: { name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder, oeffentlich },
        headers: {
          Authorization: 'Bearer ' + store.getters.getToken
        }
      })
      return response
    }
  } else {
    console.log('ich bin nicht angemldet')
    throw new Error('not Authenticated')
  }
}

export async function updateSehenswuerdigkeit (id, { name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder }) {
  if (store.getters.isAuthenticated) {
    const url = sehenswuerdigkeitUrl + '/' + id
    if (formCheck({ name, laengengrad, breitengrad, nutzerEmail })) {
      const response = await axios({
        method: 'PUT',
        url: url,
        data: { name, laengengrad, breitengrad, nutzerEmail, beschreibung, bilder },
        headers: {
          Authorization: 'Bearer ' + store.getters.getToken
        }
      })
      return response
    }
  } else {
    console.log('ich bin nicht angemldet')
    throw new Error('not Authenticated')
  }
}

export async function deleteSehenswuerdigkeit (id) {
  if (store.getters.isAuthenticated) {
    const url = sehenswuerdigkeitUrl + '/' + id
    const response = await axios({
      method: 'DELETE',
      url: url,
      headers: {
        Authorization: 'Bearer ' + store.getters.getToken
      }
    })
    return response
  } else {
    console.log('ich bin nicht angemldet')
    throw new Error('not Authenticated')
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
