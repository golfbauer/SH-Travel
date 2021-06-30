<template>
  <div class="backgroung-image">
    <main class="form-signin">
      <div>
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
          <input type="password" class="form-control" id="floatingPassword" placeholder="Passwort" ref="passwort">
          <label for="floatingPassword">Passwort</label>
        </div>
        <div class="form-floating">
          <input type="password" class="form-control" id="floatingPasswordConfirmation" placeholder="Passwort bestätigen" ref="passwortConfirm">
          <label for="floatingPassword">Passwort bestätigen</label>
        </div>
        <div>
          <select class="form-select" aria-label="Default select example" ref="rolle">
            <option value="REISENDER">Reisender</option>
            <option value="ANBIETER">Anbieter</option>
          </select>
        </div>
        <button @click="onSubmit" class="w-100 btn btn-lg btn-primary" type="submit">Abschicken</button>
      </div>
    </main>
  </div>
</template>

<script>
import * as registerService from '@/service/auth/register'
import router from '@/router'

export default {
  name: 'Register',
  methods: {
    onSubmit: async function () {
      const vorname = this.$refs.vorname.value
      const nachname = this.$refs.nachname.value
      const email = this.$refs.email.value
      const accountname = this.$refs.accountname.value
      const passwort = this.$refs.passwort.value
      const passwortConf = this.$refs.passwortConfirm.value
      const rolle = this.$refs.rolle.value

      if (passwort === passwortConf) {
        const user = {
          vorname: vorname,
          nachname: nachname,
          email: email,
          accountname: accountname,
          passwort: passwort,
          nutzerRolle: rolle
        }
        console.log(user)
        const form = registerService.checkRegisterForm(user)
        if (form !== 'success') {
          this.makeToast('error', form + ' muss noch eingetragen werden')
        } else {
          await registerService.submit(user).then(async (result) => {
            if (result) {
              this.makeToast('success', 'Account wurde erfolgreich angelegt')
              await router.push('/login')
            } else {
              this.makeToast('error', 'Fehler: Konnte Account nicht anlegen.')
              await router.push('/')
            }
          })
        }
      } else {
        this.makeToast('error', 'Passwörter stimmen nicht überein')
      }
    },
    makeToast: function (variant, message) {
      this.$toasted.show(message, {
        type: variant,
        duration: 3000
      })
    }
  }
}
</script>

<style scoped>
.backgroung-image {
  background-image: url("../assets/images/Strand_Register.jpg");
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
