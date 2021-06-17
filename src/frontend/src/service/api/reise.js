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
        reisepunkte: [],
        reisekatalog: []
      }
    }
    const {
      id,
      termin,
      name,
      oeffentlich,
      reisepunkte,
      reisekatalog
    } = input
    this.id = id
    this.termin = termin
    this.name = name
    this.oeffentlich = oeffentlich
    this.reisepunkte = reisepunkte
    this.reisekatalog = reisekatalog
  }
}

/* FUNCTIONS */

export async function fetchReisen (reiseId) {
  console.log('fetchReisen')
  const data = await getReisen()
  console.log(data)

  const reisen = []
  const length = data.length
  console.log(length)

  // eslint-disable-next-line no-plusplus
  for (let i = 0; i < length; i++) {
    const reise = new Reise(data[i])
    reisen.push(reise)
  }

  console.log(reisen)
  return reisen
}

/* PRIVATE */

async function getReisen () {
  const response = await axios.get('/SHTravel/reises')
  return response.data
}
