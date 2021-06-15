<template>
  <main class="form-signin">
    <form>
      <img class="mb-4" src="" alt="" width="72" height="57">
      <h1 class="h3 mb-3 fw-normal">Registrieren</h1>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingFirstName" placeholder="Vorname" ref="vorname">
        <label for="floatingFirstName">Vorname</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingLasstName" placeholder="Nachname" ref="nachname">
        <label for="floatingLasstName">Nachname</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingAccountName" placeholder="Accountname" ref="accountname">
        <label for="floatingAccountName">Accountname</label>
      </div>
      <div class="form-floating">
        <input type="email" class="form-control" id="floatingMail" placeholder="name@example.com" ref="email">
        <label for="floatingMail">Email Adresse</label>
      </div>
      <div class="form-floating">
        <input type="password" class="form-control" id="floatingPassword" placeholder="Passwort" ref="passswort">
        <label for="floatingPassword">Passwort</label>
      </div>
      <button @click="onSubmit" class="w-100 btn btn-lg btn-primary" type="submit">Abschicken</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
    </form>
  </main>
</template>

<script>
import * as registerService from '@/service/auth/register'

export default {
  name: 'Register',
  methods: {
    onSubmit: function () {
      const vorname = this.$refs.vorname.value
      const nachname = this.$refs.nachname.value
      const email = this.$refs.email.value
      const accountname = this.$refs.accountname.value
      const passwort = this.$refs.passswort.value

      console.log(registerService.submit({ vorname, nachname, email, accountname, passwort }))
    }
  }
}
</script>

<style scoped>
html,
body {
  height: 100%;
}

body {
  display: flex;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-signin .checkbox {
  font-weight: 400;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
