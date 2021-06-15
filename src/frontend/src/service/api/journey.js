import axios from 'axios'

const reiseUrl = '/SHTravel/reise'

/* FUNCTIONS */

export function createReise (reise) {
  if (formCheck(reise)) {
    postReise(reise)
    return true
  } else {
    return false
  }
}

export async function addReisepunkt (reise, reisepunkt) {
  const reiseId = reise.id
  const reisepunktId = reisepunkt.id

  const putUrl = reiseUrl + '/reisepunkt/' + reiseId + '?idReisepunkt=' + reisepunktId
  console.log(putUrl)
  const response = await axios.put(putUrl)
}

/* PRIVATE */

function formCheck (reise) {
  if (reise.name !== undefined || reise.name !== '') {
    return true
  } else {
    return false
  }
}

async function postReise (reise) {
  const name = reise.name
  const termin = reise.termin
  const oeffentlich = reise.oeffentlich
  const reisepunkte = reise.reisepunkte
  const reisekatalog = reise.reisekatalog

  const response = await axios.post(reiseUrl, { name, termin, oeffentlich, reisepunkte, reisekatalog })
  if (response.status === 200) {
    return true
  }
  return false
}
