import axios from 'axios'

/* FUNCTIONS */

// a function submitting a register request
export function submit ({
  vorname: vname,
  nachname: nname,
  email: mail,
  accountname: acname,
  passwort: pword
}) {
  const user = {
    vorname: vname,
    nachname: nname,
    email: mail,
    accountname: acname,
    passwort: pword
  }

  const form = checkUserForm(user)
  if (form !== 'success') {
    return form
  } else {
    console.log(user)
    // return postRegister(user)
  }
}

/* PRIVATE */
function checkUserForm (user) {
  if (user === undefined) {
    return 'undefined'
  }
  if (user.vorname === '') {
    return 'vorname'
  }
  if (user.nachname === '') {
    return 'nachname'
  }
  if (user.email === '') {
    return 'email'
  }
  if (user.accountname === '') {
    return 'accountname'
  }
  if (user.password === '') {
    return 'passwort'
  }
  return 'success'
}

async function postRegister (user) {
  const response = await axios.post('/SHTravel/register', user)
  if (response.status === 200) {
    return true
  }
  return false
}
