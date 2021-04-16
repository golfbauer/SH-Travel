<template>
  <b-container id="punkterstellen" fluid>
    <b-card no-body>
      <!-- Tabs -->
      <b-tabs card justified>
        <b-tab title="Punkt" @click="typ = 'punkt'" active>
          <!-- Textfield Punkt -->
          <div>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <b-form-group
                label-align="left"
                id="input-group-1"
                label="Name"
                label-for="input-1"
              >
                <b-form-input
                  id="input-1"
                  v-model="form.name"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>
            <div class="btn-bar">
              <div class="right">
                <b-button class="btn-gray" type="reset">Abbrechen</b-button>
                <b-button class="btn-orange" type="submit">Erstellen</b-button>
              </div>
            </div>
            </b-form>
          </div>
          <!-- End Textfield Punkt -->
        </b-tab>
        <b-tab title="Sehenswürdigkeit" @click="typ = 'sehenswuerdigkeit'">
        <!-- Textfield Sehenswürdigkeit -->
          <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group
              label-align="left"
              id="input-group-1"
              label="Name"
              label-for="input-1"
            >
              <b-form-input
                id="input-1"
                v-model="form.name"
                placeholder="Name eingeben"
                required
              >
              </b-form-input>
            </b-form-group>
            <b-form-group
              label-align="left"
              id="input-group-2"
              label="Beschreibung"
              label-for="input-2"
            >
              <b-form-textarea
                id="input-2"
                v-model="form.beschreibung"
                placeholder="Beschreibung eingeben"
                rows="2"
                max-rows="6"
              >
              </b-form-textarea>
            </b-form-group>
            <b-form-group
             label-align="left"
              id="input-group-3"
              label="Bilder"
              label-for="input-3"
            >
              <b-form-file
                id="input-3"
                v-model="form.bilder"
                accept="image/*"
                class="mt-1"
                placeholder="Wähle Bilder aus oder lege sie hier ab"
                multiple
              >
              </b-form-file>
              <b-button @click="form.bilder = []" class="mr-2">Reset Auswahl</b-button>
            </b-form-group>
            <div class="btn-bar">
              <div class="right">
                <b-button class="btn-gray" type="reset">Abbrechen</b-button>
                <b-button class="btn-orange" type="submit">Erstellen</b-button>
              </div>
            </div>
          </b-form>
          <!-- End Textfield Sehenswürdigkeit -->
        </b-tab>
        <b-tab title="Attraktion" @click="typ = 'attraktion'">
        <!-- Textfield Attraktion -->
          <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group
              label-align="left"
              id="input-group-1"
              label="Name"
              label-for="input-1"
            >
              <b-form-input
                id="input-1"
                v-model="form.name"
                placeholder="Name eingeben"
                required
              >
              </b-form-input>
            </b-form-group>
            <b-form-group
              label-align="left"
              id="input-group-4"
              label="Öffnungszeiten"
              label-for="input-4"
            >
              <b-form-input
                id="input-4"
                v-model="form.oeffunungszeiten"
                placeholder="Öffnungszeiten eingeben"
                required
              >
              </b-form-input>
            </b-form-group>
            <b-form-group
              label-align="left"
              id="input-group-2"
              label="Beschreibung"
              label-for="input-2"
            >
              <b-form-textarea
                id="input-2"
                v-model="form.beschreibung"
                placeholder="Beschreibung eingeben"
                rows="2"
                max-rows="6"
              >
              </b-form-textarea>
            </b-form-group>
            <b-form-group
             label-align="left"
              id="input-group-3"
              label="Bilder"
              label-for="input-3"
            >
              <b-form-file
                id="input-3"
                v-model="form.bilder"
                accept="image/*"
                class="mt-1"
                placeholder="Wähle Bilder aus oder lege sie hier ab"
                multiple
              >
              </b-form-file>
              <b-button @click="form.bilder = []" class="mr-2">Reset Auswahl</b-button>
            </b-form-group>
            <div class="btn-bar">
              <div class="right">
                <b-button class="btn-gray" type="reset">Abbrechen</b-button>
                <b-button class="btn-orange" type="submit">Erstellen</b-button>
              </div>
            </div>
          </b-form>
          <!-- End Textfield Attraktion -->
        </b-tab>
        <!-- End Tabs -->
      </b-tabs>
    </b-card>
  </b-container>
</template>

<script>
export default {
  name: 'ReisepunktErstellen',
  data () {
    return {
      typ: '',
      form: {
        name: '',
        beschreibung: '',
        oeffnungszeiten: '',
        bilder: []
      },
      show: true
    }
  },
  methods: {
    onSubmit (event) {
      event.preventDefault()
      const axios = require('axios')
      if (this.form.typ === 'punkt') {
        axios.post('/api/reisepunkterstellen', {
          name: this.form.name
        })
          .then(function (response) {
            console.log(response)
          })
          .catch(function (error) {
            console.log(error)
          })
      } else if (this.form.typ === 'sehehswuerdigkeit') {
        axios.post('/api/sehehswuerdigkeit', {
          name: this.form.name,
          beschreibung: this.form.beschreibung,
          bilder: this.form.bilder
        })
          .then(function (response) {
            console.log(response)
          })
          .catch(function (error) {
            console.log(error)
          })
      }
      /*
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.form)
      }
      fetch('http://192.168.178.67/api/reisepunkterstellen', requestOptions)
        .then(async response => {
          const data = await response.json()
          // check for error response
          if (!response.ok) {
            // gest error message from body or default to response status
            const error = (data && data.message) || response.status
            return Promise.reject(error)
          }
        })
        .catch(error => {
          this.errorMessage = error
          console.error('There was an error!', error)
        }) */
    },
    onReset (event) {
      event.preventDefault()
      // Reset our form values
      this.form.typ = ''
      this.form.name = ''
      this.form.beschreibung = ''
      this.form.oeffnungszeiten = ''
      this.form.bilder = []
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }
  }
}
</script>

<style lang="scss" scoped>
$orange: orange;

#punkterstellen {
  position: relative;
  padding: 0;
  max-width: 45rem;
  z-index: 999;
}

.btn {
  margin: 0.5rem;

  &-gray {
    background-color: gray;
    color: #fff;
  }

  &-orange {
    background-color: $orange;
    color: #fff;
  }

  &-bar{
    overflow: hidden;
    clear: both;
  }
}

.right{
  float: right;
}
</style>
