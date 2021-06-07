<template>
  <b-container id="punkterstellen" fluid>
    <b-card no-body>
      <!-- Tabs -->
      <b-tabs card justified>
        <b-tab title="Punkt" @click="typ = 'punkt'" active>
          <!-- Textfield Punkt -->
          <div>
            <b-form @submit="onSubmit" @reset="onReset">
              <b-form-group
                label-align="left"
                id="input-group-1"
                label="Name"
                label-for="input-1"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="input-1"
                  v-model="name"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-laengengrad"
                label="Längengrad"
                label-for="laengengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="laengengrad"
                  v-model="laengengrad"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-breitengrad"
                label="Breitengrad"
                label-for="breitengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="breitengrad"
                  v-model="breitengrad"
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
          <b-form @submit="onSubmit" @reset="onReset">
            <b-form-group
              label-align="left"
              id="input-group-1"
              label="Name"
              label-for="input-1"
            >
              <b-form-input
                id="input-1"
                v-model="name"
                placeholder="Name eingeben"
                required
              >
              </b-form-input>
            </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-laengengrad"
                label="Längengrad"
                label-for="laengengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="laengengrad"
                  v-model="laengengrad"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-breitengrad"
                label="Breitengrad"
                label-for="breitengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="breitengrad"
                  v-model="breitengrad"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>

            <b-form-group
              label-align="left"
              id="input-group-2"
              label="Beschreibung"
              label-for="input-2"
            >
              <b-form-textarea
                id="input-2"
                v-model="beschreibung"
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
                v-model="bilder"
                accept="image/*"
                class="mt-1"
                placeholder="Wähle Bilder aus oder lege sie hier ab"
                multiple
              >
              </b-form-file>
              <b-button @click="bilder = []" class="mr-2">Reset Auswahl</b-button>
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
          <b-form @submit="onSubmit" @reset="onReset">
            <b-form-group
              label-align="left"
              id="input-group-1"
              label="Name"
              label-for="input-1"
            >
              <b-form-input
                id="input-1"
                v-model="name"
                placeholder="Name eingeben"
                required
              >
              </b-form-input>
            </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-laengengrad"
                label="Längengrad"
                label-for="laengengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="laengengrad"
                  v-model="laengengrad"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group
                label-align="left"
                id="input-group-breitengrad"
                label="Breitengrad"
                label-for="breitengrad"
              >
                <!--  + this.laengengrad -->
                <b-form-input
                  id="breitengrad"
                  v-model="breitengrad"
                  placeholder="Name eingeben"
                  required
                ></b-form-input>
              </b-form-group>

            <b-form-group
              label-align="left"
              id="input-group-4"
              label="Öffnungszeiten"
              label-for="input-4"
              description="z.B. Montag 10:00 - 15:30, Mittwoch 9:00 - 14:00, Freitag 9:00 - 12:00"
            >
              <b-form-textarea
                id="input-4"
                v-model="oeffnungszeiten"
                placeholder="Trenne die Wochentage mit einem Komma"
                required
                rows="2"
                max-rows="6"
              >
              </b-form-textarea>
            </b-form-group>
            <b-form-group
              label-align="left"
              id="input-group-2"
              label="Beschreibung"
              label-for="input-2"
            >
              <b-form-textarea
                id="input-2"
                v-model="beschreibung"
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
                v-model="bilder"
                accept="image/*"
                class="mt-1"
                placeholder="Wähle Bilder aus oder lege sie hier ab"
                multiple
              >
              </b-form-file>
              <b-button @click="bilder = []" class="mr-2">Reset Auswahl</b-button>
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
import { mapGetters } from 'vuex'
export default {
  name: 'ReisepunktErstellen',
  data () {
    return {
      typ: 'punkt',
      nutzerEmail: '',
      name: '',
      beschreibung: '',
      oeffnungszeiten: '',
      bilder: [],
      laengengrad: '',
      breitengrad: ''
    }
  },
  computed: mapGetters(['getCoords']),
  methods: {
    onSubmit (event) {
      event.preventDefault()
      const axios = require('axios')
      console.log(this.laengengrad + ' ' + this.breitengrad)
      if (this.typ === 'punkt') {
        const res = axios.post('/SHTravel/punkt', {
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail,
          name: this.name
        })
          .then(response => {
            console.log(response)
            this.makeToast('success', 'Punkt', this.name + ' erfolgreich erstellt')
            this.disableThisShow()
          })
          .catch(error => {
            this.makeToast('danger', 'Punkt', this.name + ' konnte nicht erstellt werden')
            console.error(error)
          })
      } else if (this.typ === 'sehenswuerdigkeit') {
        axios.post('/SHTravel/sehenswuerdigkeit', {
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail,
          name: this.name,
          beschreibung: this.beschreibung,
          bilder: this.bilder
        })
          .then(response => {
            console.log(response)
            this.makeToast('success', 'Sehenswuerdigkeit', this.name + ' erfolgreich erstellt')
            this.disableThisShow()
          })
          .catch(error => {
            this.makeToast('danger', 'Sehenswuerdigkeit', this.name + ' konnte nicht erstellt werden')
            console.error(error)
          })
      } else if (this.typ === 'attraktion') {
        axios.post('/SHTravel/attraktion', {
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail,
          name: this.name,
          oeffnungszeiten: this.oeffnungszeiten,
          beschreibung: this.beschreibung,
          bilder: this.bilder
        })
          .then(response => {
            console.log(response)
            this.makeToast('success', 'Attraktion', this.name + ' erfolgreich erstellt')
            this.disableThisShow()
          })
          .catch(error => {
            this.makeToast('danger', 'Attraktion', this.name + ' konnte nicht erstellt werden')
            console.error(error)
          })
      }
    },
    onReset (event) {
      event.preventDefault()
      this.disableThisShow()
    },
    makeToast (variant = null, title = null, body = null) {
      this.$emit('makeToast', [variant, title, body])
    },
    disableThisShow: function () {
      this.$emit('updateShow', 'false')
    }
  },
  created () {
    const coords = this.getCoords
    this.laengengrad = coords.laengengrad
    this.breitengrad = coords.breitengrad
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
