import axios from 'axios'
/** !!!UNDER RECONSTRUCTION!!! (MAY BE REPLACED BY 'Reisepunkte' VUEX DATA STORE)
 * This script contains a 'Reisepunkt' class and a async getReisepunkte function, which fetches new 'Reisepunkt' objects
 * from the backend api.
 */

/*
 * Class representing a 'Reisepunkt' (data-)object.
 */
class Reisepunkt {
  constructor (input) {
    if (input === undefined) {
      input = {
        id: 'unknown',
        laengengrad: 0,
        breitengrad: 0,
        nutzeremail: 'unknown',
        name: 'unknown'
      }
    }

    const { id, laengengrad, breitengrad, nutzerEmail, name } = input

    this.id = id
    this.laengengrad = laengengrad
    this.breitengrad = breitengrad
    this.nutzeremail = nutzerEmail
    this.name = name
  }
}

/*
 * This function fetches new 'Reisepunkt' objects from the backend api.
 */
async function getReisepunkte () {
  try {
    const { data: response } = await axios.get('/SHTravel/reisepunkt')

    const length = response.length
    const result = []
    for (let i = 0; i < length; i++) {
      const reisepunkt = new Reisepunkt(response[i])
      result.push(reisepunkt)
    }
    console.log(result)
    return result
  } catch (error) {
    console.error(error)
  }
}

export {
  Reisepunkt,
  getReisepunkte
}
