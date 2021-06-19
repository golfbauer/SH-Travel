import axios from 'axios'

/* CLASSES */

export class Reise {
  constructor (input) {
    if (input === undefined) {
      input = {
        id: 'unknown',
        termin: 'unknown',
        name: 'unknown',
        oeffentlich: false,
        reisepunkte: []
      }
    }

    const {
      id,
      termin,
      name,
      oeffentlich,
      reisepunkte
    } = input

    this.id = id
    this.termin = termin
    this.name = name
    this.oeffentlich = oeffentlich
    this.reisepunkte = []
  }
}

export class Reisepunkt {
  constructor (input) {
    if (input === undefined) {
      input = {
        laengengrad: 'undefined',
        breitengrad: 'undefined',
        nutzerEmail: 'undefined',
        name: 'undefined',
        attraktionOeffnungszeiten: [],
        beschreibung: 'undefined',
        bilder: 'undefined'
      }
    }
  }
}

/* FUNCTIONS */

export async function fetchReisen (reiseId) {
  console.log('fetchReisen')
  const data = await getReisen()

  const reisen = []
  const length = data.length

  // eslint-disable-next-line no-plusplus
  for (let i = 0; i < length; i++) {
    const reise = new Reise(data[i])
    reisen.push(reise)
  }
  console.log('parsed: ' + JSON.parse(data))
  return JSON.parse(data)
}

/* PRIVATE */

async function getReisen () {
  const response = await axios.get('/SHTravel/reise')
  return response.data
}
