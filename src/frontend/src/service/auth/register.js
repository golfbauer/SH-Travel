import axios from 'axios'

/* FUNCTIONS */

// a function submitting a register request
export async function submit (user) {
  const response = await axios.post('/SHTravel/register', user)
  if (response.status === 200) {
    return true
  }
  return false
}

// This function checks if the entered values are defined and not empty
export function checkRegisterForm (user) {
  if (user.vorname === '' || user.vorname === undefined) {
    return 'Vorname'
  }
  if (user.nachname === '' || user.nachname === undefined) {
    return 'Nachname'
  }
  if (user.email === '' || user.email === undefined) {
    return 'Email'
  }
  if (user.accountname === '' || user.accountname === undefined) {
    return 'Accountname'
  }
  if (user.passwort === '' || user.passwort === undefined) {
    return 'Passwort'
  }
  return 'success'
}
