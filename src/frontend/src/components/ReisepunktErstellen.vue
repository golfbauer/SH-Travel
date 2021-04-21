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
                  v-model="name"
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
                v-model="name"
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
          <b-form @submit="onSubmit" @reset="onReset" v-if="show">
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
              id="input-group-4"
              label="Öffnungszeiten"
              label-for="input-4"
            >
              <b-form-input
                id="input-4"
                v-model="oeffnungszeiten"
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
export default {
  name: 'ReisepunktErstellen',
  props: {
    laengengrad: Number,
    breitengrad: Number
  },
  data () {
    return {
      typ: 'punkt',
      show: true,
      nutzerEmail: '',
      name: '',
      beschreibung: '',
      oeffnungszeiten: '',
      bilder: []
    }
  },
  methods: {
    onSubmit (event) {
      event.preventDefault()
      const axios = require('axios')
      if (this.typ === 'punkt') {
        const res = axios.post('/api/punkt', {
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail,
          name: this.name
        })
          .then(response => {
            this.makeToast('success', 'Punkt', this.name + ' erfolgreich erstellt')
            console.log(response)
          })
          .catch(error => {
            this.makeToast('danger', 'Punkt', this.name + ' konnte nicht erstellt werden')
            console.error(error)
          })
      } else if (this.typ === 'sehenswuerdigkeit') {
        axios.post('/api/sehenswuerdigkeit', {
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail,
          name: this.name,
          beschreibung: this.beschreibung,
          bilder: this.bilder
        })
          .then(response => {
            this.makeToast('success', 'Sehenswuerdigkeit', this.name + ' erfolgreich erstellt')
            console.log(response)
          })
          .catch(error => {
            this.makeToast('danger', 'Sehenswuerdigkeit', this.name + ' konnte nicht erstellt werden')
            console.error(error)
          })
      } else if (this.typ === 'attraktion') {
        axios.post('/api/attraktion', {
          laengengrad: this.laengengrad,
          breitengrad: this.breitengrad,
          nutzerEmail: this.nutzerEmail,
          name: this.name,
          oeffnungszeiten: this.oeffnungszeiten,
          beschreibung: this.beschreibung,
          bilder: this.bilder
        })
          .then(response => {
            this.makeToast('success', 'Attraktion', this.name + ' erfolgreich erstellt')
            console.log(response)
          })
          .catch(error => {
            this.makeToast('danger', 'Attraktion', this.name + ' konnte nicht erstellt werden')
            console.error(error)
          })
      }
    },
    onReset (event) {
      event.preventDefault()
      // Reset our values
      this.typ = 'punkt'
      this.lat = null
      this.long = null
      this.nutzerEmail = ''
      this.name = ''
      this.beschreibung = ''
      this.oeffnungszeiten = ''
      this.bilder = []
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
      this.disableThisShow()
    },
    makeToast (variant = null, title = null, body = null) {
      this.$bvToast.toast(body, {
        title: title,
        variant: variant,
        solid: true
      })
    },
    disableThisShow: function () {
      this.$emit('updateShow', 'false')
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
