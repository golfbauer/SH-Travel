import axios from 'axios';

/* CLASSES */

export class TravelPoint {
  constructor(input) {
    if (input === undefined) {
      // eslint-disable-next-line no-param-reassign
      input = {
        id: 'unknown',
        laengengrad: 0,
        breitengrad: 0,
        nutzeremail: 'unknown',
        name: 'unknown',
      };
    }

    const {
      id,
      laengengrad,
      breitengrad,
      nutzerEmail,
      name,
    } = input;

    this.id = id;
    this.laengengrad = laengengrad;
    this.breitengrad = breitengrad;
    this.nutzeremail = nutzerEmail;
    this.name = name;
  }
}

/* FUNCTIONS */

export async function fetchTravelPoints() {
  console.log('Fetching Reisepunkte');
  // eslint-disable-next-line no-use-before-define
  const data = await getTravelPoints();
  console.log(data);

  const result = [];
  const { length } = data;
  console.log(length);

  // eslint-disable-next-line no-plusplus
  for (let i = 0; i < length; i++) {
    const travelPoint = new TravelPoint(data[i]);
    result.push(travelPoint);
  }

  console.log(result);
  return result;
}

/* PRIVATE */

async function getTravelPoints() {
  const response = await axios.get('/SHTravel/reisepunkt');
  return response.data;
}
