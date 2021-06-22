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

export async function setReise (reise) {
  console.log(reise)
  await axios.put('/SHTravel/reise/' + reise.id, reise)
    .then(response => {
      console.log(response)
      return true
    })
    .catch(error => {
      console.error(error)
      return false
    })
}

/* PRIVATE */

async function getReisen () {
  const response = await axios.get('/SHTravel/reise')
  return response.data
}
