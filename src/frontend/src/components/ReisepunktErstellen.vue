<template>
  <b-container id="punkterstellen" fluid>
    <b-card no-body>
      <!-- Tabs -->
      <b-tabs card>
        <b-tab title="Punkt" active>
          <!-- Textfield Punkt -->
          <div>
            <b-form @submit="onSubmit" @cancel="onCancel" v-if="show">
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
              <b-button class="btn-gray" type="cancel" variant="danger">Abbrechen</b-button>
              <b-button class="btn-orange" type="submit" variant="primary">Erstellen</b-button>
            </b-form>
          </div>
          <!-- End Textfield Punkt -->
        </b-tab>
        <b-tab title="Sehenswürdigkeit">
        <!-- Textfield Sehenswürdigkeit -->
          <b-form @submit="onSubmit" @cancel="onCancel" v-if="show">
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
            <b-button class="btn-gray" type="cancel" variant="danger">Abbrechen</b-button>
            <b-button class="btn-orange" type="submit" variant="primary">Erstellen</b-button>
          </b-form>
          <!-- End Textfield Sehenswürdigkeit -->
        </b-tab>
        <b-tab title="Attraktion">
        <!-- Textfield Attraktion -->
          <b-form @submit="onSubmit" @cancel="onCancel" v-if="show">
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
            <b-button class="btn-gray" type="cancel" variant="danger">Abbrechen</b-button>
            <b-button class="btn-orange" type="submit" variant="primary">Erstellen</b-button>
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
      alert(JSON.stringify(this.form))
    },
    onCancel (event) {
      event.preventDefault()
      // Reset our form values
      this.form.name = ''
      this.form.beschreibung = null
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
  margin: 0 auto;
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
}
</style>
