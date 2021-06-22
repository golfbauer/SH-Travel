import axios from 'axios'

const reiseUrl = '/SHTravel/reise'

/* PUBLIC FUNCTIONS */

export async function fetchReisen () {
  const response = await axios.get(reiseUrl)
  return response.data
}

export async function fetchReise (id) {
  const url = reiseUrl + '/' + id
  const response = await axios.get(url)
  return response.data
}

export async function createReise ({ name, termin, oeffentlich, reisepunkte, reisekatalog }) {
  if (formCheck(name, oeffentlich)) {
    const response = await axios.post(reiseUrl, { name, termin, oeffentlich, reisepunkte, reisekatalog })
    console.log(response)
    return true
  } else {
    return false
  }
}

export async function updateReise (id, { name, termin, oeffentlich, reisepunkte, reisekatalog }) {
  const url = reiseUrl + '/' + id
  if (formCheck(name, oeffentlich)) {
    const response = await axios.put(url, { name, termin, oeffentlich, reisepunkte, reisekatalog })
    console.log(response)
    return true
  } else {
    return false
  }
}

export async function deleteReise (id) {
  const url = reiseUrl + '/' + id
  const response = await axios.delete(url)
  console.log(response)
}
// rework needed?
export async function addReisepunkt (reise, reisepunkt) {
  const reiseId = reise.id
  const reisepunktId = reisepunkt.id

  const url = reiseUrl + '/reisepunkt/' + reiseId + '?idReisepunkt=' + reisepunktId
  const response = await axios.put(url)
  console.log(response)
}
// needed or unnecessary due to put method?
export async function changePrivacy (id, oeffentlich) {
  const url = reiseUrl + '/reisepunkt/' + id + '?oeffentlich=' + oeffentlich
  const response = await axios.put(url)
  console.log(response)
}

/* PRIVATE FUNCTIONS */

function formCheck (name, oeffentlich) {
  if (name === undefined || name === '') {
    return false
  } else if (oeffentlich === undefined) {
    return false
  }
  return true
}
