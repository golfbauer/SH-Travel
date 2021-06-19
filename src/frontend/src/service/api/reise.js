import axios from 'axios'

/* FUNCTIONS */

export async function fetchReisen (reiseId) {
  const data = await getReisen()
  const result = []
  const length = data.length
  for (let i = 0; i < length; i++) {
    result.push(data[i])
  }
  return result
}

/* PRIVATE */

async function getReisen () {
  const response = await axios.get('/SHTravel/reise')
  return response.data
}
