import { fetchReisen } from '@/service/api/reise'

export async function getReise (id) {
  let reisen
  let reise
  try {
    reisen = await fetchReisen()
    const length = reisen.length
    for (let i = 0; i < length; i++) {
      if (reisen[i].id === id) {
        reise = reisen[i]
        break
      }
    }
    console.log('Helper ID: ' + reise.id + reise)
    return reise
  } catch (e) {
    console.log('Reisen konnten nicht geladen werden ERROR')
    console.log(e)
  }
}
