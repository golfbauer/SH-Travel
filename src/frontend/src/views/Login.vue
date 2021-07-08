<template>
  <div class="backgroung-image">
    <main class="form-signin">
      <div>
        <h1 class="h3 mb-3 fw-normal">Anmelden</h1>
        <div class="form-floating">
          <input type="email" class="form-control" id="floatingMail" placeholder="name@example.com" ref="email">
          <label for="floatingMail">Email Adresse</label>
        </div>
        <div class="form-floating">
          <input type="password" class="form-control" id="floatingPassword" placeholder="Passwort" ref="passwort">
          <label for="floatingPassword">Passwort</label>
        </div>
        <button @click="onSubmit" class="w-100 btn btn-lg btn-primary" type="submit">Abschicken</button>
      </div>
    </main>
  </div>
</template>

<script>
import router from '@/router'

export default {
  name: 'Login',
  methods: {
    onSubmit: async function () {
      const email = this.$refs.email.value
      const passwort = this.$refs.passwort.value

      try {
        await this.$store.dispatch('login', { username: email, password: passwort })
        this.makeToast('success', 'Login erfolgreich')
        await router.push('/')
      } catch (e) {
        this.makeToast('error', 'Login fehlgeschlagen')
      }

      //   .then(async () => {
      //   this.makeToast('success', 'Login erfolgreich');
      //   await router.push()
      // }).catch(() => {
      //   this.makeToast('error', 'Login fehlgeschlagen')
      // })
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
  background-image: url("../assets/images/Luebeck_Login.jpg");
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
  top: 35%;
  background-color: #FFFFFF;
  border-radius: 8px;
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
