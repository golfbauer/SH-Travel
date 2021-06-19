import axios from 'axios'

/* FUNCTIONS */

export async function fetchReisen (reiseId) {
  console.log('fetchReisen')
  const data = await getReisen()
  console.log('fetchreisen data: ' + data)

  const result = []
  const length = data.length
  for (let i = 0; i < length; i++) {
    result.push(data[i])
  }
  console.log('fetchReisen result' + result)
  return result
}

/* PRIVATE */

async function getReisen () {
  const response = await axios.get('/SHTravel/reise')
  return response.data
}
