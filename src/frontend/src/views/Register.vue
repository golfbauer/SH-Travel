<template>
  <div class="backgroung-image">
    <main class="form-signin">
      <form>
        <h1 class="h3 mb-3 fw-normal">Registrieren</h1>
        <div class="form-floating">
          <input type="text" class="form-control" id="floatingFirstName" placeholder="Vorname" ref="vorname">
          <label for="floatingFirstName">Vorname</label>
        </div>
        <div class="form-floating">
          <input type="text" class="form-control" id="floatingLastName" placeholder="Nachname" ref="nachname">
          <label for="floatingLastName">Nachname</label>
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
        <div>
          <select class="form-select" aria-label="Default select example" ref="rolle">
            <option value="Reisender">Reisender</option>
            <option value="Anbieter">Anbieter</option>
          </select>
        </div>
        <button @click="onSubmit" class="w-100 btn btn-lg btn-primary" type="submit">Abschicken</button>
      </form>
    </main>
  </div>
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
      const rolle = this.$refs.rolle.value

      console.log(registerService.submit({ vorname, nachname, email, accountname, passwort, rolle }))
    }
  }
}
</script>

<style scoped>
.backgroung-image {
  background-image: url("../assets/images/Germany-Lubeck-Schleswig-Holstein-river-houses_1920x1080.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  height: 100%;
}

.form-signin {
  position: relative;
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
  top: 20%;
  background-color: #FFFFFF;
  border-radius: 8px;
}

.form-signin .checkbox {
  font-weight: 400;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin #floatingFirstName {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin #floatingLastName {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

.form-signin #floatingAccountName {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

.form-signin select {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
