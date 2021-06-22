import { fetchPunkte } from '@/service/api/punkt'
import { fetchAttraktionen } from '@/service/api/attraktion'
import { fetchSehenswuerdigkeiten } from '@/service/api/sehenswuerdigkeit'

/* CLASSES */

export class Reisepunkt {
  constructor (input, typ) {
    const {
      id,
      laengengrad,
      breitengrad,
      nutzerEmail,
      name,
      beschreibung,
      attraktionOeffnungszeiten,
      reisen
    } = input

    this.id = id
    this.name = name
    this.laengengrad = laengengrad
    this.breitengrad = breitengrad
    this.nutzeremail = nutzerEmail
    this.beschreibung = beschreibung
    this.attraktionOeffnungszeiten = attraktionOeffnungszeiten
    this.reisen = reisen
    this.typ = typ
  }
}

/* FUNCTIONS */

export async function fetchReisepunkte () {
  const punkte = await fetchPunkte()
  const attrakttionen = await fetchAttraktionen()
  const sehenswuerdikeiten = await fetchSehenswuerdigkeiten()

  const reisepunkte = []

  // generating Reisepunkt objects form Punkte
  for (let i = 0; i < punkte.length; i++) {
    const reisepunkt = new Reisepunkt(punkte[i], 'punkt')
    reisepunkte.push(reisepunkt)
  }

  // generating Reisepunkt objects form Attraktionen
  for (let i = 0; i < attrakttionen.length; i++) {
    const reisepunkt = new Reisepunkt(attrakttionen[i], 'attraktion')
    reisepunkte.push(reisepunkt)
  }

  // generating Reisepunkt objects form Sehenswürdigkeiten
  for (let i = 0; i < sehenswuerdikeiten.length; i++) {
    const reisepunkt = new Reisepunkt(sehenswuerdikeiten[i], 'sehenswuerdigkeit')
    reisepunkte.push(reisepunkt)
  }
  return reisepunkte
}
