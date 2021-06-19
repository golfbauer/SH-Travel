import { fetchReisen } from '@/service/api/reise'

export async function getReise (id) {
  let reisen
  try {
    reisen = await fetchReisen()
    console.log('Reisen: ' + reisen)

    const length = reisen.length
    console.log('Length ' + length)
    console.log('searching after id ' + id)
    for (let i = 0; i < length; i++) {
      console.log('For loop')
      console.log('At Reise: ' + reisen[i] + ' ID: ' + reisen[i].id)
      if (reisen[i].id === id) {
        console.log('Got Reise: ' + this.reisen[i])
        return reisen[i]
      }
    }
    console.log('Can not get Reise')
    return undefined
  } catch (e) {
    console.log('Reisen konnten nicht geladen werden ERROR')
  }
}
