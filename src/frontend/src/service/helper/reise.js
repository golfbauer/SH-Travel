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

export function delReisepunkt (reise, reisepunktId) {
  const length = reise.reisepunkte.length
  for (let i = 0; i < length; i++) {
    if (reise.reisepunkte[i].id === reisepunktId) {
      reise.reisepunkte.splice(i, 1)
      break
    }
  }
  return reise
}

export function mvReisepunkt (reise, reisepunktId, moveUp) {
  const length = reise.reisepunkte.length
  for (let i = 0; i < length; i++) {
    if (reise.reisepunkte[i].id === reisepunktId) {
      const tmp = reise.reisepunkte[i]
      if (moveUp) {
        console.log('up')
        if (i === 0) {
          console.log('up break')
          break
        }
        reise.reisepunkte[i] = reise.reisepunkte[i - 1]
        reise.reisepunkte[i - 1] = tmp
      } else {
        console.log('down')
        if (i === length - 1) {
          console.log('down break')
          break
        }
        reise.reisepunkte[i] = reise.reisepunkte[i + 1]
        reise.reisepunkte[i + 1] = tmp
      }
      break
    }
  }
  return reise
}
