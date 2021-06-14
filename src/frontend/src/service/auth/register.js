import axios from 'axios'

/* FUNCTIONS */

// a function submitting a register request !!! DUMMY PARAM !!!
export function submit ({ username: uname, email: mail, password: pword }) {
  const user = { username: uname, email: mail, password: pword }

  // !!! WIP !!!
  if (checkUserForm(user)) {
    return postRegister(user)
  } else {
    return false
  }
}

/* PRIVATE */
function checkUserForm (user) {
  let name
  let mail
  let pw

  if (user === undefined) {
    return 'undefined'
  }
  if (user.username === '') {
    name = false
    return 'name'
  }
  if (user.email === '') {
    mail = false
    return 'mail'
  }
  if (user.password === '' || user.password.length < 8) {
    pw = false
    return 'pw'
  }
  if (name && mail && pw) {
    return 'success'
  }
}

function postRegister (user) {
  return true
}
